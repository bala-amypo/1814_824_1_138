// package com.example.demo.security;

// import com.example.demo.model.Guest;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.stereotype.Component;

// import java.util.Base64;

// @Component
// public class JwtTokenProvider {

//     /*
//      * Fake token format (Base64):
//      * userId|email|role
//      */

//     public String generateToken(Authentication authentication) {
//         Object principal = authentication.getPrincipal();

//         if (principal instanceof UserDetails userDetails) {
//             String email = userDetails.getUsername();
//             String role = userDetails.getAuthorities().iterator().next().getAuthority();

//             // userId is NOT inside UserDetails → encode dummy but non-null
//             Long userId = Math.abs(email.hashCode()) + 0L;

//             String tokenData = userId + "|" + email + "|" + role;
//             return Base64.getEncoder().encodeToString(tokenData.getBytes());
//         }
//         return null;
//     }

//     public boolean validateToken(String token) {
//         try {
//             String decoded = new String(Base64.getDecoder().decode(token));
//             return decoded.split("\\|").length == 3;
//         } catch (Exception e) {
//             return false;
//         }
//     }

//     public Long getUserIdFromToken(String token) {
//         try {
//             String decoded = new String(Base64.getDecoder().decode(token));
//             return Long.parseLong(decoded.split("\\|")[0]);
//         } catch (Exception e) {
//             return null;
//         }
//     }

//     public String getEmailFromToken(String token) {
//         try {
//             String decoded = new String(Base64.getDecoder().decode(token));
//             return decoded.split("\\|")[1];
//         } catch (Exception e) {
//             return null;
//         }
//     }

//     public String getRoleFromToken(String token) {
//         try {
//             String decoded = new String(Base64.getDecoder().decode(token));
//             return decoded.split("\\|")[2];
//         } catch (Exception e) {
//             return null;
//         }
//     }
// }


// package com.example.demo.security;

// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.stereotype.Component;

// import java.util.Base64;

// @Component
// public class JwtTokenProvider {

//     public String generateToken(Authentication authentication) {

//         UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//         String email = userDetails.getUsername();
//         String role = userDetails.getAuthorities().iterator().next().getAuthority();

//         String tokenData = email + "|" + role;
//         return Base64.getEncoder().encodeToString(tokenData.getBytes());
//     }

//     public boolean validateToken(String token) {
//         try {
//             Base64.getDecoder().decode(token);
//             return true;
//         } catch (Exception e) {
//             return false;
//         }
//     }
// }
package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    private final Key key = Keys.hmacShaKeyFor(
            "THIS_IS_A_VERY_SECURE_SECRET_KEY_1234567890".getBytes()
    );

    private final long EXPIRATION = 1000 * 60 * 60; // 1 hour

    public String generateToken(Authentication authentication) {

        UserDetails user = (UserDetails) authentication.getPrincipal();

        String roles = user.getAuthorities()
                .stream()
                .map(a -> a.getAuthority())
                .collect(Collectors.joining(","));

        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        return getClaims(token).getSubject();
    }

    public boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
