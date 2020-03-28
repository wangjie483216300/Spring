package com.CloudDinner.Controller;

import com.CloudDinner.Model.Message.updateUserMessage;
import com.CloudDinner.Service.PersonCrudServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
public class PersonCrudController {
    @Autowired
    private PersonCrudServer personCrudServer;

    @RequestMapping("/selectUser")
    public ModelAndView selectUser(HttpServletRequest request, ModelAndView mv){
        mv.setViewName("Message");
        mv.addObject("Message",
                personCrudServer.selectUser(request.getParameter("ID"))
        );
        return mv;
    }
    @RequestMapping("/updateUser")
    public ModelAndView updateUser(HttpServletRequest request,ModelAndView mv){
        updateUserMessage u = new updateUserMessage();
        u.setID(request.getParameter("ID"));
        u.setUSER_NAME(request.getParameter("USER_NAME"));
        u.setUSER_SEX(request.getParameter("USER_SEX"));
        u.setUSER_TYELEPHONE(request.getParameter("USER_TYELEPHONE"));
        u.setUSER_ADDRESS(request.getParameter("USER_ADDRESS"));
        u.setRECE_NAME(request.getParameter("RECE_NAME"));
        mv.setViewName("Message");
        mv.addObject("Message",personCrudServer.updateUser(u));
        return mv;
    }
}
