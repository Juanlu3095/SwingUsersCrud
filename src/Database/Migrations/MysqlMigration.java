
package Database.Migrations;

import Database.Connection;
import io.github.cdimascio.dotenv.Dotenv;

/**
* This class executes all migrations for Mysql Database.
*/
public class MysqlMigration {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        String user = dotenv.get("DB_USER");
        String password = dotenv.get("DB_PASS");
        int port = Integer.parseInt(dotenv.get("DB_PORT"));
        String host = dotenv.get("DB_HOST");
        String db = dotenv.get("DB_NAME");
        
        Connection connectionNoDb = new Connection(user, password, port, host);
        Connection connection = new Connection(user, password, port, host, db);
        DatabaseMigration dbMigration = new DatabaseMigration(connectionNoDb, db);
        UserTableMigration userMigration = new UserTableMigration(connection);
        
        dbMigration.migrateDB();
        userMigration.migrateUsers();
    }
}
