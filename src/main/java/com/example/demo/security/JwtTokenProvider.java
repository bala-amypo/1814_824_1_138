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

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private final SecretKey secretKey;
    private final UserDetailsService userDetailsService;
    private final long validityInMs = 86400000; // 1 day

    public JwtTokenProvider(
            @Value("${jwt.secret}") String secret,
            UserDetailsService userDetailsService
    ) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.userDetailsService = userDetailsService;
    }

    public String generateToken(Authentication authentication) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + validityInMs);

        return Jwts.builder()
                .setSubject(authentication.getName())
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Authentication getAuthentication(String token) {
        String username = getClaims(token).getSubject();
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );
    }

    public boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
