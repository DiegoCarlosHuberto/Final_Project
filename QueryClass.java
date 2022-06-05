package Casino;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QueryClass {

    private static final String JDBC = "jdbc:mysql://localhost:3306/casino";
    
    public static boolean userExists (User user) throws SQLException{
        boolean exists = true;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(QueryClass.class.getName()).log(Level.SEVERE, null, ex);
        }

        Connection con = DriverManager.getConnection(JDBC, "root", "43226225w");
        
        Statement st = con.createStatement();
        //Ejecuta el "procedure" que verifica si el usuario existe en la bbdd
        CallableStatement cstmt = con.prepareCall("{CALL existe(? , ?)}");

        cstmt.setString(1, user.getDni());

        cstmt.registerOutParameter(2, Types.BOOLEAN);

        cstmt.execute();

        if (cstmt.getInt(2) == 0) {
            exists = false;
            
        }

        cstmt.close();
        return exists;
        
    }
    public static boolean login(User user) throws SQLException {
        
        //metodo que verifica el login
        boolean login = true;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(QueryClass.class.getName()).log(Level.SEVERE, null, ex);
        }

        Connection con = DriverManager.getConnection(JDBC, "root", "43226225w");

        String psswd = "SELECT contrasenia FROM password WHERE password.idUsuario = (SELECT id FROM usuario WHERE usuario.DNI = " + "\"" + user.getDni().toUpperCase() + "\");";

        Statement st = con.createStatement();
        
        login = userExists(user);
        
        //Si el usuario existe, verifica que la contraseña introducida sea correcta
        if (login == true) {
            
            ResultSet rs = st.executeQuery(psswd);

            while (rs.next()) {
                String password = rs.getString("contrasenia");

                if (!user.getPassword().equals(password)) {
                    login = false;
                    
                }
            }
        }
        
        st.close();
        
        return login;
    }

    public static User userData(User user) throws SQLException {
        
        //método que rellena la información del usuario desde la bbdd
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(QueryClass.class.getName()).log(Level.SEVERE, null, ex);
        }

        Connection con = DriverManager.getConnection(JDBC, "root", "43226225w");
        
        //Queries a realizar
        String query = "SELECT * FROM datosusuario WHERE DNI =" + "\"" + user.getDni() + "\";";
        String query1 = "SELECT id FROM usuario WHERE DNI =" + "\"" + user.getDni() + "\";";

        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            //introducción de los valores resultantes en los atributos de 'user'
            user.setNombre(rs.getString("Nombre"));
            user.setApellido(rs.getString("Apellido"));
            user.setCredito(Double.parseDouble(rs.getString("Credito")));
            user.setFechaNacimiento(rs.getString("fechaNacimiento"));

        }

        rs = st.executeQuery(query1);

        while (rs.next()) {
            //el id lo recogemos desde otra tabla
            user.setId(Integer.parseInt(rs.getString("id")));
        }
        
        con.close();
        return user;

    }

    public static void updateDB(int idUser, Partida partida) throws SQLException {
        //Método que inserta las partidas en la bbdd
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(QueryClass.class.getName()).log(Level.SEVERE, null, ex);
        }

        Connection con = DriverManager.getConnection(JDBC, "root", "43226225w");

        Statement st = con.createStatement();
        
        System.out.println(partida.toStringDateTime());
        
        //insert con los datos de la partida
        String update = "INSERT INTO partida VALUES (" + 0 + ", " + partida.getIdJuego() + ", " + idUser + ", " + partida.getBet() + "," + partida.getBalance() + ",'" + partida.toStringDateTime() + "');";

        st.executeUpdate(update);
        
        con.close();

    }

    public static void newUser(User user) throws SQLException {
        
        //método de creación de usuario
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(QueryClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Connection con = DriverManager.getConnection(JDBC, "root", "43226225w");

        Statement st = con.createStatement();
        
        //Inserts en la base de datos con los datos del nuevo user
        String insert1 = "INSERT INTO datosusuario VALUES (\"" + user.getDni().toUpperCase() + "\", \"" + user.getFechaNacimiento() + "\", \"" + user.getNombre() + "\", \"" + user.getApellido() + "\"," + user.getCredito() + ");";
        String insert2 = "INSERT INTO usuario (DNI) VALUES (\"" + user.getDni().toUpperCase() + "\");";
        String insert3 = "INSERT INTO password VALUES ((SELECT id FROM usuario WHERE DNI = \"" + user.getDni().toUpperCase() + "\"), \"" + user.getPassword() + "\");";

        st.executeUpdate(insert1);
        st.executeUpdate(insert2);
        st.executeUpdate(insert3);
        
        //Cierre de la conexión
        con.close();
    }
    
    public static void InsertCredit(double credit, String DNI) throws SQLException{
        
        //método que permite al usuario introducir crédito en su cuenta
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(QueryClass.class.getName()).log(Level.SEVERE, null, ex);
        }

        Connection con = DriverManager.getConnection(JDBC, "root", "43226225w");

        Statement st = con.createStatement();
        
        //Realizamos el update con el crédito que el usuario haya introducido
        String query = "UPDATE datosusuario SET Credito = " + credit + " WHERE DNI = '" + DNI + "';";
        
        st.executeUpdate(query);
        
        con.close();
        
    }
    

    public static boolean banCheck(User user) throws SQLException{
        
        //método que comprueba si el usuario se encuentra baneado
        boolean ban = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(QueryClass.class.getName()).log(Level.SEVERE, null, ex);
        }

        Connection con = DriverManager.getConnection(JDBC, "root", "43226225w");
        
        CallableStatement cstmt = con.prepareCall("{CALL banCheck(? , ?)}");

        cstmt.setString(1, user.toStringId() );

        cstmt.registerOutParameter(2, Types.BOOLEAN);

        cstmt.execute();

        if (cstmt.getInt(2) == 1) {
            ban = true;
        }
        
        con.close();
        
        return ban;
    }

    public static boolean deleteUser(String DNI, String password) throws SQLException{
        
        //método que borra un usuario de la bbdd
        User user = new User(0, password, "", "", DNI, "", 0.0);
        boolean delete = true;
        
        //Comprobamos que el usuario y contraseña introducidos antes de borrar son correctos. Si lo son, realizamos el delete
        if(login(user) == true){
        
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(QueryClass.class.getName()).log(Level.SEVERE, null, ex);
            }

            Connection con = DriverManager.getConnection(JDBC, "root", "43226225w");

            Statement st = con.createStatement();
            
            //Realizamos el delete en la bbdd
            String query = "DELETE FROM usuario WHERE DNI = '" + DNI + "';";
            
            
            st.executeUpdate(query);
            
            con.close();
        
        }
        else{
            delete = false;
        }
        
        
        return delete;

    }
    
    

}
