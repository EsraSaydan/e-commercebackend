package com.e_commerce.e_commerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

import static javax.management.Query.and;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authManager(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(provider);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:5173","http://localhost:8080"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        corsConfiguration.setAllowedHeaders(Arrays.asList(HttpHeaders.AUTHORIZATION, HttpHeaders.CONTENT_TYPE));
        corsConfiguration.setExposedHeaders(Arrays.asList(HttpHeaders.AUTHORIZATION));
        corsConfiguration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
        return security.csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // CORS ayarlarını manuel yapılandır
                .authorizeHttpRequests(auth -> {
                    // Giriş ve kayıt gibi endpointler
                    auth.requestMatchers("/auth/**").permitAll();
                    auth.requestMatchers("/auth/signup").permitAll();


                    // AddressController Yetkilendirme
                    auth.requestMatchers(HttpMethod.GET, "/address/**").hasAuthority("USER");
                    auth.requestMatchers(HttpMethod.PUT, "/address/**").hasAuthority("USER");

                    // CardController Yetkilendirme
                    auth.requestMatchers(HttpMethod.POST, "/card/create").hasAuthority("USER");
                    auth.requestMatchers(HttpMethod.GET, "/card/**").hasAuthority("USER");

                    // CategoryController Yetkilendirme
                    auth.requestMatchers(HttpMethod.GET, "/categories/**").hasAuthority("ADMIN");
                    auth.requestMatchers(HttpMethod.POST, "/categories/**").hasAuthority("ADMIN");

                    // OrderController Yetkilendirme
                    auth.requestMatchers(HttpMethod.POST, "/order/**").hasAuthority("USER");

                    // ReviewController Yetkilendirme
                    auth.requestMatchers(HttpMethod.POST, "/reviews/**").hasAuthority("USER");
                    auth.requestMatchers(HttpMethod.GET, "/reviews/product/**").hasAuthority("USER");
                    auth.requestMatchers(HttpMethod.GET, "/reviews/user/**").hasAuthority("USER");

                    // RoleController Yetkilendirme
                    auth.requestMatchers(HttpMethod.GET, "/roles/**").hasAuthority("ADMIN");

                    // UserController Yetkilendirme
                    auth.requestMatchers(HttpMethod.GET, "/user/all").hasAuthority("ADMIN");
                    auth.requestMatchers(HttpMethod.POST, "/user/address").hasAuthority("USER");
                    auth.requestMatchers(HttpMethod.GET, "/user/address").hasAuthority("USER");
                    auth.requestMatchers(HttpMethod.GET, "/user/admin/all").hasAuthority("ADMIN");

                    // WishlistController Yetkilendirme
                    auth.requestMatchers(HttpMethod.POST, "/wishlist/**").hasAuthority("USER");
                    auth.requestMatchers(HttpMethod.GET, "/wishlist/**").hasAuthority("USER");
                    auth.requestMatchers(HttpMethod.DELETE, "/wishlist/**").hasAuthority("USER");

                    // Ürün listeleme işlemleri herkese açık
                    auth.requestMatchers("/products/**").permitAll();

                    // Ürün ekleme, güncelleme ve silme işlemleri sadece ADMIN yetkisi olanlara açık
                    auth.requestMatchers(HttpMethod.POST, "/products/**").hasAuthority("ADMIN");
                    auth.requestMatchers(HttpMethod.PUT, "/products/**").hasAuthority("ADMIN");
                    auth.requestMatchers(HttpMethod.DELETE, "/products/**").hasAuthority("ADMIN");

                    // Swagger ve diğer public alanlar serbest
                    auth.requestMatchers("/swagger-ui/**").permitAll();

                    auth.anyRequest().authenticated();
                })
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // Session-based auth, oturum gerektiğinde başlat
                )
                .httpBasic(Customizer.withDefaults()) // Basic HTTP auth desteği
                .build();

    }

}