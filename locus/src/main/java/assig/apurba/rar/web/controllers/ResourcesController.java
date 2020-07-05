/* RESOURCES CONTROLLER.java
 *   by Anonymous
 *
 * Created:
 *   7/5/2020, 2:22:08 PM
 * Last edited:
 *   7/5/2020, 2:22:08 PM
 * Auto updated?
 *   Yes
 *
 * Description:
 *   Apurba Mondal
**/

/* RESOURCES CONTROLLER.java
 *   by Apurba Mondal
 *
 * Created:
 *   7/5/2020, 2:19:41 PM
 * Last edited:
 *   7/5/2020, 2:19:41 PM
 * Auto updated?
 *   Yes
 *
 * Description:
 *   .*
**/

package assig.apurba.rar.web.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import assig.apurba.rar.dao.Resource;
import assig.apurba.rar.web.model.ResourceStatus;
import assig.apurba.rar.dao.Tasks;
import assig.apurba.rar.security.spring.ContextAwarePolicyEnforcement;
import assig.apurba.rar.web.model.TasksUser;
import assig.apurba.rar.web.services.ResourceService;
import assig.apurba.rar.web.services.TasksService;
import assig.apurba.rar.web.services.UserService;

@RestController
@RequestMapping("/task/{TasksId}/resources")
public class ResourcesController {
private static final Logger logger = LoggerFactory.getLogger(ResourcesController.class);
	
	@Autowired
	private ResourceService ResourceService;
	
	@Autowired
	private TasksService TasksService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ContextAwarePolicyEnforcement policy;
	
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = {"application/json"})
	@ResponseStatus(HttpStatus.OK)
	public List<Resource> listResources(@PathVariable Integer TasksId) {
		logger.info("[listResources({})] started ...", TasksId);
		Tasks Tasks = TasksService.getTasks(TasksId);
		if(Tasks == null) {
			logger.info("[listResources({})] ignored, non-existing Tasks.", TasksId);
			return null;
		}

		// Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		// logger.info("object object objectobject apurba ",auth.getName().toString());

		policy.checkPermission(Tasks, "ResourceS_LIST");
		List<Resource> result = ResourceService.getResources(new Tasks(TasksId));
		logger.info("[listResources({})] done, result: {} Resources.", TasksId, result == null? null : result.size());
		return result;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
	@ResponseStatus(HttpStatus.OK)
	public void createResource(@PathVariable Integer TasksId, @RequestBody Resource Resource) {
		logger.info("[createResource({}, {})] started ...", TasksId, Resource);
		if(Resource == null) {
			logger.info("[createResource({}, {})] ignored, empty Resource.", TasksId, Resource);
			return;
		}
		Tasks existingTasks = TasksService.getTasks(TasksId);
		if(existingTasks == null) {
			logger.info("[createResource({}, {})] ignored, non-existing Tasks.", TasksId, Resource);
			return;
		}
		Resource.setTasks(existingTasks);
		
		policy.checkPermission(Resource, "ResourceS_CREATE");
		
		ResourceService.createResource(Resource);
		logger.info("[createResource({}, {})] done.", TasksId, Resource);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = {"application/json"}, produces = {"application/json"})
	@ResponseStatus(HttpStatus.OK)
	public void updateResource(@PathVariable Integer TasksId, @PathVariable Integer id, @RequestBody Resource Resource) {
		logger.info("[updateResource({}, {}, {})] started ...", TasksId, id, Resource);
		
		Tasks existingTasks = TasksService.getTasks(TasksId);
		if(existingTasks == null) {
			logger.info("[updateResource({}, {}, {})] ignored, non-existing Tasks.", TasksId, id, Resource);
			return;
		}
		
		Resource currentResource = ResourceService.getResource(id);
		if(currentResource == null) {
			logger.info("[updateResource({}, {}, {})] ignored, non-existing Resource.", TasksId, id, Resource);
			return;
		}
		
		policy.checkPermission(currentResource, "ResourceS_UPDATE");
		
		Resource.setId(id);
		Resource.setTasks(existingTasks);
		ResourceService.updateResource(Resource);
		logger.info("[updateResource({}, {}, {})] done.", TasksId, id, Resource);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = {"application/json"})
	@ResponseStatus(HttpStatus.OK)
	public void deleteResource(@PathVariable Integer id) {
		logger.info("[deleteResource({})] started ...", id);
		Resource currentResource = ResourceService.getResource(id);
		if(currentResource == null) {
			logger.info("[deleteResource({})] ignored, non-existing Resource.", id);
			return;
		}
		
		policy.checkPermission(currentResource, "ResourceS_DELTE");
		
		ResourceService.deleteResource(new Resource(id));
		logger.info("[deleteResource({})] done.", id);
	}
	
	@RequestMapping(value = "/{id}/assignee", method = RequestMethod.PUT, consumes = {"text/plain"}, produces = {"application/json"})
	@ResponseStatus(HttpStatus.OK)
	public void updateResourceAssignee(@PathVariable Integer TasksId, @PathVariable Integer id, @RequestBody String assigneeName) {
		logger.info("[updateResourceAssignee({}, {}, {})] started ...", TasksId, id, assigneeName);
		Resource currentResource = ResourceService.getResource(id);
		if(currentResource == null) {
			logger.info("[updateResourceAssignee({}, {}, {})] ignored, non-existing Resource.", TasksId, id, assigneeName);
			return;
		}
		
		policy.checkPermission(currentResource, "ResourceS_ASSIGN");
		
		TasksUser assignee = userService.findUserByName(assigneeName);
		if(assignee == null) {
			logger.info("[updateResourceAssignee({}, {}, {})] ignored, non-existing user.", TasksId, id, assigneeName);
			return;
		}
		
		currentResource.setAssignedTo(assigneeName);
		currentResource.setStatus(ResourceStatus.ASSIGNED);
		logger.info("[updateResourceAssignee({}, {}, {})] done.", TasksId, id, assigneeName);
	}
	
	@RequestMapping(value = "/{id}/status", method = RequestMethod.PUT, consumes = {"text/plain"}, produces = {"application/json"})
	@ResponseStatus(HttpStatus.OK)
	public void updateResourceStatus(@PathVariable Integer TasksId, @PathVariable Integer id, @RequestBody String newStatusStr) {
		logger.info("[updateResourceStatus({}, {}, {})] started ...", TasksId, id, newStatusStr);
		
		ResourceStatus newStatus = null;
		try {
			newStatus = ResourceStatus.valueOf(newStatusStr);
		} catch(IllegalArgumentException ex) {
			logger.info("[updateResourceStatus({}, {}, {})] ignored, unrecognized status.", TasksId, id, newStatusStr);
			return;
		}
		Resource currentResource = ResourceService.getResource(id);
		if(currentResource == null) {
			logger.info("[updateResourceStatus({}, {}, {})] ignored, non-existing Resource.", TasksId, id, newStatusStr);
			return;
		}
		
		if(newStatus == ResourceStatus.BLOCKED) {
			policy.checkPermission(currentResource, "ResourceS_STATUS_CLOSE");
		} else {
			policy.checkPermission(currentResource, "ResourceS_UPDATE");
		}
		currentResource.setStatus(newStatus);
		logger.info("[updateResourceStatus({}, {}, {})] done.", TasksId, id, newStatusStr);
	}

}
