package com.CloudDinner.Controller;

import com.CloudDinner.Service.TeamServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
public class TeamController {
    @Autowired
    private TeamServer teamServer;

    /**
     * 加入或者建立队伍之前查询该成员是否已经在某一队伍中
     *
     * @param request
     * @param modelAndView
     * @return
     */
    @PostMapping("/selectUserinTeam")
    public ModelAndView selectUserinTeam(HttpServletRequest request, ModelAndView modelAndView) {
        modelAndView.setViewName("Message");
        modelAndView.addObject("Message", teamServer.selectUserinTeam(request.getParameter("ID")));
        return modelAndView;
    }

    /**
     * 队长建立团队
     *
     * @param request
     * @param modelAndView
     * @return
     */
    @PostMapping("/insertTeam")
    public ModelAndView insertTeam(HttpServletRequest request, ModelAndView modelAndView) {
        modelAndView.setViewName("Message");
        modelAndView.addObject("Message", teamServer.insertTeam(request.getParameter("CAPTAIN_ID")));
        return modelAndView;
    }

    /**
     * 新增队员
     *
     * @param request
     * @param modelAndView
     * @return
     */
    @PostMapping("/updateTeamMember1")
    public ModelAndView updateTeamMember1(HttpServletRequest request, ModelAndView modelAndView) {
        modelAndView.setViewName("Message");
        modelAndView.addObject("Message",
                teamServer.updateTeamMember1(request.getParameter("teamId"),
                        request.getParameter("userId")));
        return modelAndView;
    }

    @PostMapping("/updateTeamMember2")
    public ModelAndView updateTeamMember2(HttpServletRequest request, ModelAndView modelAndView) {
        modelAndView.setViewName("Message");
        modelAndView.addObject("Message",
                teamServer.updateTeamMember2(request.getParameter("teamId"),
                        request.getParameter("userId")));
        return modelAndView;
    }

    @PostMapping("/updateTeamMember3")
    public ModelAndView updateTeamMember3(HttpServletRequest request, ModelAndView modelAndView) {
        modelAndView.setViewName("Message");
        modelAndView.addObject("Message",
                teamServer.updateTeamMember3(request.getParameter("teamId"),
                        request.getParameter("userId")));
        return modelAndView;
    }

    @PostMapping("/updateTeamMember4")
    public ModelAndView updateTeamMember4(HttpServletRequest request, ModelAndView modelAndView) {
        modelAndView.setViewName("Message");
        modelAndView.addObject("Message",
                teamServer.updateTeamMember4(request.getParameter("teamId"),
                        request.getParameter("userId")));
        return modelAndView;
    }

    @PostMapping("/updateTeamMember5")
    public ModelAndView updateTeamMember5(HttpServletRequest request, ModelAndView modelAndView) {
        modelAndView.setViewName("Message");
        modelAndView.addObject("Message",
                teamServer.updateTeamMember5(request.getParameter("teamId"),
                        request.getParameter("userId")));
        return modelAndView;
    }

    @PostMapping("/updateTeamMember6")
    public ModelAndView updateTeamMember6(HttpServletRequest request, ModelAndView modelAndView) {
        modelAndView.setViewName("Message");
        modelAndView.addObject("Message",
                teamServer.updateTeamMember6(request.getParameter("teamId"),
                        request.getParameter("userId")));
        return modelAndView;
    }

    @PostMapping("/updateTeamMember7")
    public ModelAndView updateTeamMember7(HttpServletRequest request, ModelAndView modelAndView) {
        modelAndView.setViewName("Message");
        modelAndView.addObject("Message",
                teamServer.updateTeamMember7(request.getParameter("teamId"),
                        request.getParameter("userId")));
        return modelAndView;
    }

    @PostMapping("/updateTeamMember8")
    public ModelAndView updateTeamMember8(HttpServletRequest request, ModelAndView modelAndView) {
        modelAndView.setViewName("Message");
        modelAndView.addObject("Message",
                teamServer.updateTeamMember8(request.getParameter("teamId"),
                        request.getParameter("userId")));
        return modelAndView;
    }

