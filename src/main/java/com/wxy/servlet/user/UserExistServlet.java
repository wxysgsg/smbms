package com.wxy.servlet;

import com.alibaba.fastjson.JSONObject;
import com.wxy.entity.SmbmsUser;
import com.wxy.service.LoginService;
import com.wxy.service.impl.LoginServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet("/jsp/userexist.do")
public class UserExistServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取页面参数
        String userCode = req.getParameter("userCode");
        //2.调用service
        LoginService loginService=new LoginServiceImpl();
        SmbmsUser smbmsUserByUserCode = loginService.getSmbmsUserByUserCode(userCode);
        HashMap hashMap=new HashMap();
        if(smbmsUserByUserCode==null){
            //用户不存在，可以使用
            hashMap.put("userCode","noexist");
        }else {
            //用户存在，不能使用
            hashMap.put("userCode","exist");
        }
        //3.ajax请求，不需要跳转，写回页面即可
        String jsonString = JSONObject.toJSONString(hashMap);
        resp.getWriter().write(jsonString);
    }
}
