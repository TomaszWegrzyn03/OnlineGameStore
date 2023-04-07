package OnlineGameStore.security.jwt;

import OnlineGameStore.security.user.UserInfoDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity

public class SecurityConfig {
    private static final String[] SECURED_URLs = {"api/games/post", "api/games/delete/*", "api/games/update/*"};

    private static final String[] UN_SECURED_URLs = {
            "api/games",
            "api/games/*",
            "api/login",
            "api/h2-console"
    };

    private final JWTAuthenticationFilter authenticationFilter;

    private final UserInfoDetailsService userInfoDetailsService;

    public SecurityConfig(JWTAuthenticationFilter authenticationFilter, UserInfoDetailsService userInfoDetailsService) {
        this.authenticationFilter = authenticationFilter;
        this.userInfoDetailsService = userInfoDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        var authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userInfoDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.cors().and().csrf().disable().
        authorizeHttpRequests()
                .requestMatchers(UN_SECURED_URLs).permitAll()
                .and()
                .authorizeHttpRequests()
                .requestMatchers(SECURED_URLs)
                .hasAuthority("ADMIN")
                .anyRequest()
                .authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .cors().and().csrf().disable()
                .build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

}