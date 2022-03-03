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

	@Override
	public Tyranid create(Tyranid t) {
		Tyranid created = this.repo.save(t);
		return created;
	}

	@Override
	public List<Tyranid> getAll() {
		return this.repo.findAll();
	}

	@Override
	public Tyranid getOne(Integer id) {
		Optional<Tyranid> found = this.repo.findById(id);
		return found.get();
	}

	@Override
	public Tyranid replace(Integer id, Tyranid t) {
		Tyranid existing = this.repo.findById(id).get();
		existing.setName(t.getName());
		existing.setHiveFleet(t.getHiveFleet());
		existing.setPoints(t.getPoints());
		Tyranid updated = this.repo.save(existing);
		return updated;
	}

	@Override
	public void remove(Integer id) {
		this.repo.deleteById(id);
	}

}
