package next.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by johngrib on 2017. 4. 17..
 */
public abstract class Controller {
    public abstract void exec(HttpServletRequest request, HttpServletResponse response) throws Exception;

    void forward(HttpServletRequest req, HttpServletResponse resp, String uri) throws Exception {
        RequestDispatcher rd = req.getRequestDispatcher(uri);
        rd.forward(req, resp);
    }
}
