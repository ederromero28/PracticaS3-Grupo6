package grupo6.practica.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true) //usar todos los metodos globales
public class ConfigSecurity extends WebSecurityConfigurerAdapter{
	
	 @Autowired
	    private UserDetailService service;

	   @Bean
	    @Override
	    protected AuthenticationManager authenticationManager() throws Exception {
	        return super.authenticationManager();
	    }



	   @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(service).passwordEncoder(encriptado());
	    }



	   @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http.anonymous().disable();
	    }



	   @Bean
	    public PasswordEncoder encriptado() {
	        return new BCryptPasswordEncoder();
	    }



//	   @Bean
//	    public TokenStore tokenStore() {
//	        return new InMemoryTokenStore();
//	    }
	   
	   @Bean
	   public JwtAccessTokenConverter accessTokenConverter() {
		   JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		   converter.setSigningKey("publicidad");
		   
		   return converter;
	   }
	   
	   @Bean
	   public JwtTokenStore tokenStore() {
		   return new JwtTokenStore(accessTokenConverter());
	   }
}
