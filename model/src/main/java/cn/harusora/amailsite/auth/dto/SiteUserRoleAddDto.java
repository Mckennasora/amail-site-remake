package cn.harusora.amailsite.auth.dto;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
    @NotBlank
    @Size(min = 19,max = 19, message = "id长度为19")
    @Pattern(regexp = "[0-9]+", message = "只能包含数字")
    private String userId;
    //角色Id
    @NotBlank
    @Size(min = 19,max = 19, message = "id长度为19")
    @Pattern(regexp = "[0-9]+", message = "只能包含数字")
    private String roleId;
}

