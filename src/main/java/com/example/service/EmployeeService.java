package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Employee;
import com.example.repository.EmployeeRepository;

/**
 * 従業員情報を操作するサービスクラス
 * 
 * @author yukisato
 */
@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * 全従業員の情報を取得
     * 
     * @return 従業員情報のリスト
     */
    public List<Employee> showList() {
		List<Employee> employeeList = employeeRepository.findAll();
		return employeeList;
    }
}
