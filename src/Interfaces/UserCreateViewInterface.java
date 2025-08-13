
package Interfaces;

public interface UserCreateViewInterface {
    public void setVisibleView (boolean option);
    
    public void exitView(); // Para cerrar la vista.
    
    public javax.swing.JTextField getNombreTextField(); // Devuelve el Text Field del nombre.
    
    public javax.swing.JTextField getApellidosTextField(); // Devuelve el Text Field de los apellidos.
    
    public javax.swing.JTextField getEmailTextField(); // Devuelve el Text Field del email.
    
    public javax.swing.JTextField getDniTextField(); // Devuelve el Text Field del dni.
    
    public javax.swing.JButton getConfirmButton(); // Obtiene el botón para confirmar la creación del usuario.
    
    public javax.swing.JButton getCancelButton(); // Obtiene el botón para cancelar y cerrar la ventana.
}
