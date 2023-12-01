/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Admin
 */
       @MultipartConfig(
fileSizeThreshold = 1024 * 1024 * 10,
maxFileSize = 1024 * 1024 * 50,
maxRequestSize = 1024 * 1024 * 100
       )
               /*@WebServlet( "/NewProductServlet")*/
public class NewProductServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
private static final String UPLOAD_DIR = "Images";


    /**
     *
     */

public NewProductServlet() {
super();
}


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
       Connection conn = null; //connect SQL
try {
request.setCharacterEncoding("utf-8");
response.setCharacterEncoding("utf-8");
conn = MySQLConntUtils.getMySQLConnection();
//xử lý tham số từ form
String id=request.getParameter("ID");
String name=request.getParameter("name");
String type=request.getParameter("type");
int price=Integer.parseInt(request.getParameter("price"));
String image="";
Part part= request.getPart("image");
String fileName=extractFileName(part);
fileName=new File(fileName).getName();
String path1=request.getContextPath();
System.out.println(path1);
part.write("D:/CNJ2022/TH1/WebContent/Images/"+fileName);
image="Images/"+fileName;
Product pro=new Product(id, name, type, price, image); //tao đối tượng

DBUtils.insertProduct(conn, pro);
request.getRequestDispatcher("/NewProduct. jsp"). forward(request,response);
} catch (ClassNotFoundException ex) {
Logger.getLogger(LoginServlet.class.getName()). log(Level. SEVERE, null,ex);
} catch (SQLException ex) {
Logger.getLogger(LoginServlet.class.getName()). log(Level. SEVERE, null,ex);
    }finally{
    if(conn !=null){
        try{
            
conn.close();
} catch (SQLException ex) {
Logger.getLogger(LoginServlet. class.getName()). log(Level. SEVERE,null,ex);
}
}
}

doGet(request, response);
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
    }
       }



    