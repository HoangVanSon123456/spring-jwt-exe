package com.example.son.springJWT.controller;

import com.example.son.springJWT.dto.response.PayloadResponse;
import com.example.son.springJWT.utils.JWT;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/jwt")
@RequiredArgsConstructor
public class JwtController {

    private final JWT jwt;

    @GetMapping("/generate")
    public ResponseEntity<String> generateJwt() throws Exception {
        PayloadResponse payload = new PayloadResponse();
        payload.setUserId(1);
        payload.setEmail("you@example.com");
        payload.setRole("ADMIN");
        payload.setIat((int) (System.currentTimeMillis() / 1000));
        payload.setExp((int) (System.currentTimeMillis() / 1000) + 3600);

        String token = jwt.createJwtPayload(payload);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/verify")
    public ResponseEntity<PayloadResponse> verifyJwt(@RequestBody String verifyStringJwt) throws Exception {
        PayloadResponse result = jwt.verifyJwt(verifyStringJwt);
        return ResponseEntity.ok(result);
    }
}
