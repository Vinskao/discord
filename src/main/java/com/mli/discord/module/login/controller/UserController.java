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

import com.mli.discord.module.login.dao.UserRepository;
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

    @Autowired
    private UserRepository userRepository;

    /**
     * 使用者登入
     * 
     * @param loginDTO 登入資訊的資料傳輸物件
     * @param request  HTTP 請求物件
     * @return ResponseEntity 包含認證使用者資訊或錯誤訊息的回應實體
     */
    @Operation(summary = "使用者登入")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO, HttpServletRequest request) {
        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();
        logger.info("使用者登入嘗試：{}", username);

        try {
            User authenticatedUser = userService.findByUsernameAndPassword(username, password);

            if (authenticatedUser != null) {
                HttpSession session = request.getSession(true);
                session.setAttribute("username", authenticatedUser.getUsername());
                session.setAttribute("authenticatedUser", authenticatedUser);
                logger.info("使用者名稱 {} 新增到會話，會話 ID 為 {}", username, session.getId());

                Enumeration<String> attributeNames = session.getAttributeNames();
                while (attributeNames.hasMoreElements()) {
                    String attributeName = attributeNames.nextElement();
                    logger.info("會話屬性: {} = {}", attributeName, session.getAttribute(attributeName));
                }
                return new ResponseEntity<>(authenticatedUser, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("帳號或密碼錯誤", HttpStatus.UNAUTHORIZED);
            }
        } catch (EmptyResultDataAccessException e) {
            logger.error("使用者登入期間出錯: {}", e.getMessage());
            return new ResponseEntity<>("登入中出錯", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 使用者登出
     * 
     * @param request HTTP 請求物件
     * @return ResponseEntity 包含登出成功訊息的回應實體
     */
    @Operation(summary = "使用者登出")
    @PostMapping("/logout")
    @ResponseBody
    public ResponseEntity<String> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return new ResponseEntity<>("登出成功", HttpStatus.OK);
    }

    /**
     * 檢查使用者登入狀態
     * 
     * @param request HTTP 請求物件
     * @return 字串表示使用者是否登入的狀態碼，"1" 表示已登入，"0" 表示未登入
     */
    @Operation(summary = "檢查登入狀況")
    @PostMapping("/check-session")
    public String checkSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("authenticatedUser") != null) {
            // 使用者已登入
            return "1";
        } else {
            // 使用者未登入
            return "0";
        }
    }

    /**
     * 根據使用者 ID 查詢使用者資訊
     * 
     * @param userIdDTO 使用者 ID 的資料傳輸物件
     * @return 匹配給定 ID 的使用者列表，如果找不到則返回空列表
     */
    @Operation(summary = "用使用者 ID 查詢")
    @PostMapping("/find-by-id")
    public List<User> findById(@RequestBody UserIdDTO userIdDTO) {
        logger.info("控制器，使用者 ID 資料傳輸物件: {}", userIdDTO);
        int userId = userIdDTO.getId();
        return userService.findById(userId);
    }

    /**
     * 註冊使用者
     * 
     * @param loginDTO 註冊資訊的資料傳輸物件
     * @return ResponseEntity 包含註冊成功或失敗訊息的回應實體
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody LoginDTO loginDTO) {
        int result = userRepository.createUser(loginDTO.getUsername(), loginDTO.getPassword());

        if (result == 1) {
            return ResponseEntity.ok("使用者成功註冊");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("註冊失敗");
        }
    }

    /**
     * 更新使用者密碼
     * 
     * @param loginDTO 包含使用者名稱和新密碼的資料傳輸物件
     * @return ResponseEntity 包含密碼更新成功或失敗訊息的回應實體
     */
    @PostMapping("/update-password")
    public ResponseEntity<?> updatePassword(@RequestBody LoginDTO loginDTO) {
        boolean updated = userService.updatePassword(loginDTO.getUsername(), loginDTO.getPassword());
        if (updated) {
            return ResponseEntity.ok().body("密碼更新成功");
        } else {
            return ResponseEntity.badRequest().body("密碼更新失敗");
        }
    }

}
