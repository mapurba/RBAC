package assig.apurba.rar.web.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import assig.apurba.rar.dao.Roles;


public class UserHelper implements Serializable {
	private static final Long serialVersionUID = -2977271355914647053L;
	
	private String name;
	private List<Roles> roles;
	
	public UserHelper(String name, Roles role) {
		super();
		this.name = name;
		this.roles = new ArrayList<Roles>();
		this.setRole(role);
		// this.role = role;
	}

	public UserHelper(String name, List<Roles> roles) {
		super();
		this.name = name;
		this.roles = new ArrayList<Roles>();
		this.roles = roles;
	}

	public UserHelper() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	// @Override
	public List<Roles> getRoles() {
		return this.roles;
	}
	

	

	//#todo  a user can have many roles 
	// @Override
	public void setRoles(List<Roles> roles) {
		// if(role instanceof Roles){
		// 	this.roles.add(role);
		// }
		// this.role.add(role);
		this.roles=roles;
	}


	// @Override
	public void setRole(Roles role) {
		if(role instanceof Roles){
			this.roles.add(role);
		}
		// this.role.add(role);
	}
}
