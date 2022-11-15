//package mtt.webyte.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class ConfigCsrfBean {
//
//    @Autowired
//    private Environment env;
//
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry
//                        .addMapping("/**")
//                        .allowedMethods("OPTIONS","GET","POST","PATCH","PUT","DELETE") // GET, POST, PUT, DELETE, OPTIONS
////                        .allowedMethods(env.getProperty("web.cors.allowed-methods").split(",")) // GET, POST, PUT, DELETE, OPTIONS
//                        .allowedHeaders("*")
//                        .allowedOriginPatterns(env.getProperty("web.cors.allowed-origins")) // http://localhost:4200
//                        .allowCredentials(true);
//            }
//        };
//    }
//}
