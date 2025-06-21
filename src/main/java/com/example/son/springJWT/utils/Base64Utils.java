package com.example.son.springJWT.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64Utils {

    public static String base64UrlEncode(String input) {
        return Base64.getUrlEncoder().withoutPadding().encodeToString(input.getBytes(StandardCharsets.UTF_8));
    }

    public static String base64urlDecode(String input) {
        int padding = (4 - input.length() % 4) % 4;
        String paddingInput = input + "=".repeat(padding);
        byte[] decodeBytes = Base64.getUrlDecoder().decode(paddingInput);
        return new String(decodeBytes, StandardCharsets.UTF_8);
    }

    public static void main(String[] args) {
        System.out.println(base64UrlEncode("hoangvansons"));
        System.out.println(base64urlDecode(base64UrlEncode("hoangvansons")));
    }
}
