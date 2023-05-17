package com.wxy.servlet;

import com.alibaba.fastjson.JSONObject;
import com.wxy.entity.SmbmsUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

@WebServlet("/jsp/validatePwd.do")
public class ValidatePwdServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.接受页面的值
        String oldPassWorld = req.getParameter("oldpassword");
        HttpSession httpSession = req.getSession();
        //获取登陆时的用户
        Object userObj = httpSession.getAttribute("userSession");
        HashMap hashMap=new HashMap();
        if(userObj!=null){
            SmbmsUser loginSmbmsUser = (SmbmsUser)userObj;
            if(oldPassWorld.equals(loginSmbmsUser.getUserPassword())){
                hashMap.put("result", "true");
            }else {
                hashMap.put("result", "false");
            }
        }
        //{"result":"true"}
        String jsonStr = JSONObject.toJSONString(hashMap);
        resp.getWriter().write(jsonStr);
    }
}
