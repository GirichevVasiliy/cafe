package com.girichev.security.jwt.filter;

import com.girichev.excepton.JwtAuthenticationException;
import com.girichev.security.jwt.util.JwtTokenUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {
    private final JwtTokenUtils jwtTokenUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwtToken = jwtTokenUtils.resolveToken(request);
        try {
            if (jwtToken != null) {
                String phone = jwtTokenUtils.getUsername(jwtToken);
                String uidHeader = request.getHeader("X-User-Uid");
                String uid = jwtTokenUtils.getUid(jwtToken);
                if (!uidHeader.equals(uid)) {
                    throw new JwtAuthenticationException("user is disabled", HttpStatus.FORBIDDEN);//TODO:UID должен быть всегда
                }
                if (!jwtTokenUtils.isUserEnabled(phone, uidHeader)) {
                    throw new JwtAuthenticationException("user is disabled", HttpStatus.FORBIDDEN);
                }
                if (phone != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            phone,
                            null,
                            jwtTokenUtils.getRoles(jwtToken).stream()
                                    .map(SimpleGrantedAuthority::new)
                                    .collect(Collectors.toList())
                    );
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        } catch (JwtAuthenticationException e) {
            SecurityContextHolder.clearContext();
            response.setStatus(e.getHttpStatus().value());
            response.setContentType("application/json");
            response.getWriter().write(e.getMessage());
            log.error(e.getMessage());
            return;
        }
        filterChain.doFilter(request, response);
    }
}
