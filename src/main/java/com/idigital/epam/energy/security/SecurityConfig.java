package com.idigital.epam.energy.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private JwtFilter jwtFilter;

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Autowired
    UserProvider userProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.userDetailsService(userProvider).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable()
                .httpBasic().disable()
                .authorizeRequests()
                .antMatchers( "/api/account/authentication","/api/account/registration",
                        "/v2/api-docs", // swagger
                        "/webjars/**", // swagger-ui webjars
                        "/swagger-resources/**", // swagger-ui resources
                        "/configuration/**", // swagger configuration
                        "/*.html", "/favicon.ico", "/**/*.html", "/**/*.css", "/**/*.js",
                        "/*", "/api/v1/attachment/**", "/resources/**")
                .permitAll()
                .antMatchers("/api/user/**").hasAnyAuthority("ROLE_ADMIN")
               .antMatchers("/api/user/**").hasAnyAuthority("ROLE_USER")
                .anyRequest()
                //.authenticated();
                        .permitAll();

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web
//                .ignoring()
//                .antMatchers(HttpMethod.OPTIONS, "/**")
//                .antMatchers("/app/**/*.{js,html}")
//                .antMatchers("/i18n/**")
//                .antMatchers("/content/**")
//                .antMatchers("/h2-console/**")
//                .antMatchers("/v2/api-docs",
//                "/webjars/**",
//                "/swagger-resources/**",
//                "/configuration/**" ,  "/*.html", "/favicon.ico", "/**/*.html", "/**/*.css", "/**/*.js",
//                        "/*", "/api/v1/attachment/**", "/resources/**"
//                )
//
//                .antMatchers("/test/**")
//                .antMatchers("/*.*"); // #3
//
//    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }



@Bean
public CorsFilter corsFilter() {
    UrlBasedCorsConfigurationSource source =
            new UrlBasedCorsConfigurationSource();
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowCredentials(true);
    config.addAllowedOrigin("http://localhost:4200");
    config.addAllowedOrigin("http://localhost:3000");
    config.addAllowedOrigin("http://localhost:5500");
    config.addAllowedOrigin("http://localhost:5501");
    config.addAllowedOrigin("http://localhost:5502");
    config.addAllowedOrigin("http://192.168.43.144:4200");

    config.addAllowedHeader("*");
    config.addAllowedMethod("*");
    source.registerCorsConfiguration("/**", config);
    return new CorsFilter(source);
}
}
