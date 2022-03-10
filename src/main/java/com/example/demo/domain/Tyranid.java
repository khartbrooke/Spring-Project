package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Tyranid {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	String name;
	String hiveFleet;
	Integer points;
	
	public Tyranid() {
		super();
	}

	public Tyranid(Integer id, String name, String hiveFleet, Integer points) {
		super();
		this.id = id;
		this.name = name;
		this.hiveFleet = hiveFleet;
		this.points = points;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHiveFleet() {
		return hiveFleet;
	}

	public void setHiveFleet(String hiveFleet) {
		this.hiveFleet = hiveFleet;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	@Override
	public String toString() {
		return "Tyranid [id=" + id + ", name=" + name + ", hiveFleet=" + hiveFleet + ", points=" + points + "]";
	}		

}
