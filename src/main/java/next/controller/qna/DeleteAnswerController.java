package next.controller.qna;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import core.mvc.Controller;
import core.mvc.ResponseResolve;
import next.dao.AnswerDao;
import next.model.Result;

public class DeleteAnswerController implements Controller {
    @Override
    public ResponseResolve execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Long answerId = Long.parseLong(req.getParameter("answerId"));
        AnswerDao answerDao = new AnswerDao();

        answerDao.delete(answerId);

        return new ResponseResolve(req, resp).json(Result.ok());
    }
}
