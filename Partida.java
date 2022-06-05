
public class Partida {

	private int Id;
	private int idJuego;
	private double bet;
	private double balance;
	private String fechaHora;
	private static int autoincrement = 0;

	public Partida(int idJuego, double bet, double balance, String fechaHora) {
		autoincrement++;
		this.setId(autoincrement);
		this.setIdJuego(idJuego);
		this.setBet(bet);
		this.setBalance(balance);
		this.setFechaHora(fechaHora);
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getIdJuego() {
		return idJuego;
	}

	public void setIdJuego(int idJuego) {
		this.idJuego = idJuego;
	}

	public double getBet() {
		return bet;
	}

	public void setBet(double bet) {
		this.bet = bet;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(String fechaHora) {
		this.fechaHora = fechaHora;
	}

	@Override
	public String toString() {
		return "Partida{" + "id=" + Id + ", idJuego=" + idJuego + ", bet=" + bet + ", balance=" + balance + "}'";
	}

	public String toStringDateTime() {
		return "" + fechaHora + "";
	}

	public Partida() {
	}

}
