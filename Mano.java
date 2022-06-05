/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BlackJack;

import java.util.ArrayList;


public class Mano {
    
    private ArrayList<Carta> mano;
    private int puntaje;

    public ArrayList<Carta> getMano() {
        return mano;
    }

    public void setMano(ArrayList<Carta> mano) {
        this.mano = mano;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public Mano() {
        ArrayList<Carta> mano = new ArrayList<>();
        this.setMano(mano);
        this.setPuntaje(0);
    }
    
      public void cogerCarta() {
        Carta carta = new Carta();
        this.getMano().add(carta);
    }
      
      public int contarPuntos(int x){
        for(int i = 0;i<this.getMano().size();i++){
            if(this.getMano().get(i).getRango().equals("A")){
                if(x<11){
                    x += this.getMano().get(i).puntos() + 10;
                }
                else{
                    x += this.getMano().get(i).puntos();
                }
            }
            else{
                x += this.getMano().get(i).puntos();
            }
            this.getMano().get(i).puntos();
        }
        return x;
    }
    
    
}
