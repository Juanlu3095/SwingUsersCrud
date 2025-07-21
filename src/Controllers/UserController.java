
package Controllers;

import Entities.User;
import Interfaces.UserControllerInterface;
import Interfaces.UserListViewInterface;
import Interfaces.UserModelInterface;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class UserController implements UserControllerInterface {
    private final UserModelInterface userModel;
    private final UserListViewInterface listaUsuarios;
    
    public UserController(UserModelInterface userModel, UserListViewInterface listaUsuarios) {
        this.userModel = userModel;
        this.listaUsuarios = listaUsuarios;
    }
    
    /**
    * It creates a view with all users.
    */
    @Override
    public void index () {
        // Obtenemos los usuarios
        ArrayList<User> users = this.list();
        
        // Añadimos los usuarios a la tabla
        String[] columnNames = {"id", "Nombre", "Apellidos", "Email", "DNI"}; // Nombres de las columnas de la tabla
        DefaultTableModel model = new DefaultTableModel(null, columnNames){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            
        };
        
        for(User user : users) { // Bucle ForEach para obtener los datos de un array
            // Array que contiene los datos de cada fila conforme avanzamos en el bucle for.
            Object[] data = new Object[columnNames.length]; // Al ser un array, le ponemos la longitud de las columnas para que aumente dinámicamente
            
            // Le damos valores a cada columna de cada fila de data:
            data[0] = user.getId();
            data[1] = user.getNombre();
            data[2] = user.getApellidos();
            data[3] = user.getEmail();
            data[4] = user.getDni();
            
            model.addRow(data); // Añadimos la fila de data al model de la tabla, por eso al usar new antes le habíamos puesto null
        }
        
        this.listaUsuarios.getTabla().setModel(model);
        this.listaUsuarios.setVisibleView(true);
    }
    
    /**
    * It returns a list of all users.
    * @return The list of users.
    */
    @Override
    public ArrayList<User> list() {
        ArrayList<User> users = this.userModel.getAll();
        return users;
    }
    
        
    /**
    * It updates an user in database.
    * @param column The name of the column in database to filter the users.
    * @param columnValue The value of the column passed to query to filter.
    * @return The list of users filtered.
    */
    @Override
    public ArrayList<User> filter ( String column, String columnValue ) {
        ArrayList<User> users = this.userModel.consultar(column, columnValue);
        return users;
    }

    /**
    * It returns one user by id.
    * @param id The id of the user
    * @return The user.
    */
    @Override
    public User show (int id) {
        User user = this.userModel.getById(id);
        return user;
    }
    
    /**
    * It creates the form to post a new user.
    */
    @Override
    public void create () {
        
    }
    
    /**
    * It creates a new user in database.
    * @param user The user data as an Object.
    * @return If the user was created or not.
    */
    @Override
    public boolean store(User user) {
        boolean respuesta = this.userModel.create(user);
        return respuesta;
    }
    
    /**
    * It creates the form to update a given user. (By id or passing all the user as Object)
    */
    @Override
    public void edit () {
        
    }
    
    /**
    * It updates an user in database.
    * @param id The id id of the user to update.
    * @param user The user data as an Object.
    * @return If the user was updated or not.
    */
    @Override
    public boolean update(int id, User user) {
        // SE COMPRUEBA PRIMERO QUE EXISTA EL USUARIO
        if (this.show(id) == null) {
            return false;
        }
        
        boolean respuesta = this.userModel.update(id, user);
        return respuesta;
    }
    
    /**
    * It deletes an user in database by id.
    * @param id The id id of the user to delete.
    * @return If the user was deleted or not.
    */
    @Override
    public boolean delete (int id) {
        // SE COMPRUEBA PRIMERO QUE EXISTA EL USUARIO
        if (this.show(id) == null) {
            return false;
        }
        
        boolean respuesta = this.userModel.delete(id);
        return respuesta;
    }
    
}
