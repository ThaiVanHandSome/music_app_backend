package vn.iostar.springbootbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.iostar.springbootbackend.service.impl.UserService;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PatchMapping("/updateUserByFields/{id_user}")
    public ResponseEntity<?> updateUserByFields(@PathVariable Long id_user, @RequestBody Map<String, Object> fields) {
        return ResponseEntity.ok(userService.updateUserByFields(id_user, fields));
    }
}
