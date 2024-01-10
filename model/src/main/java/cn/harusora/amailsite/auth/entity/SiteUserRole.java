package cn.harusora.amailsite.auth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * 用户角色表(SiteUserRole)表实体类
 *
 * @author makejava
 * @since 2024-01-09 12:28:39
 */
@Data
@ToString
public class SiteUserRole {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;
    //用户Id
    private String userId;
    //角色Id
    private String roleId;
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

