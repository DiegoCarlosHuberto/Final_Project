package Casino;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Reel implements numbersInterface {

    private int num1;
    private int num2;
    private int num3;

    public Reel(int num1, int num2, int num3) {
        this.num1 = num1;
        this.num2 = num2;
        this.num3 = num3;
    }

    public Reel() {
    }

    public int getNum1() {
        return num1;
    }

    public int getNum2() {
        return num2;
    }

    public int getNum3() {
        return num3;
    }

    public void setNum1(int num1) {
        this.num1 = num1;
    }

    public void setNum2(int num2) {
        this.num2 = num2;
    }

    public void setNum3(int num3) {
        this.num3 = num3;
    }

    @Override
    public String toString() {
        return "Reel{" + "num1:" + num1 + ", num2:" + num2 + ", num3" + num3 + '}';
    }

    @Override
    public int getRandomNumber() {
        //método que genera un número aleatorio en el rango [0,140)
        int min = 0;
        int max = 140;

        return (int) ((Math.random() * (max - min)) + min);
    }

    @Override
    public int numberSelector(int i) {
        
        //Se atribuye un valor a ese número según el rango en el que se encuentre

        if (i < 5) {
            i = 1;
        } else if (i >= 5 && i <= 14) {
            i = 2;
        } else if (i >= 15 && i <= 29) {
            i = 3;
        } else if (i >= 30 && i <= 49) {
            i = 4;
        } else if (i >= 50 && i <= 74) {
            i = 5;
        } else if (i >= 75 && i <= 104) {
            i = 6;
        } else if (i >= 105 && i <= 139) {
            i = 7;
        }
        return i;

    }

    public static double rewardCalculator(Partida partida, Reel reel1, Reel reel2, Reel reel3) throws FileNotFoundException, IOException {
        //método de calculo de premios
        
        BufferedReader br = new BufferedReader (new FileReader("parameters.txt"));
        boolean eof = false;
        String reader;
        ArrayList parameters = new ArrayList();
        
        while (!eof){
            reader = br.readLine();
            if (reader != null){
                parameters.add(reader);
            }
            
            else{
                eof = true;
            }
        }
        
        //Se atribuyen los multiplicadores de las lineas segun lo establecido en el archivo de texto
        double bonus = Double.parseDouble((String) parameters.get(0));
        double multiplyer = Double.parseDouble ((String) parameters.get(1));
        double divisor = Double.parseDouble((String) parameters.get(2));
        double horizontal = Double.parseDouble((String) parameters.get(3));
        double diagonal = Double.parseDouble((String) parameters.get(4));
        double combinada = Double.parseDouble((String) parameters.get(5));
        
        br.close();
        
        double reward = 0;
        
        //Cálculo de premio según las combinaciones resultantes
        
        if (reel1.getNum1() == reel1.getNum2() && reel1.getNum1() == reel1.getNum3()) {

            if (reel1.getNum1() == 1) {
                reward = reward + (multiplyer * partida.getBet() * bonus) / reel1.getNum1();
            }
            reward = reward + (multiplyer * partida.getBet() * horizontal) / (reel1.getNum1() / divisor);

        }

        if (reel2.getNum1() == reel2.getNum2() && reel2.getNum1() == reel2.getNum3()) {

            if (reel2.getNum1() == 1) {
                reward = reward + (multiplyer * partida.getBet() * bonus) / reel2.getNum1();
            }
            reward = reward + (multiplyer * partida.getBet() * horizontal) / (reel2.getNum1() / divisor);

        }

        if (reel3.getNum1() == reel3.getNum2() && reel3.getNum1() == reel3.getNum3()) {

            if (reel3.getNum1() == 1) {
                reward = reward + (multiplyer * partida.getBet() * bonus) / reel3.getNum1();
            }
            reward = reward + (multiplyer * partida.getBet() * horizontal) / (reel3.getNum1() / divisor);

        }

        if (reel1.getNum1() == reel2.getNum2() && reel1.getNum1() == reel3.getNum3()) {

            if (reel1.getNum1() == 1) {
                reward = reward + (multiplyer * partida.getBet() * bonus) / reel1.getNum1();
            }
            reward = reward + (multiplyer * partida.getBet() * diagonal) / (reel1.getNum1() / divisor);

        }

        if (reel3.getNum1() == reel2.getNum2() && reel3.getNum1() == reel1.getNum3()) {

            if (reel3.getNum1() == 1) {
                reward = reward + (multiplyer * partida.getBet() * bonus) / reel3.getNum1();
            }
            reward = reward + (multiplyer * partida.getBet() * diagonal) / (reel3.getNum1() / divisor);

        }

        if (reel1.getNum1() == reel1.getNum2() && reel1.getNum1() == reel2.getNum3()) {

            if (reel1.getNum1() == 1) {
                reward = reward + (multiplyer * partida.getBet() * bonus) / reel1.getNum1();
            }
            reward = reward + (multiplyer * partida.getBet()) * combinada / (reel1.getNum1() / divisor);

        }

        if (reel3.getNum1() == reel3.getNum2() && reel3.getNum1() == reel2.getNum3()) {

            if (reel3.getNum1() == 1) {
                reward = reward + (multiplyer * partida.getBet() * bonus) / reel3.getNum1();
            }
            reward = reward + (multiplyer * partida.getBet() * combinada) / (reel3.getNum1() / divisor);

        }

        if (reel2.getNum1() == reel3.getNum2() && reel2.getNum1() == reel3.getNum3()) {

            if (reel2.getNum1() == 1) {
                reward = reward + (multiplyer * partida.getBet() * bonus) / reel2.getNum1();
            }
            reward = reward + (multiplyer * partida.getBet() * combinada) / (reel2.getNum1() / divisor);

        }

        if (reel2.getNum1() == reel1.getNum2() && reel2.getNum1() == reel1.getNum3()) {

            if (reel2.getNum1() == 1) {
                reward = reward + (multiplyer * partida.getBet() * bonus) / reel2.getNum1();
            }
            reward = reward + (multiplyer * partida.getBet() * combinada) / (reel2.getNum1() / divisor);

        }

        if (reel2.getNum1() == reel1.getNum2() && reel2.getNum1() == reel2.getNum3()) {

            if (reel2.getNum1() == 1) {
                reward = reward + (multiplyer * partida.getBet() * bonus) / reel2.getNum1();
            }
            reward = reward + (multiplyer * partida.getBet() * combinada) / (reel2.getNum1() / divisor);

        }

        if (reel2.getNum1() == reel3.getNum2() && reel2.getNum1() == reel2.getNum3()) {

            if (reel2.getNum1() == 1) {
                reward = reward + (multiplyer * partida.getBet() * bonus) / reel2.getNum1();
            }
            reward = reward + (multiplyer * partida.getBet() * combinada) / (reel2.getNum1() / divisor);

        }

        return reward;
    }

}
