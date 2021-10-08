package com.sp.fc.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SnsLoginSecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private SpOAuth2UserService oAuth2UserService;
//
//    @Autowired
//    private SpOidcUserService oidcUserService;

    @Autowired
    private SpOAuth2SuccessHandler successHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .oauth2Login(oauth2->oauth2
//                     .userInfoEndpoint(
//                        userinfo->userinfo.userService(oAuth2UserService)
//                                .oidcUserService(oidcUserService)
//                    )
                                .successHandler(successHandler)
                )
                ;
    }
}
