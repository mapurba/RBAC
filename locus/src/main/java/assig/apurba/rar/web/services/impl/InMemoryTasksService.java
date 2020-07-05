package assig.apurba.rar.web.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import assig.apurba.rar.dao.Tasks;
import assig.apurba.rar.web.services.TasksService;

@Component
public class InMemoryTasksService implements TasksService {
	private static final Logger logger = LoggerFactory.getLogger(InMemoryTasksService.class);
	
	private final HashMap<Integer, Tasks> TaskssById = new HashMap<>();
	private final InMemorySequence seq = new InMemorySequence();
	
	@Override
	public List<Tasks> getTaskss() {
		ArrayList<Tasks> result = new ArrayList<>(TaskssById.size());
		for(Tasks Tasks : TaskssById.values()) {
			result.add(Tasks);
		}
		return result;
	}

	@Override
	public Tasks getTasks(Integer id) {
		if(id == null)
			return null;
		return TaskssById.get(id);
	}

	@Override
	public void createTasks(Tasks Tasks) {
		if(Tasks == null)
			return;
		Integer newId = seq.increment();
		Tasks.setId(newId);
		TaskssById.put(newId, Tasks);
	}

	@Override
	public void updateTasks(final Tasks Tasks) {
		Tasks currentTasks = getTasks(Tasks.getId());
		if(currentTasks == null)
			return;
		currentTasks.setName(Tasks.getName());
		currentTasks.setDescription(Tasks.getDescription());
	}

	@Override
	public void deleteTasks(final Tasks Tasks) {
		TaskssById.remove(Tasks.getId());
	}
}
