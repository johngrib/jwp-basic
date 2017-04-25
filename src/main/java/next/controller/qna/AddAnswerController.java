package next.controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.dao.AnswerDao;
import next.model.Answer;

public class AddAnswerController extends AbstractController {
    private static final Logger log = LoggerFactory.getLogger(AddAnswerController.class);

    private AnswerDao answerDao = new AnswerDao();

    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse response) throws Exception {

        final Long questionId = Long.parseLong(req.getParameter("questionId"));
        final Answer answer = new Answer(req.getParameter("writer"), req.getParameter("contents"), questionId);

        log.debug("answer : {}", answer);

        final Answer savedAnswer = answerDao.insert(answer);
        final Integer answerCount = answerDao.findAllByQuestionId(questionId).size();

        return jsonView().addObject("answer", savedAnswer).addObject("answerCount", answerCount);
    }
}
