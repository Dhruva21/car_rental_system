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
public class bookedinfo extends HttpServlet {

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
            String h=(String)session.getAttribute("hid");  
            //out.println(h);
       
            String pl=request.getParameter("pl");
            String dl=request.getParameter("dl");
            int tm=Integer.parseInt(request.getParameter("tm"));
            int res=(tm*85);
            
            
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");
                PreparedStatement ps=conn.prepareStatement("insert into bookedcar(pickl,dropl,exptime,id,price) values(?,?,?,?,?)");
                ps.setString(1,pl); 
                ps.setString(2,dl);
                ps.setLong(3,tm);
                ps.setString(4,h);
                ps.setLong(5,res);
                
               // PreparedStatement ps1=conn.prepareStatement("select * from bookedcar(?,?,?)");
                //ResultSet rs=ps1.executeQuery(); 
                int i=ps.executeUpdate();
                if (i>0)
                {
                    out.println("<head>");
                    out.println("<style> #d{ background:grey; font-family:san-serif} p{text-align:center;}</style>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<div id='d'>");
                    out.println("<h2><center>Your fare amount in rs:</center></h2>");
                    out.println("<h2><p>"+res+"</p></h2>");
                    out.println("</div>");
                    out.println("</body>");
                    RequestDispatcher rd=request.getRequestDispatcher("payments.html");  
                    rd.include(request,response);  
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
