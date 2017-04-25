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

public class CreateQuestionController extends AbstractController {
    private static final Logger log = LoggerFactory.getLogger(CreateQuestionController.class);

    private QuestionDao dao = new QuestionDao();

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        final Question question = new Question(
                request.getParameter("writer"),
                request.getParameter("title"),
                request.getParameter("contents")
        );

        log.debug("Question : {}", question);
        dao.insert(question);
        return jspView("redirect:/");
    }
}
