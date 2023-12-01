/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet;

//import com.mysql.cj.x.protobuf.MysqlxNotice.Warning.Level;
import java.io.IOException;
import java.io.PrintWriter;
//import java.lang.System.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
@WebServlet(name = "DBUtils", urlPatterns = {"/DBUtils"})
public class DBUtils extends HttpServlet {
public static UserAccount findUser(Connection conn, //
String userName, String password) throws SQLException {
String sql = "Select a.User_Name, a.Password, a.Gender from User_Acc a"
+" where a.User_Name =? and a.password =? ";
PreparedStatement pstm = conn.prepareStatement(sql);
pstm.setString(1, userName);
pstm.setString(2, password);
ResultSet rs = pstm.executeQuery();
if (rs.next()) {
    String gender = rs.getString("Gender");
UserAccount user = new UserAccount();
user.setUserName(userName);
user.setPassword(password);
user.setGender(gender);
return user;
}
return null;
}
public static UserAccount findUser(Connection conn, String userName) throws
SQLException {
String sql ="Select a.User_Name, a.Password, a.Gender from User_Account a "//
+ " where a.User_Name =? ";
PreparedStatement pstm = conn.prepareStatement(sql);
pstm.setString(1, userName);
ResultSet rs = pstm.executeQuery();
if (rs.next()) {
String password = rs.getString("Password");
String gender = rs.getString("Gender");
UserAccount user = new UserAccount();
user.setUserName(userName);
user.setPassword(password);
user.setGender(gender);
return user;
}
return null;
}

public static void insertUserAccount(Connection conn, UserAccount user) throws
SQLException {
String sql = "Insert into UserAccount(Username, Gender, Password) values(?,?,?)";

PreparedStatement pstm = conn.prepareStatement(sql);
pstm.setString(1, user.getUserName());
pstm.setString(2, user.getGender());
pstm.setString(3, user.getPassword());
pstm.executeUpdate();

}

    static void insertProduct(Connection conn, Product pro) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    static Product findProduct(Connection conn, String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    static List<Product> selectAllProduct(com.sun.jdi.connect.spi.Connection conn) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DBUtils</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DBUtils at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
//    public static List<Product> selectAllProduct(Connection conn) throws SQLException{
//PreparedStatement ps=null;
//ResultSet rs=null;
//List<Product> productList=new ArrayList<Product>();
//try {
//ps=conn.prepareStatement("select * from Product ");
//rs=ps.executeQuery();
//Product p;
//while(rs.next()) {
//p=new
//Product(rs.getString("ID"),rs.getString("Name"),rs.getString("Type"),rs.getInt("Price"),rs.getString("Image"));
//}catch(Exception e) {
//System.out.print(e);
//        }
//finally {
//if(ps != null)
//ps.close();
//if(conn != null)
//conn.close();
//        }
//return productList;
//
//}
//    } 
    public static int insertProduct(Product product) throws
SQLException, ClassNotFoundException {
Connection conn = null;
int rows=0;
try{
conn = 
MySQLConntUtils.getMySQLConnection();
String sql = "Insert into Product(Id, Name,Type,Price, Image) values (?,?,?,?,?)";
PreparedStatement pstm = 
conn.prepareStatement(sql);
pstm.setString(1, product.getId());
pstm.setString(2, product.getName());
pstm.setString(3, product.getType());
pstm.setInt(4, product.getPrice());
pstm.setString(5, product.getImage());
rows=pstm.executeUpdate();

}finally{
if(conn != null){
try {
conn.close();
} catch (SQLException ex) {
Logger.getLogger(NewProductServlet.class.getName()).log(Level.SEVERE,null, ex);
}
}
}
return rows;
}
public static void updateProduct( Connection conn,Product product) 
throws SQLException, ClassNotFoundException {

try{
conn = 
MySQLConntUtils.getMySQLConnection();
String sql = "Update Product set Id=?, Name=?,Type=?,Price=?,"
+ " Image=? where Id=?";
PreparedStatement pstm = 
conn.prepareStatement(sql);
pstm.setString(1, product.getId());
pstm.setString(2, product.getName());
pstm.setString(3, product.getType());
pstm.setInt(4, product.getPrice());
pstm.setString(5, product.getImage());
pstm.setString(6, product.getId());
pstm.executeUpdate();
}finally{
if(conn != null){
try {
conn.close();
} catch (SQLException ex) {
Logger.getLogger(NewProductServlet.class.getName()).log(Level.SEVERE,null, ex);
}
}
}
}
public static void deleteProductById(Connection 
conn,String id) throws SQLException {
String sql="delete from Product where id=?";
PreparedStatement pstm = 
conn.prepareStatement(sql);
pstm.setString(1, id);
pstm.executeUpdate();
}
public static Product findProductById(String id) throws
SQLException, ClassNotFoundException {
Connection conn = null;
try{
conn = 
MySQLConntUtils.getMySQLConnection();
String sql = "Select * from product where id=?";
PreparedStatement pstm = 
conn.prepareStatement(sql);
pstm.setString(1, id);
ResultSet rs = pstm.executeQuery();
if (rs.next()) {
Product pro=new
Product(rs.getString("id"),rs.getString("Name"),rs.getString("Type"),
rs.getInt("Price"),rs.getString("Image"));
return pro;
}
return null;
}finally{
if(conn != null){
try {
conn.close();
} catch (SQLException ex) {
Logger.getLogger(NewProductServlet.class.getName()).log(Level.SEVERE,null, ex);
}
}
}
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
    
    
