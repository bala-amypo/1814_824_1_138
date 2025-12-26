// // package com.example.demo.security;

// // import io.jsonwebtoken.*;
// // import io.jsonwebtoken.security.Keys;
// // import org.springframework.beans.factory.annotation.Value;
// // import org.springframework.stereotype.Component;

// // import java.security.Key;
// // import java.util.Date;

// // @Component
// // public class JwtTokenProvider {

// //     // Secret key (should be in application.properties)
// //     @Value("${jwt.secret}")
// //     private String jwtSecret;

// //     // Token validity in milliseconds
// //     @Value("${jwt.expiration}")
// //     private long jwtExpirationMs;

// //     private Key getSigningKey() {
// //         return Keys.hmacShaKeyFor(jwtSecret.getBytes());
// //     }

// //     // Generate JWT token
// //     public String generateToken(String username) {
// //         Date now = new Date();
// //         Date expiryDate = new Date(now.getTime() + jwtExpirationMs);

// //         return Jwts.builder()
// //                 .setSubject(username)
// //                 .setIssuedAt(now)
// //                 .setExpiration(expiryDate)
// //                 .signWith(getSigningKey(), SignatureAlgorithm.HS256)
// //                 .compact();
// //     }

// //     // Get username from JWT token
// //     public String getUsernameFromToken(String token) {
// //         return Jwts.parserBuilder()
// //                 .setSigningKey(getSigningKey())
// //                 .build()
// //                 .parseClaimsJws(token)
// //                 .getBody()
// //                 .getSubject();
// //     }

// //     // Validate JWT token
// //     public boolean validateToken(String token) {
// //         try {
// //             Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token);
// //             return true;
// //         } catch (JwtException | IllegalArgumentException e) {
// //             return false;
// //         }
// //     }
// // }

// package com.example.demo.security;

// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.SignatureAlgorithm;
// import io.jsonwebtoken.security.Keys;
// import org.springframework.stereotype.Component;

// import java.security.Key;
// import java.util.Date;

// @Component
// public class JwtTokenProvider {

//     // Secret key for signing the JWT (keep it safe in production)
//     private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

//     // Token validity period (e.g., 1 hour)
//     private final long validityInMilliseconds = 3600000; // 1 hour

//     // Generate JWT token
//     public String generateToken(String username) {
//         Claims claims = Jwts.claims().setSubject(username);
//         Date now = new Date();
//         Date expiry = new Date(now.getTime() + validityInMilliseconds);

//         return Jwts.builder()
//                 .setClaims(claims)
//                 .setIssuedAt(now)
//                 .setExpiration(expiry)
//                 .signWith(key)
//                 .compact();
//     }

//     // Validate JWT token
//     public boolean validateToken(String token) {
//         try {
//             Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
//             return true;
//         } catch (Exception e) {
//             return false;
//         }
//     }

//     // Extract username from JWT token
//     public String getUsernameFromToken(String token) {
//         Claims claims = Jwts.parserBuilder()
//                 .setSigningKey(key)
//                 .build()
//                 .parseClaimsJws(token)
//                 .getBody();
//         return claims.getSubject();
//     }
// }
// package com.example.demo.security;

// import io.jsonwebtoken.*;
// import io.jsonwebtoken.security.Keys;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.stereotype.Component;

// import java.security.Key;
// import java.util.Date;

// @Component
// public class JwtTokenProvider {

//     private static final String SECRET =
//             "THIS_IS_A_VERY_LONG_SECRET_KEY_FOR_JWT_TOKEN_SIGNING_256_BITS";

//     private static final long EXPIRATION = 1000 * 60 * 60; // 1 hour

//     private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

//     public String generateToken(Authentication authentication) {
//         String email = authentication.getName();
//         String role = authentication.getAuthorities()
//                 .stream()
//                 .map(GrantedAuthority::getAuthority)
//                 .findFirst()
//                 .orElse("ROLE_USER");

//         return Jwts.builder()
//                 .setSubject(email)
//                 .claim("role", role)
//                 .claim("userId", 1L)
//                 .setIssuedAt(new Date())
//                 .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
//                 .signWith(key, SignatureAlgorithm.HS256)
//                 .compact();
//     }

//     public boolean validateToken(String token) {
//         try {
//             Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
//             return true;
//         } catch (JwtException | IllegalArgumentException ex) {
//             return false;
//         }
//     }

//     private Claims getClaims(String token) {
//         return Jwts.parserBuilder()
//                 .setSigningKey(key)
//                 .build()
//                 .parseClaimsJws(token)
//                 .getBody();
//     }

//     public String getEmailFromToken(String token) {
//         return getClaims(token).getSubject();
//     }

//     public String getRoleFromToken(String token) {
//         return getClaims(token).get("role", String.class);
//     }

//     public Long getUserIdFromToken(String token) {
//         return getClaims(token).get("userId", Long.class);
//     }
// }

package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private static final String SECRET =
            "THIS_IS_A_VERY_LONG_SECRET_KEY_FOR_JWT_TOKEN_SIGNING_256_BITS";

    private static final long EXPIRATION = 1000 * 60 * 60; // 1 hour

    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    public String generateToken(Authentication authentication) {
        String email = authentication.getName();
        String role = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse("ROLE_USER");

        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .claim("userId", 1L)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException ex) {
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

    public String getEmailFromToken(String token) {
        return getClaims(token).getSubject();
    }

    public String getRoleFromToken(String token) {
        return getClaims(token).get("role", String.class);
    }

    public Long getUserIdFromToken(String token) {
        return getClaims(token).get("userId", Long.class);
    }
}
