package next.controller;

import core.db.DataBase;
import next.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by johngrib on 2017. 4. 18..
 */
@annotation.Controller(name = "/users/profile")
public class Profile extends Controller {

    private static final Logger log = LoggerFactory.getLogger(Profile.class);

    @Override
    public void exec(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String userId = req.getParameter("userId");
        User user = DataBase.findUserById(userId);
        if (user == null) {
            throw new NullPointerException("사용자를 찾을 수 없습니다.");
        }
        req.setAttribute("user", user);
        this.forward(req, resp, "/user/profile.jsp");
    }
}
