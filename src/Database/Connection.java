
package Database;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection {
    protected String user;
    protected String password;
    protected int port;
    protected String host;
    protected String db;
    protected String url;
    
    public Connection (String user, String password, int port, String host, String db) {
        this.user = user;
        this.password = password;
        this.port = port;
        this.host = host;
        this.db = db;
        this.url = "jdbc:mysql://" + this.host + ":" + this.port + "/" + this.db + "?useSSL=false&serverTimezone=Europe/Madrid";
    }
    
    public java.sql.Connection MysqlConnection () {
        try {
            java.sql.Connection connection = DriverManager.getConnection(this.url, this.user, this.password);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace(); // Imprime el detalle del error
        }
        return null;
    }
}
