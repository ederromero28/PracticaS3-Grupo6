package grupo6.practica.app.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import grupo6.practica.app.model.Colegio;


public interface ColegioRepository extends JpaRepository<Colegio, Integer> {
	

}
