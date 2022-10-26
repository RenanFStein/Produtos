package com.produtos.apirest.config.Security;

import com.produtos.apirest.config.Security.AutenticacaoServiceConfig;
import com.produtos.apirest.repository.UsuarioRepository;
import org.apache.catalina.User;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig  extends WebSecurityConfigurerAdapter {
    @Autowired
    private AutenticacaoServiceConfig autenticacaoServiceConfig;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UsuarioRepository usuarioReposiroty;
    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception{
        return super.authenticationManager();
    }

    // Responsavel pela autenticação
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(autenticacaoServiceConfig).passwordEncoder(new BCryptPasswordEncoder());

    }

    // Responsavel pela autorização
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/*").authenticated()//Libera todas as rotas depois desse caminho
                .antMatchers(HttpMethod.POST, "/auth").permitAll()
                .anyRequest().authenticated() // As demais rotas que não estão acima devem ter autorização
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(new AutenticacaoViaTokenFilter(tokenService, usuarioReposiroty), UsernamePasswordAuthenticationFilter.class);
    }

    // Responsavel por recursos estaticos(js, css, img etc...)
    @Override
    public void configure(WebSecurity web) throws Exception {

    }

}
