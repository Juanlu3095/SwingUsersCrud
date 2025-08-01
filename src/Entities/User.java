
package Entities;

public class User {
    int id;
    String nombre;
    String apellidos;
    String email;
    String dni;
    
    public User (int id, String nombre, String apellidos, String email, String dni) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.dni = dni;
    }
    
    public User() { // Sobrecarga de métodos
        
    }
    
    public int getId() {
        return id;
    }
    
    public String getNombre() {
        return this.nombre;
    }
    
    public String getApellidos() {
        return this.apellidos;
    }    
    
    public String getEmail() {
        return this.email;
    }
    
    public String getDni() {
        return this.dni;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setDni(String dni) {
        this.dni = dni;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", email=" + email + ", dni=" + dni + '}';
    }
}
