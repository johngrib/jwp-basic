package next.controller;

import core.db.DataBase;
import next.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by johngrib on 2017. 4. 18..
 */
@annotation.Controller(name = "/users/updateForm")
public class UpdateUserForm extends Controller {

    private static final Logger log = LoggerFactory.getLogger(UpdateUserForm.class);

    @Override
    public void exec(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String userId = req.getParameter("userId");
        User user = DataBase.findUserById(userId);
        if (!UserSessionUtils.isSameUser(req.getSession(), user)) {
            throw new IllegalStateException("다른 사용자의 정보를 수정할 수 없습니다.");
        }
        req.setAttribute("user", user);
        this.forward(req, resp, "/user/updateForm.jsp");
    }
}
