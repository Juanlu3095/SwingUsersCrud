
package Database.Seeders;

import Database.Connection;
import io.github.cdimascio.dotenv.Dotenv;

public class MysqlSeed {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        String user = dotenv.get("DB_USER");
        String password = dotenv.get("DB_PASS");
        int port = Integer.parseInt(dotenv.get("DB_PORT"));
        String host = dotenv.get("DB_HOST");
        String db = dotenv.get("DB_NAME");
        
        Connection connection = new Connection(user, password, port, host, db);
        UserSeeder userSeeder = new UserSeeder(connection);
        userSeeder.createUsers();
    }
}
