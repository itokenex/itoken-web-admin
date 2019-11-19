package com.funtl.itoken.web.admin.controller;

import com.funtl.itoken.web.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/login")
    public String login(String accountId, String name, Model model){
        String login = adminService.login(accountId, name);
        System.out.println(login);

        model.addAttribute("message",login);
        return "index";
    }

    @RequestMapping(value="getUser",method = RequestMethod.GET)
    public String getUser(){
        return "user";
    }
}
