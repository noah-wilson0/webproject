package com.wplab.entity;

public class UserDO {
	private String id;
	private String name;
	private String authority;
	
	public UserDO() {}
	public UserDO(String id, String name, String authority) {
		
		this.id = id;
		this.name = name;
		this.authority = authority;
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
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	@Override
	public String toString() {
		return "UserDO [id=" + id + ", name=" + name + ", authority=" + authority + "]";
	}
}
