package cn.harusora.amailsite.auth.dto;

import lombok.Data;
import lombok.ToString;

/**
 * 用户角色表(SiteUserRole)表实体类
 *
 * @author makejava
 * @since 2024-01-09 12:28:39
 */
@Data
@ToString
public class SiteUserRoleAddDto {

    //用户Id
    private String userId;
    //角色Id
    private String roleId;
}

