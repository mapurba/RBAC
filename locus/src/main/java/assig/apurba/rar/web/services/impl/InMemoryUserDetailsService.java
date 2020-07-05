package assig.apurba.rar.web.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import assig.apurba.rar.dao.*;
import assig.apurba.rar.web.model.TasksUser;
import assig.apurba.rar.dao.Roles;
import assig.apurba.rar.web.services.UserService;

@Component
public class InMemoryUserDetailsService implements UserDetailsService, UserService {
	private Map<String, Users> users = new HashMap<>();
	// private List<Roles> roles;
	// private Map<Long, Policy> policies = new HashMap<>();

	public InMemoryUserDetailsService() {
	}

	@PostConstruct
	private void init() {
		this.users = new HashMap<>();
		// this.roles = new ArrayList<Roles>();
		// this.policies = new HashMap<>();

		// Policy dummyPolicies = new Policy("PM view his Tasks", "PM read the details of his Tasks only",
		// 		"subject.hasRole('PM',subject.role) && action == 'TasksS_VIEW'", "subject.Tasks.id == resource.id");
		// this.policies.put(dummyPolicies.getId(), dummyPolicies);
		
		// Roles dummyRole= new Roles("DEV", "Developer", new ArrayList(this.policies.values()));

		// this.roles.add(dummyRole);

		List<Roles> multipleRole = new ArrayList<Roles>();
		multipleRole.add(Roles.DEVELOPER);
		multipleRole.add(Roles.TESTER);

		users.put("admin", new Users("admin", "password", Roles.ADMIN));
		users.put("pm1", new Users("pm1", "password", multipleRole));
		users.put("pm2", new Users("pm2", "password", Roles.PM));
		users.put("dev1", new Users("dev1", "password", multipleRole));
		users.put("dev2", new Users("dev2", "password", Roles.DEVELOPER));
		users.put("test1", new Users("test1", "password", Roles.TESTER));
		users.put("test2", new Users("test2", "password", Roles.TESTER));
	}

	public void createUser(Users user) {
		Assert.isTrue(!userExists(user.getUsername()));

		users.put(user.getUsername().toLowerCase(), user);
	}

	public boolean userExists(String username) {
		return users.containsKey(username.toLowerCase());
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = users.get(username.toLowerCase());

		if (user == null) {
			throw new UsernameNotFoundException(username);
		}

		return new Users(user.getUsername(), user.getPassword(), user.getTasks(),
				user.getRoles().get(0));
	}

	@Override
	public TasksUser findUserByName(String name) {
		return users.get(name.toLowerCase());
	}

	@Override
	public List<TasksUser> findUserByTasks(Integer taskId) {
		//search for taskid
		if (taskId == null)
			return null;
		List<TasksUser> result = new LinkedList<>();
		for (Users user : users.values()) {
			Tasks task = user.getTasks();
			if (task != null && taskId.equals(task.getId())) {
				result.add(user);
			}
		}
		return result;
	}

	public List<TasksUser> getUsers() {
		return new ArrayList<TasksUser>(users.values());
	}

	@Override
	public Boolean deleteUserByName(String username) {
		Boolean userExists = users.containsKey(username.toLowerCase());
		if (userExists) {
			users.remove(username.toLowerCase());
			return true;
		}
		return false;

	}
	
	

	




}
