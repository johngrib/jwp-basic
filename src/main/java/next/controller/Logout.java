package next.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by johngrib on 2017. 4. 18..
 */
@annotation.Controller(name = "/users/logout")
public class Logout extends Controller {

    private static final Logger log = LoggerFactory.getLogger(Logout.class);

    @Override
    public void exec(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession();
        session.removeAttribute(UserSessionUtils.USER_SESSION_KEY);
        resp.sendRedirect("/");
    }
}
