package swjungle.springboard.filter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import swjungle.springboard.model.Role;
import swjungle.springboard.util.JwtTokenUtil;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;

    public JwtAuthenticationFilter(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");

        String userName = null;
        String token = null;

        // JWT 토큰이 포함된 Authorization 헤더 확인
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7); // "Bearer " 이후의 실제 토큰 값 추출
            try {
                userName = jwtTokenUtil.getUserNameFromToken(token); // JWT 토큰에서 사용자 이름 추출
            } catch (Exception e) {
                // JWT 토큰을 파싱하지 못한 경우
                logger.error("JWT Token validation error", e);
            }
        }

        // 사용자 이름이 존재하고 현재 SecurityContext에 인증 정보가 없는 경우
        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            Role role = Role.valueOf(jwtTokenUtil.getRoleFromToken(token));

            // 토큰이 유효한 경우
            if (jwtTokenUtil.validateToken(token)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userName, null, Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role.name())));
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // SecurityContextHolder에 인증 정보 설정
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        // 다음 필터로 요청 전달
        filterChain.doFilter(request, response);
    }
}
