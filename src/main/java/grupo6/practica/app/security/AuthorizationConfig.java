package grupo6.practica.app.security;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationConfig extends AuthorizationServerConfigurerAdapter{

	
	  @Autowired
	    private JwtTokenStore tokenStore;//almacena las llaves
	    
	    @Autowired
	    private AuthenticationManager authenticationManager;
	    
	    @Autowired
	    private JwtAccessTokenConverter accessTokenConverter;
	    
	    @Override
	    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
	        // TODO Auto-generated method stub
	        super.configure(security);
	    }



	   @Override
	    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
	        
	        clients.inMemory()
	            .withClient("edu")//usuario externo
	            .secret(new BCryptPasswordEncoder().encode("sistemexterior"))//contra exterior
	            .authorizedGrantTypes("password", "authorization_code", "refresh_token")//indica el tipo de permiso que tendra el usuario
	            .scopes("read","write","trust")//acciones a las que tendra permiso
	            .accessTokenValiditySeconds(1*60*60)
	            .refreshTokenValiditySeconds(5*60*60);
	    }



	   @Override
	    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception { 
		   //se guarda la autenticacion
		   //endpoint es una url
		   
		   TokenEnhancerChain chain = new TokenEnhancerChain();
		   
		   chain.setTokenEnhancers(Arrays.asList(new TokenEnhancer() {
			
			@Override
			public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
				
				Map<String, Object> informacionAdicional = new HashMap<>();
				informacionAdicional.put("admin", "julio"); //nuevos campos
				informacionAdicional.put("user", "jose");
				
				DefaultOAuth2AccessToken token = new DefaultOAuth2AccessToken(accessToken); //agrega por defecto los campos que se quieeere agregar
				token.setAdditionalInformation(informacionAdicional);
				
				return token;
			}
		},accessTokenConverter)); //se convierte los campos como token de acceso
		   
	        endpoints.tokenStore(tokenStore)
	            .authenticationManager(authenticationManager)
	            .accessTokenConverter(accessTokenConverter)
	            .tokenEnhancer(chain);
	    }


}
