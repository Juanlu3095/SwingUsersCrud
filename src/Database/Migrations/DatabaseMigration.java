
package Database.Migrations;

import Interfaces.ConnectionInterface;
import java.sql.SQLException;
import java.sql.PreparedStatement;

/**
* This class allows to create or delete the database.
*/
public class DatabaseMigration {
    private final ConnectionInterface connectionDatabase; // Este objeto Connection no puede contener DB_NAME
    private final String database;
    
    public DatabaseMigration (ConnectionInterface connection, String database) {
        this.connectionDatabase = connection;
        this.database = database;
    }
    
    public void migrateDB() {
        try {
            String query = "CREATE DATABASE IF NOT EXISTS " + this.database;
            PreparedStatement instruction = this.connectionDatabase.MysqlConnection().prepareStatement(query);
            instruction.executeUpdate();
            
            instruction.close();
            this.connectionDatabase.MysqlConnection().close();
            
            System.out.println("Base de datos creada con éxito.");
        } catch (SQLException e) {
            System.out.println("La base de datos no ha podido ser creada. Error: " + e.getMessage());
        }
    }
    
    public void deleteDB () {
        try {
            String query = "DROP DATABASE IF EXISTS " + this.database;
            PreparedStatement instruction = this.connectionDatabase.MysqlConnection().prepareStatement(query);
            instruction.executeUpdate();
            
            instruction.close();
            this.connectionDatabase.MysqlConnection().close();
            
            System.out.println("Base de datos eliminada con éxito.");
        } catch (SQLException e) {
            System.out.println("La base de datos no ha podido ser elimanada. Error: " + e.getMessage());
        }
    }
}
