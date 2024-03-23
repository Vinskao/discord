package com.mli.assetjdbc.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.mli.assetjdbc.dto.LoginDTO;
import com.mli.assetjdbc.dto.UserIdDTO;
import com.mli.assetjdbc.model.Users;
import com.mli.assetjdbc.service.UsersService;

import io.swagger.v3.oas.annotations.Operation;

/**
 * 使用者控制器類，處理使用者相關的 HTTP 請求。
 * 
 * @version 1.0
 * @author D3031104
 */
@CrossOrigin
@RestController
@RequestMapping("/users")
public class UsersController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UsersService usersService;

    @Operation(summary = "用户登入")
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        int id = loginDTO.getId();
        String password = loginDTO.getPassword();
        logger.info("User login attempt: {}", id);

        try {
            boolean isAuthenticated = usersService.authenticate(id, password);
            if (isAuthenticated) {
                return new ResponseEntity<>("登入成功", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("帳號或密碼錯誤", HttpStatus.UNAUTHORIZED);
            }
        } catch (EmptyResultDataAccessException e) {
            logger.error("Error during User login: {}", e.getMessage());
            return new ResponseEntity<>("登入中出錯", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 根據使用者 ID 查找使用者。
     * 
     * @param userId 使用者 ID
     * @return 匹配給定 ID 的使用者清單，如果找不到則返回空列表
     */
    @Operation(summary = "Find users by user ID")
    @PostMapping("/find-by-id")
    public List<Users> findById(@RequestBody UserIdDTO userIdDTO) {
        logger.info("controller, userIdDTO: {}", userIdDTO);
        int userId = userIdDTO.getId();
        return usersService.findById(userId);
    }

}
