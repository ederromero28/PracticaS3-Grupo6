package grupo6.practica.app.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import grupo6.practica.app.model.Alumno;


public interface AlumnoRepository extends JpaRepository<Alumno, Integer> {
	

}
