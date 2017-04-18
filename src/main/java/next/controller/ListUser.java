package next.controller;

import core.db.DataBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by johngrib on 2017. 4. 18..
 */
@annotation.Controller(name = "/users")
public class ListUser extends Controller {

    private static final Logger log = LoggerFactory.getLogger(ListUser.class);

    @Override
    public void exec(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        if (!UserSessionUtils.isLogined(req.getSession())) {
            resp.sendRedirect("/users/loginForm");
            return;
        }

        req.setAttribute("users", DataBase.findAll());

        RequestDispatcher rd = req.getRequestDispatcher("/user/list.jsp");
        rd.forward(req, resp);
    }
}
