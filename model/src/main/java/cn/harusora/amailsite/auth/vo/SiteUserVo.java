package cn.harusora.amailsite.auth.vo;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
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
public class SiteUserVo {

    private String id;
    //账号
    private String username;
    //昵称
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
}

