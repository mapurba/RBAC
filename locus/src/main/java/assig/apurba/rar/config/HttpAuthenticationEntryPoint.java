/* HTTP AUTHENTICATION ENTRY POINT.java
 *   by Anonymous
 *
 * Created:
 *   7/5/2020, 2:22:48 PM
 * Last edited:
 *   7/5/2020, 2:22:48 PM
 * Auto updated?
 *   Yes
 *
 * Description:
 *   Apurba Mondal
**/

package assig.apurba.rar.config;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class HttpAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
    }
}