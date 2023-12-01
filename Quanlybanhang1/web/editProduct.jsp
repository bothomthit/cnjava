<%-- 
    Document   : editProduct
    Created on : Nov 29, 2023, 3:38:36 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       <title>Edit Product</title>
 </head>
 <body>
 <h1>Edit Product</h1>
 <form method="POST" action="editProductServlet" enctype="multipart/formdata">
 <table>
 <tr>
 <td>Product ID</td>
 <td><input type="text" name="ID" value="${product.getID()}"/></td>
 </tr>
 <tr>
 <td>Product Name</td>
 <td><input type="text" name="name"
value="${product.getName()}"/></td> 
 </tr>
 <tr>
 <td>Product Type</td>
 <td><input type="text" name="type"
value="${product.getType()}"/></td>
 </tr>
 <tr>
 <td>Product Price</td>
 <td><input type="text" name="price"
value="${product.getPrice()}"/></td>
 </tr>
 <tr>
 <td>Product Image</td>
 <td><img src="${product.getImage()}" width=100 height=100 /></td>
 </tr>
 <tr>
 <td>Change Image</td>
 <td><input type="file" name="image" value=""></td>
 </tr>
 <tr>
 <td colspan="2"><button>Update</button><input type="reset"></td>
 </tr>
 </table>
 </form>
 <div> 
 <h5>${thongbao}</h5> 
 
 </div>
 </body>
</html>
