package assig.apurba.rar.security.spring;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import assig.apurba.rar.security.policy.PolicyEnforcement;

@Component
public class ContextAwarePolicyEnforcement {
	@Autowired
	protected PolicyEnforcement policy;
	
	public void checkPermission(Object resource, String permission) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		Map<String, Object> environment = new HashMap<>();
		
		/*
		Object authDetails = auth.getDetails();
		if(authDetails != null) {
			if(authDetails instanceof WebAuthenticationDetails) {
				environment.put("remoteAddress", ((WebAuthenticationDetails) authDetails).getRemoteAddress());
			}
		}
		*/
		environment.put("time", new Date());
		
		if(!policy.check(auth.getPrincipal(), resource, permission, environment))
			throw new AccessDeniedException("Access is denied");
	}
}
