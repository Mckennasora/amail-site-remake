package cn.harusora.amailsite.auth.config;

import cn.dev33.satoken.stp.StpInterface;

import cn.harusora.amailsite.auth.entity.SiteUserRole;
import cn.harusora.amailsite.auth.service.SiteUserRoleService;
import cn.harusora.amailsite.auth.vo.SiteUserRoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 自定义权限加载接口实现类
 */
@Component    // 保证此类被 SpringBoot 扫描，完成 Sa-Token 的自定义权限验证扩展
public class StpInterfaceImpl implements StpInterface {

    private final SiteUserRoleService siteUserRoleService;

    @Autowired
    public StpInterfaceImpl(SiteUserRoleService siteUserRoleService) {
        this.siteUserRoleService = siteUserRoleService;
    }


    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        return getRoleList(loginId, loginType);
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {

        List<SiteUserRoleVo> userRolesByUserId = siteUserRoleService.findUserRoleByUserId((String) loginId);

        return userRolesByUserId.stream().map(SiteUserRoleVo::getRoleName).collect(Collectors.toList());
    }

}
