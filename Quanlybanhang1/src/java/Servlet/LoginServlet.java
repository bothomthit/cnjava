package Servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Servlet implementation class LoginServlet */
@WebServlet ("/LoginServlet")

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    /**@see HttpServlet#HttpServlet()*/
    public LoginServlet(){
        super();
        // TODO Auto-gene ted constructor stub
    }

/**@see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)*/

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub 
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }
/**@see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)*/

    @Override
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        Connection conn;
           try {
                conn = 
                        MySQLConntUtils.getMySQLConnection(); 
                //xử lý tham số tù form  
                String
                        username = request.getParameter ("username");
                String 
                        password = request.getParameter("password");
                UserAccount usr = new UserAccount("username", "password"); //tạo đối tượng user với username và password
                //thêm đối tượng CSDL
                UserAccount u = DBUtils.findUser(conn,usr.getUserName (), usr.getPassword());
                if (u!= null){
                    UserAccount user=new UserAccount (u); request.setAttribute ("username","Chào bạn:" + user.getUserName());
                    request.getRequestDispatcher ("/index.jsp").forward (request, response);
                }
                else
                {
                //request.getRequestDispatcher ("/LogIn.jsp").forward (request, response);
                response.setContentType ("text/html; charset=UTF-8"); try (PrintWriter out = response.getWriter()){

            /* TODO output your page here.
            You may use following sample code. */

                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Thông báo</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>Thông tin đăng nhập không chính xác <a href=LogIn.jsp>Nhập lai</a></h1>");
                    out.println("</body>"); out.println("</html>");
                    }
                }
            
           } catch (ClassNotFoundException ex) {
        Logger.getLogger(LoginServlet.class.getName()).log (Level.SEVERE, null, ex);
        }
        catch (SQLException ex) {
        Logger.getLogger(LoginServlet.class.getName()).log (Level. SEVERE, null, ex);
        
        } 
    }
}
    
    
