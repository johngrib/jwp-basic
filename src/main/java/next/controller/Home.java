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
@annotation.Controller(name = "/")
public class Home extends Controller {

    private static final Logger log = LoggerFactory.getLogger(Home.class);

    @Override
    public void exec(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        log.info("home");
        req.setAttribute("users", DataBase.findAll());
        RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
        rd.forward(req, resp);
    }
}
