package me.kzv.fullstackdev.controller;

import lombok.RequiredArgsConstructor;
import me.kzv.fullstackdev.controller.request.LoginRequest;
import me.kzv.fullstackdev.security.JwtService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> getToken(@RequestBody LoginRequest request) {
        var credits = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getUsername());

        Authentication authentication = authenticationManager.authenticate(credits);
        String token = jwtService.getToken(authentication.getName());

        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, JwtService.PREFIX + token)
                .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization")
                .build();
    }

}
