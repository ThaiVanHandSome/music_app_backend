package vn.iostar.springbootbackend.auth.authentication;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.iostar.springbootbackend.entity.Provider;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthenticationResponse {
    private String accessToken;
    private String refreshToken;

    private boolean error;
    private boolean success;
    private String type;
    private Long id;
    private String message;
    private String nickname;
    private String firstName;
    private String lastName;
    private String email;
    private String avatar;
    private int gender;
    private Provider provider;
}

