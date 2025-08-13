
package Entities;

import java.util.HashMap;
import java.util.Map;

public class UserValidation {
    private boolean success;
    private Map<String, String> errors = new HashMap<>();
    
    public UserValidation() {}
    
    public boolean getSuccess() {
        return this.success;
    }
    
    public Map<String, String> getErrors () {
        return errors;
    }

    // SETTER SUCCESS
    public void setSuccess(boolean success) {
        this.success = success;
    }
    
    // SETTERS ERRORS
    public void setErrorNombre (String errorNombre) {
        this.errors.put("nombre", errorNombre);
    }
    
    public void setErrorApellidos(String errorApellidos) {
        this.errors.put("apellidos", errorApellidos);
    }
    
    public void setErrorEmail (String errorEmail) {
        this.errors.put("email", errorEmail);
    }
    
    public void setErrorDni (String errorDni) {
        this.errors.put("dni", errorDni);
    }
    
}
