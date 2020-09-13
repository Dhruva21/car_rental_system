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
public class signupvalidation extends HttpServlet {

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
            
            String fn=request.getParameter("fname");
            String ln=request.getParameter("lname");
            String em=request.getParameter("email");
            String ph=request.getParameter("phone");
            String pwd=request.getParameter("pwd");
            String ans=request.getParameter("ans");
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","");
                PreparedStatement ps=conn.prepareStatement("insert into login(username,password) values(?,?)");
                ps.setString(1,em);
                ps.setString(2,pwd);
                ps.executeUpdate();


                PreparedStatement ps1=conn.prepareStatement("insert into signup(firstname,lastname,email,phoneno,pwd,ans) values(?,?,?,?,?,?)");
                ps1.setString(1,fn);
                ps1.setString(2, ln);
                ps1.setString(3, em);
                ps1.setString(4, ph);
                ps1.setString(5, pwd);
                ps1.setString(6, ans);
                int i=ps1.executeUpdate();
                if (i>0)
                {
                    out.println("Account created successfully !");
                   RequestDispatcher rd=request.getRequestDispatcher("index.html");  
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
