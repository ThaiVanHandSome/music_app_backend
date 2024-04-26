package vn.iostar.springbootbackend.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.iostar.springbootbackend.auth.authentication.AuthenticationRequest;
import vn.iostar.springbootbackend.auth.authentication.AuthenticationResponse;
import vn.iostar.springbootbackend.auth.refreshToken.RefreshTokenRequest;
import vn.iostar.springbootbackend.auth.registration.RegisterRequest;
import vn.iostar.springbootbackend.auth.registration.RegisterResponse;
import vn.iostar.springbootbackend.entity.RefreshToken;
import vn.iostar.springbootbackend.service.RefreshTokenService;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    private final RefreshTokenService refreshTokenService;

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }

    @GetMapping("/register/confirm")
    public ResponseEntity<?> confirm(@RequestParam("token") String token, @RequestParam("type") String type) {
        return ResponseEntity.ok(service.confirmToken(token, type));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok(service.refreshToken(refreshTokenRequest));
    }

    @PostMapping("/send-email")
    public ResponseEntity<?> sendEmail(@RequestBody Map<String, String> reqBody) {
        String email = reqBody.get("email");
        return ResponseEntity.ok(service.sendEmail(email));
    }
}
