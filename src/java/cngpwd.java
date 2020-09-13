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

/**
 *
 * @author hp
 */
public class cngpwd extends HttpServlet {

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
            String em=request.getParameter("em");
            String cans=request.getParameter("sans");
            String cgpwd=request.getParameter("cgpwd");
       
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");
            PreparedStatement ps1=conn.prepareStatement("select * from signup where email=? and ans=?");
            ps1.setString(1,em);
            ps1.setString(2,cans);
            ResultSet rs=ps1.executeQuery();
            
            if (rs.next())
                    {
                        
                        PreparedStatement ps2=conn.prepareStatement("UPDATE signup SET pwd =? WHERE email =?");
                        ps2.setString(1,cgpwd);
                        ps2.setString(2,em);
                        int i=ps2.executeUpdate();
                        
                        PreparedStatement ps3=conn.prepareStatement("UPDATE login SET password =? WHERE username =?");
                        ps3.setString(1,cgpwd);
                        ps3.setString(2,em);
                        int j=ps3.executeUpdate();
                        if (i>0 && j>0){
                            out.println("<h3>successfully changed</h3>");  
                            RequestDispatcher rd=request.getRequestDispatcher("login.html");  
                            rd.include(request,response); 
                        }
                        else{
                            out.println("failed");
                        }
                    }
            else{
                    out.println("Incorrect username/ans please check");
                    RequestDispatcher rd=request.getRequestDispatcher("forgotpwd.html");  
                    rd.include(request,response); 
                }
           }
           
    catch(ClassNotFoundException | SQLException cs){
                
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
