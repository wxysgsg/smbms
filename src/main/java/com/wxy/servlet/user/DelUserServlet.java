package com.wxy.servlet;

import com.alibaba.fastjson.JSONObject;
import com.wxy.service.UserService;
import com.wxy.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet("/jsp/deluser.do")
public class DelUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.接收页面传值
        String uid = req.getParameter("uid");
        //2.调用service
        UserService userService=new UserServiceImpl();
        userService.delSmbmsUser(uid);
        HashMap hashMap=new HashMap();
        hashMap.put("delResult","true");
        String jsonString = JSONObject.toJSONString(hashMap);
        resp.getWriter().write(jsonString);
    }
}
