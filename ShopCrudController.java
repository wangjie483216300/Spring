package com.CloudDinner.Controller;

import com.CloudDinner.Model.SHOP_TABLE;
import com.CloudDinner.Service.ShopCrudServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import static com.CloudDinner.Controller.ControllerUtil.ModelAndViewUtil.getMv;
import static com.CloudDinner.Controller.ControllerUtil.RequestUtil.getParam;
import static com.CloudDinner.Controller.ControllerUtil.RequestUtil.setRequest;

@RestController
public class ShopCrudController {
    @Autowired
    private ShopCrudServer shopCrudServer;

    /**
     * 查看商店信息
     * @param request
     * @return
     */
    @PostMapping("/selectShop")
    public ModelAndView selectShop(HttpServletRequest request){
        setRequest(request);
        return getMv(shopCrudServer.selectShop(getParam("id")));
    }

    /**
     * 更新商店信息
     * @param request
     * @return
     */
    @PostMapping("/updateShop")
    public ModelAndView updateShop(HttpServletRequest request){
        setRequest(request);
        SHOP_TABLE shop_table = new SHOP_TABLE();
        shop_table.setID(getParam("id"));
        shop_table.setSHOP_NAME(getParam("shopname"));
        shop_table.setTELEPHONE(getParam("telephone"));
        shop_table.setSHOP_ADDRESS(getParam("address"));
        shop_table.setSHOP_INTRODUCTION(getParam("INTRODUCTION"));
        shop_table.setFOOD_TYPE(getParam("foodtype"));
        System.out.println(shop_table);
        return getMv(shopCrudServer.updateShop(shop_table));
    }
}
