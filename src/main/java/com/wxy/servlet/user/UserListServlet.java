package com.wxy.servlet;

import com.github.pagehelper.PageInfo;
import com.wxy.entity.SmbmsRole;
import com.wxy.entity.SmbmsUser;
import com.wxy.service.SmbmsRoleService;
import com.wxy.service.UserService;
import com.wxy.service.impl.SmbmsRoleServiceImpl;
import com.wxy.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/jsp/userlist.do")
public class UserListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.接受页面传值
        String pageIndex = req.getParameter("pageIndex");
        String queryname = req.getParameter("queryname");
        String queryUserRole = req.getParameter("queryUserRole");
        //定义当前页
        int currentPageNo = 1;
        if(pageIndex!=null&&!"".equals(pageIndex)){
            currentPageNo = Integer.valueOf(pageIndex);
        }
        //2.调用service
        UserService userService=new UserServiceImpl();
        PageInfo<SmbmsUser> smbmsUserByPage = userService.getSmbmsUserByPage(currentPageNo, 5, queryname, queryUserRole);
        SmbmsRoleService smbmsRoleService=new SmbmsRoleServiceImpl();
        List<SmbmsRole> smbmsRoleAll = smbmsRoleService.getSmbmsRoleAll();

        HttpSession httpSession = req.getSession();
        httpSession.setAttribute("queryUserName", queryname);
        httpSession.setAttribute("queryUserRole", queryUserRole);
        httpSession.setAttribute("roleList", smbmsRoleAll);
        httpSession.setAttribute("userList", smbmsUserByPage.getList());

        httpSession.setAttribute("totalPageCount", smbmsUserByPage.getPages());
        httpSession.setAttribute("totalCount", smbmsUserByPage.getTotal());
        httpSession.setAttribute("currentPageNo", currentPageNo);
        //3.跳转页面
        resp.sendRedirect("userlist.jsp");
    }
}
