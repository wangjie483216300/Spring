package com.CloudDinner.Controller.ControllerUtil;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import static com.CloudDinner.Controller.ControllerUtil.RequestUtil.setRequest;

public class ModelAndViewUtil {
    public static ModelAndView getMv(Object object){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("Message");
        modelAndView.addObject("Message",object);
        return modelAndView;
    }
}
