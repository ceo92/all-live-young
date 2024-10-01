package allliveyoung.wms.config;

import allliveyoung.wms.config.filter.LoginFail;
import allliveyoung.wms.config.filter.LoginSuccess;
import allliveyoung.wms.config.filter.LogoutSuccess;
import allliveyoung.wms.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

@EnableWebSecurity
@RequiredArgsConstructor
@Component
public class SecurityConfig {
    private final UserDetailsServiceImpl userDetailsService;
    private final LoginSuccess loginSuccess;
    private final LoginFail loginFail;
    private final LogoutSuccess logoutSuccess;
    private final String[] admin = {"/members/requests/**", "/members/cancel/**", "/warehouses/save/*", "/warehouses/*/update-search"};
    private final String[] manager = {"/inbound-requests/*/update-status", "/expenses/save", "/expenses/*/update", "/sales/save", "/sales/*/update", "/warehouses/save", "/warehouses/*/update-request"};
    private final String[] adminAndManager = {"/members", "/announcements/save", "/announcements/*/**", "/inquiries/*/**", "/answers", "/expenses/statistic", "/stocks/*/**"};
    private final String[] company = {"/inbound-requests/save", "/inbound-requests/*/update", "/inbound-requests/*/delete", "/outbound-requests/save", "/inquiries/save", "/inquiries/*/update"};
    private final String[] all = {"/", "/inbound-requests", "/outbound-requests", "/members/*/**", "/announcements", "/inquiries", "/inquiries/*", "/expenses", "/sales", "/stocks", "/stocks/*", "/warehouses", "/warehouses/*"};
    private final String[] permitAll = {"/join", "/find-account", "/reset-password", "/join-type"};

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(requestMatcher -> requestMatcher
                        .requestMatchers(adminAndManager).hasAnyRole("ADMIN", "MANAGER")
                        .requestMatchers(admin).hasRole("ADMIN")
                        .requestMatchers(manager).hasRole("MANAGER")
                        .requestMatchers(company).hasRole("COMPANY")
                        .requestMatchers(all).authenticated()
                        .requestMatchers(permitAll).permitAll()
                        .anyRequest().authenticated())

                .formLogin(login -> login
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/")
                        .successHandler(loginSuccess)
                        .failureHandler(loginFail)
                        .permitAll())

                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .logoutSuccessHandler(logoutSuccess)
                        .deleteCookies("JSESSIONID"))

                .userDetailsService(userDetailsService);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
