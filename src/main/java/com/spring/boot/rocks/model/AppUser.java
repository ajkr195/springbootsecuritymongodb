package com.spring.boot.rocks.model;

import java.io.Serializable;
import java.util.List;

//import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlElement;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "appuser")
public class AppUser implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private String id;
	private String username;
	private String password;
	private String useremail;
	private String userfirstname;
	private String userlastname;
	private String useraddress;

	public AppUser() {
	}

	public AppUser(String id) {
		this.id = id;
	}

	public AppUser(String id, String username, String password, String useremail, String userfirstname,
			String userlastname, String useraddress) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.useremail = useremail;
		this.userfirstname = userfirstname;
		this.userlastname = userlastname;
		this.useraddress = useraddress;
	}

	@DBRef
	private List<AppRole> roles;

	

	public List<AppRole> getRoles() {
		return roles;
	}

	public void setRoles(List<AppRole> roles) {
		this.roles = roles;
	}

	

	public String getId() {
		return id;
	}
	@XmlElement
	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}
	@XmlElement
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

	public String getUseremail() {
		return useremail;
	}
	@XmlElement
	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}

	public String getUserfirstname() {
		return userfirstname;
	}
	@XmlElement
	public void setUserfirstname(String userfirstname) {
		this.userfirstname = userfirstname;
	}

	public String getUserlastname() {
		return userlastname;
	}
	@XmlElement
	public void setUserlastname(String userlastname) {
		this.userlastname = userlastname;
	}

	public String getUseraddress() {
		return useraddress;
	}
	@XmlElement
	public void setUseraddress(String useraddress) {
		this.useraddress = useraddress;
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
		if (!(object instanceof AppUser)) {
			return false;
		}
		AppUser other = (AppUser) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.spring.boot.rocks.model.AppUser[ id=" + id + " ]";
	}

}
