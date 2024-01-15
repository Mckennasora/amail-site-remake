package cn.harusora.amailsite.auth.dto;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * 角色表(SiteRole)表实体类
 *
 * @author makejava
 * @since 2024-01-09 12:24:51
 */
@Data
@ToString
public class SiteRoleAddDto {

    //角色名
    @Size(min = 1, max = 16, message = "角色名长度为1-16")
    @Pattern(regexp = "[A-Za-z0-9]+", message = "只能包含字母和数字")
    private String roleName;
}

