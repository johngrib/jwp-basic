package next.controller.qna;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.dao.QuestionDao;
import next.model.Question;
import next.model.Result;
import next.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ModifyQuestionController extends AbstractController {
    private static final Logger log = LoggerFactory.getLogger(ModifyQuestionController.class);

    private QuestionDao dao = new QuestionDao();

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        final Long questionId = Long.parseLong(request.getParameter("questionId"));
        final User loginUser = (User) request.getSession().getAttribute("user");
        final Question question = dao.findById(questionId);

        log.debug("login user : {}", loginUser);

        if (loginUser == null || ! loginUser.getUserId().equals(question.getWriter())) {
            log.debug("not matched user");
            return jsonView().addObject("result", Result.fail("작성자가 아닙니다."));
        }

        final Question modifiedQuestion = new Question(
                loginUser.getName(),
                request.getParameter("title"),
                request.getParameter("contents")
        );

        log.debug("Question : {}", modifiedQuestion);
        dao.update(modifiedQuestion);

        return jsonView().addObject("result", Result.ok());
    }
}
