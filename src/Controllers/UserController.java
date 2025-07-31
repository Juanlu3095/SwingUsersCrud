
package Controllers;

import Database.Utilities.CustomDatabaseValidation;
import Entities.User;
import Entities.UserValidation;
import Interfaces.UserControllerInterface;
import Interfaces.UserCreateViewInterface;
import Interfaces.UserListViewInterface;
import Interfaces.UserModelInterface;
import Schemas.UserSchema;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class UserController implements UserControllerInterface, ActionListener {
    private final UserModelInterface userModel;
    private final UserListViewInterface listaUsuarios;
    private final UserCreateViewInterface crearUsuarioView;
    private final CustomDatabaseValidation databaseValidations;
    
    public UserController(UserModelInterface userModel, UserListViewInterface listaUsuarios, UserCreateViewInterface crearUsuarioView, CustomDatabaseValidation dbValidations) {
        this.userModel = userModel;
        this.listaUsuarios = listaUsuarios;
        this.crearUsuarioView = crearUsuarioView;
        this.databaseValidations = dbValidations;
        
        this.listaUsuarios.getFilterButton().addActionListener(this);
        this.listaUsuarios.getDeleteFilterButton().addActionListener(this);
        this.listaUsuarios.getExitButton().addActionListener(this);
        this.listaUsuarios.getNewUserButton().addActionListener(this);
        
        this.crearUsuarioView.getConfirmButton().addActionListener(this);
        this.crearUsuarioView.getCancelButton().addActionListener(this);
    }
    
    /**
    * It creates a view with all users.
    */
    @Override
    public void index () {
        // Obtenemos los usuarios
        ArrayList<User> users = this.list();
        
        // Añadimos los usuarios a la tabla
        this.addDataTable(users);
        this.listaUsuarios.setVisibleView(true);
    }
    
    /**
     * It lets to call a method through an action event in view like a button click.
     * @param e The event from the view which calls a method in this controller.
     */
    @Override
    public void actionPerformed (ActionEvent e) {
        // BOTÓN FILTRO
        if(e.getSource() == this.listaUsuarios.getFilterButton()) {
            ArrayList<User> users = this.filter(this.listaUsuarios.getColumnNameFilter(), this.listaUsuarios.getColumnValueFilter());
            
            // Añadimos los usuarios a la tabla
            this.addDataTable(users);
        }
        
        // BOTÓN LIMPIAR FILTROS. SE DEBERÍA CREAR UN MÉTODO PARA ENVIAR EL MODELO A LA VISTA
        else if (e.getSource() == this.listaUsuarios.getDeleteFilterButton()) {
            ArrayList<User> users = this.list();
        
            // Añadimos los usuarios a la tabla
            this.addDataTable(users);
        }
        
        // BOTÓN SALIR
        else if(e.getSource() == this.listaUsuarios.getExitButton()) {
            this.listaUsuarios.exitView();
        }
        
        // BOTÓN NUEVO USUARIO
        else if(e.getSource() == this.listaUsuarios.getNewUserButton()) {
            this.create();
        }
        
        // BOTÓN GUARDAR NUEVO USUARIO PARA LA VISTA CREAR USUARIO
        else if (e.getSource() == this.crearUsuarioView.getConfirmButton()) {
            User user = new User();
            
            user.setNombre(this.crearUsuarioView.getNombreTextField().getText());
            user.setApellidos(this.crearUsuarioView.getApellidosTextField().getText());
            user.setEmail(this.crearUsuarioView.getEmailTextField().getText());
            user.setDni(this.crearUsuarioView.geDniTextField().getText());
            
            UserValidation userValidation = UserSchema.validate(user);
            
            if(!userValidation.getSuccess()) {
                JOptionPane.showConfirmDialog(
                    null,
                    "Error en la validación. Errores: " + userValidation.getErrors().values(),
                    "Respuesta",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.ERROR_MESSAGE,
                    null
                );
                return;
            }
            
            int conteoEmail = databaseValidations.repeatedValues("users", "email", user.getEmail());
            int conteoDni = databaseValidations.repeatedValues("users", "dni", user.getDni());
            
            if(conteoEmail > 0) {
                JOptionPane.showConfirmDialog(
                    null,
                    "El campo email ya existe.",
                    "Respuesta",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.ERROR_MESSAGE,
                    null
                );
                return;
            } else if(conteoDni > 0) {
                JOptionPane.showConfirmDialog(
                    null,
                    "El campo dni ya existe.",
                    "Respuesta",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.ERROR_MESSAGE,
                    null
                );
                return;
            }
            
            boolean resultado = this.store(user);
            
            if (resultado) {
                JOptionPane.showConfirmDialog(
                    null,
                         "Usuario " + user.getNombre() + " " + user.getApellidos() + " registrado con éxito.",
                    "Respuesta",
                    JOptionPane.DEFAULT_OPTION, // Botones para aceptar o cancelar. En este caso, sólo OK
                    JOptionPane.INFORMATION_MESSAGE // Icono de info
                );
                
                ArrayList<User> updatedUsers = this.list();
                this.addDataTable(updatedUsers);
                
                this.crearUsuarioView.getNombreTextField().setText("");
                this.crearUsuarioView.getApellidosTextField().setText("");
                this.crearUsuarioView.getEmailTextField().setText("");
                this.crearUsuarioView.geDniTextField().setText("");

            } else {
                JOptionPane.showConfirmDialog(
                    null,
                    "Hubo un problema. Consulte con el administrador del sistema.",
                    "Respuesta",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.ERROR_MESSAGE,
                    null
                );
            }
        }
        
        // BOTÓN CANCELAR CREAR USUARIO
        else if (e.getSource() == this.crearUsuarioView.getCancelButton()) {
            this.crearUsuarioView.exitView();
        }
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
     * It updates the users from table
     * @param users The ArrayList of Users to inyect into view's table.
     */
    @Override
    public void addDataTable (ArrayList<User> users) {
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
        this.crearUsuarioView.setVisibleView(true);
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
