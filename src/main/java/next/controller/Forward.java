package next.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by johngrib on 2017. 4. 18..
 */
public class Forward extends Controller {

    private static final Logger log = LoggerFactory.getLogger(CreateUser.class);
    private static Map<String, String> forwardMap = new HashMap<>();
    static {
        forwardMap.put("/users/form", "/user/form.jsp");
        forwardMap.put("/users/loginForm", "/user/login.jsp");
    }

    @Override
    public void exec(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        final String fileName = (forwardMap.containsKey(req.getRequestURI()))
                ? forwardMap.get(req.getRequestURI())
                : req.getRequestURI() + ".jsp";

        log.info("contains:" + req.getRequestURI());
        log.info("fileName:" + fileName);

        final File file = new File(req.getServletContext().getRealPath(fileName));

        if(file.exists()){
            log.info("jsp file found : " + fileName);
            final RequestDispatcher rd = req.getRequestDispatcher(fileName);
            rd.forward(req, resp);
            return;
        }

        resp.sendRedirect("/common/404");
    }
}
