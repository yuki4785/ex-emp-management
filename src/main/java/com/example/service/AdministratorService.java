package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Administrator;
import com.example.repository.AdministratorRepository;

/**
 * 管理者関連機能の業務処理を⾏うサービス
 * @author yukisato
 */
@Service
@Transactional
public class AdministratorService {

    @Autowired
	private AdministratorRepository administratorRepository;
    
    /**
     * 管理者情報を挿入
     * 
     * @param administrator 管理者情報
     */
    public void insert(Administrator administrator) {
        administratorRepository.insert(administrator);
    }

    /**
     * ログイン処理をする
     * 
     * @param mailAddress 管理者用のメールアドレス
     * @param password    管理者用のパスワード
     * 
     * @return 検索した管理者情報
     */
    public Administrator login(String mailAddress, String password) {
        return administratorRepository.findByMailAddressAndPassword(mailAddress, password);
    }
}
