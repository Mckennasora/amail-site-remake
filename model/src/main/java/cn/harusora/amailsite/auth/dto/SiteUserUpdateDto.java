package cn.harusora.amailsite.auth.dto;

import lombok.Data;
import lombok.ToString;

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

    private String id;
    //账号
    private String username;
    //账号
    private String userNickname;
    //性别
    private String gender;
    //邮箱
    private String userEmail;
    //电话
    private String userPhone;
}

