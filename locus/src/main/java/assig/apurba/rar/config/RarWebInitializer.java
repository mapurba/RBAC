/* RAR WEB INITIALIZER.java
 *   by Anonymous
 *
 * Created:
 *   7/5/2020, 2:22:35 PM
 * Last edited:
 *   7/5/2020, 2:22:35 PM
 * Auto updated?
 *   Yes
 *
 * Description:
 *   Apurba Mondal
**/

package assig.apurba.rar.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class RarWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[]{RootConfiguration.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[]{"/"};
	}
}