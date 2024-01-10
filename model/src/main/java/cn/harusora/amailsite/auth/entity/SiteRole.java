package cn.harusora.amailsite.auth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * 角色表(SiteRole)表实体类
 *
 * @author makejava
 * @since 2024-01-09 12:24:51
 */
@Data
@ToString
public class SiteRole {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;
    //角色名
    private String roleName;
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

