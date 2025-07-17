
package Models;

import Database.Connection;
import Entities.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserModel {
    private final Connection connection;
    
    public UserModel (Connection connection) {
        this.connection = connection; // Le pasamos la instancia de Connection directamente y no cada atributo de esa clase
    }
    
    public ArrayList<User> getAll () {
        ArrayList<User> users = new ArrayList<User>();
        try {
            String query = "SELECT * from users";
            PreparedStatement instruction = this.connection.MysqlConnection().prepareStatement(query);
            ResultSet result = instruction.executeQuery();
             
            while(result.next()) {
                User user = new User();
                user.setId(result.getInt("id"));
                user.setNombre(result.getString("nombre"));
                user.setApellidos(result.getString("apellidos"));
                user.setEmail(result.getString("email"));
                user.setDni(result.getString("dni"));
                
                users.add(user);
            }
            instruction.close();
            result.close();
            this.connection.MysqlConnection().close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users; // Si ha entrado en el catch, enviará un lista vacía
    }
    
    public User getById (int id) {
        try {
            String query = "SELECT * from users WHERE id = ?";
            PreparedStatement instruction = this.connection.MysqlConnection().prepareStatement(query);
            instruction.setInt(1, id); // El 1 marca que es el primer parámetro con ? en la consulta y el segundo parámetro es lo que le pasamos
            ResultSet result = instruction.executeQuery();
            
            User user = new User();
            if (result.next()) { // Como se espera sólo un resultado se usa "if" y no "while"
                user.setId(result.getInt("id"));
                user.setNombre(result.getString("nombre"));
                user.setApellidos(result.getString("apellidos"));
                user.setEmail(result.getString("email"));
                user.setDni(result.getString("dni"));
            }

            instruction.close();
            result.close();
            this.connection.MysqlConnection().close();
            
            if (user.getId() == 0) return null; // CUIDADO CON ESTO, SI NO DEVUELVE NULL, DEVOLVERÁ UN USER CON TODO NULL Y LOS INT 0
            
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean create ( User user) {
        
        try {
            String query = "INSERT into users (nombre, apellidos, email, dni) VALUES (?,?,?,?)";
            PreparedStatement instruction = this.connection.MysqlConnection().prepareStatement(query);
            instruction.setString(1, user.getNombre());
            instruction.setString(2, user.getApellidos());
            instruction.setString(3, user.getEmail());
            instruction.setString(4, user.getDni());
            instruction.executeUpdate();
            
            instruction.close();
            this.connection.MysqlConnection().close();
            return true;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Función que usa el caballero del vídeo
    public boolean update (User user) {
        try {
            String query = "UPDATE users SET nombre = ?, apellidos = ?, email = ?, dni = ? WHERE id = ?";
            PreparedStatement instruction = this.connection.MysqlConnection().prepareStatement(query);
            instruction.setString(1, user.getNombre());
            instruction.setString(2, user.getApellidos());
            instruction.setString(3, user.getEmail());
            instruction.setString(4, user.getDni());
            instruction.setInt(5, user.getId()); // Lo que dice el pavo
            instruction.executeUpdate();
            
            instruction.close();
            this.connection.MysqlConnection().close();
            return true;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Función que YO utilizo
    public boolean update (int id, User user) {
        try {
            String query = "UPDATE users SET nombre = ?, apellidos = ?, email = ?, dni = ? WHERE id = ?";
            PreparedStatement instruction = this.connection.MysqlConnection().prepareStatement(query);
            instruction.setString(1, user.getNombre());
            instruction.setString(2, user.getApellidos());
            instruction.setString(3, user.getEmail());
            instruction.setString(4, user.getDni());
            instruction.setInt(5, id);
            instruction.executeUpdate();
            
            instruction.close();
            this.connection.MysqlConnection().close();
            return true;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Eliminar usuario de la BD
    public boolean delete (int id) {
        try {
            String query = "DELETE FROM users WHERE id = ?";
            PreparedStatement instruction = this.connection.MysqlConnection().prepareStatement(query);
            instruction.setInt(1, id);
            instruction.executeUpdate();
            
            instruction.close();
            this.connection.MysqlConnection().close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public ArrayList<User> consultar ( String column, String columnValue ) {
        ArrayList<User> users = new ArrayList<>();
        
        try {
            String query = "SELECT * from users WHERE " +  column + " LIKE ?";
            // String query = "SELECT * from users WHERE " +  column + " LIKE concat('%', '"+columnValue+"', '%')"; // La forma del chico del video
            PreparedStatement instruction = this.connection.MysqlConnection().prepareStatement(query);
            instruction.setString(1, '%' + columnValue + '%'); // Con '%' decimos que lo que haya en esa posición no nos importa
            ResultSet result = instruction.executeQuery();
            
            // Vamos añadiendo al arraylist cada uno de los usuarios que se obtiene de la consulta sql
            while(result.next()) {
                User user = new User();
                user.setId(result.getInt("id"));
                user.setNombre(result.getString("nombre"));
                user.setApellidos(result.getString("apellidos"));
                user.setEmail(result.getString("email"));
                user.setDni(result.getString("dni"));
                
                users.add(user);
            }
            
            result.close();
            instruction.close();
            this.connection.MysqlConnection().close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users; // Puede devolverlo vacío si entre en el catch o si no hay users que cumplan las condiciones
    }
}
