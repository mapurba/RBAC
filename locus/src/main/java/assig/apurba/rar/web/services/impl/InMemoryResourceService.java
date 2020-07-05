package assig.apurba.rar.web.services.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import assig.apurba.rar.dao.Resource;
import assig.apurba.rar.dao.Tasks;
import assig.apurba.rar.web.model.ResourceStatus;
import assig.apurba.rar.web.services.ResourceService;


@Component
public class InMemoryResourceService implements ResourceService {
	private final HashMap<Integer, List<Resource>> ResourcesByTasksId = new HashMap<>();
	private final HashMap<Integer, Resource> ResourcesById = new HashMap<>();
	private final InMemorySequence seq = new InMemorySequence();

	@Override
	public List<Resource> getResources(Tasks Tasks) {
		if(Tasks == null)
			return null;
		Integer TasksId = Tasks.getId();
		return ResourcesByTasksId.get(TasksId);
	}

	@Override
	public Resource getResource(Integer ResourceId) {
		if(ResourceId == null)
			return null;
		return ResourcesById.get(ResourceId);
	}

	@Override
	public void createResource(Resource Resource) {
		if(Resource == null)
			return;
		Integer newId = seq.increment();
		Tasks Tasks = Resource.getTasks();
		if(Tasks == null)
			return;
		Integer TasksId = Tasks.getId();
		if(TasksId == null)
			return;
		Resource.setId(newId);
		Resource.setStatus(ResourceStatus.NEW);
		ResourcesById.put(newId, Resource);
		addToTasks(TasksId, Resource);
	}

	@Override
	public void updateResource(Resource Resource) {
		if(Resource == null)
			return;
		Integer id = Resource.getId();
		if(id == null)
			return;
		Tasks newTasks = Resource.getTasks();
		if(newTasks == null)
			return;
		Integer newTasksId = newTasks.getId();
		if(newTasksId == null)
			return;
		Resource existingResource = ResourcesById.get(id);
		Integer oldTasksId = existingResource.getTasks().getId();
		
		copyResource(Resource, existingResource);
		
		if(!newTasksId.equals(oldTasksId)) {
			addToTasks(newTasksId, existingResource);
			removeFromTasks(oldTasksId, existingResource);
		}	
	}

	@Override
	public void deleteResource(Resource Resource) {
		Integer id = Resource.getId();
		if(id == null)
			return;
		Resource existingResource = ResourcesById.get(Resource.getId());
		if(existingResource == null)
			return;
		
		Integer TasksId = existingResource.getTasks().getId();
		if(TasksId == null)
			return;
		
		ResourcesById.remove(id);
		removeFromTasks(TasksId, existingResource);
	}
	
	private void addToTasks(Integer TasksId, Resource Resource) {
		List<Resource> TasksResources = ResourcesByTasksId.get(TasksId);
		if(TasksResources == null) {
			TasksResources = new LinkedList<>();
			ResourcesByTasksId.put(TasksId, TasksResources);
		} 
		TasksResources.add(Resource);
	}
	
	private void removeFromTasks(Integer TasksId, Resource Resource) {
		List<Resource> TasksResources = ResourcesByTasksId.get(TasksId);
		if(TasksResources == null) 
			return;
		TasksResources.remove(Resource);	
	}
	
	private Resource copyResource(Resource source, Resource target) {
		target.setName(source.getName());
		target.setDescription(source.getDescription());
		target.setTasks(source.getTasks());
		target.setType(source.getType());
		target.setStatus(source.getStatus());
		target.setCreatedBy(source.getCreatedBy());
		target.setAssignedTo(source.getAssignedTo());
		return target;
	}

}
