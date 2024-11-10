package com.devvictor.ecommerce_api.shared.infra.security;

import com.devvictor.ecommerce_api.shared.application.exceptions.UnauthorizedException;
import com.devvictor.ecommerce_api.user.application.jwt.JwtPayload;
import com.devvictor.ecommerce_api.user.application.providers.JwtProvider;
import com.devvictor.ecommerce_api.user.domain.entities.User;
import com.devvictor.ecommerce_api.user.domain.repositories.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        Optional<String> token = extractJwtToken(request);

        if (token.isPresent()) {
            JwtPayload jwtPayload = jwtProvider.validateToken(token.get());

            Optional<User> user = userRepository.findById(jwtPayload.getUserId());

            if (user.isEmpty()) {
                throw new UnauthorizedException();
            }

            Collection<GrantedAuthority> authorities = user.get().getRoles()
                    .stream()
                    .map((role) -> new SimpleGrantedAuthority(role.name()))
                    .collect(Collectors.toSet());

            var authentication = new UsernamePasswordAuthenticationToken(
                    user,
                    null,
                    authorities
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private Optional<String> extractJwtToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
            return Optional.of(bearerToken.substring(7));
        }

        return Optional.empty();
    }
}
