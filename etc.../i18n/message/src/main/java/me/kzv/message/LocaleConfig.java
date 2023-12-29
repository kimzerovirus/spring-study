//package me.kzv.message;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.MessageSource;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.LocaleResolver;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
//import org.springframework.web.servlet.i18n.SessionLocaleResolver;
//
//import java.util.Locale;
//
//@Configuration
//public class LocaleConfig implements WebMvcConfigurer {
//
//    @Bean
//    public LocaleResolver localeResolver() {
//        SessionLocaleResolver locale = new SessionLocaleResolver();
//        locale.setDefaultLocale(Locale.KOREA); // ko_KR
//        return locale;
//    }
//
//    // [GET] /say/hello?lang=ko_KR
//    // [GET] /say/hello?lang=en_US
//    @Bean
//    public LocaleChangeInterceptor localeChangeInterceptor() {
//        LocaleChangeInterceptor locale = new LocaleChangeInterceptor();
//        locale.setParamName("lang");
//        return locale;
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(localeChangeInterceptor());
//    }
//}
