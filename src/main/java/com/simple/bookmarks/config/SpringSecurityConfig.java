package com.simple.bookmarks.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by jcarretero on 22/01/2018.
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${ldap.urls}")
    private String ldapUrls;

    @Value("${ldap.base.dn}")
    private String ldapBaseDn;

    @Value("${ldap.username}")
    private String ldapSecurityPrincipal;

    @Value("${ldap.password}")
    private String ldapPrincipalPassword;

    @Value("${ldap.user.search.base}")
    private String ldapUserSearchBase;

    @Value("${ldap.user.search.filter}")
    private String ldapUserSearchFilter;

    @Value("${ldap.enabled}")
    private String ldapEnabled;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/login**").permitAll()
                    .antMatchers("/getAllCustomers").fullyAuthenticated()
                    //.antMatchers("/profile/**").fullyAuthenticated()
                    //.antMatchers("/").permitAll()
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/getAllCustomers")
                    //.failureUrl("/login?error")
                    //.permitAll()
                    .and()
                .logout()
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
                    .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        if(Boolean.parseBoolean(ldapEnabled)) {
            auth
                   .ldapAuthentication()
                        .contextSource().url(ldapUrls + ldapBaseDn)
                        .managerDn(ldapSecurityPrincipal).managerPassword(ldapPrincipalPassword)
                        .and()
                        .userSearchBase(ldapUserSearchBase)
                        .userSearchFilter(ldapUserSearchFilter);
        } else {
            auth
                    .inMemoryAuthentication()
                    .withUser("javier").password("password").roles("USER");
        }
    }


//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/*.css");
//        web.ignoring().antMatchers("/*.js");
//    }
}
