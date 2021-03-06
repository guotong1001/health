package com.tech.health.handler;

import com.alibaba.fastjson.JSON;
import com.tech.health.config.MyFilterSecurityMetadataSource;
import com.tech.health.config.ReloadSecuritySource;
import com.tech.health.entity.Admin;
import com.tech.health.entity.Role;
import com.tech.health.entity.TreeMenu;
import com.tech.health.mapper.RoleMapper;
import com.tech.health.mapper.TreeMenuMapper;
import com.tech.health.service.RoleService;
import com.tech.health.util.AjaxResult;
import com.tech.health.util.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * @Auther: wdd
 * @Date: 2019/12/30 15:04
 * @Description:
 */
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private AjaxResult ajaxResult;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ReloadSecuritySource reloadSecuritySource;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        HttpSession session = request.getSession();
        Admin ad = (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        session.setAttribute(Const.ADMIN,ad);
        //???????????????????????????session
        if(session.getAttribute(Const.ROLE) == null){
            List<Role> roleList = roleService.selectAll();
            session.setAttribute(Const.ROLE,roleList);
        }

        // ????????????
        //reloadSecuritySource.getReloadSecuritySource();
        ajaxResult.ajaxTrue("????????????");
        String json = JSON.toJSONString(ajaxResult);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(json);
    }
}
