package com.example.demo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Tyranid;
import com.example.demo.service.TyranidService;

@CrossOrigin
@RestController
public class TyranidController {
	
	private TyranidService service;
	
	@Autowired
	public TyranidController(TyranidService service) {
		super();
		this.service = service;
	}
	
	@PostMapping("/create")
	public ResponseEntity<Tyranid> createTyranid(@RequestBody Tyranid t) {
		Tyranid created = this.service.create(t);
		ResponseEntity<Tyranid> response = new ResponseEntity<Tyranid>(created, (HttpStatus.CREATED));
		return response;
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Tyranid>> getAllTyranids() {
		return ResponseEntity.ok(this.service.getAll());
	}
	
	@GetMapping("/get/{id}")
	public Tyranid getTyranid(@PathVariable Integer id) {
		return this.service.getOne(id);
	}
	
	@GetMapping("/getByName/{name}")
	public ResponseEntity<List<Tyranid>> getTyranidByName(@PathVariable String name){
		List<Tyranid> found = this.service.getTyranidsByName(name); 
		return ResponseEntity.ok(found);
	}
	
	@GetMapping("/getByHiveFleet/{hiveFleet}")
	public ResponseEntity<List<Tyranid>> getTyranidByHiveFleet(@PathVariable String hiveFleet){
		List<Tyranid> found = this.service.getTyranidsByHiveFleet(hiveFleet); 
		return ResponseEntity.ok(found);
	}
	
	@GetMapping("/getByPoints/{points}")
	public ResponseEntity<List<Tyranid>> getTyranidByPoints(@PathVariable Integer points){
		List<Tyranid> found = this.service.getTyranidsByPoints(points); 
		return ResponseEntity.ok(found);
	}
	
	@PutMapping("/replace/{id}")
	public ResponseEntity<Tyranid> replaceTyranid(@PathVariable Integer id, @RequestBody Tyranid t) {
		Tyranid body = this.service.replace(id, t);
		ResponseEntity<Tyranid> response = new ResponseEntity<Tyranid>(body, HttpStatus.ACCEPTED);
		return response;
	}
	
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<?> removeGame(@PathVariable Integer id) {
		this.service.remove(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
