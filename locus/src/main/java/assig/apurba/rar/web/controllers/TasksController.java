/* TASKS CONTROLLER.java
 *   by Anonymous
 *
 * Created:
 *   7/5/2020, 2:22:12 PM
 * Last edited:
 *   7/5/2020, 2:22:12 PM
 * Auto updated?
 *   Yes
 *
 * Description:
 *   Apurba Mondal
**/

/* TASKS CONTROLLER.java
 *   by Apurba Mondal
 *
 * Created:
 *   7/5/2020, 2:20:23 PM
 * Last edited:
 *   7/5/2020, 2:20:23 PM
 * Auto updated?
 *   Yes
 *
 * Description:
 *   <Todo>
**/

package assig.apurba.rar.web.controllers;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import assig.apurba.rar.dao.Tasks;
import assig.apurba.rar.security.spring.*;
import assig.apurba.rar.web.model.UserHelper;
import assig.apurba.rar.web.model.TasksUser;
import assig.apurba.rar.dao.Roles;
import assig.apurba.rar.web.services.TasksService;
import assig.apurba.rar.web.services.UserService;

@RestController
@RequestMapping("/task")
public class TasksController {
	private static final Logger logger = LoggerFactory.getLogger(TasksController.class);

	@Autowired
	private ContextAwarePolicyEnforcement policy;
	
