package com.wxy.servlet;

import com.wxy.entity.SmbmsUser;
import com.wxy.service.UserService;
import com.wxy.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/jsp/upduser.do")
public class UpdUserServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.接受页面值
        String id = req.getParameter("id");
        String userCode = req.getParameter("userCode");
        String userName = req.getParameter("userName");
        String userPassword = req.getParameter("userPassword");
        String gender = req.getParameter("gender");
        String birthday = req.getParameter("birthday");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");
        String userRole = req.getParameter("userRole");
        //2.调用service
        UserService userService = new UserServiceImpl();
        SmbmsUser smbmsUser = new SmbmsUser();
        smbmsUser.setId(Long.valueOf(id));
        smbmsUser.setUserCode(userCode);
        smbmsUser.setUserName(userName);
        smbmsUser.setUserPassword(userPassword);
        smbmsUser.setGender(Integer.valueOf(gender));
        smbmsUser.setPhone(phone);
        smbmsUser.setAddress(address);
        smbmsUser.setUserRole(Integer.valueOf(userRole));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = simpleDateFormat.parse(birthday);
            smbmsUser.setBirthday(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        userService.updSmbmsUser(smbmsUser);
        //3.跳转
        resp.sendRedirect("userlist.do");

    }
}
