package grupo6.practica.app.services;

import java.util.List;

import grupo6.practica.app.model.Alumno;

public interface AlumnoService {

	void guardar(Alumno alumno);
	void actualizar(Alumno alumno);
	void eliminar(Integer id);
	List<Alumno> listar();
	Alumno obtener(Integer id);
}
