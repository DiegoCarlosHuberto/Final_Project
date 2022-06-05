package BlackJack;

import java.util.Random;


public class Carta {

    private String rango;
    private String palo;

    public String getRango() {
        return rango;
    }

    public void setRango(String rango) {
        this.rango = rango;
    }

    public String getPalo() {
        return palo;
    }

    public void setPalo(String palo) {
        this.palo = palo;
    }

    public Carta() {
        Random al = new Random();
        int valorCarta = al.nextInt(13) + 1;
        switch (valorCarta) {
            case 1:
                this.setRango("A");
                break;
            case 11:
                this.setRango("J");
                break;
            case 12:
                this.setRango("Q");
                break;
            case 13:
                this.setRango("K");
                break;
            default:
                this.setRango(Integer.toString(valorCarta));
                break;
        }
        int paloCarta = al.nextInt(4) + 1;
        switch (paloCarta) {
            case 1:
                this.setPalo("Corazones");
                break;
            case 2:
                this.setPalo("Picas") ;
                break;
            case 3:
                this.setPalo("Rombos");
                break;
            case 4:
                this.setPalo("Diamantes");
                break;
        }
    }

    public Carta(String rango, String palo) {
        this.rango = rango;
        this.palo = palo;
    }
    
     public int puntos(){
        int puntos;
        if(this.getRango().equals("A")){
            puntos = 1;
        }
        else if(this.getRango().equals("J") || this.getRango().equals("Q") || this.getRango().equals("K")){
            puntos = 10;
        }
        else{
            puntos = Integer.parseInt(this.getRango());
        }
        return puntos;
        
    }
      @Override
    public String toString() {
        return   rango + " de " + palo ;
    }
}

