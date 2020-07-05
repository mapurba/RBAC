package assig.apurba.rar.web.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import assig.apurba.rar.dao.Policy;
import assig.apurba.rar.dao.Roles;
import assig.apurba.rar.web.services.PolicyService;

@Component
public class InMemoryPolicyService implements PolicyService {
    
    private static final Logger logger = LoggerFactory.getLogger(InMemoryPolicyService.class);
	
	private final HashMap<Long, Policy> policyById = new HashMap<>();
	private final InMemorySequence seq = new InMemorySequence();
	
	@Override
	public List<Policy> getPolicies() {
		ArrayList<Policy> result = new ArrayList<>(policyById.size());
		for(Policy policy : policyById.values()) {
			result.add(policy);
		}
		return result;
	}

	@Override
	public Policy getPolicy(Long id) {
		if(id == null)
			return null;
		return policyById.get(id);
	}

	@Override
	public void createPolicy(Policy role) {
		if(role == null)
			return;
        Long newId = Long.valueOf(seq.increment());
		role.setId(newId);
		policyById.put(newId, role);
	}

	@Override
	public void updatePolicy(final Policy role) {
		Policy currentRole = getPolicy(role.getId());
		if(currentRole == null)
			return;
        currentRole.setName(role.getName());
        currentRole.setDescription(role.getDescription());
	}

	@Override
	public void deletePolicy(final Policy role) {
		policyById.remove(role.getId());
	}
    
}