
package Controllers;

import Database.Connection;
import Entities.User;
import Models.UserModel;
import Views.ListaUsuarios;
import java.util.ArrayList;

public class UserController {
    private final UserModel userModel;
    
    public UserController(UserModel userModel) {
        this.userModel = userModel;
    }
    
    /**
    * It creates a view with all users.
    */
    public void index () {
        ArrayList<User> users = this.list();
        new ListaUsuarios(users);
    }
    
        
    /**
    * It updates an user in database.
    * @param column The name of the column in database to filter the users.
    * @param columnValue The value of the column passed to query to filter.
    * @return The list of users filtered.
    */
    public ArrayList<User> filter ( String column, String columnValue ) {
        ArrayList<User> users = this.userModel.consultar(column, columnValue);
        return users;
    }
    
    /**
    * It returns a list of all users.
    * @return The list of users.
    */
    public ArrayList<User> list() {
        ArrayList<User> users = this.userModel.getAll();
        return users;
    }
    
    /**
    * It returns one user by id.
    * @param id The id of the user
    * @return The user.
    */
    public User show (int id) {
        User user = this.userModel.getById(id);
        return user;
    }
    
    /**
    * It creates the form to post a new user.
    */
    public void create () {
        
    }
    
    /**
    * It creates a new user in database.
    * @param user The user data as an Object.
    * @return If the user was created or not.
    */
    public boolean store(User user) {
        boolean respuesta = this.userModel.create(user);
        return respuesta;
    }
    
    /**
    * It creates the form to update a given user. (By id or passing all the user as Object)
    */
    public void edit () {
        
    }
    
    /**
    * It updates an user in database.
    * @param id The id id of the user to update.
    * @param user The user data as an Object.
    * @return If the user was updated or not.
    */
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
    public boolean delete (int id) {
        // SE COMPRUEBA PRIMERO QUE EXISTA EL USUARIO
        if (this.show(id) == null) {
            return false;
        }
        
        boolean respuesta = this.userModel.delete(id);
        return respuesta;
    }
    
}
