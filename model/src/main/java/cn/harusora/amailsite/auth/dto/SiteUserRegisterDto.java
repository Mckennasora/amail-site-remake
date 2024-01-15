package cn.harusora.amailsite.auth.dto;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * 用户表(SiteUser)表实体类
 *
 * @author makejava
 * @since 2024-01-09 12:17:48
 */
@Data
@ToString
public class SiteUserRegisterDto {

    //账号
    @Size(min = 4, max = 16, message = "用户名长度为4-16")
    @Pattern(regexp = "[A-Za-z0-9]+", message = "只能包含字母和数字")
    private String username;
    //密码
    @Size(min = 4, max = 16, message = "密码长度为4-16")
    @Pattern(regexp = "[A-Za-z0-9]+", message = "只能包含字母和数字")
    private String password;
    //校验码
    @Size(min = 4, max = 16, message = "密码长度为4-16")
    @Pattern(regexp = "[A-Za-z0-9]+", message = "只能包含字母和数字")
    private String checkPassword;
}

