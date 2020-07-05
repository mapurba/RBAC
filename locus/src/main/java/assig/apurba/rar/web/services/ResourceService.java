package assig.apurba.rar.web.services;

import java.util.List;

import assig.apurba.rar.dao.Resource;
import assig.apurba.rar.dao.Tasks;

public interface ResourceService {
	public List<Resource> getResources(Tasks Tasks);
	public Resource getResource(Integer id);
	public void createResource(Resource Resource);
	public void updateResource(Resource Resource);
	public void deleteResource(Resource Resource);
}