    /**
     * 删除队员(自己退出,或者队长踢出去)
     * @param request
     * @param modelAndView
     * @return
     */
    @PostMapping("/deleteTeamMember1")
    public ModelAndView deleteTeamMember1(HttpServletRequest request , ModelAndView modelAndView) {
        modelAndView.setViewName("Message");
        modelAndView.addObject("Message",
                teamServer.deleteTeamMember1(request.getParameter("teamId"),
                        request.getParameter("userId")));
        return modelAndView;
    }
    @PostMapping("/deleteTeamMember2")
    public ModelAndView deleteTeamMember2(HttpServletRequest request , ModelAndView modelAndView) {
        modelAndView.setViewName("Message");
        modelAndView.addObject("Message",
                teamServer.deleteTeamMember2(request.getParameter("teamId"),
                        request.getParameter("userId")));
        return modelAndView;
    }
    @PostMapping("/deleteTeamMember3")
    public ModelAndView deleteTeamMember3(HttpServletRequest request , ModelAndView modelAndView) {
        modelAndView.setViewName("Message");
        modelAndView.addObject("Message",
                teamServer.deleteTeamMember3(request.getParameter("teamId"),
                        request.getParameter("userId")));
        return modelAndView;
    }
    @PostMapping("/deleteTeamMember4")
    public ModelAndView deleteTeamMember4(HttpServletRequest request , ModelAndView modelAndView) {
        modelAndView.setViewName("Message");
        modelAndView.addObject("Message",
                teamServer.deleteTeamMember4(request.getParameter("teamId"),
                        request.getParameter("userId")));
        return modelAndView;
    }
    @PostMapping("/deleteTeamMember5")
    public ModelAndView deleteTeamMember5(HttpServletRequest request , ModelAndView modelAndView) {
        modelAndView.setViewName("Message");
        modelAndView.addObject("Message",
                teamServer.deleteTeamMember5(request.getParameter("teamId"),
                        request.getParameter("userId")));
        return modelAndView;
    }
    @PostMapping("/deleteTeamMember6")
    public ModelAndView deleteTeamMember6(HttpServletRequest request , ModelAndView modelAndView) {
        modelAndView.setViewName("Message");
        modelAndView.addObject("Message",
                teamServer.deleteTeamMember6(request.getParameter("teamId"),
                        request.getParameter("userId")));
        return modelAndView;
    }
    @PostMapping("/deleteTeamMember7")
    public ModelAndView deleteTeamMember7(HttpServletRequest request , ModelAndView modelAndView) {
        modelAndView.setViewName("Message");
        modelAndView.addObject("Message",
                teamServer.deleteTeamMember7(request.getParameter("teamId"),
                        request.getParameter("userId")));
        return modelAndView;
    }
    @PostMapping("/deleteTeamMember8")
    public ModelAndView deleteTeamMember8(HttpServletRequest request , ModelAndView modelAndView) {
        modelAndView.setViewName("Message");
        modelAndView.addObject("Message",
                teamServer.deleteTeamMember8(request.getParameter("teamId"),
                        request.getParameter("userId")));
        return modelAndView;
    }

    /**
     * 某人--查看队伍信息(先根据userid查id,再用id查)
     * @param request
     * @param modelAndView
     * @return
     */
    @PostMapping("/selectTeam")
    public ModelAndView selectTeam(HttpServletRequest request , ModelAndView modelAndView) {
        modelAndView.setViewName("Message");
        modelAndView.addObject("Message",
                teamServer.selectTeam(request.getParameter("userId")));
        return modelAndView;
    }

    /**
     * 删除队伍,队长关闭团队.或者队长退出团队时
     * @param request
     * @param modelAndView
     * @return
     */
    @PostMapping("/deleteTeam")
    public ModelAndView deleteTeam(HttpServletRequest request , ModelAndView modelAndView) {
        modelAndView.setViewName("Message");
        modelAndView.addObject("Message",
                teamServer.deleteTeam(request.getParameter("CAPTAIN_ID")));
        return modelAndView;
    }

}
