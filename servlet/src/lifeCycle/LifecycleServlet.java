package lifeCycle;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/LifecycleServlet")
public class LifecycleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public LifecycleServlet() {
        /*
            if(메모리에 없음){
                해당 서블릿 클래스를 메모리에 올림
                init()메소드를 실행
            }
            service()메소드를 실행
         */
        System.out.println("Create LifecycleServlet 생성!!");
    }

    public void init(ServletConfig config) throws ServletException {
        System.out.println("Call init test 호출!!");
    }


    public void destroy() {
        //WAS가 종료되거나, 웹어플리케이션이 새롭게 갱신될 경우 destroy()메소드가 실행된다.
        System.out.println("Call destroy 호출!!");
    }

//	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("service 호출!!");
//	}


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //다른브라우저 화면을 띄우거나 새로고침할 때 마다 다시 호출된다.
        System.out.println("Call service호출!!");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>form</title></head>");
        out.println("<body>");
        out.println("<form method='post' action='/firstweb/LifecycleServlet'>");
        out.println("name : <input type='text' name='name'><br>");
        out.println("<input type='submit' value='ok'><br>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
        out.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        out.println("<h1> hello " + name + "</h1>");
        out.close();
    }
}
