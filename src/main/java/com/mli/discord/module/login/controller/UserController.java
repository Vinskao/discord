package com.mli.discord.module.login.controller;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mli.discord.module.login.dto.LoginDTO;
import com.mli.discord.module.login.dto.UserIdDTO;
import com.mli.discord.module.login.model.User;
import com.mli.discord.module.login.service.UserService;

import io.swagger.v3.oas.annotations.Operation;

/**
 * 使用者控制器類，處理使用者相關的 HTTP 請求。
 * 
 * @version 1.0
 * @author D3031104
 */
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @Operation(summary = "用户登入")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO, HttpServletRequest request) {
        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();
        logger.info("User login attempt: {}", username);

        try {
            User authenticatedUser = userService.findByUsernameAndPassword(username, password);

            if (authenticatedUser != null) {
                HttpSession session = request.getSession(true);
                session.setAttribute("username", authenticatedUser.getUsername());
                logger.info("Username {} added to session with ID {}", username, session.getId());

                Enumeration<String> attributeNames = session.getAttributeNames();
                while (attributeNames.hasMoreElements()) {
                    String attributeName = attributeNames.nextElement();
                    logger.info("Session attribute: {} = {}", attributeName, session.getAttribute(attributeName));
                }
                return new ResponseEntity<>(authenticatedUser, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("帳號或密碼錯誤", HttpStatus.UNAUTHORIZED);
            }
        } catch (EmptyResultDataAccessException e) {
            logger.error("Error during User login: {}", e.getMessage());
            return new ResponseEntity<>("登入中出錯", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "用户登出")
    @PostMapping("/logout")
    @ResponseBody
    public ResponseEntity<String> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return new ResponseEntity<>("登出成功", HttpStatus.OK);
    }

    @Operation(summary = "檢查登入狀態")
    @PostMapping("/check-session")
    public String checkSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("authenticatedUser") != null) {
            // 用户已登录
            return "1";
        } else {
            // 用户未登录
            return "0";
        }
    }

    /**
     * 根據使用者 ID 查找使用者。
     * 
     * @param userId 使用者 ID
     * @return 匹配給定 ID 的使用者清單，如果找不到則返回空列表
     */
    @Operation(summary = "用user ID查詢")
    @PostMapping("/find-by-id")
    public List<User> findById(@RequestBody UserIdDTO userIdDTO) {
        logger.info("controller, userIdDTO: {}", userIdDTO);
        int userId = userIdDTO.getId();
        return userService.findById(userId);
    }

}
