package com.bugtracker.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "releas")
public class Release implements Serializable {

	private static final long serialVersionUID = 1566234L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "releasedate")
    private String releaseDate;
    private String description;

    public Release() {
    }

    public Release(String description, String releaseDate){
    	
        this.releaseDate = releaseDate;
        this.description = description;
    }
    
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	@Override
	public String toString() {
		return "Release [id=" + id + ", releaseDate=" + releaseDate + ", description=" + description + "]";
	}
    
    
}
