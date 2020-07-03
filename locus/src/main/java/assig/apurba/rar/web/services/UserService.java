package assig.apurba.rar.web.services;

import java.util.List;

import assig.apurba.rar.web.model.ProjectUser;

public interface UserService {
	ProjectUser findUserByName(String name);
	List<ProjectUser> findUserByProject(Integer projectId);
}
