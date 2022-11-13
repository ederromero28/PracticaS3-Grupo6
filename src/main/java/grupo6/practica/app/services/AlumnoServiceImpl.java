package grupo6.practica.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import grupo6.practica.app.model.Alumno;
import grupo6.practica.app.repository.AlumnoRepository;

@Service
public class AlumnoServiceImpl implements AlumnoService {

	@Autowired
	private AlumnoRepository repositorio;
	
	@Override
	public void guardar(Alumno alumno) {
		repositorio.save(alumno);
		
	}

	@Override
	public void actualizar(Alumno alumno) {
		repositorio.saveAndFlush(alumno);
		
	}

	@Override
	public void eliminar(Integer id) {
		repositorio.deleteById(id);
	}

	@Override
	public List<Alumno> listar() {

		return repositorio.findAll();
	}

	@Override
	public Alumno obtener(Integer id) {
		return repositorio.findById(id).orElse(null);
	}

}
