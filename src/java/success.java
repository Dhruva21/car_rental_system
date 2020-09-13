/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author hp
 */
public class success extends HttpServlet {

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
              HttpSession session=request.getSession();  
              String x=(String)session.getAttribute("hid"); 
              //out.println(x);
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");
                
                PreparedStatement ps=conn.prepareStatement("select * from bookedcar where id='"+x+"'");
                PreparedStatement ps1=conn.prepareStatement("select * from availablecars where id='"+x+"'");
                //PreparedStatement ps1=conn.prepareStatement("select * from bookedcar where cvv='"+cvv+"'");
            
                ResultSet rs=ps.executeQuery();     //For Payment Details
                ResultSet rs1=ps1.executeQuery();   //For pickup and drop details
                        out.println("<head>");
                        out.println("<style> font-family:courier,arial,helvetica; color:white;  table {border: 2px solid black;border-collapse: collapse;}th, td {padding: 15px;} #b{text-align:center;} td,th{border:none; color:white;}</style>");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<center>");
                        out.println("<title></title>");
                        out.println("<br>");
                        out.println("<body bgcolor='#56baed'>");
                        out.println("<h3><i>Booking Successful! Happy journey!  :) </i></h3>");
                        out.println("<br>");
                        out.println("<table BORDER=1 CELLPADDING=0 CELLSPACING=0 WIDTH=25%>");
            if (rs1.next() && rs.next())
                    {
                       
                        out.println("<tr><th>Your booked car</th><td>"+rs1.getString(2)+"</td></tr>"
                                + "<tr><th>Car Number plate</th><td>"+rs1.getString(3)+"</td></tr>"
                                        + "<tr><th>Color</th><td>"+rs1.getString(4)+"</td></tr>"
                                                + "<tr><th>Pickup Location</th><td>"+rs.getString(1)+"</td></tr>"
                                                        + "<tr><th>Drop Location</th><td>"+rs.getString(2)+"</td></tr>"
                                                                + "<tr><th>Amount paid</th><td>"+rs.getString(5)+"</td></tr>");      
                }
            
            out.println("</table>");
            out.println("</center>");
            out.println("<br>");
            out.println("<form action='logout'>");
            out.println("<div id='b'>");
            out.println("<button type='submit'>Logout</button>");
            out.println("</div>");
            out.println("</form>");
            out.println("</body>");
            //RequestDispatcher rd=request.getRequestDispatcher("logout");  
            //rd.include(request,response);
            
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
