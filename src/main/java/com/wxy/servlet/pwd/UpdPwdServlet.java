package com.wxy.servlet;

import com.wxy.entity.SmbmsUser;
import com.wxy.service.UpdPwdService;
import com.wxy.service.impl.UpdPwdServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/jsp/updPwd.do")
public class UpdPwdServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.接收页面传值
        String newPassWord = req.getParameter("newpassword");
        HttpSession httpSession = req.getSession();
        Object userObj = httpSession.getAttribute("userSession");
        //2.调用service
        UpdPwdService updPwdService = new UpdPwdServiceImpl();
        SmbmsUser loginSmbmsUser = (SmbmsUser) userObj;
        String userCode = loginSmbmsUser.getUserCode();
        if (userObj != null) {
            //设置新密码
            loginSmbmsUser.setUserPassword(newPassWord);
            updPwdService.updPwd(loginSmbmsUser);
        }
        //3.跳转页面
        resp.sendRedirect("pwdmodify.jsp");
    }
}

