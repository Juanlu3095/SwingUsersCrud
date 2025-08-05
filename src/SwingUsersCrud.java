
import Controllers.UserController;
import Database.Connection;
import Database.Utilities.CustomDatabaseValidation;
import Models.UserModel;
import Views.CrearUsuarioView;
import Views.EditarUsuarioView;
import Views.ListaUsuarios;
import io.github.cdimascio.dotenv.Dotenv;

public class SwingUsersCrud {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        String user = dotenv.get("DB_USER");
        String password = dotenv.get("DB_PASS");
        int port = Integer.parseInt(dotenv.get("DB_PORT"));
        String host = dotenv.get("DB_HOST");
        String db = dotenv.get("DB_NAME");
        
        Connection connection = new Connection(user, password, port, host, db);
        UserModel userModel = new UserModel(connection);
        ListaUsuarios listaUsuarios = new ListaUsuarios();
        CrearUsuarioView crearNuevoUsuario = new CrearUsuarioView();
        EditarUsuarioView editarUsuario = new EditarUsuarioView();
        CustomDatabaseValidation databaseValidation = new CustomDatabaseValidation(connection);
        
        new UserController(userModel, listaUsuarios, crearNuevoUsuario, editarUsuario, databaseValidation).index();
        
    }
}
