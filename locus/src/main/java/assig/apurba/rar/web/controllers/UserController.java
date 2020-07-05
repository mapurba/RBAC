/* USER CONTROLLER.java
 *   by Anonymous
 *
 * Created:
 *   7/5/2020, 2:22:21 PM
 * Last edited:
 *   7/5/2020, 2:22:21 PM
 * Auto updated?
 *   Yes
 *
 * Description:
 *   Apurba Mondal
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
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import assig.apurba.rar.web.model.UserHelper;
import assig.apurba.rar.dao.Tasks;
import assig.apurba.rar.dao.Users;
// import assig.apurba.rar.security.policy.RoleSecurityContextPolicy;
import assig.apurba.rar.security.spring.*;
import assig.apurba.rar.web.model.TasksUser;
import assig.apurba.rar.dao.Roles;
import assig.apurba.rar.web.services.TasksService;
import assig.apurba.rar.web.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private ContextAwarePolicyEnforcement policy;
	

	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = {"application/json"})
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasPermission(null,'USER_LIST')")
	public List<TasksUser> listUsers() {
		logger.info("[UserList] started ...");
		final List<TasksUser> result = userService.getUsers();
		logger.info("[UserList] done, result: {} Users", result == null ? null : result.size());
		return result;

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = { "application/json" })
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasPermission(null,'USER_DELETE')")
	public Boolean deleteUsers(@PathVariable final String id) {
		logger.info("[UserDelete] started ...");
		final Boolean result = userService.deleteUserByName(id);
		logger.info("[UserDelete] done, result: {} Users", result == null? null : true);
        return result;
        

	}
	

	
    
	
}