	@Autowired
	private TasksService TaskssService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = {"application/json"})
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasPermission(null,'LIST')")
	public List<Tasks> listTaskss() {
		logger.info("[ListTaskss] started ...");
		List<Tasks> result = TaskssService.getTaskss();
		logger.info("[ListTaskss] done, result: {} Taskss", result == null? null : result.size());
		return result;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {"application/json"})
	@ResponseStatus(HttpStatus.OK)
	@PostAuthorize("hasPermission(returnObject,'VIEW')")
	public Tasks getTasks(@PathVariable Integer id) {
		logger.info("[getTasks({})] started ...", id);
		Tasks result = TaskssService.getTasks(id);
		logger.info("[getTasks({})] done, result: {}", id, result);
		return result;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes={"application/json"}, produces = {"application/json"})
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasPermission(null,'LIST')")
	public void createTasks(@RequestBody Tasks Tasks) {

		logger.info("[createTasks({})] started ...", Tasks);
		TaskssService.createTasks(Tasks);
		logger.info("[createTasks({})] done.", Tasks);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes={"application/json"}, produces = {"application/json"})
	@ResponseStatus(HttpStatus.OK)
	public void updateTasks(@PathVariable Integer id, @RequestBody Tasks Tasks) {
		logger.info("[updateTasks({}, {})] started ...", id, Tasks);
		if(Tasks == null) {
			logger.info("[updateTasks({}, {})] ignored, empty Tasks", id, Tasks);
			return;
		}
		
		Tasks existingTasks = TaskssService.getTasks(id);
		if(existingTasks == null) {
			logger.info("[updateTasks({}, {})] ignored, non-exiting Tasks", id, Tasks);
			return;
		}
			
		
		policy.checkPermission(existingTasks, "UPDATE");
		
		TaskssService.updateTasks(Tasks);
		logger.info("[updateTasks({}, {})] done.", id, Tasks);
	}
	

	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = {"application/json"})
	@ResponseStatus(HttpStatus.OK)
	public void deleteTasks(@PathVariable Integer id) {
		logger.info("[deleteTasks({})] started ...", id);
		Tasks existingTasks = TaskssService.getTasks(id);
		if(existingTasks == null) {
			logger.info("[deleteTasks({})] ingnored, non existing Tasks.", id);
			return;
		}
		
		policy.checkPermission(existingTasks, "DELETE");
		
		TaskssService.deleteTasks(existingTasks);
		logger.info("[deleteTasks({})] done.", id);
	}
	
	@RequestMapping(value = "/{id}/pm/", method = RequestMethod.PUT, consumes= {"text/plain"} , produces = {"application/json"})
	@ResponseStatus(HttpStatus.OK)
	public void updateTasksManager(@PathVariable Integer id, @RequestBody String newManagerName) {
		logger.info("[updateTasksManager({}, {})] started ...", id, newManagerName);
		Tasks existingTasks = TaskssService.getTasks(id);
		if(existingTasks == null) {
			logger.info("[updateTasksManager({}, {})] ingnored, non-existing Tasks.", id, newManagerName);
			return;
		}
		
		policy.checkPermission(existingTasks, "TASK_PM_UPDATE");
		
		TasksUser user = userService.findUserByName(newManagerName);
		if(user == null) {
			logger.info("[updateTasksManager({}, {})] ingnored, non-existing user.", id, newManagerName);
			return;
		}
		user.setTasks(existingTasks);
		//only provide role if this end point is used
		user.setRole(Roles.PM);
		logger.info("[updateTasksManager({}, {})] done.", id, newManagerName);
	}
	
	@RequestMapping(value = "/{id}/users/", method = RequestMethod.GET, produces = {"application/json"})
	@ResponseStatus(HttpStatus.OK)
	public List<UserHelper> listTasksUsers(@PathVariable Integer id) {
		logger.info("[listTasksUsers({})] started ...", id);
		Tasks existingTasks = TaskssService.getTasks(id);
		if(existingTasks == null) {
			logger.info("[listTasksUsers({})] ignored, non-existing Tasks.", id);
			return null;
		}
		
		policy.checkPermission(existingTasks, "LIST");
		
		
		List<UserHelper> result = new LinkedList<>();
		List<TasksUser> existingUsers = userService.findUserByTasks(id);
		if(existingUsers != null) {
			for(TasksUser user : existingUsers) {
				result.add(new UserHelper(user.getName(), user.getRoles()));
			}
		}
		logger.info("[listTasksUsers({})] done, result: {} users.", id, result.size());
		return result;
	}
	
	@RequestMapping(value = "/{id}/users/", method = RequestMethod.POST, consumes= {"application/json"}, produces = {"application/json"})
	@ResponseStatus(HttpStatus.OK)
	public void addTasksUser(@PathVariable Integer id, @RequestBody UserHelper user) {
		logger.info("[addTasksUser({}, {})] started ...", id, user);

		Tasks existingTasks = TaskssService.getTasks(id);
		if(existingTasks == null) {
			logger.info("[addTasksUser({}, {})] ignored, non-existing Tasks.", id, user);
			return;
		}
		logger.info("adding Tasks to user : {} and Tasks id {}", id, user);

		policy.checkPermission(existingTasks, "TASKS_USERS_ADD");
		
		String userName = user.getName();
		if(userName == null || userName.isEmpty()) {
			logger.info("[addTasksUser({}, {})] ignored, empty user name.", id, user);
			return;
		}
		
		List<Roles> userRole = user.getRoles();
		if(userRole == null) {
			logger.info("[addTasksUser({}, {})] ignored, empty user role.", id, user);
			return;
		}
		
		TasksUser existingUser = userService.findUserByName(userName);
		if(existingUser == null) {
			logger.info("[addTasksUser({}, {})] ignored, non-existing user.", id, user);
			return;
		}
		
		existingUser.setTasks(existingTasks);
		// existingUser.setRoles(userRole);
		logger.info("[addTasksUser({}, {})] done.", id, user);
	}
	
	@RequestMapping(value = "/{id}/users/{userName}", method = RequestMethod.DELETE, produces = {"application/json"})
	@ResponseStatus(HttpStatus.OK)
	public void removeTasksUser(@PathVariable Integer id, @PathVariable String userName) {
		logger.info("[removeTasksUser({}, {})] started ...", id, userName);
		Tasks existingTasks = TaskssService.getTasks(id);
		if(existingTasks == null) {
			logger.info("[removeTasksUser({}, {})] ignored, non-existing Tasks.", id, userName);
			return;
		}
		
		policy.checkPermission(existingTasks, "REMOVE");
		
		if(userName == null || userName.isEmpty()) {
			logger.info("[removeTasksUser({}, {})] ignored, empty user name.", id, userName);
			return;
		}
		
		TasksUser existingUser = userService.findUserByName(userName);
		if(existingUser == null) {
			logger.info("[removeTasksUser({}, {})] ignored, non-existing user.", id, userName);
			return;
		}
		
		existingUser.setTasks(null);
		existingUser.setRoles(null);
		logger.info("[removeTasksUser({}, {})] done.", id, userName);
	}
	
}
