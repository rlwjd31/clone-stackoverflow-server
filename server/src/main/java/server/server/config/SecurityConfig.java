package server.server.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import server.server.config.jwt.JwtAuthenticationFilter;
import server.server.config.jwt.JwtAuthorizationFilter;
import server.server.user.repository.UserRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CorsConfig corsConfig; //@CroosOrigin(인증 X) 시큐리티 필터에 등록(인증 O)

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws  Exception{

        return http
                .headers().frameOptions().disable()
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().disable()
                .httpBasic().disable()
                .apply(new MyCustomDsl()) // 커스텀 필터 등록
                .and()
                .authorizeRequests(authroize -> authroize.antMatchers("/h2/**").permitAll()
                        //.antMatchers("/v1/user/**")
                        //.access("hasRole('ROLE_USER')")
                        .anyRequest().permitAll())
                .build();


    }
    @Bean
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }
    public class MyCustomDsl extends AbstractHttpConfigurer<MyCustomDsl, HttpSecurity> {
        @Override
        public void configure(HttpSecurity http) throws Exception {
            AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
            http
                    .addFilter(corsConfig.corsFilter())
                    .addFilter(new JwtAuthenticationFilter(authenticationManager))
                    .addFilter(new JwtAuthorizationFilter(authenticationManager, userRepository));
        }
    }
}
