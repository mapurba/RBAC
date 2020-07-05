/* RESOURCE.java
 *   by Apurba Mondal
 *
 * Created:
 *   7/5/2020, 2:21:27 PM
 * Last edited:
 *   7/5/2020, 2:21:27 PM
 * Auto updated?
 *   Yes
 *
 * Description:
 *   <Todo>
**/

package assig.apurba.rar.dao;

import assig.apurba.rar.web.model.ResourceStatus;
import assig.apurba.rar.web.model.ResourceType;

public class Resource {
	private Integer id;

	private Tasks Tasks;
	private ResourceType type;
	private String name;
	private String description;
	
	private String createdBy;
	private String assignedTo;
	private ResourceStatus status;
	
	public Resource() {
		super();
	}
	
	public Resource(Integer id) {
		super();
		this.id = id;
	}

	public Resource(Integer id, Tasks Tasks, ResourceType type, String name, String description, String createdBy,
			String assignedTo, ResourceStatus status) {
		super();
		this.id = id;
		this.Tasks = Tasks;
		this.type = type;
		this.name = name;
		this.description = description;
		this.createdBy = createdBy;
		this.assignedTo = assignedTo;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Tasks getTasks() {
		return Tasks;
	}
	public void setTasks(Tasks Tasks) {
		this.Tasks = Tasks;
	}
	public ResourceType getType() {
		return type;
	}
	public void setType(ResourceType type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getAssignedTo() {
		return assignedTo;
	}
	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}
	public ResourceStatus getStatus() {
		return status;
	}
	public void setStatus(ResourceStatus status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "{id:" + id + ", Tasks:" + Tasks + ", type:" + type + ", name:" + name + ", description:"
				+ description + ", createdBy:" + createdBy + ", assignedTo:" + assignedTo + ", status:" + status + "}";
	}	
}
