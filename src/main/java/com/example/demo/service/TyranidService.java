package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.domain.Tyranid;
import com.example.demo.repo.TyranidRepo;

@Service
public class TyranidService implements ServiceInter<Tyranid>{
	
	private TyranidRepo repo;
	
	@Autowired
	public TyranidService(TyranidRepo repo) {
		super();
		this.repo = repo;
	}

	public Tyranid create(Tyranid t) {
		Tyranid created = this.repo.save(t);
		return created;
	}
	
	public List<Tyranid> getAll() {
		return this.repo.findAll();
	}
	
	public Tyranid getOne(Integer id) {
		Optional<Tyranid> found = this.repo.findById(id);
		return found.get();
	}
	
	public List<Tyranid> getTyranidsByName(String name) {
		List<Tyranid> found = this.repo.findByNameIgnoreCase(name);
		return found;
	}
	
	public List<Tyranid> getTyranidsByHiveFleet(String hiveFleet) {
		List<Tyranid> found = this.repo.findByHiveFleet(hiveFleet);
		return found;
	}
	
	public List<Tyranid> getTyranidsByPoints(Integer points) {
		List<Tyranid> found = this.repo.findByPoints(points);
		return found;
	}
	
	public Tyranid replace(Integer id, Tyranid t) {
		Tyranid existing = this.repo.findById(id).get();
		existing.setName(t.getName());
		existing.setHiveFleet(t.getHiveFleet());
		existing.setPoints(t.getPoints());
		Tyranid updated = this.repo.save(existing);
		return updated;
	}
	
	public void remove(Integer id) {
		this.repo.deleteById(id);
	}

}
