package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.form.InsertAdministratorForm;
import com.example.service.AdministratorService;

/**
 * 管理者関連機能の処理の制御を⾏うコントローラ
 * 
 * @author yukisato
 */
@Controller
@RequestMapping("/")
public class AdministratorController {

    @Autowired
    private AdministratorService administratorService;

    @ModelAttribute
    public InsertAdministratorForm setUpForm() {
        return new InsertAdministratorForm();
    }

    /**
     * 管理者情報の登録画面に遷移
     * 
     * @param form リクエストパラメータを受け取るフォーム
     * @return 管理者情報の登録画面
     */
    @GetMapping("/toInsert")
    public String toInsert(InsertAdministratorForm form) {
        return "administrator/insert";
    }

    
}
