/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet;

import static Servlet.MySQLConntUtils.getMySQLConnection;
import java.io.File;
import java. io.FileOutputStream;
import java. io. IOException;
import java.io. InputStream;
import java. io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util. logging.Level;
import java.util.logging. Logger;
import java.net. URL;
import javax.servlet. ServletException;
import javax. servlet. annotation.MultipartConfig;
import javax.servlet. annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http. Part;
@MultipartConfig(
 fileSizeThreshold = 1024 * 1024 * 10,
 maxFileSize = 1024 * 1024 * 50,
 maxRequestSize = 1024 * 1024 * 100
)
@WebServlet("/editProductServlet")
public class editProductServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
public editProductServlet(){
    super();
}
/**
 *
 * @author Admin
 */



    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       request.setCharacterEncoding("utf-8");
 response.setCharacterEncoding("utf-8");
response.getWriter().append("Served at: ").append(request.getContextPath());
 String errorString = null;
 String id=request.getParameter("ID");
Product product = new Product();
try {
Connection conn = MySQLConntUtils.getMySQLConnection();
product = DBUtils.findProduct(conn,id);
} catch (SQLException e) {
e.printStackTrace();
} catch (ClassNotFoundException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
request.setAttribute("errorString",errorString);
request.setAttribute("product", product);
request.getRequestDispatcher("/EditProduct.jsp").forward(request, 
response);
}
/**
* @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse 
response)
*/
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse 
response) throws ServletException, IOException {
// TODO Auto-generated method stub
Connection conn = null; //connect SQL
try {
request.setCharacterEncoding("utf-8");
 response.setCharacterEncoding("utf-8");
 conn = getMySQLConnection();
 String id=request.getParameter("ID");
 String name=request.getParameter("name");
 String type=request.getParameter("type"); 
 int price=Integer.parseInt(request.getParameter("price"));
 String image=""; 
 Part part= request.getPart("image");
 String fileName=extractFileName(part);
 fileName=new File(fileName).getName();
 part.write("D:/CNJ2022/TH1/WebContent/Images/"+fileName);
 image="Images/"+fileName;
 Product pro=new Product(id, name,type, price, image); //tạo đối tượng

 //thêm đối tượng vào CSDL
 DBUtils.updateProduct(conn, pro); 
 request.getRequestDispatcher("/EditProduct.jsp").forward(request, 
response);
} catch (ClassNotFoundException ex) {
Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, 
ex);
 } catch (SQLException ex) {
 Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, 
ex);
 }finally{
 if(conn != null){
 try {
 conn.close();
 } catch (SQLException ex) {
 Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, 
null, ex);
 }
 }
 
 } 
//doGet(request, response);
}
private String extractFileName(Part part) {
 String contentDisp = part.getHeader("content-disposition");
 String[] items = contentDisp.split(";");
 for (String s : items) {
 if (s.trim().startsWith("filename")) {
 return s.substring(s.indexOf("=") + 2, s.length() - 1);
 }
 }
 return "";
 }

        }
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
