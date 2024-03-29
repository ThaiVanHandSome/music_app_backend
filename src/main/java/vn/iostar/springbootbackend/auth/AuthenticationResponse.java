package vn.iostar.springbootbackend.auth;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthenticationResponse {
    private Long id;
    private String token;

    private String firstName;
    private String lastName;
    private String email;
    private int gender;
    private String avatar;
}

