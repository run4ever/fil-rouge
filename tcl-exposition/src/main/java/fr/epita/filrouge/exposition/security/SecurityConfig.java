package fr.epita.filrouge.exposition.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import fr.epita.filrouge.exposition.security.jwt.JwtAuthenticationEntryPoint;
import fr.epita.filrouge.exposition.security.jwt.JwtRequestFilter;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableGlobalMethodSecurity(securedEnabled = true, proxyTargetClass = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //Une liste URL qu'on autorise à appeler sans authentifier
    private static final String UNAUTHENTICATED_WHITE_LIST[] = { "/authenticate","/" };

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Override
    public void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(final HttpSecurity httpSecurity) throws Exception {
        // Add a filter to validate the tokens with every request
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        httpSecurity
                //Voilà ce qu'il fallait rajouter
                .cors ().and ()
                .csrf().disable()
                // dont authenticate this authentication request
                .authorizeRequests().antMatchers(UNAUTHENTICATED_WHITE_LIST).permitAll()
                // autoriser Get sur AppUser
                .antMatchers(HttpMethod.GET, "/api/*/appuser/**").permitAll()
                //autorisé get sur movie
                .antMatchers(HttpMethod.GET, "/api/*/movie/**").permitAll()
                //autorisé get sur Serie
                .antMatchers(HttpMethod.GET, "/api/*/serie/**").permitAll()
                // and authorize swagger-ui
                .antMatchers("/v2/api-docs", "/configuration/**", "/swagger*/**", "/webjars/**").permitAll()
                // all other requests need to be authenticated
                .anyRequest().authenticated().and().
                // make sure we use stateless session; session won't be used to
                // store user's state.
                        exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint) //
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
       // httpSecurity.cors();//cette ligne permet de résoudre le pb CORS policy
    }
}
