/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Schemas;

import Entities.User;
import Entities.UserValidation;
import Utilities.Validations;

public class UserSchema {
    
    /**
     * It allows to validate a User object with no database intervention.
     * @param user
     * @return UserValidation
     */
    public static UserValidation validate(User user) {
        UserValidation validation = new UserValidation();
        validation.setSuccess(true);
        
        if(user.getNombre().isEmpty()) {
            validation.setSuccess(false);
            validation.setErrorNombre("El campo nombre está vacío.");
        }
        
        if(user.getApellidos().isEmpty()) {
            validation.setSuccess(false);
            validation.setErrorApellidos("El campo apellidos está vacío.");
        }
        
        if(user.getEmail().isEmpty()) {
            validation.setSuccess(false);
            validation.setErrorEmail("El campo email está vacío.");
        } else if (!Validations.validateEmail(user.getEmail())) {
            validation.setSuccess(false);
            validation.setErrorEmail("El campo email no tiene un formato correcto.");
        }
        
        if(user.getDni().isEmpty()) {
            validation.setSuccess(false);
            validation.setErrorDni("El campo dni está vacío.");
        } else if (!Validations.validateDni(user.getDni())) {
            validation.setSuccess(false);
            validation.setErrorDni("El campo dni no tiene un formato correcto.");
        }
            
        return validation;
    }
    
}
