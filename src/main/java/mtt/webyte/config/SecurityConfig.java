package mtt.webyte.config;

import mtt.webyte.config.jwt.JwtFilter;
import mtt.webyte.enums.RoleType;
import mtt.webyte.services.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configurable
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Value("${web.cors.allowed-origins}")//lấy giá trị của local được cho phép sử dụng api
    private String url;
    @Value("${web.cors.allowed-methods}")// lấy các method được phép sử dụng
    private String[] method;
    @Autowired
    JwtFilter jwtFilter;

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userDetailsService());
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable().cors().configurationSource(request -> {
                    CorsConfiguration configuration = new CorsConfiguration();
                    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                    configuration.setAllowedOriginPatterns(Collections.singletonList("*"));
                    configuration.setAllowedMethods(Arrays.asList(method));
                    configuration.setAllowedHeaders(Collections.singletonList("*"));
                    configuration.setAllowCredentials(true);
                    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                    source.registerCorsConfiguration("/**", configuration);
                    return configuration;
                });
//                .and()
//                .authorizeRequests()
//                .antMatchers("/webyte/account/**").permitAll()
//                .antMatchers("/webyte/doctor/**", "/webyte/dept/**").hasAnyAuthority(RoleType.ROLE_DOCTOR.name(), RoleType.ROLE_ADMIN.name())
//                .antMatchers("/webyte/patient/**").hasAnyAuthority(RoleType.ROLE_USER.name())
//                .antMatchers("/webyte/news/**", "/**").hasAnyAuthority(RoleType.ROLE_ADMIN.name())
//                .antMatchers("http://localhost:8080/swagger-ui/index.html#").permitAll()
//                .anyRequest().authenticated()
//                .and().exceptionHandling().and().sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

//        httpSecurity
//                .csrf().disable()
//                .cors().disable()
//                .authorizeRequests()
//                .antMatchers("/webyte/account/**").permitAll()
//                .antMatchers("/webyte/doctor/**","/webyte/dept/**").hasAnyAuthority(RoleType.ROLE_DOCTOR.name())
//                .antMatchers("/webyte/patient/**").hasAnyAuthority(RoleType.ROLE_USER.name())
//                .antMatchers("/webyte/news/**","/**").hasAnyAuthority(RoleType.ROLE_ADMIN.name())
//                .antMatchers("/blog-app/swagger-ui/**").permitAll()
//                .antMatchers("/swagger-ui.html#!/**").permitAll()
////                .and()
////                .formLogin().permitAll()
////                .and()
////                .logout().permitAll()
//                .and().exceptionHandling().accessDeniedPage("/403");

//                .and().sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        //httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        httpSecurity.authorizeRequests().antMatchers("/webyte/account/**", "/webyte/doctor/**", "/webyte/news/**", "/webyte/dept/**", "/**").permitAll().anyRequest().authenticated()
                .and().exceptionHandling().and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
