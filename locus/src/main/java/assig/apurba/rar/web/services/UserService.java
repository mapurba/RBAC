package assig.apurba.rar.web.services;

import java.util.List;


import assig.apurba.rar.web.model.TasksUser;

public interface UserService {
	TasksUser findUserByName(String name);
	List<TasksUser> findUserByTasks(Integer TasksId);
	List<TasksUser> getUsers();
	Boolean deleteUserByName(String userId);


}
