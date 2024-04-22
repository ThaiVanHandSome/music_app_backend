package vn.iostar.springbootbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.iostar.springbootbackend.entity.User;
import vn.iostar.springbootbackend.response.Response;
import vn.iostar.springbootbackend.service.impl.UserService;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PatchMapping("/{idUser}")
    public ResponseEntity<?> updateUserByFields(@PathVariable("idUser") Long idUser, @RequestBody Map<String, Object> fields) {
        User user = userService.updateUserByFields(idUser, fields);
        Response response = new Response(true, false, "Update Success!", user);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{idUser}/change-password")
    public ResponseEntity<?> changePassword(@PathVariable("idUser") Long idUser, @RequestBody Map<String, String> reqBody) {
        String password = reqBody.get("password");
        return ResponseEntity.ok(userService.changePassword(idUser, password));
    }

    @PatchMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody Map<String, String> reqBody) {
        String password = reqBody.get("password");
        String email = reqBody.get("email");
        return ResponseEntity.ok(userService.changePasswordForgot(email, password));
    }
}
