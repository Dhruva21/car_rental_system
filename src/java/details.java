/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;
/**
 *
 * @author hp
 */
@WebServlet("/details")
public class details extends HttpServlet {

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
            String n=request.getParameter("hide"); 
             //out.println(n); 
            
            //String h=(String)session.getAttribute("hid");
            
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");
            PreparedStatement ps=conn.prepareStatement("select * from availablecars where id='"+n+"'");
            //PreparedStatement ps1=conn.prepareStatement("select * from cars where id='"+n+"'");
            //ps.setString(1,id);
            
            ResultSet rs=ps.executeQuery();
            //ResultSet rs1=ps1.executeQuery();
            
            if (rs.next())
                    {
                        out.println("<br>");
                        out.println("<head>");
                        out.println("<style> table, th, td {border: 1px solid black;border-collapse: collapse;}th, td {padding: 15px;}</style>");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<center>");
                        out.println("<title>Features</title>");
                        
                        out.println("<p><i><b>FEATURES</b></i></p>");
                        out.println("<table BORDER=1 CELLPADDING=0 CELLSPACING=0 WIDTH=25%>");
                        out.println("<tr><th>Name</th><td>"+rs.getString(2)+"</td><tr><th>Car No.</th><td>"+rs.getString(3)+"</td></tr><th>Color</th><td>"+rs.getString(4)+"</td></tr><th>Mileage</th><td>"+rs.getString(5)+"</td></tr><th>Seats</th><td>"+rs.getString(6)+"</td></tr>");
                        out.println("</table>");
                        out.println("</center>");
                        out.println("</body>");
                     /*   if(rs1.next()){
                        Blob blob=rs1.getBlob("pic");
                        byte byteArray[]=blob.getBytes(1,(int)blob.length());
                        response.setContentType("image/gif");
                        OutputStream os=response.getOutputStream();
                        os.write(byteArray);
                        os.flush();
                        os.close();
                        }*/
                    }
          
            
    }catch(ClassNotFoundException | SQLException cs){
                
            }
    } 
    
    catch(Exception e){

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
