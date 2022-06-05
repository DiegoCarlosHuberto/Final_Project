package Casino;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable{
    
    //Creación de clase 'User'

    private int id;
    private String password;
    private String nombre;
    private String apellido;
    private String dni;
    private String fechaNacimiento;
    private double credito;

    public User(int id, String password, String nombre, String apellido, String dni, String fechaNacimiento, double credito) {
        this.setId(id);
        this.setPassword(password);
        this.setNombre(nombre);
        this.setApellido(apellido);
        this.setDni(dni);
        this.setFechaNacimiento(fechaNacimiento);
        this.setCredito(credito);
    }
    
    public User(){
    }


    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.id != other.id) {
            return false;
        }
        if (Double.doubleToLongBits(this.credito) != Double.doubleToLongBits(other.credito)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.apellido, other.apellido)) {
            return false;
        }
        if (!Objects.equals(this.dni, other.dni)) {
            return false;
        }
        if (!Objects.equals(this.fechaNacimiento, other.fechaNacimiento)) {
            return false;
        }
        return true;
    }
    
    

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
 
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDni() {
        return dni;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public double getCredito() {
        return credito;
    }

    public void setCredito(double credito) {
        this.credito = credito;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", password=" + password + ", nombre=" + nombre + ", apellido=" + apellido + ", dni=" + dni + ", fechaNacimiento=" + fechaNacimiento + ", credito=" + credito + '}';
    }

    public String toStringNombre() {
        return "" + nombre;
    }
    
    public String toStringPassword(){
        return "" + password;
    }

    public String toStringFullName() {
        return "" + nombre + " " + apellido;
    }

    public String toStringDNI() {
        return "" + dni;
    }

    public String toStringNacimiento() {
        return "" + fechaNacimiento;
    }
    
    public String toStringCredito(){
        return "" + credito;
    }
    
    public String toStringId(){
        return "" + id;
    }
    
}
