
package Database.Seeders;

import Entities.User;
import Interfaces.ConnectionInterface;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserSeeder {
    private final ConnectionInterface connection;
    
    public UserSeeder (ConnectionInterface connection) {
        this.connection = connection;
    }
    
    public void createUsers () {
        User[] users = new User[3];
        users[0] = new User("Gustavo", "Jiménez", "gjimenez@gmail.com", "18640413V");
        users[1] = new User("Jaime", "Pérez", "jperez@gmail.com", "53236154D");
        users[2] = new User("María", "Belmonte", "mbelmonte@gmail.com", "53354066T");
        
        try {
            String query = "INSERT into users (nombre, apellidos, email, dni) VALUES (?,?,?,?)";
            PreparedStatement instruction = this.connection.MysqlConnection().prepareStatement(query);
            
            for (User user : users) {
                instruction.setString(1, user.getNombre());
                instruction.setString(2, user.getApellidos());
                instruction.setString(3, user.getEmail());
                instruction.setString(4, user.getDni());
                instruction.executeUpdate();
            }
            
            instruction.close();
            this.connection.MysqlConnection().close();
            
            System.out.println("Usuarios creados con éxito.");
        } catch (SQLException e) {
            System.out.println("No se ha podido crear a los usuarios. Error: " + e.getMessage());
        }
    }
}
