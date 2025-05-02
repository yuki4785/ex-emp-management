package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Employee;
import com.example.form.UpdateEmployeeForm;
import com.example.service.EmployeeService;

/**
 * 従業員関連機能の制御を行うコントローラクラス
 * 
 * @author yukisato
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * 従業員一覧画面に遷移
     * 
     * @param model モデルに従業員リストを格納
     * @return 従業員一覧画面
     */
    @GetMapping("/showList")
    public String showList(Model model) {
        List<Employee> employeeList = employeeService.showList();
        model.addAttribute("employeeList", employeeList);
        return "employee/list";
    }

    /**
     * 従業員詳細画面を表示
     * 
     * @param id    従業員ID
     * @param model 格納するためのオブジェクト
     * @param form  従業員更新時に使⽤するフォーム
     * @return 従業員情報詳細画面
     */
	@GetMapping("/showDetail")
	public String showDetail(String id, Model model, UpdateEmployeeForm form) {
		Employee employee = employeeService.showDetail(Integer.parseInt(id));
		model.addAttribute("employee", employee);
		return "employee/detail";
	}
}
