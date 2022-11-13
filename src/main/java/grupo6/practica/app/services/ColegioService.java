package grupo6.practica.app.services;

import java.util.List;

import grupo6.practica.app.model.Colegio;

public interface ColegioService {

	void guardar(Colegio colegio);
	void actualizar(Colegio colegio);
	void eliminar(Integer id);
	List<Colegio> listar();
	Colegio obtener(Integer id);
}
