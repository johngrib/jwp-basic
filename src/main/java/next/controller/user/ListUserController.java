package next.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.Controller;
import core.mvc.ResponseResolve;
import next.controller.UserSessionUtils;
import next.dao.UserDao;

public class ListUserController implements Controller {
    @Override
    public ResponseResolve execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        if (!UserSessionUtils.isLogined(req.getSession())) {
            return new ResponseResolve(req, resp).redirect("/users/loginForm");
        }

        UserDao userDao = new UserDao();
        req.setAttribute("users", userDao.findAll());
        return new ResponseResolve(req, resp).view("/user/list.jsp");
    }
}
