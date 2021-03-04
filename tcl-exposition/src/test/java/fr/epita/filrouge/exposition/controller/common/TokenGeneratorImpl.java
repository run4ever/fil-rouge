package fr.epita.filrouge.exposition.controller.common;

import fr.epita.filrouge.exposition.security.jwt.JwtTokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class TokenGeneratorImpl implements TokenGenerator {
    private String token;
    private HttpHeaders headers;

    @Autowired
    private JwtTokenManager jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    public TokenGeneratorImpl() {
    }

    @Override
    public HttpHeaders getHeadersWithJwtToken(String email) {
            if(token==null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(email);
                token = "Bearer "+jwtTokenUtil.generateToken(userDetails);
                headers = new HttpHeaders();
                headers.set("Authorization",token);
                return headers;
            }
            return headers;
    }

}
