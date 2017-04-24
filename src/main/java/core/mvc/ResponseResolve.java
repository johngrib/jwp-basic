package core.mvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by johngrib on 2017. 4. 24..
 */
public class ResponseResolve {

    final private HttpServletRequest req;
    final private HttpServletResponse resp;
    private Resolver resolver = new NoneResolver();

    public ResponseResolve(HttpServletRequest req, HttpServletResponse resp) {
        this.req = req;
        this.resp = resp;
    }

    public ResponseResolve view(final String viewName) {
        this.resolver = new ViewResolver(viewName);
        return this;
    }

    public ResponseResolve json(final Object data) {
        this.resolver = new JsonResolve(data);
        return this;
    }

    interface Resolver {
        void resolve() throws IOException, ServletException;
    }

    public void run() {
        try {
            resolver.resolve();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    class JsonResolve implements Resolver {

        final private Object data;

        JsonResolve(Object data) {
            this.data = data;
        }

        @Override
        public void resolve() throws IOException {
            ObjectMapper mapper = new ObjectMapper();
            resp.setContentType("application/json;charset=UTF-8");
            PrintWriter out = resp.getWriter();
            out.print(mapper.writeValueAsString(data));
        }
    }

    public ResponseResolve redirect(final String redirect) {
        this.resolver = new RedirectResolver(redirect);
        return this;
    }

    class NoneResolver implements Resolver {
        @Override
        public void resolve() {
        }
    }

    class RedirectResolver implements Resolver {

        final private String redirect;

        public RedirectResolver(String redirect) {
            this.redirect = redirect;
        }

        @Override
        public void resolve() throws IOException {
            resp.sendRedirect(redirect);
        }
    }

    class ViewResolver implements Resolver {
        final private String viewName;

        public ViewResolver(String viewName) {
            this.viewName = viewName;
        }

        @Override
        public void resolve() throws IOException, ServletException {
            RequestDispatcher rd = req.getRequestDispatcher(viewName);
            rd.forward(req, resp);
        }
    }
}
