
package Database.Migrations;

import Interfaces.ConnectionInterface;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
* This class allows to create or delete the user table in database.
*/
public class UserTableMigration {
    private final ConnectionInterface connectionDatabase;
    
    public UserTableMigration (ConnectionInterface connection) {
        this.connectionDatabase = connection;
    }
    
    public void migrateUsers () {
        try {
            String structure = "id INT AUTO_INCREMENT PRIMARY KEY, nombre VARCHAR(100) NOT NULL, apellidos VARCHAR(100) NOT NULL, "
                             + "dni VARCHAR(10) UNIQUE NOT NULL, email VARCHAR(100) UNIQUE NOT NULL";
            String query = "CREATE TABLE IF NOT EXISTS users (" + structure + ")";
            PreparedStatement instruction = this.connectionDatabase.MysqlConnection().prepareStatement(query);
            instruction.executeUpdate();
            
            instruction.close();
            this.connectionDatabase.MysqlConnection().close();
            
            System.out.println("Tabla users creada con éxito.");
        } catch (SQLException e) {
            System.out.println("La tabla users no ha podido ser creada. Error: " + e.getMessage());
        }
    }
    
    public void deleteUsers () {
        try {
            String query = "DROP TABLE IF EXISTS users";
            PreparedStatement instruction = this.connectionDatabase.MysqlConnection().prepareStatement(query);
            instruction.executeUpdate();
            
            instruction.close();
            this.connectionDatabase.MysqlConnection().close();
            
            System.out.println("Tabla users eliminada con éxito.");
        } catch (SQLException e) {
            System.out.println("La tabla users no ha podido ser eliminada. Error: " + e.getMessage());
        }
    }
}
