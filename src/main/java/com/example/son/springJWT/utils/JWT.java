package com.example.son.springJWT.utils;

import com.example.son.springJWT.dto.response.HeaderResponse;
import com.example.son.springJWT.dto.response.PayloadResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static com.example.son.springJWT.utils.Base64Utils.base64UrlEncode;

@Service
public class JWT {

    @Value("${jwt.secret}")
    private String secretKey;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public String createJwtPayload(PayloadResponse payloadResponse) throws Exception {
        HeaderResponse header = new HeaderResponse();
        header.setAlg("HS256");
        header.setTyp("JWT");

        String encodedHeader = base64UrlEncode(objectMapper.writeValueAsString(header));
        String encodedPayload = base64UrlEncode(objectMapper.writeValueAsString(payloadResponse));
        String dataToSign = encodedHeader + "." + encodedPayload;

        String signature = base64UrlEncode(hmacSha256(dataToSign, secretKey));

        return encodedHeader + "." + encodedPayload + "." + signature;
    }

    public PayloadResponse verifyJwt(String jwtToken) throws Exception {
        String[] parts = jwtToken.split("\\.");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Định dạng JWT không hợp lệ.");
        }

        String encodedHeader = parts[0];
        String encodedPayload = parts[1];
        String receivedSignature = parts[2];

        String dataToVerify = encodedHeader + "." + encodedPayload;
        String expectedSignature = base64UrlEncode(hmacSha256(dataToVerify, secretKey));

        if (!receivedSignature.equals(expectedSignature)) {
            throw new SecurityException("Chữ ký JWT không hợp lệ.");
        }

        // Giải mã payload và trả về đối tượng PayloadResponse
        String jsonPayload = new String(Base64.getUrlDecoder().decode(encodedPayload), StandardCharsets.UTF_8);
        return objectMapper.readValue(jsonPayload, PayloadResponse.class);
    }

    private static String hmacSha256(String data, String secret) throws Exception {
        Mac hmacSha256 = Mac.getInstance("HmacSHA256");
        SecretKeySpec keySpec = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        hmacSha256.init(keySpec);
        byte[] macData = hmacSha256.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return Base64.getUrlEncoder().withoutPadding().encodeToString(macData);
    }

}
