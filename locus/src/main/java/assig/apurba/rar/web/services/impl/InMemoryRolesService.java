// package assig.apurba.rar.web.services.impl;

// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.List;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.stereotype.Component;

// import assig.apurba.rar.dao.Roles;
// import assig.apurba.rar.web.services.RolesService;

// @Component
// public class InMemoryRolesService implements RolesService{

//     private static final Logger logger = LoggerFactory.getLogger(InMemoryRolesService.class);
	
// 	private final HashMap<Long, Roles> rolesById = new HashMap<>();
// 	private final InMemorySequence seq = new InMemorySequence();
	
// 	@Override
// 	public List<Roles> getRoles() {
// 		ArrayList<Roles> result = new ArrayList<>(rolesById.size());
// 		for(Roles roles : rolesById.values()) {
// 			result.add(roles);
// 		}
// 		return result;
// 	}

// 	@Override
// 	public Roles getRole(Long id) {
// 		if(id == null)
// 			return null;
// 		return rolesById.get(id);
// 	}

// 	@Override
// 	public void createRoles(Roles role) {
// 		if(role == null)
// 			return;
//         Long newId = Long.valueOf(seq.increment());
// 		role.setId(newId);
// 		rolesById.put(newId, role);
// 	}

// 	@Override
// 	public void updateRoles(final Roles role) {
// 		Roles currentRole = getRole(role.getId());
// 		if(currentRole == null)
// 			return;
//         currentRole.setName(role.getName());
//         currentRole.setDescription(role.getDescription());
// 	}

// 	@Override
// 	public void deleteRoles(final Roles role) {
// 		rolesById.remove(role.getId());
// 	}
    
// }