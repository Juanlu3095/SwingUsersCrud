
package Feature;

import Controllers.UserController;
import Database.Connection;
import Database.Migrations.DatabaseMigration;
import Database.Migrations.UserTableMigration;
import Database.Seeders.UserSeeder;
import Models.UserModel;
import Database.Utilities.CustomDatabaseValidation;
import Entities.User;
import Views.CrearUsuarioView;
import Views.EditarUsuarioView;
import Views.ListaUsuarios;
import io.github.cdimascio.dotenv.Dotenv;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.mockito.Mockito;

public class UserControllerTest {
    private static final Dotenv dotenv = Dotenv.load();
    private static final String user = dotenv.get("DB_USER");
    private static final String password = dotenv.get("DB_PASS");
    private static final int port = Integer.parseInt(dotenv.get("DB_PORT"));
    private static final String host = dotenv.get("DB_HOST");
    private static final String db = dotenv.get("DB_NAME_TEST");
    
    private static ListaUsuarios listaUsuariosView;
    private static CrearUsuarioView createUsuarioView;
    private static EditarUsuarioView editarUsuarioView;
    
    static ListaUsuarios listadoSpy;
    static CrearUsuarioView createFormSpy;
    static EditarUsuarioView editFormSpy;
    
    private static CustomDatabaseValidation dbValidations;
    private static UserController userController;
    private static Connection connection;
    private static Connection connectionNoDb;
    private static UserModel userModel;
    private static DatabaseMigration dbMigration;
    private static UserTableMigration userMigration;
    private static UserSeeder userSeeder;
    
    @BeforeClass
    public static void setUp() {
        connection = new Connection(user, password, port, host, db);
        connectionNoDb = new Connection(user, password, port, host);
        
        dbValidations = new CustomDatabaseValidation(connection);
        dbMigration = new DatabaseMigration(connectionNoDb, db);
        userMigration = new UserTableMigration(connection);
        userSeeder = new UserSeeder(connection);
        
        listaUsuariosView = new ListaUsuarios();
        createUsuarioView = new CrearUsuarioView();
        editarUsuarioView = new EditarUsuarioView();
        
        listadoSpy = Mockito.spy(listaUsuariosView);
        createFormSpy = Mockito.spy(createUsuarioView);
        editFormSpy = Mockito.spy(editarUsuarioView);
        
        userModel = new UserModel(connection);
        userController = new UserController(userModel, listadoSpy, createFormSpy, editFormSpy, dbValidations);
        
        dbMigration.migrateDB();
        userMigration.migrateUsers();
        userSeeder.createUsers();
    }
    
    @AfterClass
    public static void tearDown() {
        dbMigration.deleteDB(); // Borra la base de datos al terminar. NO SE ESTÁ BORRANDO AL FINALIZAR LOS TESTS
    }
    
    @Before
    public void setUpEach() {
        Mockito.reset(this.listadoSpy, this.createFormSpy, this.editFormSpy);
    }
    
    @After
    public void tearDownEach() {
        
    }
    
    @Test
    public void getUsersTest() {
        ArrayList<User> users = userController.list();
        assertSame(users.size(), 3);
    }
    
    @Test
    public void getUserTest() {
        User user = new User();
        user.setId(1);
        user.setNombre("Gustavo");
        user.setApellidos("Jiménez");
        user.setEmail("gjimenez@gmail.com");
        user.setDni("18640413V");
        
        User userDB = userController.show(user.getId());
        
        // Si usamos assertEquals con los objetos directamente, se comprueba la referencia y no el contenido
        assertEquals(userDB.getId(), user.getId());
        assertEquals(userDB.getNombre(), user.getNombre());
        assertEquals(userDB.getApellidos(), user.getApellidos());
        assertEquals(userDB.getEmail(), user.getEmail());
        assertEquals(userDB.getDni(), user.getDni());
    }
    
    @Test
    public void getUserErrorTest() {
        
    }
    
    @Test
    public void createUserTest() {
        User user = new User();
        user.setNombre("Pepe");
        user.setApellidos("Alberto");
        user.setEmail("palberto@gmail.com");
        user.setDni("60245073T");
        
        boolean resultado = userController.store(user);
        assertTrue(resultado);
        
        User createdUser = userController.show(4);
        
        assertEquals(createdUser.getId(), 4);
        assertEquals(createdUser.getNombre(), user.getNombre());
        assertEquals(createdUser.getApellidos(), user.getApellidos());
        assertEquals(createdUser.getEmail(), user.getEmail());
        assertEquals(createdUser.getDni(), user.getDni());
    }
    
    @Test
    public void createUserErrorTest() {
        
    }
    
    @Test
    public void updateUserTest() {
        User user = new User();
        user.setId(3);
        user.setNombre("Pepe");
        user.setApellidos("Moreno López");
        user.setEmail("pmoreno@gmail.com");
        user.setDni("07670154E");
        
        boolean resultado = userController.update(user);
        
        assertTrue(resultado);
        
        User updatedUser = userController.show(3);
        
        assertEquals(updatedUser.getId(), 3);
        assertEquals(updatedUser.getNombre(), user.getNombre());
        assertEquals(updatedUser.getApellidos(), user.getApellidos());
        assertEquals(updatedUser.getEmail(), user.getEmail());
        assertEquals(updatedUser.getDni(), user.getDni());
    }
    
    @Test
    public void updateUserErrorTest() {
        
    }
    
    @Test
    public void deleteUserTest() {
        boolean resultado = userController.delete(3);
        
        assertTrue(resultado);
        
        
    }
    
    @Test
    public void deleteUserErrorTest() {
        
    }
    
}
