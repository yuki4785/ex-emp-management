package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Administrator;

/**
 * administrators テーブルを操作するリポジトリクラス
 * 
 * @author yukisato
 */
@Repository
public class AdministratorRepository {

    /**
     * administrators テーブル名（定数）
     */
    private static final String TABLE_NAME = "administrators";

    /**
     * SQL操作用テンプレート
     */
    @Autowired
    private NamedParameterJdbcTemplate template;

    /**
     * Administratorオブジェクトを生成するRowMapper
     */
    private static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER = (rs, i) -> {
        Administrator administrator = new Administrator();
        administrator.setId(rs.getInt("id"));
        administrator.setName(rs.getString("name"));
        administrator.setMailAddress(rs.getString("mail_address")); // DBカラム名
        administrator.setPassword(rs.getString("password"));
        return administrator;
    };

    /**
     * 管理者情報を挿入する
     * 
     * @param administrator 管理者情報
     */
    public void insert(Administrator administrator){
        String sql = "INSERT INTO " + TABLE_NAME + " (name, mail_address, password) "
                   + "VALUES (:name, :mailAddress, :password)";

        SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);
        template.update(sql, param);
    }

    /**
     * メールアドレスとパスワードから管理者情報を取得する
     * (存在しない場合はnull)
     * 
     * @param mailAddress メールアドレス
     * @param password パスワード
     * @return 管理者情報 or null
     */
    public Administrator findByMailAddressAndPassword(String mailAddress, String password){
        String sql = "SELECT id, name, mail_address, password "
                   + "FROM " + TABLE_NAME + " "
                   + "WHERE mail_address = :mailAddress AND password = :password";

        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("mailAddress", mailAddress)
                .addValue("password", password);

        List<Administrator> administratorList = template.query(sql, param, ADMINISTRATOR_ROW_MAPPER);
        return administratorList.isEmpty() ? null : administratorList.get(0);
    }
}
