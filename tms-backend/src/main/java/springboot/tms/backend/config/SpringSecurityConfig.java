package springboot.tms.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import springboot.tms.backend.security.JWTAuthenticationEntryPoint;
import springboot.tms.backend.security.JWTAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SpringSecurityConfig {

    private UserDetailsService userDetailsService;

    private JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    private JWTAuthenticationFilter jwtAuthenticationFilter;

    public SpringSecurityConfig(UserDetailsService userDetailsService,
                                JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint,
                                JWTAuthenticationFilter jwtAuthenticationFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests((authorize) -> {
//              Only allows the ADMIN  to access any http POST requests with url "api/**"
//                    authorize.requestMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN");

//              Only allows the ADMIN  to access any http PUT requests with url "api/**"
//                    authorize.requestMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN");

//              Only allows the ADMIN  to access any http DELETE requests with url "api/**"
//                    authorize.requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN");

//              allows access to both the ADMIN and USER to access any http GET requests with url "api/**"
//                    authorize.requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole("ADMIN", "USER");

//              allows access to both the ADMIN and USER to access any http PATCH requests with url "api/**"
//                    authorize.requestMatchers(HttpMethod.PATCH, "/api/**").hasAnyRole("ADMIN", "USER");

//               Allows public access to everyone without the need to provide any credentials(No Auth)
//                    authorize.requestMatchers(HttpMethod.GET, "/api/**").permitAll();

                    authorize.requestMatchers("/api/auth/**").permitAll();

//              Allows public access to preflight requests from client
                    authorize.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll();
                    authorize.anyRequest().authenticated();
                }).httpBasic(Customizer.withDefaults());

//      Checking unauthorized access
        http.exceptionHandling( exception -> exception
                .authenticationEntryPoint(jwtAuthenticationEntryPoint));

//         making sure that our authentication filter class executes before rest of the filter classes
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

//    @Bean
//    public UserDetailsService userDetailsService(){
//
//        UserDetails ramesh = User.builder()
//                .username("ramesh")
//                .password(passwordEncoder().encode("password"))
//                .roles("USER")
//                .build();
//
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(passwordEncoder().encode("admin"))
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(ramesh, admin);
//    }
}
