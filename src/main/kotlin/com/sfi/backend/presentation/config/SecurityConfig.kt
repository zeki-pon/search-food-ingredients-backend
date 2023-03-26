package com.sfi.backend.presentation.config

import com.sfi.backend.presentation.handler.SfiAccessDeniedHandler
import com.sfi.backend.presentation.handler.SfiAuthenticationEntryPoint
import com.sfi.backend.presentation.handler.SfiAuthenticationFailureHandler
import com.sfi.backend.presentation.handler.SfiAuthenticationSuccessHandler
import com.sfi.backend.service.security.ApplicationUserDetailsService
import com.sfi.backend.service.security.AuthenticationService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val authenticationService: AuthenticationService
): WebSecurityConfigurerAdapter()  {
    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
            /* 認可の設定 */
            .mvcMatchers("/login").permitAll()
            .anyRequest().authenticated()
            .and()
            .csrf().disable()
            /* 認証の設定 */
            .formLogin()
            .loginProcessingUrl("/login")
            .usernameParameter("email")
            .passwordParameter("pass")
            /* 認証・認可時の各種ハンドラーを設定 */
            .successHandler(SfiAuthenticationSuccessHandler())
            .failureHandler(SfiAuthenticationFailureHandler())
            .and()
            .exceptionHandling()
            .authenticationEntryPoint(SfiAuthenticationEntryPoint())
            .accessDeniedHandler(SfiAccessDeniedHandler())
            /* CORSの設定 */
            .and()
            .cors()
            .configurationSource(corsConfigurationSource())
    }

    /** 認証処理に関する設定 */
    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(ApplicationUserDetailsService(authenticationService))
            .passwordEncoder(BCryptPasswordEncoder())
    }

    private fun corsConfigurationSource(): CorsConfigurationSource {

        val corsConfiguration = CorsConfiguration().apply {
            addAllowedMethod(CorsConfiguration.ALL)
            addAllowedHeader(CorsConfiguration.ALL)
            addAllowedOrigin("http://localhost:8081")
            allowCredentials = true
        }

        val corsConfigurationSource = UrlBasedCorsConfigurationSource().apply {
            registerCorsConfiguration("/**", corsConfiguration)
        }

        return corsConfigurationSource
    }
}