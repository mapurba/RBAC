/* BASIC POLICY ENFORCEMENT.java
 *   by Anonymous
 *
 * Created:
 *   7/5/2020, 2:23:19 PM
 * Last edited:
 *   7/5/2020, 2:23:19 PM
 * Auto updated?
 *   Yes
 *
 * Description:
 *   Apurba Mondal
**/

package assig.apurba.rar.security.policy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.expression.EvaluationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class BasicPolicyEnforcement implements PolicyEnforcement {
	private static final Logger logger = LoggerFactory.getLogger(BasicPolicyEnforcement.class);
	
	@Autowired
	@Qualifier("jsonFilePolicyDefinition")
	private PolicyDefinition policyDefinition;
	
	
	@Override
	public boolean check(Object subject, Object resource, Object action, Object environment) {
		//Get all policy rules
		List<PolicyRule> allRules = policyDefinition.getAllPolicyRules();
		// logger.info("test 1.{}",subject.toString());

		// get authentication object
		// Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		// List<String> userRoles = hasRole(auth);

		//Wrap the context
		SecurityAccessContext cxt = new SecurityAccessContext(subject, resource, action, environment);
		//Filter the rules according to context.
		List<PolicyRule> matchedRules = filterRules(allRules, cxt);
		//finally, check if any of the rules are satisfied, otherwise return false.
		return checkRules(matchedRules, cxt);
		// return true;
	}
	
	private List<PolicyRule> filterRules(List<PolicyRule> allRules, SecurityAccessContext cxt) {
		List<PolicyRule> matchedRules = new ArrayList<>();
		Integer counter =0;
		for(PolicyRule rule : allRules) {
			try {
				// logger.info("test 1.{}",counter);

				if(rule.getTarget().getValue(cxt, Boolean.class)) {
					matchedRules.add(rule);
				}
			} catch(EvaluationException ex) {
				logger.error("An error occurred while evaluating PolicyRule Filter.", ex);
			}
		}
		return matchedRules;
	}



	  
	private boolean checkRules(List<PolicyRule> matchedRules, SecurityAccessContext cxt) {
		for(PolicyRule rule : matchedRules) {
			try {
				if(rule.getCondition().getValue(cxt, Boolean.class)) {
					return true;
				}
			} catch(EvaluationException ex) {
				logger.error("An error occurred while evaluating PolicyRule check error check rules.", ex);
			}
		}
		return false;
	}
}


