package next.controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.AnswerDao;
import next.model.Result;
import core.jdbc.DataAccessException;
import core.mvc.AbstractController;
import core.mvc.ModelAndView;

public class DeleteAnswerController extends AbstractController {
    private AnswerDao answerDao = new AnswerDao();

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Long answerId = Long.parseLong(request.getParameter("answerId"));
        Long questionId = Long.parseLong(request.getParameter("questionId"));

        ModelAndView mav = jsonView();
        try {
            answerDao.delete(answerId);

            final Integer answerCount = answerDao.findAllByQuestionId(questionId).size();

            mav.addObject("result", Result.ok());
            mav.addObject("answerCount", answerCount);
            mav.addObject("answerId", answerId);
        } catch (DataAccessException e) {
            mav.addObject("result", Result.fail(e.getMessage()));
        }
        return mav;
    }
}
