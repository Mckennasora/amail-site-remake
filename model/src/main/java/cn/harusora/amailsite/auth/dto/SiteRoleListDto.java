package cn.harusora.amailsite.auth.dto;

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
public class SiteRoleListDto {

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
}

