package com.girichev.security.jwt.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.sts.clientservice.clients.client.storage.ClientsRepository;
import ru.sts.clientservice.excepton.JwtAuthenticationException;
import ru.sts.clientservice.security.jwt.model.response.JwtResponse;

import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtTokenUtils {
    private final ClientsRepository clientsRepository;
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.lifetime}")
    private Duration lifetime;
    @Value("${jwt.header}")
    private String authorizationHeader;

    public JwtResponse generateToken(UserDetails user, String uid) {
        Map<String, Object> claims = new HashMap<>();
        List<String> roles = user.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        claims.put("roles", roles);
        claims.put("uid", uid);
        Date issued = new Date();
        Date expired = new Date(issued.getTime() + lifetime.toMillis());
        String token = Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getUsername())
                .setIssuedAt(issued)
                .setExpiration(expired)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
        return JwtResponse.builder()
                .uid(uid)
                .token(token)
                .expired(expired)
                .build();
    }

    public String getUsername(String token) {
        try {
            return parseToken(token).getSubject();
        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtAuthenticationException(e.getMessage(), HttpStatus.FORBIDDEN);
        }
    }

    public List<String> getRoles(String token) {
        return parseToken(token).get("roles", List.class);
    }

    public String getUid(String token) {
        return parseToken(token).get("uid", String.class);
    }

    //По умолчанию происходит разные проверки токена, в том числе и на дату действия.
    private Claims parseToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    @Transactional(readOnly = true)
    public boolean isUserEnabled(String phone, String uid) {
        return clientsRepository.isUserEnabled(phone, uid).orElse(false);
    }

    public String resolveToken(HttpServletRequest request) {
        return request.getHeader(authorizationHeader);
    }
}
