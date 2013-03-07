/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample.dao;

import com.jalapeno.ApplicationContext;
import com.jalapeno.ObjectManager;
import samplecache.Users;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.sql.DataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Adm
 */
@Repository
public class UsersDaoImpl implements UsersDao {

    /* Contante para loggeo de la clase.*/
    private static final Logger LOG = Logger.getLogger(UsersDaoImpl.class);
    
    /* Contante para loggeo de la clase.*/
    private ObjectManager objectManager;

    @Autowired
    @Qualifier("dataSourceCache")
    public void setDataSourceFrom(final DataSource dataSource) throws Exception {
        this.objectManager = ApplicationContext.createObjectManager(dataSource.getConnection());
    }

    @Override
    public boolean saveUser(final Users user) {
        final boolean result = false;
        try {
            this.objectManager.startTransaction();
            this.objectManager.save(user, true);
            this.objectManager.commit();
        } catch (Exception ex) {
            LOG.error("Error al guardar el usuario:" + ex.getMessage(), ex);
            try {
                this.objectManager.rollback();
            } catch (Exception ex1) {
                LOG.error("Imposible realizar rollback de guardado de usuario:" + ex1.getMessage(), ex1);
            }
        }
        return result;
    }

    @Override
    public List<Users> getUsers() {
        List<Users> result = new ArrayList<Users>();
        try {

            Iterator users = this.objectManager.openByQuery(Users.class, null, null);
            while (users.hasNext()) {
                result.add((Users) users.next());
            }
        } catch (Exception ex) {
            LOG.error("Error al obtener los usuarios:" + ex.getMessage(), ex);
        }
        return result;
    }

    @Override
    public boolean deleteUser(final Integer id) {
        boolean result = false;
        try {
            Users user = (Users) this.objectManager.openByPrimaryKey(Users.class, id);
            this.objectManager.removeFromDatabase(user);
            result = true;
        } catch (Exception ex) {
            LOG.error("Error al eliminar el usuario:" + ex.getMessage(), ex);
        }
        return result;
    }
}
