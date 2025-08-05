/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

public interface UserListViewInterface {
    public void setVisibleView (boolean option);
    
    public void exitView();
    
    public javax.swing.JTable getTabla(); // Devuelve la tabla a la que se le va a insertar los usuarios
    
    public javax.swing.JButton getFilterButton(); // Obtiene el botón para filtrar valores en la lista de usuarios
    
    public String getColumnNameFilter(); // Devuelve el nombre de la columna por la que se filtra
    
    public String getColumnValueFilter(); // Devuelve el valor por el que se filtra
    
    public javax.swing.JButton getDeleteFilterButton(); // Devuelve el botón para limpiar los filtros de la vista
    
    public javax.swing.JButton getExitButton(); // Devuelve el botón para salir de la aplicación
    
    public javax.swing.JButton getNewUserButton(); // Devuelve el botón para crear un nuevo usuario.
    
    public javax.swing.JMenuItem getTableMenuItemEditar(); // Devuelve la opción 'Editar' de la tabla en la lista de usuarios.
    
    public javax.swing.JMenuItem getTableMenuItemEliminar(); // Devuelve la opción 'Eliminhar' de la tabla en la lista de usuarios.
}
