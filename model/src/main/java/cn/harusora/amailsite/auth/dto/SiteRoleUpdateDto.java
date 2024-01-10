package cn.harusora.amailsite.auth.dto;

import lombok.Data;
import lombok.ToString;

/**
 * 角色表(SiteRole)表实体类
 *
 * @author makejava
 * @since 2024-01-09 12:24:51
 */
@Data
@ToString
public class SiteRoleUpdateDto {

    private String id;
    //角色名
    private String roleName;
}

