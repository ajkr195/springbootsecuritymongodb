package com.spring.boot.rocks.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "approle")
public class AppRole implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private String id;
	private String name;

	public AppRole() {
	}

	public AppRole(String id) {
		this.id = id;
	}

	public AppRole(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof AppRole)) {
			return false;
		}
		AppRole other = (AppRole) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "AppRole [id=" + id + ", name=" + name + "]";
	}

}
