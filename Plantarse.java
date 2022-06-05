package BlackJack;

import BBDD.BBDD;
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


@WebServlet(name = "Plantarse", urlPatterns = {"/Plantarse"})
public class Plantarse extends HttpServlet {

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
            out.println("<title>Servlet Plantarse</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Plantarse at " + request.getContextPath() + "</h1>");
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
        int puntaje = Integer.parseInt(request.getParameter("puntos"));
        int numCartas = Integer.parseInt(request.getParameter("numCartas"));
        Double apuesta = Double.parseDouble(request.getParameter("apuesta"));
        String rango = request.getParameter("rango");
        String palo = request.getParameter("palo");
        String id = request.getParameter("id");
        Mano banca = new Mano();
        Carta carta = new Carta(rango, palo);
        banca.getMano().add(carta);
        while (((((banca.getPuntaje() < puntaje) || ((banca.getPuntaje() == puntaje)) && banca.getPuntaje() < 16))
                && ((banca.getPuntaje() < 21) && puntaje < 22)) || banca.getMano().size() < 2) {
            banca.cogerCarta();
            banca.setPuntaje(banca.contarPuntos(0));
        }
        String respuesta = "";
        for (int i = 1; i < banca.getMano().size(); i++) {
            respuesta += banca.getMano().get(i).toString();
            respuesta += "&";
        }
        respuesta += "$";
        Double ganancia = 0.0;
        if ((((puntaje == 21) && (banca.getPuntaje() == 21)) || ((puntaje == 21) && (banca.getPuntaje() != 21))) && ((numCartas == 2) && (banca.getMano().size() > 2))) {
            respuesta += ("Has ganado " + apuesta * 2 + " euros");
            ganancia += apuesta *2;
        } else if ((((puntaje > banca.getPuntaje())) && (puntaje < 22)) || ((banca.getPuntaje() > 21) && (puntaje < 22))) {
            respuesta += ("Has ganado " + apuesta * 1.5 + " euros");
            ganancia += apuesta *1.5;
        } else if ((puntaje == banca.getPuntaje()) || ((puntaje > 21) && (banca.getPuntaje() > 21))) {
            ganancia += apuesta;
            respuesta += ("Has ganado " + apuesta + " euros");
        } else {
            respuesta += "Has perdido";
        }
        try {
           BBDD.setSaldo(id, ganancia);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Plantarse.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.getWriter().append(respuesta + "%" + banca.getPuntaje());
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


