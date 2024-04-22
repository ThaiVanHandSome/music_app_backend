package vn.iostar.springbootbackend.auth.otp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OTPResponse {
    private OTPStatus status;
    private String message;
}
