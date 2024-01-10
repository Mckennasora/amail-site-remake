package cn.harusora.amailsite.auth.dto;

import lombok.Data;
import lombok.ToString;

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
    private String username;
    //密码
    private String password;
    //校验码
    private String checkPassword;
}

