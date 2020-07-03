package assig.apurba.rar.web.services;

import java.util.List;

import assig.apurba.rar.web.model.Project;

public interface ProjectService {
	public List<Project> getProjects();
	public Project getProject(Integer id);
	public void createProject(Project project);
	public void updateProject(Project project);
	public void deleteProject(Project project);
}
