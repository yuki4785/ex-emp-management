package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Employee;

/**
 * employeesテーブルを操作するリポジトリクラス
 */
@Repository
public class EmployeeRepository {

    /**
     * SQL操作用テンプレート。
     */
    @Autowired
    private NamedParameterJdbcTemplate template;

    /**
     * Employeeオブジェクトを生成するRowMapper
     */
    private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER = (rs, i) -> {
        Employee employee = new Employee();
        employee.setId(rs.getInt("id"));
        employee.setName(rs.getString("name"));
        employee.setImage(rs.getString("image"));
        employee.setGender(rs.getString("gender"));
        employee.setHireDate(rs.getDate("hire_date"));
        employee.setMailAddress(rs.getString("mail_address"));
        employee.setZipCode(rs.getString("zip_code"));
        employee.setAddress(rs.getString("address"));
        employee.setTelephone(rs.getString("telephone"));
        employee.setSalary(rs.getInt("salary"));
        employee.setCharacteristics(rs.getString("characteristics"));
        employee.setDependentsCount(rs.getInt("dependents_count"));
        return employee;
    };


    /**
     * 従業員一覧情報を入社日順（降順）で取得する
     * 
     * @return 従業員情報のリスト
     */
    public List<Employee> findAll() {
        String sql = "SELECT id, name, image, gender, hire_date, mail_address, zip_code, "
                   + "address, telephone, salary, characteristics, dependents_count "
                   + "FROM employees ORDER BY hire_date DESC";
        return template.query(sql, EMPLOYEE_ROW_MAPPER);
    }

    /**
     * 主キーから従業員情報を取得します。
     * 
     * @param id 従業員ID
     * @return 対応する従業員情報（存在しない場合は例外発生）
     */
    public Employee load(Integer id) {
        String sql = "SELECT id, name, image, gender, hire_date, mail_address, zip_code, "
                   + "address, telephone, salary, characteristics, dependents_count "
                   + "FROM employees WHERE id = :id";
        SqlParameterSource param = new MapSqlParameterSource("id", id);
        Employee employee = template.queryForObject(sql, param, EMPLOYEE_ROW_MAPPER);

        return employee;
    }

    /**
     * 従業員情報を更新します（ID以外のすべてのカラムを更新）。
     * 
     * @param employee 更新する従業員情報
     */
    public void update(Employee employee) {
        String sql = "UPDATE employees SET "
                   + "name = :name, image = :image, gender = :gender, hire_date = :hireDate, "
                   + "mail_address = :mailAddress, zip_code = :zipCode, address = :address, "
                   + "telephone = :telephone, salary = :salary, characteristics = :characteristics, "
                   + "dependents_count = :dependentsCount "
                   + "WHERE id = :id";
        SqlParameterSource param = new BeanPropertySqlParameterSource(employee);
        template.update(sql, param);
    }
}
