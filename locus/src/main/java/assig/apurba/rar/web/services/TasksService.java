package assig.apurba.rar.web.services;

import java.util.List;

import assig.apurba.rar.dao.Tasks;


public interface TasksService {
	public List<Tasks> getTaskss();
	public Tasks getTasks(Integer id);
	public void createTasks(Tasks Tasks);
	public void updateTasks(Tasks Tasks);
	public void deleteTasks(Tasks Tasks);
}
