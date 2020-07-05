package assig.apurba.rar.web.model;

import java.util.ArrayList;
import java.util.List;

import assig.apurba.rar.dao.Tasks;
import assig.apurba.rar.dao.Roles;

public interface TasksUser {
	public String getName();

	public Tasks getTasks();

	public void setTasks(Tasks Tasks);

	public List<Roles>  getRoles();

	public List<Roles>  getRole();


	public void setRole(Roles role);

	public void setRoles(List<Roles> roles);

	public boolean hasRole(String role,ArrayList<String> userRole);


	

}
