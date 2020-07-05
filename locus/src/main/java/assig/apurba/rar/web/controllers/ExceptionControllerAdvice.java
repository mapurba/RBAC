/* EXCEPTION CONTROLLER ADVICE.java
 *   by Anonymous
 *
 * Created:
 *   7/5/2020, 2:22:00 PM
 * Last edited:
 *   7/5/2020, 2:22:00 PM
 * Auto updated?
 *   Yes
 *
 * Description:
 *   Apurba Mondal
**/


package assig.apurba.rar.web.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice 
public class ExceptionControllerAdvice {

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public String exception(AccessDeniedException e) {
        return "{\"status\":\"access denied\"}";
    }
}