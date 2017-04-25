package next.controller.qna;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AnswerListController extends AbstractController {
    private static final Logger log = LoggerFactory.getLogger(AnswerListController.class);

    private QuestionDao questionDao = new QuestionDao();
    private AnswerDao answerDao = new AnswerDao();

    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse response) throws Exception {

        Long questionId = Long.parseLong(req.getParameter("questionId"));

        final Question question = questionDao.findById(questionId);
        final List<Answer> answers = answerDao.findAllByQuestionId(questionId);

        log.debug("answers : {}", answers);

        return jsonView().addObject("question", question).addObject("answers", answers);
    }
}
