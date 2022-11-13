package grupo6.practica.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import grupo6.practica.app.model.Alumno;
import grupo6.practica.app.services.AlumnoService;

@RestController
@RequestMapping("/alumno")
public class AlumnoController {

	@Autowired
	private AlumnoService service;
	
	
	@RequestMapping(path= "/listar", method = RequestMethod.GET)
	public ResponseEntity<List<Alumno>> listar(){
		List<Alumno> listaproductos = service.listar();
		return new ResponseEntity<List<Alumno>>(listaproductos,HttpStatus.OK);
		
	}

	@RequestMapping(path= "/listar/{id}", method = RequestMethod.GET)
	public ResponseEntity<?>buscarporid(@PathVariable Integer id){
		Alumno alumno = service.obtener(id);
		if (alumno!= null) {
			return new ResponseEntity<>(alumno,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(alumno,HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PostMapping("/guardar")
	public ResponseEntity<?> registrar(@RequestBody Alumno alumno){
		service.guardar(alumno);
		return new ResponseEntity<>(alumno,HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> actualizar(@RequestBody Alumno p,@PathVariable Integer id){
		//producto.setId_producto(id);

		Alumno alumno = service.obtener(p.getIdAlumno());
		if (alumno!= null) {
			service.actualizar(alumno);
			return new ResponseEntity<>(alumno,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(alumno,HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Integer id){
		Alumno alumno = service.obtener(id);
		if (alumno!= null) {
			service.eliminar(id);
			return new ResponseEntity<>(id,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(id,HttpStatus.NOT_FOUND);
		}
	}
	
}
