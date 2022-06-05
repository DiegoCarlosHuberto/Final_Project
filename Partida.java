
package Casino;


public class Partida {
    
    //Creación de clase 'Partida'
    private int id;
    private int idJuego;
    private double bet;
    private double balance;
    private String fechaHora;
    private static int autoincrement = 0;

    public Partida() {
    }

    public Partida(int idJuego, double bet, double balance, String fechaHora) {
        autoincrement++;
        this.setId(autoincrement);
        this.setIdJuego(idJuego);
        this.setBet(bet);
        this.setBalance(balance);
        this.setFechaHora(fechaHora);
    }

    public int getId() {
        return id;
    }

    public int getIdJuego() {
        return idJuego;
    }

    public double getBet() {
        return bet;
    }

    public double getBalance() {
        return balance;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdJuego(int idJuego) {
        this.idJuego = idJuego;
    }

    public void setBet(double bet) {
        this.bet = bet;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    @Override
    public String toString() {
        return "Partida{" + "id=" + id + ", idJuego=" + idJuego + ", bet=" + bet + ", balance=" + balance + "}'";
    }
    
    public String toStringDateTime(){
        return "" + fechaHora + "";
    }
    
}