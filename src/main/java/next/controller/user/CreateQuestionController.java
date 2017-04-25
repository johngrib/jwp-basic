package next.controller.user;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.dao.QuestionDao;
import next.dao.UserDao;
import next.model.Question;
import next.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreateQuestionController extends AbstractController {
    private static final Logger log = LoggerFactory.getLogger(CreateQuestionController.class);

    private QuestionDao dao = new QuestionDao();

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        final User loginUser = (User) request.getSession().getAttribute("user");
        log.debug("login user : {}", loginUser);

        if (loginUser == null) {
            log.debug("not login user");
            return jspView("redirect:/");
        }

        final Question question = new Question(
                loginUser.getName(),
                request.getParameter("title"),
                request.getParameter("contents")
        );

        log.debug("Question : {}", question);
        dao.insert(question);

        return jspView("redirect:/");
    }
}
