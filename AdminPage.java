package admincode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class AdminPage {

    static Scanner teclado = new Scanner(System.in);
    static final String JDBC = "jdbc:mysql://162.16.66.62:3306/casino";
    static final String BDuser = "admin";
    static final String SBpsw = "cashConcos";

    public static void main(String[] args) throws SQLException {

        boolean menu = true;
        while (menu) {
            System.out.println("\n----------------------------------"
                    + "BIENVENIDO A LA PÁGINA DE CONTROL"
                    + "----------------------------------");

            System.out.println("1. REALIZAR MODIFICACIONES BASE DE DATOS"
                    + "\n2. CONSULTAR LA BASE DE DATOS"
                    + "\n3. VER BALANCE ENTRE DOS FECHAS"
                    + "\n4. BANEAR UN USUARIO"
                    + "\n5. CERRAR"
                    + "\n ----------------------------------");

            boolean path = teclado.hasNextInt();
            int option;
            while (path != true) {
                System.out.println("La opción escogida no es correcta.");
                System.out.println("1. REALIZAR MODIFICACIONES BASE DE DATOS"
                    + "\n2. CONSULTAR LA BASE DE DATOS"
                    + "\n3. VER BALANCE ENTRE DOS FECHAS"
                    + "\n4. BANEAR UN USUARIO"
                    + "\n5. CERRAR"
                    + "\n ----------------------------------");
                path = teclado.hasNextInt();
                teclado.nextLine();
            }
            option = teclado.nextInt();
            switch (option) {
                case 1:
                    modify();
                    break;
                case 2:
                    query();
                    break;
                case 3:
                    balance();
                    break;
                case 4:
                    banearUser();
                case 5: 
                    menu = false;
                default: break;
            }
        }

    }

    public static void modify() throws SQLException {

        String query;
        System.out.println("Ingresa a continuación la modificación a realizar:"
                + "\n!!ADVERTENCIA!!"
                + "\n Si vas a hacer un 'DELETE', no olvides el 'WHERE' :D");
        query = teclado.nextLine();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error:" + ex);
        }

        Connection con = DriverManager.getConnection(JDBC, BDuser, SBpsw);

        Statement st = con.createStatement();

        int result = st.executeUpdate(query);

        System.out.println("Se han modificado " + result + "columnas.");
    }

    public static void query() throws SQLException {

        String query;
        teclado.nextLine();
        System.out.println("Ingresa a continuación la consulta a realizar: ");
        query = teclado.nextLine();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error:" + ex);
        }

        Connection con = DriverManager.getConnection(JDBC, BDuser, SBpsw);

        Statement st = con.createStatement();

        ResultSet rs = st.executeQuery(query);
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnas = rsmd.getColumnCount();
        while (rs.next()) {

            for (int i = 1; i <= columnas; i++) {
                if (i > 1) {
                    System.out.print(" | ");
                }
                String columnValue = rs.getString(i);
                System.out.print(rsmd.getColumnName(i) + " : " + columnValue);
            }
            System.out.println("");
        }

    }

    public static void balance() throws SQLException {

        teclado.reset();

        System.out.println("AVISO IMPORTANTE!"
                + "\nRECUERDA QUE EL FORMATO CORRECTO ES: yyyy/MM/dd HH:mm:ss");

        teclado.nextLine();
        System.out.println("Desde:");
        String desde = teclado.nextLine();

        System.out.println("Hasta: ");
        String hasta = teclado.nextLine();

        String query = "SELECT ( sum(balance) * (-1) ) AS Balance FROM partida WHERE fechaHora BETWEEN '" + desde + "' AND '" + hasta + "';";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error:" + ex);
        }

        Connection con = DriverManager.getConnection(JDBC, BDuser, SBpsw);

        Statement st = con.createStatement();

        ResultSet rs = st.executeQuery(query);
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnas = rsmd.getColumnCount();
        while (rs.next()) {

            for (int i = 1; i <= columnas; i++) {
                if (i > 1) {
                    System.out.print(" | ");
                }
                String columnValue = rs.getString(i);
                System.out.print(rsmd.getColumnName(i) + " : " + columnValue);
            }
            System.out.println("");
        }

    }
    
    public static void banearUser() throws SQLException{
        
        teclado.reset();
        
        teclado.nextLine();
        System.out.println("Ingresa el DNI del usuario a banear");
        String DNI = teclado.nextLine();
        
        System.out.println("Ingresa el motivo del baneo: ");
        String motivo = teclado.nextLine();
        
        String query = "INSERT INTO bannedUser VALUES (0, 1, \"" + motivo + "\", (SELECT id FROM usuario WHERE DNI = '" + DNI + "');";
        
         try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error:" + ex);
        }

        Connection con = DriverManager.getConnection(JDBC, BDuser, SBpsw);

        Statement st = con.createStatement();

        st.executeUpdate(query);
        
        System.out.println("Se ha introducido el valor correctamente!");
    }

}
