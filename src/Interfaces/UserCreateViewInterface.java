/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

public interface UserCreateViewInterface {
    public void setVisibleView (boolean option);
    
    public void exitView();
    
    public javax.swing.JTextField getNombreTextField(); // Devuelve el Text Field del nombre.
    
    public javax.swing.JTextField getApellidosTextField(); // Devuelve el Text Field de los apellidos.
    
    public javax.swing.JTextField getEmailTextField(); // Devuelve el Text Field del email.
    
    public javax.swing.JTextField geDniTextField(); // Devuelve el Text Field del dni.
    
    public javax.swing.JButton getConfirmButton(); // Obtiene el botón para confirmar la creación del usuario.
    
    public javax.swing.JButton getCancelButton(); // Obtiene el botón para cancelar y cerrar la ventana.
}
