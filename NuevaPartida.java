package BlackJack;

import BBDD.BBDD;
import General.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "NuevaPartida", urlPatterns = {"/NuevaPartida"})
public class NuevaPartida extends HttpServlet {

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
            out.println("<title>Servlet NuevaPartida</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NuevaPartida at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        response.addHeader("Access-Control-Allow-Origin", "*");
        String idUsuario = request.getParameter("id");
        Double saldo = 0.0;
        try {
            saldo = BBDD.selectSaldo(idUsuario);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(NuevaPartida.class.getName()).log(Level.SEVERE, null, ex);
        }
        Double apuesta = Double.parseDouble(request.getParameter("apuesta"));
        User jugador = new User(idUsuario, saldo);
        Mano mano = new Mano();
        if (apuesta > jugador.getCredito()) {
            response.getWriter().append("No");
        } else {
            try {
                BBDD.setSaldo(idUsuario, -apuesta);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(NuevaPartida.class.getName()).log(Level.SEVERE, null, ex);
            }
            mano.cogerCarta();
            mano.cogerCarta();
            Carta carta = new Carta();
            
                response.getWriter().append(mano.getMano().get(0).toString()
                        + "&" + mano.getMano().get(1).toString() + "$" + mano.contarPuntos(0) + "%" + carta.toString());
            
        }
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

