package com.wxy.servlet;

import com.wxy.entity.SmbmsUser;
import com.wxy.service.UserService;
import com.wxy.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/jsp/viewuser.do")
public class ViewUserServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.接收页面传值
        String uid = req.getParameter("uid");
        String method = req.getParameter("method");
        //2.调用service
        UserService userService=new UserServiceImpl();
        SmbmsUser smbmsUserById = userService.getSmbmsUserById(uid);
        HttpSession httpSession = req.getSession();
        httpSession.setAttribute("user",smbmsUserById);
        if(method.equals("view")){
            resp.sendRedirect("userview.jsp");
        }else {
            resp.sendRedirect("usermodify.jsp");
        }
    }
}
