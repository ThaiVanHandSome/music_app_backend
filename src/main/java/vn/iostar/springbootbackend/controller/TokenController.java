package vn.iostar.springbootbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.iostar.springbootbackend.response.Response;
import vn.iostar.springbootbackend.service.GoogleAuthService;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1")
public class TokenController {

    @Autowired
    private GoogleAuthService googleAuthService;

    @GetMapping("/token")
    public ResponseEntity<?> getToken() {
        try {
            String accessToken = googleAuthService.getAccessToken();
            Response res = new Response();
            res.setError(false);
            res.setSuccess(true);
            res.setMessage("Get User Count Successfully!");
            res.setData(accessToken);
            return ResponseEntity.ok(res);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error retrieving access token: " + e.getMessage());
        }
    }
}
