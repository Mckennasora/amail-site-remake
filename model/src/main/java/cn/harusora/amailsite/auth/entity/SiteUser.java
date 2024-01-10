package cn.harusora.amailsite.auth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
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
public class SiteUser {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;
    //账号
    private String username;
    //密码
    private String password;
    //账号
    private String userNickname;
    //性别
    private String gender;
    //邮箱
    private String userEmail;
    //电话
    private String userPhone;
    //创建时间
    private Date createTime;
    //创建人
    private String createBy;
    //更新时间
    private Date updateTime;
    //更新人
    private String updateBy;
    //是否删除
    @TableLogic
    private Integer isDeleted;
}

