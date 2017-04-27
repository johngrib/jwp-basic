package next.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.annotation.Controller;
import core.annotation.RequestMapping;
import next.dao.UserDao;
import next.model.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;

@Controller
public class CreateUserController extends AbstractController {
    private static final Logger log = LoggerFactory.getLogger(CreateUserController.class);

    private UserDao userDao = UserDao.getInstance();

    @RequestMapping("/users/create")
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        User user = new User(request.getParameter("userId"), request.getParameter("password"),
                request.getParameter("name"), request.getParameter("email"));
        log.debug("User : {}", user);
        userDao.insert(user);
        return jspView("redirect:/");
    }
}
