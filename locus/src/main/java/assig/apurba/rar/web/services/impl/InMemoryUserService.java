// package assig.apurba.rar.web.services.impl;

// import java.util.HashMap;
// import java.util.List;

// import assig.apurba.rar.web.model.TasksUser;
// import assig.apurba.rar.web.services.UserService;
// import org.springframework.stereotype.Component;


// @Component
// public class InMemoryUserService  {
//     // private final HashMap<Integer, List<TasksUser>> usersList = new HashMap<>();
//     // private final InMemoryUserDetailsService users = new InMemoryUserDetailsService();
    
//     @Override
// 	public List<TasksUser> getUsers() {
// 		return new ArrayList<TasksUser>(users.values());
// 	}

// 	@Override
// 	public Boolean deleteUserByName(String username) {
// 		Boolean userExists = users.containsKey(username.toLowerCase());
// 		if (userExists) {
// 			users.remove(username.toLowerCase());
// 			return true;
// 		}
// 		return false;

// 	}

// }