package grupo6.practica.app.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import grupo6.practica.app.model.Usuario;
import grupo6.practica.app.repository.UsuarioRepository;

@Service
public class UserDetailService implements UserDetailsService {

	@Autowired
	private UsuarioRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Usuario usu = repository.findByUsuario(username);
		
		if(usu != null) {
			
			//lista de roles de un usuario
			List<GrantedAuthority> listGranted = new ArrayList<>();
			
			GrantedAuthority granted = new SimpleGrantedAuthority(usu.getRol());
			
			listGranted.add(granted);
			
			return new User(
					usu.getUsuario(),
					usu.getContrasenia(),
					listGranted);
		}else {
			throw new UsernameNotFoundException("Usuario "+ username+"  no existe ");
		}
		
//		if ("acevedo".equals(username)) {
//			return new User("acevedo", new BCryptPasswordEncoder().encode("123"), new ArrayList<>());
//		}else {
//			throw new UsernameNotFoundException("Usuario "+ username+"  no existe ");
//		}
	}

}
