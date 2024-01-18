package cn.harusora.amailsite.auth.service;

import cn.harusora.amailsite.auth.dto.SiteRoleAddDto;
import cn.harusora.amailsite.auth.dto.SiteUserRoleAddDto;
import cn.harusora.amailsite.auth.dto.SiteUserRoleListDto;
import cn.harusora.amailsite.auth.entity.SiteUserRole;
import cn.harusora.amailsite.auth.vo.SiteRoleVo;
import cn.harusora.amailsite.auth.vo.SiteUserRoleVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户角色表(SiteUserRole)表服务接口
 *
 * @author makejava
 * @since 2024-01-09 12:28:39
 */
public interface SiteUserRoleService extends IService<SiteUserRole> {

    SiteUserRoleVo addSiteUserRole(SiteUserRoleAddDto roleAddDto);

    @Transactional
    void batchDeleteSiteUserRole(String[] roleIds);

    SiteUserRoleVo getSiteUserRoleVo(String id);

    Page<SiteUserRole> findSiteUserRoleListPage(int page, int limit, SiteUserRoleListDto roleListDto);

    List<SiteUserRoleVo> findUserRoleByUserId(String userId);
}

