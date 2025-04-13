package vn.aptech.java.configs;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.NullSecurityContextRepository;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@Order(1) // Đảm bảo cấu hình API được ưu tiên trước MVC
public class ApiSecurityConfig {
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain apiSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/api/**") // Chỉ áp dụng cho API
                .csrf(csrf -> csrf.disable()) // Tắt CSRF cho API
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/customers/register", "/api/vehicles/**","/api/payment/vnpay/return","/api/customers/login","/api/payment/vnpay/return").permitAll()
                        .anyRequest().hasAuthority("CUSTOMER") // Cần quyền CUSTOMER
                )
                .httpBasic(withDefaults()) // Dùng Basic Auth cho API
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .securityContext(securityContext -> securityContext
                        .securityContextRepository(new NullSecurityContextRepository())
                )
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint((request, response, authException) -> {
                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                            response.getWriter().write("Unauthorized - Please login with Basic Auth");
                        })
                );

        return http.build();
    }

}
