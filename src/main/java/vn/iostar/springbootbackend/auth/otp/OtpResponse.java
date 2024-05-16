package vn.iostar.springbootbackend.auth.email.otp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OtpResponse {
    private boolean success;
    private boolean error;
    private String message;
    private String type;
    private Long id;
}
