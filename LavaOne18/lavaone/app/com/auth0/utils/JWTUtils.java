package com.auth0.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import javax.xml.bind.DatatypeConverter;

import play.libs.Json;

public class JWTUtils {

    public static String generateJWT(final Object object){
        String result = "";

        try {
            result = Jwts.builder()
                    .setClaims(new HashMap<String, Object>(){{
                        put("json", Json.toJson(object));
                    }})
                    .signWith(SignatureAlgorithm.HS256, "secre")
                    .compact();
        } catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }

        return result;
    }

    public static boolean validateJWT(String jwt){
        boolean result = false;

        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary("secre"))
                    .parseClaimsJws(jwt).getBody();
            result = true;
        } catch (SignatureException e){
            e.printStackTrace();
        } catch (MalformedJwtException e){
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }

        return result;
    }

    private static String decodeBase64(String base64) throws UnsupportedEncodingException, ArrayIndexOutOfBoundsException {
        return new String(DatatypeConverter.parseBase64Binary(base64), "UTF-8");
    }

}