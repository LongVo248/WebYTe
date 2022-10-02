//package mtt.webyte.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Configurable;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.BeanIds;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//import java.util.Arrays;
//
//@Configurable
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//  @Value("${web.cors.allowed-origins}")//lấy giá trị của local được cho phép sử dụng api
//  private String url;
//  @Value("${web.cors.allowed-methods}")// lấy các method được phép sử dụng
//  private String[] method;
////  @Autowired
////  AccountDetailServiceImpl accountDetailService;
////  @Autowired
////  JwtFilter jwtFilter;
//
//  @Bean
//  public PasswordEncoder passwordEncoder() {
//    return new BCryptPasswordEncoder();
//  }
//
//  @Override
//  protected void configure(HttpSecurity httpSecurity) throws Exception {
//    httpSecurity.csrf().disable().cors().configurationSource(request -> {
//      CorsConfiguration configuration = new CorsConfiguration();
//      configuration.setAllowedOrigins(Arrays.asList(url));// nếu muốn cho phép tất cả thì thay url thành "*"
//      configuration.setAllowedMethods(Arrays.asList(method));
//      configuration.setAllowedHeaders(Arrays.asList("*"));
//      configuration.setAllowCredentials(true);
//      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//      source.registerCorsConfiguration("/**", configuration);
//      return configuration;
//    });
//    ;
//    httpSecurity.authorizeRequests().antMatchers("/webyte/account/**","/webyte/doctor/**","/webyte/news/**","/webyte/dept/**","/**").permitAll().anyRequest().authenticated()
////.and().formLogin().permitAll();
//            .and().exceptionHandling().and().sessionManagement()
//            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
////       httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
////        httpSecurity.antMatcher("/**").authorizeRequests().anyRequest().permitAll();
//  }
//
//  @Override
//  public void configure(WebSecurity web) throws Exception {
////		web.ignoring().antMatchers("/resources/**").anyRequest();
//    web.ignoring().antMatchers("/images/**");
//  }
//
//  @Autowired
//  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
////    auth.userDetailsService(accountDetailService).passwordEncoder(passwordEncoder());
//  }
//
//  @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
//  @Override
//  public AuthenticationManager authenticationManagerBean() throws Exception {
//    return super.authenticationManagerBean();
//  }
//
//
//}
