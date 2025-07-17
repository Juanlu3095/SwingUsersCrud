/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Usuario
 */
public class Validations {
    public static boolean validateDni (String dni) {
        if (dni.length() != 9) { // Colocar esto primero, de lo contrario, al definir letter se produce un error StringIndexOutOfBoundsException por el charAt, pues no existe ese indice si length no es 9
            return false;
        }
        
        char letter = dni.charAt(8);
        String numbers = dni.substring(0, 8);
        String letrasDNI = "TRWAGMYFPDXBNJZSQVHLCKE";
        
        int formatedNumbers;
        
        // Comprobamos que los números sean realmente números y no letras
        try {
            formatedNumbers = Integer.parseInt(numbers);
        } catch (NumberFormatException e) {
            return false;
        }
        
        int resto = formatedNumbers%23; // Divide los numeros del dni entre 23 para obtener la posicion del char en letrasDNI
        
        if (letrasDNI.charAt(resto) == Character.toUpperCase(letter)) { // Si coinciden el DNI es correcto
            return true;
        }
        
        return false;
    }
    
    public static boolean validateEmail (String email) {
        Pattern pattern = Pattern.compile("^([0-9a-zA-Z]+[-._+&])*[0-9a-zA-Z]+@([-0-9a-zA-Z]+[.])+[a-zA-Z]{2,6}$");
        Matcher matcher = pattern.matcher(email);
        
        return matcher.matches();
    }
}
