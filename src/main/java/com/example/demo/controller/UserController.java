package com.example.demo.controller;

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.UUID;


@Controller
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request, Map<String, Object> map,
                        HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = userMapper.select(username, password);

        HttpSession session = request.getSession();

        if (user != null) {
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            Cookie cookie = new Cookie("token", token);
            cookie.setMaxAge(60 * 60 * 24 * 7);
            response.addCookie(cookie);

            session.setAttribute("user", user);

            map.put("msg", "登录成功");
            return "redirect:/display/type";
        } else {
            map.put("msg", "用户并未注册");
            return "login";

        }
    }
}
