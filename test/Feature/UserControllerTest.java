
package Feature;

import Controllers.UserController;
import Database.Connection;
import Database.Migrations.DatabaseMigration;
import Database.Migrations.UserTableMigration;
import Database.Seeders.UserSeeder;
import Models.UserModel;
import Database.Utilities.CustomDatabaseValidation;
import io.github.cdimascio.dotenv.Dotenv;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserControllerTest {
    Dotenv dotenv = Dotenv.load();
    String user = dotenv.get("DB_USER");
    String password = dotenv.get("DB_PASS");
    int port = Integer.parseInt(dotenv.get("DB_PORT"));
    String host = dotenv.get("DB_HOST");
    String db = dotenv.get("DB_NAME_TEST");
    
    private CustomDatabaseValidation dbValidations;
    private UserController userController;
    private Connection connection;
    private UserModel userModel;
    private DatabaseMigration dbMigration;
    private UserTableMigration userMigration;
    private UserSeeder userSeeder;
    
    @BeforeClass
    public void setUp() {
        this.dbValidations = new CustomDatabaseValidation(connection);
        this.connection = new Connection(user, password, port, host, db);
        this.dbMigration = new DatabaseMigration(connection, db);
        this.userMigration = new UserTableMigration(connection);
        this.userSeeder = new UserSeeder(connection);
        this.userModel = new UserModel(connection);
        this.userController = new UserController(); // Hay que mockear las vistas que se le pasan al controlador
        
    }
    
    @AfterClass
    public void tearDown() {
        this.dbMigration.deleteDB(); // Borra la base de datos al terminar
    }
    
    @Test
    public void getUsersTest() {
        
    }
    
    @Test
    public void getUserTest() {
        
    }
    
    @Test
    public void createUserTest() {
        
    }
    
    @Test
    public void updateUserTest() {
        
    }
    
    @Test
    public void deleteUserTest() {
        
    }
    
}
