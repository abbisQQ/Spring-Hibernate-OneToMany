package com.project.project;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "prefectures")
public class Prefectures {

	@OneToMany(mappedBy="prefecture")
	private List<Municipalities> municipalities; 
	
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "parentID", nullable = true)
	private Integer parentID;

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

	public Integer getParentID() {
		return parentID;
	}

	public void setParentID(Integer parentID) {
		this.parentID = parentID;
	}

	public List<Municipalities> getMunicipalities() {
		return municipalities;
	}

	public void setMunicipalities(List<Municipalities> municipalities) {
		this.municipalities = municipalities;
	}

	
	
	
}
