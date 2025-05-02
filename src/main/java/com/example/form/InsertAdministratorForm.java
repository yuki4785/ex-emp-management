package com.example.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * 管理者登録時に使用するフォームクラス
 * 
 * @author yukisato
 */
public class InsertAdministratorForm {

    /** 名前 */
    @NotBlank(message = "名前を入力してください")
    @Size(max = 100, message = "名前は100文字以内で入力してください")
    private String name;

    /** メールアドレス */
    @NotBlank(message = "メールアドレスを入力してください")
    @Size(max = 254, message = "メールアドレスは254文字以内で入力してください")
    @Email(message = "正しい形式のメールアドレスを入力してください")
    private String mailAddress;

    /** パスワード */
    @NotBlank(message = "パスワードを入力してください")
    @Size(min = 8, max = 100, message = "パスワードは8〜100文字で入力してください")
    @Pattern(
        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-={}|\\[\\]:;\"'<>,.?/])[A-Za-z\\d!@#$%^&*()_+\\-={}|\\[\\]:;\"'<>,.?/]{8,100}$",
        message = "パスワードは英大文字・小文字・数字・記号を含めた8〜100文字で入力してください"
    )
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "InsertAdministratorForm [name=" + name + ", mailAddress=" + mailAddress + ", password=" + password + "]";
    }
}
