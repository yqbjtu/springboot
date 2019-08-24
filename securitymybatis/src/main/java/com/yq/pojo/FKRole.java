package com.yq.pojo;


import java.io.Serializable;

public class FKRole implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
    private String authority;
    
	public FKRole() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	@Override
	public String toString() {
		return "FKRole [id=" + id + ", authority=" + authority + "]";
	}
	
	
    
    
}
