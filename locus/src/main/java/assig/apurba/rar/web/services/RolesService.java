package assig.apurba.rar.web.services;

import java.util.List;

import assig.apurba.rar.dao.*;

public interface RolesService {
    public List<Roles> getRoles();
	public Roles getRole(Long id);
	public void createRoles(Roles Tasks);
	public void updateRoles(Roles Tasks);
	public void deleteRoles(Roles Tasks);
}