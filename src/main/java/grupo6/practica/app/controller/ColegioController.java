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

import grupo6.practica.app.model.Colegio;
import grupo6.practica.app.services.ColegioService;

@RestController
@RequestMapping("/colegio")
public class ColegioController {

	@Autowired
	private ColegioService service;
	
	
	@RequestMapping(path= "/listar", method = RequestMethod.GET)
	public ResponseEntity<List<Colegio>> listar(){
		List<Colegio> listaproductos = service.listar();
		return new ResponseEntity<List<Colegio>>(listaproductos,HttpStatus.OK);
		
	}

	@RequestMapping(path= "/listar/{id}", method = RequestMethod.GET)
	public ResponseEntity<?>buscarporid(@PathVariable Integer id){
		Colegio colegio = service.obtener(id);
		if (colegio!= null) {
			return new ResponseEntity<>(colegio,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(colegio,HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PostMapping("/guardar")
	public ResponseEntity<?> registrar(@RequestBody Colegio colegio){
		service.guardar(colegio);
		return new ResponseEntity<>(colegio,HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> actualizar(@RequestBody Colegio c,@PathVariable Integer id){
		//producto.setId_producto(id);

		Colegio colegio = service.obtener(c.getIdColgeio());
		if (colegio!= null) {
			service.actualizar(colegio);
			return new ResponseEntity<>(colegio,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(colegio,HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Integer id){
		Colegio colegio = service.obtener(id);
		if (colegio!= null) {
			service.eliminar(id);
			return new ResponseEntity<>(id,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(id,HttpStatus.NOT_FOUND);
		}
	}
	
}
