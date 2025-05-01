package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Administrator;
import com.example.form.InsertAdministratorForm;
import com.example.service.AdministratorService;

/**
 * 管理者関連機能の処理の制御を⾏うコントローラ
 */
@Controller
@RequestMapping("/")
public class AdministratorController {

    @Autowired
    private AdministratorService administratorService;

    /**
     * フォームの初期化処理を行うメソッド
     * 
     * @return 初期化されたInsertAdministratorFormオブジェクト
     */
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
        return "administrator/insert.html";
    }

    /**
     * 管理者情報を登録し、ログイン画面にリダイレクト
     * 
     * @param form 入力フォーム情報
     * @return ログイン画面へのリダイレクト
     */
    @PostMapping("/insert")
    public String insert(InsertAdministratorForm form) {
        Administrator administrator = new Administrator();
        administrator.setName(form.getName());
        administrator.setMailAddress(form.getMailAddress());
        administrator.setPassword(form.getPassword());

        administratorService.insert(administrator);

        return "redirect:/";
    }  
}
