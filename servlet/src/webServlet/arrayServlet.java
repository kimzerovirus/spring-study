package webServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/arr")
public class arrayServlet extends HttpServlet {

    public void init() throws ServletException {
        System.out.println("init호출");
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String uid = req.getParameter("uid");
        String pwd = req.getParameter("pwd");

        res.setContentType("text/html");
        req.setCharacterEncoding("utf-8");

        PrintWriter out = res.getWriter(); //client와 연결통로를 열어주는것
        out.println("<html>");
        out.println("<head><title>form</title></head>");
        out.println("<body>");

        String[] subject = req.getParameterValues("subject");
        for (String str : subject) {
            out.println("selected subject : " + str + "<br>");
        }

        out.println("</body>");
        out.println("</html>");
    }

    public void destroy() {
        System.out.println("destroy()호출");
    }
}

