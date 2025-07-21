/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import Entities.User;
import java.util.ArrayList;

public interface UserModelInterface {
    public ArrayList<User> getAll ();
    
    public ArrayList<User> consultar ( String column, String columnValue );
    
    public User getById (int id);
    
    public boolean create ( User user);
    
    public boolean update (User user);
    
    public boolean update (int id, User user);
    
    public boolean delete (int id);
}
