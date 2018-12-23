package com.project.project;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;



	
	@Entity
	@Table(name = "municipalities")
	public class Municipalities{

		@Id
		//@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "id")
		private Integer id;

		@Column(name = "name")
		private String name;

		@ManyToOne(cascade= {CascadeType.ALL})
		@JoinColumn(name="parentID")
		private Prefectures prefecture;

		public Prefectures getPrefecture() {
			return prefecture;
		}

		public void setPrefecture(Prefectures prefecture) {
			this.prefecture = prefecture;
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

		
	}

	
	
	
	

