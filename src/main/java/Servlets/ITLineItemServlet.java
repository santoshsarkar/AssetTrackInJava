/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ssarkar
 */
public class ITLineItemServlet extends HttpServlet {
       public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
           
      
      
      // JDBC driver name and database URL
      
      // Set response content type
      response.setContentType("text/html");

      PrintWriter out = response.getWriter();
      String title = "Form728";
      String docType =
         "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
         
      out.println(docType +
         "<html>\n" +
            "<head><title>" + title + "</title>\n" +
            "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">\n"+
            "<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js\"></script>\n"+
            "<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js\"></script>\n"+        
            "</head>\n"+        
            "<body bgcolor = \"#f0f0f0\">\n" +
               //"<h1 align = \"center\">" + title +" ID: " + request.getParameter("id")+"</h1>\n"+
                          
               "<div class=\"container\">"+
               "<div class=\"row\">"+  
               "<div class=\"col-sm-6\">STATE OF CALIFORNIA <br/>Stock ReceivedInventory-NON-IT Goods<br/>HCD 728-Non-IT(New 08/14)  </div>"+
               "<div class=\"col-sm-6\">DEPARTMENT OF HOUSING AND COMMUNITY DEVELOPMENT<br/>ADMINISTRATION AND MANAGEMENT DIVISION<br/>BUSINESS SERVICES OFFICE</div>"+ 
               "</div>"+           
               "<div class=\"row\">"
                 + "<br/><br/><p>Purpose: <br/>The purpose of this form is to track receipt of Non-IT goods received in BSO.</p>"
                 + "<p>BSO staff will document the date and line item number from the PO. The following will be documented if applicable; item description, serial number and tag equipment with a decal number.This information will be entered in the table below prior to releasing/delivering Non-IT goods to the assigned location.</p>"
              + "</div><br/><br/> "
                 
                          
                          
        + "<div class=\"tables\" style=\"border:1px solid black;\">"
            
                +""
      );
   
    // Database Query   
       try
    {
      DriverManager.registerDriver(new com.mysql.jdbc.Driver());
      //String myDriver = "com.mysql.cj.jdbc.Driver";
      String myUrl = "jdbc:mysql://localhost/form728";
      //Class.forName(myDriver);
      Connection conn = DriverManager.getConnection(myUrl, "santosh", "sarkar@1234");
      String query = "select * from assets where purchase_order="+ request.getParameter("po");
      
      //out.println(query);
      
      // create the java statement
      Statement st = conn.createStatement();
      Statement st2 = conn.createStatement();
      
      // execute the query, and get a java resultset
      ResultSet rs = st.executeQuery(query);
      
      String query2 = " select * from it_goods where po_number="+ request.getParameter("po");
      ResultSet rs2 = st2.executeQuery(query2);
      while (rs2.next())
      {
          String po_no=rs2.getString(2);
          String dateorder_receiving_from_vendor=rs2.getString(3);
          String employee_receiving_order_BSO=rs2.getString(4);
          String partial_order=rs2.getString(5);
          String complete_of_order=rs2.getString(6);
          String dateorder_receiving_from_contractor=rs2.getString(7);
          String employee_receiving_order_program=rs2.getString(8);
          
      out.println("<h2><center>BSO Complete this section</center></h2>"
            + "<table class=\"table\">"
            + "<tr><td>Date order received from vendor: "+ dateorder_receiving_from_vendor 
            + "</td><td>Po Number:"+po_no
            + "</td></tr>"
                    + "<tr><td>Employee Receiving order (print name) :"+employee_receiving_order_BSO
                    + "</td><td>Employee Signature: </td></tr>"
                    + ""
                    + ""
            + "/<table>"); 
      out.println("<h2><center>Program complete this section</center></h2>"
              + "<table class=\"table\"><tr><td>Date order received from contractor:"+dateorder_receiving_from_contractor+"</td></tr>"
              + "<tr><td>Employee Receiving order (print name):"+employee_receiving_order_program+"</td></tr>"
              + "</table>");
      }
   
	 
	  
  
	
	 
  out.println("<h2><center>BSO Complete this section</center></h2>");	    
   
   out.println("<hr></br><table class=\"table\" cellspacing='0' cellpadding='5' border='1'>");
   out.println("<tr>");
   //out.println("<td><b>ID</b></td>");
   out.println("<td><b>Date Received</b></td>");
   //out.println("<td><b>line_item_number</b></td>");
   out.println("<td><b>item_description</b></td>");
   out.println("<td><b>Asset Type</b></td>");
   out.println("<td><b>Asset Sub Type</b></td>");
   //out.println("<td><b>form_id</b></td>");
   out.println("</tr>");
      while (rs.next())
      {
                /*
                int id = rs.getInt("id");
                String date_received = rs.getString("date_received");
                String line_item_number="";
                String item_description="";
                String make_model_number="";
                String decal_number="";
                String form_id="";
                */
        out.println("<tr>");
        out.println("<td>"+rs.getDate("accounting_dt") + "</td>");
        out.println("<td>"+rs.getString("descr")+ "</td>");
        out.println("<td>"+rs.getString("asset") + "</td>");
        out.println("<td>"+rs.getString("asset_subtype") + "</td>");
        //out.println("<td>"+rs.getString(5) + "</td>");
        //out.println("<td>"+rs.getString(6) + "</td>");
        //out.println("<td>"+rs.getString(7) + "</td>");
       
        out.println("</tr>");
        
                
        
        //System.out.format("%s, %s,",id, date_received);
        //System.out.format("%s, %s, %s, %s, %s, %s, %s\n", id, date_received, line_item_number, item_description, make_model_number, decal_number, form_id);
        //out.println(id);
        //out.println(date_received);
        
        
      }
      out.println("</table></br><hr>");
      
      
     rs.close();
     st.close();
     conn.close();
    }catch (Exception e){
      System.err.println("Got an exception! ");
      System.err.println(e.getMessage());
    }
 out.println("<center><button onclick=\"myFunction()\">Print this page</button></center>"); 
 out.println("<script>function myFunction(){ window.print();}</script>");
 
out.println("</div></div></body></html>");
       
       
       }
       
   
   // Method to handle POST method request.
   public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
      
      doGet(request, response);
   }

}
