package com.CloudDinner.Controller.ControllerUtil;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {
    public static HttpServletRequest request;
    public static HttpServletRequest getRequest() {
        return request;
    }
    public static void setRequest(HttpServletRequest request) {
        RequestUtil.request = request;
    }







    public static String getParam(String string){
        return request.getParameter(string);
    }


}
