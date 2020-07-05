/* METHOD SECURITY CONFIG.java
 *   by Anonymous
 *
 * Created:
 *   7/5/2020, 2:22:43 PM
 * Last edited:
 *   7/5/2020, 2:22:43 PM
 * Auto updated?
 *   Yes
 *
 * Description:
 *   Apurba Mondal
**/

// package assig.apurba.rar.config;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
// import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
// import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
// import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

// import assig.apurba.rar.security.spring.RolesPolicy;

// @EnableGlobalMethodSecurity(prePostEnabled = true)
// public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {
// 	@Autowired
// 	RolesPolicy permissionEvaluator;
	
// 	@Override
// 	protected MethodSecurityExpressionHandler createExpressionHandler() {
// 		DefaultMethodSecurityExpressionHandler result = new DefaultMethodSecurityExpressionHandler();
// 		result.setPermissionEvaluator(permissionEvaluator);
// 		return result;
// 	}
	
// }