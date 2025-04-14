package com.realestate.rentalmanagement.security.jwt;

import com.realestate.rentalmanagement.entity.User;
import com.realestate.rentalmanagement.repository.UserRepository;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {
    private static final String SECRET = "a_very_long_super_secret_key_that_is_at_least_64_bytes_long_1234567890!!!";
    private final Key jwtSecretKey = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    private final long jwtExpirationMs = 86400000; // 1 день

    private final UserRepository userRepository;

    public JwtTokenProvider(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String generateToken(Authentication authentication) {
        String username = authentication.getName();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationMs);

        return Jwts.builder()
                .setSubject(user.getUsername()) // sub
                .claim("role", user.getRole().getName())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(jwtSecretKey, SignatureAlgorithm.HS512)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
