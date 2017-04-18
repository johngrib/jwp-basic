package next.controller;

import core.db.DataBase;
import next.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by johngrib on 2017. 4. 18..
 */
@annotation.Controller(name = "/users/create")
public class CreateUser extends Controller {

    private static final Logger log = LoggerFactory.getLogger(CreateUser.class);

    @Override
    public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, Exception {

        if ( ! "POST".equals(req.getMethod())) {
            resp.sendRedirect("/users/form");
            return;
        }

        final User user = new User(
                req.getParameter("userId"),
                req.getParameter("password"),
                req.getParameter("name"),
                req.getParameter("email")
        );

        log.debug("User : {}", user);

        DataBase.addUser(user);

        resp.sendRedirect("/");
    }
}
