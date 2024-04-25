package vn.iostar.springbootbackend.auth.authentication;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthenticationResponse {
    private String accessToken;
    private String refreshToken;

    private boolean error;
    private boolean success;
    private Long id;
    private String message;
    private String firstName;
    private String lastName;
    private String email;
    private String avatar;
    private int gender;
}
