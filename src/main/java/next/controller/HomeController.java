package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.Controller;
import core.mvc.ResponseResolve;
import next.dao.QuestionDao;

public class HomeController implements Controller {
    @Override
    public ResponseResolve execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        QuestionDao questionDao = new QuestionDao();
        req.setAttribute("questions", questionDao.findAll());
        return new ResponseResolve(req, resp).view("home.jsp");
    }
}
