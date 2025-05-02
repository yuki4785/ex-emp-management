package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Administrator;
import com.example.form.InsertAdministratorForm;
import com.example.form.LoginForm;
import com.example.service.AdministratorService;

import jakarta.servlet.http.HttpSession;

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

    @Autowired
    private HttpSession session;

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

    /**
     * ログイン画面に遷移する
     * 
     * @param form ログインフォーム
     * @return ログイン画面のテンプレート名
     */
    @GetMapping("/")
    public String toLogin(LoginForm form) {
        return "administrator/login";
    }
    
    /**
     * ログイン処理
     * 
     * @param form  ログイン時に使⽤するフォーム
     * @param model 格納するためのオブジェクト
     * @return ログイン画面へリダイレクト or 従業員⼀覧画面
     */
    @PostMapping("/login")
    public String login(LoginForm form, Model model) {
        Administrator administrator = new Administrator();
        administrator = administratorService.login(form.getMailAddress(), form.getPassword());

        if (administrator == null) {
            model.addAttribute("errorMessage", "メールアドレスまたはパスワードが間違っています");
            return "administrator/login";
        }
        session.setAttribute("administratorName", administrator.getName());
        return "redirect:/employee/showList";
    }

    /**
	 * ログアウト処理
	 * 
	 * @return ログイン画面
	 */
	@GetMapping(value = "/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/";
	}
}
