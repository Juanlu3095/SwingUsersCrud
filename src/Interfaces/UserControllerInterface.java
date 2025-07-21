/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import Entities.User;
import java.util.ArrayList;

public interface UserControllerInterface {
    public void index();
    
    public ArrayList<User> list();
    
    public ArrayList<User> filter ( String column, String columnValue );
    
    public User show (int id);
    
    public void create ();
    
    public boolean store(User user);
    
    public void edit();
    
    public boolean update(int id, User user);
    
    public boolean delete (int id);
}
