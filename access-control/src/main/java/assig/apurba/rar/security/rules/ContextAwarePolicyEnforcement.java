package assig.apurba.rar.security.rules;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import assig.apurba.rar.security.policy.PolicyEnforcement;

@Component
public class ContextAwarePolicyEnforcement {
	private static final Logger logger = LoggerFactory.getLogger(ContextAwarePolicyEnforcement.class);

	@Autowired
	protected PolicyEnforcement policy;
	
	public void checkPermission(Object resource, String permission) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		Map<String, Object> environment = new HashMap<>();
		

		// logger.info("booboo",auth.toString());
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
