package vn.iostar.springbootbackend.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vn.iostar.springbootbackend.entity.ConfirmationToken;
import vn.iostar.springbootbackend.entity.User;
import vn.iostar.springbootbackend.repository.ConfirmationTokenRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {
    private final ConfirmationTokenRepository confirmationTokenRepository;
    public void saveConfirmationToken(ConfirmationToken token) {
        confirmationTokenRepository.save(token);
    }

    public Optional<ConfirmationToken> getToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }

    public int setConfirmedAt(String token) {
        return confirmationTokenRepository.updateConfirmedAt(
                token, LocalDateTime.now());
    }

    public Optional<ConfirmationToken> getTokenByUser(User user) {
        return confirmationTokenRepository.findByUser(user);
    }

    public void delete(ConfirmationToken confirmationToken) {
        confirmationTokenRepository.delete(confirmationToken);
    }
}
