package assig.apurba.rar.web.services;

import java.util.List;

import assig.apurba.rar.dao.Policy;

public interface PolicyService {
    
    public List<Policy> getPolicies();
	public Policy getPolicy(Long id);
	public void createPolicy(Policy policy);
	public void updatePolicy(Policy policy);
	public void deletePolicy(Policy policy);
}