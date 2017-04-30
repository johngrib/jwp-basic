package core.mvc;

import core.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by johngrib on 2017. 4. 27..
 */
public class ControllerData {

    final public Controller controller;
    final public String uri;
    final public RequestMethod httpMethod;
    final public Method method;

    public ControllerData(Controller controller, String uri, RequestMethod httpMethod, Method method) {
        this.controller = controller;
        this.uri = uri;
        this.httpMethod = httpMethod;
        this.method = method;
    }

    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            return (ModelAndView) method.invoke(controller, new Object[]{req, resp});
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        return "ControllerData{" +
                "uri='" + uri + '\'' +
                ", httpMethod=" + httpMethod +
                '}';
    }
}
