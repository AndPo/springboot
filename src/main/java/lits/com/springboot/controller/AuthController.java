package lits.com.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lits.com.springboot.service.AuthService;
import lits.com.springboot.dto.AuthRequest;


@RestController
@RequestMapping(value = "/api")
public class AuthController {


    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> auth(@Validated @RequestBody AuthRequest auth){
        return ResponseEntity.ok(authService.auth(auth.getEmail(), auth.getPassword()));
    }

}
