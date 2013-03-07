/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample.dao;

import samplecache.Users;
import java.util.List;

/**
 *
 * @author Adm
 */
public interface UsersDao {
    
    /**
     *
     * @
     */
    public boolean saveUser(final Users user);
    
    /**
     *
     * @return
     */
    public List<Users> getUsers();
    
    public boolean deleteUser(final Integer id);
    
}
