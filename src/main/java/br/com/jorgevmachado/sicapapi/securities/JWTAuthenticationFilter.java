package br.com.jorgevmachado.sicapapi.securities;

import br.com.jorgevmachado.sicapapi.dto.CredenciaisDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    private JWTUtil jwtUtil;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        setAuthenticationFailureHandler(new JWTAuthenticationFailureHandler());
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    public Authentication attemptAuthentication(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ) throws AuthenticationException {
        try {
            CredenciaisDTO credenciaisDTO = new ObjectMapper()
                    .readValue(httpServletRequest.getInputStream(), CredenciaisDTO.class);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    credenciaisDTO.getEmail(),
                    credenciaisDTO.getSenha(),
                    new ArrayList<>()
            );
            return authenticationManager.authenticate(authenticationToken);
        } catch (IOException e) {
            throw  new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            FilterChain filterChain,
            Authentication authentication
    ) {
        String username =((UserSpringSecurity) authentication.getPrincipal()).getUsername();
        String token = jwtUtil.generateToken(username);
        httpServletResponse.addHeader("Authorization", "Bearer " + token);
        httpServletResponse.addHeader("access-control-expose-headers", "Authorization");
        httpServletRequest.getHeader("Bearer " + token);
    }
}
