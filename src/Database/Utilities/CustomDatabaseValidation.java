/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database.Utilities;

import Database.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomDatabaseValidation {
    private Connection connection;
    
    public CustomDatabaseValidation (Connection connection) {
        this.connection = connection;
    }
    
    public int repeatedValues (String table, String column, String value) {
        int conteo = 0;
        try {
            String query = "SELECT COUNT(*) from " + table + " WHERE " +  column + " = ?";
            PreparedStatement instruction = this.connection.MysqlConnection().prepareStatement(query);
            instruction.setString(1, value);
            ResultSet result = instruction.executeQuery();
            if(result.next()) {
                conteo = result.getInt(1);
            }
            
            instruction.close();
            result.close();
            this.connection.MysqlConnection().close();
            return conteo;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
