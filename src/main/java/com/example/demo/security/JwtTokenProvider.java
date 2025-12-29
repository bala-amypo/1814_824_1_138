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
package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    // injected via reflection in tests
    private String jwtSecret = "default-secret-key-which-will-be-overridden";
    private long jwtExpirationMs = 86400000;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    // =========================
    // Generate JWT
    // =========================
    public String generateToken(Long userId, String email, Set<String> roles) {

        String rolesCsv = roles.stream()
                .collect(Collectors.joining(","));

        return Jwts.builder()
                .setSubject(email)
                .claim("userId", userId)
                .claim("email", email)
                .claim("roles", rolesCsv)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // =========================
    // ✅ REQUIRED BY FILTER
    // =========================
    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject(); // email
    }

    // =========================
    // Token validation
    // =========================
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException ex) {
            return false;
        }
    }

    // =========================
    // Used in tests
    // =========================
    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
