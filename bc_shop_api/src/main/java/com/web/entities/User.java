	package com.web.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	
//	@EmbeddedId
//	private UserPK idpk;

	private byte enabled;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

//	@Column(name="max_user_connections")
//	private int maxUserConnections;

	private String password;

	private String role;
	@Column(name="ADDRESS")
	private String address;
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	private String sdt;
	private String cookie;


	public String getCookie() {
		return cookie;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

	@Column(name="TEN_KH")
	private String tenKh;
//	@Column(nullable = false, unique = true)
	private String username;

	public User() {
	}

//	public UserPK getIdpk() {
//		return this.idpk;
//	}

//	public void setId(UserPK idpk) {
//		this.idpk = idpk;
//	}

	public byte getEnabled() {
		return this.enabled;
	}

	public void setEnabled(byte enabled) {
		this.enabled = enabled;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

//	public int getMaxUserConnections() {
//		return this.maxUserConnections;
//	}
//
//	public void setMaxUserConnections(int maxUserConnections) {
//		this.maxUserConnections = maxUserConnections;
//	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getSdt() {
		return this.sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getTenKh() {
		return this.tenKh;
	}

	public void setTenKh(String tenKh) {
		this.tenKh = tenKh;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}