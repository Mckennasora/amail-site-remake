package cn.harusora.amailsite.auth.dto;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 用户表(SiteUser)表实体类
 *
 * @author makejava
 * @since 2024-01-09 12:17:48
 */
@Data
@ToString
public class SiteUserUpdateDto {

    @Size(min = 19,max = 19, message = "id长度为19")
    @Pattern(regexp = "[0-9]+", message = "只能包含数字")
    private String id;
    //账号
    @Size(min = 4, max = 16, message = "密码长度为4-16")
    @Pattern(regexp = "[A-Za-z0-9]+", message = "只能包含字母和数字")
    private String username;
    //账号
    @Size(min = 4, max = 16, message = "密码长度为4-16")
    private String userNickname;
    //性别
    @Size(min = 1, max = 10, message = "性别长度为4-16")
    private String gender;
    //邮箱
    @Email
    private String userEmail;
    //电话
    private String userPhone;
}

