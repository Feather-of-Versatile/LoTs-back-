package FoV.LoTs.Global.Config;


import FoV.LoTs.Global.Security.JwtFilter;
import FoV.LoTs.Global.Security.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsUtils;

import java.util.stream.Stream;

@EnableWebSecurity      //기본적인 웹 보안 활성화
@EnableMethodSecurity   //@PreAuthorize 어노테이션을 메소드단위로 추가하기 위해 적용
@Configuration
@RequiredArgsConstructor
public class SecurityConfig { //추가적인 보안 설정
    private final TokenProvider tokenProvider;
    //권한 확인을 하지 않는 uri
    private static final String[] PERMIT_ALL_PATTERNS = new String[]{
            "/h2-console/**",
                        "/favicon.ico",
            "/error",
            "/api/authenticate",
            /* swagger v2 */
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            /* swagger v3 */
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/css/**",
            "/fonts/**",
            "/img/**",
            "/js/**",
            /* privacy html */
            "/terms/**",
            /* Service */
            "/api/user/auth/check/**",
            "/api/**/auth/login",
            "/api/user/auth/join",
            "/api/user/auth/exist",
            "/api/**/auth/login/**",
            "/api/user/auth/join/**",
            "/api/user/auth/exist/**",
            "/api/**/auth/refresh",
            "/api/user/rewards/billing/google/**",
            "/api/user/rewards/billing/apple/v2/**",
            "/api/user/rewards/test",
            "/api/images/**",
            "/"
    };

//     //passwordEncoder 로는 BCryptPasswordEncoder 사용
//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }

    //requestMatchers에 파라미터로 넘겨주는 endpoints는 Spring Security Filter Chain을 거치지 않기 때문에 '인증' , '인가' 서비스가 모두 적용되지 않는다.
    //HttpSecurity보다 우선적용
    //일반적으로 로그인 페이지, public 페이지 등 인증, 인가 서비스가 필요하지 않은 endpoint에 사용
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web ->
                web.ignoring()
                        .requestMatchers( // <== 여기
                                Stream
                                        .of(PERMIT_ALL_PATTERNS)
                                        .map(AntPathRequestMatcher::new)
                                        .toArray(AntPathRequestMatcher[]::new)
                        );
    }

    //requestMatchers에 있는 endpoint에 대한 '인증'을 무시한다.
    //Security Filter Chain에서 요청에 접근할 수 있기 때문에(요청이 security filter chain 거침) 인증, 인가 서비스와
    //Secure headers, CSRF protection 등 같은 Security Features 또한 사용된다.
    //취약점에 대한 보안이 필요할 경우 HttpSecurity 설정을 사용
    @Bean
    protected SecurityFilterChain webSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                // token을 사용하는 방식이기 때문에 csrf설정을 disable합니다.
                .csrf(AbstractHttpConfigurer::disable)

                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                        
                        // .requestMatchers(new AntPathRequestMatcher("/api/admin/**")).
                        // .requestMatchers(new AntPathRequestMatcher("/web-resources/**"), new AntPathRequestMatcher("/actuator/**")).permitAll()//해당 api 요청은 인증없이 접근 허용하겠다는 의미
                        // .requestMatchers(new AntPathRequestMatcher("/api/admin/convo"),
                        //         new AntPathRequestMatcher("/api/admin/counseling"),
                        //         new AntPathRequestMatcher("/api/admin/psytest")).hasAnyRole("COUNSELOR", "ADMIN")
//                .requestMatchers(new AntPathRequestMatcher( "/api/admin/**")).hasRole("ADMIN")
                        .anyRequest().authenticated()) //나머지 요청들은 모두 인증되어야 한다

                // ption 핸들링 (직접 만든 클래스로)
                // .exceptionHandling(configurer -> {
                //     configurer.authenticationEntryPoint(jwtAuthenticationEntryPoint);
                //     configurer.accessDeniedHandler(jwtAccessDeniedHandler);
                // // })

                // 세션을 사용하지 않기 때문에 STATELESS로 설정
                .sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // JWTFilter를 addFilterBefore로 등록했던 JwtSecurityConfig클래스도 적용
                .addFilterBefore(
                        new JwtFilter(tokenProvider),
                        UsernamePasswordAuthenticationFilter.class);
//                .apply(new JwtSecurityConfig(tokenProvider, jwtExceptionFilter));
        return httpSecurity.build();
    }
}
