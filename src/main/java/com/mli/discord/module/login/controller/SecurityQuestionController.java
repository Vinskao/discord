package com.mli.discord.module.login.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mli.discord.module.login.model.SecurityQuestion;
import com.mli.discord.module.login.service.SecurityQuestionService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class SecurityQuestionController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SecurityQuestionService securityQuestionService;

    /**
     * 添加安全问题
     * 
     * @param securityQuestion 安全问题实体对象
     * @return ResponseEntity 包含操作结果的响应实体
     */
    @Operation(summary = "添加安全问题")
    @PostMapping("/add-security-question")
    public ResponseEntity<String> addSecurityQuestion(@RequestBody SecurityQuestion securityQuestion) {
        try {
            securityQuestionService.addSecurityQuestion(securityQuestion);
            logger.info("安全问题添加成功");
            return new ResponseEntity<>("安全问题添加成功", HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("添加安全问题时出现异常：{}", e.getMessage());
            return new ResponseEntity<>("添加安全问题失败", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 修改安全問題
     * 
     * @param securityQuestion 安全問題實體對象，包含要修改的詳細信息
     * @return ResponseEntity 包含操作結果的響應實體
     */
    @Operation(summary = "修改安全問題")
    @PostMapping("/modify-security-question")
    public ResponseEntity<String> modifySecurityQuestion(@RequestBody SecurityQuestion securityQuestion) {
        try {
            Integer result = securityQuestionService.modifySecurityQuestion(securityQuestion);
            if (result > 0) {
                logger.info("安全問題修改成功，問題ID: {}", securityQuestion.getId());
                return new ResponseEntity<>("安全問題修改成功", HttpStatus.OK);
            } else {
                logger.info("安全問題修改失敗，問題ID: {}", securityQuestion.getId());
                return new ResponseEntity<>("安全問題修改失敗", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            logger.error("修改安全問題時出現異常：{}", e.getMessage());
            return new ResponseEntity<>("修改安全問題失敗", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
