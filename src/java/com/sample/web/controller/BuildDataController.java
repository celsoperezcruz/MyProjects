/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sample.web.controller;

import com.sample.dao.UsersDao;
import samplecache.Users;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Adm
 */
@Controller
@RequestMapping("/")
public class BuildDataController {

    private final static Logger LOG = Logger.getLogger(BuildDataController.class);
    @Autowired
    private UsersDao userDao;

    @RequestMapping(value = "init.htm", method = RequestMethod.GET)
    public ModelAndView initialize(final HttpServletRequest request) {
        ModelAndView model = new ModelAndView("users");
        model.addObject("newUsers", new Users());
        model.addObject("listUsers", this.userDao.getUsers());
        return model;
    }

    @RequestMapping(value = "save.htm", method = RequestMethod.POST)
    public String insertUser(final HttpServletRequest request, final ModelMap map, @ModelAttribute("newUsers") final Users user) {
        LOG.info("Usuario insertado name:" + user.getName());
        this.userDao.saveUser(user);
        return "redirect:init.htm";
    }

    @RequestMapping(value = "delete.htm", method = RequestMethod.GET)
    public String deleteUser(final HttpServletRequest request, final ModelMap map, @ModelAttribute("id") final Integer id) {
        LOG.info("Usuario a eliminar:" + id);
        this.userDao.deleteUser(id);
        return "redirect:init.htm";
    }
}
