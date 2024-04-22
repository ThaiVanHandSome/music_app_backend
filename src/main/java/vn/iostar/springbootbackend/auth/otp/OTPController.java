package vn.iostar.springbootbackend.auth.otp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/otp")
public class OTPController {
    @Autowired
    private SMSService smsService;

    @GetMapping("/process")
    public String processSMS() {
        return "SMS sent";
    }

    @PostMapping("/send-otp")
    public OTPResponse sendOtp(@RequestBody OTPRequest otpRequest) {
        return smsService.sendSMS(otpRequest);
    }
    @PostMapping("/validate-otp")
    public String validateOtp(@RequestBody OTPValidationRequest otpValidationRequest) {
        return smsService.validateOtp(otpValidationRequest);
    }
}
