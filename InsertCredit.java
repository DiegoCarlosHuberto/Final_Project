package Casino;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "InsertCredit", urlPatterns = {"/InsertCredit"})
public class InsertCredit extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.addHeader("Access-Control-Allow-Origin", "*");
        
        //Se reciben por parámetro el valor del usuario y su dni
        double credit = Double.parseDouble(request.getParameter("credito"));
        String DNI = request.getParameter("dni");

        try {
            QueryClass.InsertCredit(credit, DNI);
        } catch (SQLException ex) {
            Logger.getLogger(InsertCredit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
