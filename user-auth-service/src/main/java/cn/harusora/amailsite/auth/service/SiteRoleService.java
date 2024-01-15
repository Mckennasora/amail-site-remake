package cn.harusora.amailsite.auth.service;

import cn.harusora.amailsite.auth.dto.SiteRoleAddDto;
import cn.harusora.amailsite.auth.dto.SiteRoleListDto;
import cn.harusora.amailsite.auth.dto.SiteRoleUpdateDto;
import cn.harusora.amailsite.auth.entity.SiteRole;
import cn.harusora.amailsite.auth.vo.SiteRoleVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 角色表(SiteRole)表服务接口
 *
 * @author makejava
 * @since 2024-01-09 12:24:51
 */
public interface SiteRoleService extends IService<SiteRole> {

    SiteRoleVo addSiteRole(SiteRoleAddDto roleAddDto);

    void batchDeleteSiteRole(String[] roleIds);

    void updateSiteRole(SiteRoleUpdateDto roleUpdateDto);

    SiteRoleVo getSiteRoleVo(String id);

    Page<SiteRole> findSiteRoleListPage(int page, int limit, SiteRoleListDto roleListDto);
}

