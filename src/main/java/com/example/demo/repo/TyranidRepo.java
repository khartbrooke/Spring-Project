package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Tyranid;

@Repository
public interface TyranidRepo extends JpaRepository<Tyranid, Integer>{
	
	List<Tyranid> findByNameIgnoreCase(String name);
	
	List<Tyranid> findByHiveFleet(String hiveFleet);

	List<Tyranid> findByPoints(Integer points);

}
