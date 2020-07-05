/* POLICY ENFORCEMENT.java
 *   by Anonymous
 *
 * Created:
 *   7/5/2020, 2:23:27 PM
 * Last edited:
 *   7/5/2020, 2:23:27 PM
 * Auto updated?
 *   Yes
 *
 * Description:
 *   Apurba Mondal
**/

package assig.apurba.rar.security.policy;

import java.util.List;

public interface PolicyEnforcement {

	boolean check(Object subject, Object resource, Object action, Object environment);

}