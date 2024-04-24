package vn.iostar.springbootbackend.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.iostar.springbootbackend.auth.authentication.AuthenticationRequest;
import vn.iostar.springbootbackend.auth.authentication.AuthenticationResponse;
import vn.iostar.springbootbackend.auth.email.EmailService;
import vn.iostar.springbootbackend.auth.refreshToken.RefreshTokenRequest;
import vn.iostar.springbootbackend.auth.refreshToken.RefreshTokenResponse;
import vn.iostar.springbootbackend.auth.registration.EmailValidator;
import vn.iostar.springbootbackend.auth.registration.RegisterRequest;
import vn.iostar.springbootbackend.auth.registration.RegisterResponse;
import vn.iostar.springbootbackend.auth.registration.token.ConfirmationToken;
import vn.iostar.springbootbackend.auth.registration.token.ConfirmationTokenService;
import vn.iostar.springbootbackend.entity.RefreshToken;
import vn.iostar.springbootbackend.entity.Role;
import vn.iostar.springbootbackend.entity.User;
import vn.iostar.springbootbackend.repository.UserRepository;
import vn.iostar.springbootbackend.security.jwt.JWTService;
import vn.iostar.springbootbackend.service.impl.RefreshTokenService;
import vn.iostar.springbootbackend.service.impl.UserService;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    @Autowired
    private ConfirmationTokenService confirmationTokenService;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private RefreshTokenService refreshTokenService;

    private final EmailValidator emailValidator;

    private final AuthenticationManager authenticationManager;
    public RegisterResponse register(RegisterRequest request) {
        Boolean isValidEmail = emailValidator.test(request.getEmail());
        if(!isValidEmail) {
            throw new IllegalStateException("Email Not Valid!");
        }
        Optional<User> optUser = repository.findByEmail(request.getEmail());
        if(optUser.isEmpty()) {
            var user = User.builder()
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .phoneNumber(request.getPhoneNumber())
                    .role(Role.USER)
                    .isActive(false)
                    .build();
            repository.save(user);

            String token = generateOTP();
            ConfirmationToken confirmationToken = new ConfirmationToken(
                    token,
                    LocalDateTime.now(),
                    LocalDateTime.now().plusMinutes(15),
                    user

            );
            confirmationTokenService.saveConfirmationToken(confirmationToken);
            emailService.send(request.getEmail(), buildEmailOTP(request.getFirstName() + " " + request.getLastName(), token));
            return RegisterResponse.builder()
                    .message("Please Check Email To See OTP!")
                    .error(false)
                    .success(true)
                    .build();
        }
        else {
            return RegisterResponse.builder()
                    .message("Email Already Taken!")
                    .error(true)
                    .success(false)
                    .build();
        }
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        Optional<User> opt = repository.findByEmail(request.getEmail());
        if(opt.isEmpty()) {
            return AuthenticationResponse.builder().error(true).message("Email or Password wrong!").success(false).build();
        }
        User user = opt.get();
        if(!user.isActive()) {
            return AuthenticationResponse.builder()
                    .error(true)
                    .success(false)
                    .message("Account Not Confirm!")
                    .build();
        }
        var jwtToken = jwtService.generateAccessToken(user);
        var jwtRefreshToken = refreshTokenService.createRefreshToken(user.getEmail());
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(jwtRefreshToken.getToken())
                .id(user.getIdUser())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .avatar(user.getAvatar())
                .gender(user.getGender())
                .error(false)
                .success(true)
                .message("Successfully!")
                .build();
    }

    public RegisterResponse confirmToken(String token) {
        Optional<ConfirmationToken> otpConfirmationToken = confirmationTokenService.getToken(token);
        if(otpConfirmationToken.isPresent()) {
            ConfirmationToken confirmationToken = otpConfirmationToken.get();
            if(confirmationToken.getConfirmedAt() != null) {
                return RegisterResponse.builder()
                        .message("Email Already Confirmed")
                        .error(true)
                        .success(false)
                        .build();
            }
            LocalDateTime expiredAt = confirmationToken.getExpiredAt();
            if(expiredAt.isBefore(LocalDateTime.now())) {
                return RegisterResponse.builder()
                        .message("Token Expired")
                        .error(true)
                        .success(false)
                        .build();
            }
            confirmationTokenService.setConfirmedAt(token);
            userService.enableUser(confirmationToken.getUser().getEmail());
            return RegisterResponse.builder().message("Confirmed!").error(false).success(true).build();
        }
        return RegisterResponse.builder().message("Token Not Valid!").error(true).success(false).build();
    }

    private String buildEmailOTP(String name, String otp) {
        return "<html>\n" +
                "  <body>\n" +
                "    <h1>Xin Chào "+ name + "!</h1>\n" +
                "    <h2>Mã OTP để xác nhận account của bạn là: <span style=\"color: red;\">"+otp+"</span></h2>\n" +
                "  </body>\n" +
                "</html>";
    }

    public Object refreshToken(RefreshTokenRequest refreshTokenRequest) {
        return refreshTokenService.findByToken(refreshTokenRequest.getToken())
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String accessToken = jwtService.generateAccessToken(user);
                    return RefreshTokenResponse.builder()
                            .accessToken(accessToken)
                            .refreshToken(refreshTokenRequest.getToken())
                            .error(false)
                            .success(true)
                            .message("Refresh Token Successfully!")
                            .build();
                })
                .orElseThrow(() -> new RuntimeException(
                        "Refresh Token not in database!"));
    }

    private String generateOTP() {
        return new DecimalFormat("000000")
                .format(new Random().nextInt(999999));
    }

    public Object sendEmail(String email) {
        Optional<User> optUser = userService.getUserByEmail(email);
        if(optUser.isPresent()) {
            User user = optUser.get();
            String token = generateOTP();
            ConfirmationToken confirmationToken = new ConfirmationToken(
                    token,
                    LocalDateTime.now(),
                    LocalDateTime.now().plusMinutes(15),
                    user
            );
            Optional<ConfirmationToken> optConfirmationToken = confirmationTokenService.getTokenByUser(user);
            optConfirmationToken.ifPresent(value -> confirmationTokenService.delete(value));
            confirmationTokenService.saveConfirmationToken(confirmationToken);
            emailService.send(email, buildEmailOTP(user.getFirstName() + " " + user.getLastName(), token));
            return RegisterResponse.builder()
                    .message("Please Check Email To See OTP!")
                    .error(false)
                    .success(true)
                    .build();
        }
        return RegisterResponse.builder()
                .message("Email Not Register!")
                .error(true)
                .success(false)
                .build();
    }
}
