/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author HMartin
 */
@WebServlet(name = "Conexion", urlPatterns = { "/Conexion" })
public class Conexion extends HttpServlet {

	int idUser;
	String dniUser;
	double bet = 0.00;
	double credito;
	Partida partida;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		double balance;
		double premio;
		int x;
		double bet;
		if (request.getParameter("moneyN").equals("")) {
			bet = 0.0 + Double.parseDouble(request.getParameter("moneyG"))
					+ Double.parseDouble(request.getParameter("moneyColorA"));
		} else if (request.getParameter("moneyG").equals("") && !request.getParameter("moneyColorA").equals("")) {
			bet = Double.parseDouble(request.getParameter("moneyN")) + 0.0
					+ Double.parseDouble(request.getParameter("moneyColorA"));

		} else if (request.getParameter("moneyColorA").equals("") && !request.getParameter("moneyG").equals("")) {
			bet = Double.parseDouble(request.getParameter("moneyN"))
					+ Double.parseDouble(request.getParameter("moneyG")) + 0.0;

		} else {
			bet = Double.parseDouble(request.getParameter("moneyN"));

		}

		if (request.getParameter("nums").equals("") && request.getParameter("groups").equals("")
				&& request.getParameter("moneyN").equals("") && request.getParameter("moneyG").equals("")
				&& request.getParameter("colorA").equals("") && request.getParameter("moneyColorA").equals("")) {

			NumWin numeroGanador = new NumWin();
			x = numeroGanador.getNumRuleta();

			String cString = numeroGanador.getColorWList();
			String gString = numeroGanador.getGroupNumWin();

			premio = 0;
			balance = bet - premio;

			response.getWriter().append("{\"prime\": \"" + 0 + "\",\"numberW\":\"" + x + "\",\"color\":\"" + cString
					+ "\",\"grupo\":\"" + gString + "\"}");

		} else if (request.getParameter("nums").equals("")) {
			Apuesta apuestaNumNull = new Apuesta(40, request.getParameter("groups"), 0.0,
					Double.parseDouble(request.getParameter("moneyG")), request.getParameter("colorA"),
					Double.parseDouble(request.getParameter("moneyColorA")));

			String cString = apuestaNumNull.getValuesNum().getColorWList();
			String gString = apuestaNumNull.getValuesNum().getGroupNumWin();

			x = apuestaNumNull.getValuesNum().getNumRuleta();

			premio = apuestaNumNull.gains(x);
			balance = bet - premio;

			response.getWriter().append("{\"prime\": \"" + premio + "\",\"numberW\":\"" + x + "\",\"color\":\""
					+ cString + "\",\"grupo\":\"" + gString + "\"}");

		} else if (request.getParameter("groups").equals("") && request.getParameter("moneyG").equals("")
				&& request.getParameter("colorA").equals("") && request.getParameter("moneyColorA").equals("")) {
			Apuesta apuestaPlenoApuesta = new Apuesta(Integer.parseInt(request.getParameter("nums")), "sin grupo",
					Double.parseDouble(request.getParameter("moneyN")), 0.0, "sin color", 0.0);
			String cString = apuestaPlenoApuesta.getValuesNum().getColorWList();
			String gString = apuestaPlenoApuesta.getValuesNum().getGroupNumWin();

			x = apuestaPlenoApuesta.getValuesNum().getNumRuleta();

			premio = apuestaPlenoApuesta.gains(x);
			balance = bet - premio;

			response.getWriter().append("{\"prime\": \"" + premio + "\",\"numberW\":\"" + x + "\",\"color\":\""
					+ cString + "\",\"grupo\":\"" + gString + "\"}");

		} else if (request.getParameter("groups").equals("") && request.getParameter("moneyG").equals("")) {
			Apuesta apuestaGroupNull = new Apuesta(Integer.parseInt(request.getParameter("nums")), "Sin grupo",
					Double.parseDouble(request.getParameter("moneyN")), 0.0, request.getParameter("colorA"),
					Double.parseDouble(request.getParameter("moneyColorA")));

			String cString = apuestaGroupNull.getValuesNum().getColorWList();
			String gString = apuestaGroupNull.getValuesNum().getGroupNumWin();

			x = apuestaGroupNull.getValuesNum().getNumRuleta();

			premio = apuestaGroupNull.gains(x);
			balance = bet - premio;
			response.getWriter().append("{\"prime\": \"" + premio + "\",\"numberW\":\"" + x + "\",\"color\":\""
					+ cString + "\",\"grupo\":\"" + gString + "\"}");

		} else if (request.getParameter("colorA").equals("") && request.getParameter("moneyColorA").equals("")) {
			Apuesta apuestaColorNull = new Apuesta(Integer.parseInt(request.getParameter("nums")),
					request.getParameter("groups"), Double.parseDouble(request.getParameter("moneyN")),
					Double.parseDouble(request.getParameter("moneyN")), "sin color", 0.0);

			String cString = apuestaColorNull.getValuesNum().getColorWList();
			String gString = apuestaColorNull.getValuesNum().getGroupNumWin();

			x = apuestaColorNull.getValuesNum().getNumRuleta();

			premio = apuestaColorNull.gains(x);
			balance = bet - premio;

			response.getWriter().append("{\"prime\": \"" + premio + "\",\"numberW\":\"" + x + "\",\"color\":\""
					+ cString + "\",\"grupo\":\"" + gString + "\"}");

		}

		else {

			Integer.parseInt(request.getParameter("nums"));
			Apuesta apuesta = new Apuesta(Integer.parseInt(request.getParameter("nums")),
					request.getParameter("groups"), Double.parseDouble(request.getParameter("moneyN")),
					Double.parseDouble(request.getParameter("moneyG")), request.getParameter("colorA"),
					Double.parseDouble(request.getParameter("moneyColorA")));

			String cString = apuesta.getValuesNum().getColorWList();
			String gString = apuesta.getValuesNum().getGroupNumWin();

			x = apuesta.getValuesNum().getNumRuleta();

			premio = apuesta.gains(x);
			balance = bet - premio;
			response.getWriter().append("{\"prime\": \"" + premio + "\",\"numberW\":\"" + x + "\",\"color\":\""
					+ cString + "\",\"grupo\":\"" + gString + "\"}");

		}

		Partida partida = new Partida(2, bet, balance, dtf.format(now));
		// QueryClass.updateDB(idUser, partida);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");

		double bet = Double.parseDouble(request.getParameter("moneyN"))
				+ Double.parseDouble(request.getParameter("moneyG"))
				+ Double.parseDouble(request.getParameter("moneyColorA"));
		;

		// Recepción de valores a través de parámetro
		this.bet = bet;
		this.idUser = Integer.parseInt(request.getParameter("id"));
		this.credito = Double.parseDouble(request.getParameter("credito"));
		this.dniUser = request.getParameter("dni");

		// Si se apuesta más que el crédito, se devuelve 0 (false)
		if (bet > credito) {
			response.getWriter().append("0");
		} else {
			response.getWriter().append("1");
		}

	}

}
