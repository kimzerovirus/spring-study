package webServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//cannot be cast to javax.servlet.Servlet -> HttpServlet을 상속받지 않아서 생기는 오류
//localhost:9090/login.html

@WebServlet("/loginEnd")
public class LoginServlet extends HttpServlet {
    public void init() throws ServletException{
        System.out.println("init호출");
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String uid = req.getParameter("uid");
        String pwd = req.getParameter("pwd");

        res.setContentType("text/html;charset=utf-8");

        PrintWriter out = res.getWriter(); //client와 연결통로를 열어주는것
        out.println("<html>");
        out.println("<head><title>form</title></head>");
        out.println("<body>");


        out.println("아이디 : " + uid + "<br>");
        out.println("패스워드 : " +pwd + "<br>");

        out.println("</body>");
        out.println("</html>");
    }

    public void destroy(){
        System.out.println("destroy()호출");
    }
}
