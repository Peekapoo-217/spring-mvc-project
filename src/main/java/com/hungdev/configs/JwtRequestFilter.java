/*package com.hungdev.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;*/

/*
 * import java.io.IOException; import java.util.List; import java.util.Optional;
 * import java.util.stream.Collectors;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.security.authentication.
 * UsernamePasswordAuthenticationToken; import
 * org.springframework.security.core.authority.SimpleGrantedAuthority; import
 * org.springframework.security.core.context.SecurityContextHolder; import
 * org.springframework.security.core.userdetails.User; import
 * org.springframework.security.core.userdetails.UserDetails; import
 * org.springframework.security.web.authentication.
 * WebAuthenticationDetailsSource; import
 * org.springframework.stereotype.Component; import
 * org.springframework.util.StringUtils; import
 * org.springframework.web.filter.OncePerRequestFilter;
 * 
 * import com.hungdev.services.JwtService;
 * 
 * import jakarta.servlet.FilterChain; import jakarta.servlet.ServletException;
 * import jakarta.servlet.http.HttpServletRequest; import
 * jakarta.servlet.http.HttpServletResponse;
 * 
 * @Component public class JwtRequestFilter extends OncePerRequestFilter {
 * private JwtService jwtService; private static final String AUTHORIZATION =
 * "Authorization "; private static final String BEARER_PREFIX = "Bearer ";
 * 
 * @Autowired public JwtRequestFilter(JwtService jwtService) { this.jwtService =
 * jwtService; }
 * 
 * @Override protected void doFilterInternal(HttpServletRequest request,
 * HttpServletResponse response, FilterChain filterChain) throws
 * ServletException, IOException { Optional<String> jwtTokenOpt =
 * parseJwtToken(request); if (!jwtTokenOpt.isPresent()) {
 * filterChain.doFilter(request, response); return; }
 * 
 * String jwtToken = jwtTokenOpt.get(); if (!jwtService.validateToken(jwtToken))
 * { filterChain.doFilter(request, response); return; }
 * 
 * String username = jwtService.extractUsernameFromToken(jwtToken); List<String>
 * rawGrantedAuthorities = jwtService.extractAuthoritiesFromToken(jwtToken);
 * List<SimpleGrantedAuthority> grantedAuthorities =
 * rawGrantedAuthorities.stream()
 * .map(SimpleGrantedAuthority::new).collect(Collectors.toList());
 * 
 * UserDetails userDetails = new User(username, username, grantedAuthorities);
 * UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new
 * UsernamePasswordAuthenticationToken(userDetails, null, grantedAuthorities);
 * usernamePasswordAuthenticationToken.setDetails(new
 * WebAuthenticationDetailsSource().buildDetails(request));
 * SecurityContextHolder.getContext().setAuthentication(
 * usernamePasswordAuthenticationToken);
 * 
 * filterChain.doFilter(request, response); }
 * 
 * private Optional<String> parseJwtToken(HttpServletRequest request) { String
 * headerAuth = request.getHeader(AUTHORIZATION); if
 * (StringUtils.hasText(headerAuth) && headerAuth.startsWith(BEARER_PREFIX)) {
 * return Optional.of(headerAuth.substring(7)); } return Optional.empty(); } }
 */


package com.hungdev.configs;

import java.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.hungdev.services.JwtService;
import org.springframework.security.core.userdetails.UserDetailsService;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String token = null;

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("JWT_TOKEN".equals(cookie.getName())) {
                    token = cookie.getValue();
                    break;
                }
            }
        }

        if (token != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            String username = jwtService.extractUsernameFromToken(token);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            
            if (jwtService.validateToken(token)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        chain.doFilter(request, response);
    }
}

