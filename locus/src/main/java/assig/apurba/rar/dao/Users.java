/* USERS.java
 *   by Anonymous
 *
 * Created:
 *   7/5/2020, 2:21:52 PM
 * Last edited:
 *   7/5/2020, 2:21:52 PM
 * Auto updated?
 *   Yes
 *
 * Description:
 *   Apurba Mondal
**/

package assig.apurba.rar.dao;

import java.util.ArrayList;
import java.util.List;

import javax.management.relation.Role;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import assig.apurba.rar.dao.Roles;
import assig.apurba.rar.dao.Tasks;
import assig.apurba.rar.web.model.TasksUser;
import assig.apurba.rar.dao.Roles;

public class Users extends User implements TasksUser {
	private static final long serialVersionUID = 8498233196842987555L;
	
	private Tasks Tasks;
	private List<Roles> roles;
	private List<Roles> newRoles;
	private Boolean hasNewRole;

	public Users(String username, String password, Roles role) {
		super(username, password, new ArrayList<GrantedAuthority>(0));
		// if(role instanceof Roles){
		// 	this.role.add(role);
		// }
		this.roles =new ArrayList<Roles>();
		this.setRole(role);

	}

	public Users(String username, String password, List<Roles> roles) {
		super(username, password, new ArrayList<GrantedAuthority>(0));
		// if(role instanceof Roles){
		// 	this.role.add(role);
		// }
		this.roles =new ArrayList<Roles>();

		this.roles=roles;


	}
	
	public Users(String username, String password, Tasks Tasks, Roles role) {
		super(username, password, new ArrayList<GrantedAuthority>(0));
		// if(role instanceof Roles){
		// 	this.role.add(role);
		// }
		this.roles =new ArrayList<Roles>();
		this.setRole(role);
		this.Tasks = Tasks;
	}

	public Users(String username, String password, Tasks Tasks, List<Roles> roles) {
		super(username, password, new ArrayList<GrantedAuthority>(0));
		// if(role instanceof Roles){
		// 	this.role.add(role);
		// }
		roles =new ArrayList<Roles>();
		this.roles=roles;
		this.Tasks = Tasks;
	}

	public Users(String username, String password, List<Roles> newRoles,Boolean hasNewRole) {
		super(username, password, new ArrayList<GrantedAuthority>(0));
		// if(role instanceof Roles){
		// 	this.role.add(role);
		// }
		// roles =new ArrayList<Roles>();
		this.hasNewRole=true;
		this.newRoles=newRoles;
	}

	public Users(String username, String password) {
		super(username, password, new ArrayList<GrantedAuthority>(0));
		// if(role instanceof Roles){
		// 	this.role.add(role);
		// }
		// this.roles =new ArrayList<Roles>();
		// this.setRole(role);

	}


	
	@Override
	public Tasks getTasks() {
		return Tasks;
	}

	//#todo  a user can have many Taskss 


	@Override
	public void setTasks(Tasks Tasks) {
		this.Tasks = Tasks;
	}

    //#todo  a user can have many roles 

	@Override
	public List<Roles> getRoles() {
		return this.roles;
	}

	@Override
	public List<Roles> getRole() {
		return this.roles;
	}

    //#todo  a user can have many roles 


	@Override
	public void setRole(Roles role) {
		if(role instanceof Roles){
			this.roles.add(role);
		}
	}

	@Override
	public void setRoles(List<Roles> roles) {
		
		this.roles=roles;
	}

	@Override
	public String getName() {
		return super.getUsername();
	}

	

	@Override
	public String toString() {
		return "{username:" + getUsername() + ", password: [PROTECTED], enabled:" + isEnabled()
				+ ", accountNonExpired:" + isAccountNonExpired() + ", accountNonLocked:" + isAccountNonLocked()
				+ ", credentialsNonExpired:" + isCredentialsNonExpired() + ", Tasks:" + Tasks + ", role:" + roles
				+ "}";
	}

	@Override
	public boolean hasRole(String role,ArrayList<String>userRole){

		// for(roles :)

		if(userRole.contains(role) ){
			return true;
		}
		else{
			return false;
		}
	}
	
	public Boolean hasNewRole(){
		return this.hasNewRole;
	}
	

}
