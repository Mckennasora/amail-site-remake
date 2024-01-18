package cn.harusora.amailsite.auth.service.impl;

import cn.harusora.amailsite.auth.dao.SiteRoleDao;
import cn.harusora.amailsite.auth.dao.SiteUserDao;
import cn.harusora.amailsite.auth.dao.SiteUserRoleDao;

import cn.harusora.amailsite.auth.dto.SiteRoleAddDto;
import cn.harusora.amailsite.auth.dto.SiteUserRoleAddDto;
import cn.harusora.amailsite.auth.dto.SiteUserRoleListDto;

import cn.harusora.amailsite.auth.entity.SiteRole;
import cn.harusora.amailsite.auth.entity.SiteUser;
import cn.harusora.amailsite.auth.entity.SiteUserRole;

import cn.harusora.amailsite.auth.service.SiteUserRoleService;

import cn.harusora.amailsite.auth.vo.SiteUserRoleVo;
import cn.harusora.amailsite.common.exception.AmailException;
import cn.harusora.amailsite.common.result.ResultCodeEnum;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户角色表(SitesiteSiteUserRole)表服务实现类
 *
 * @author makejava
 * @since 2024-01-09 12:28:39
 */
@Service("siteUserRoleService")
public class SiteUserRoleServiceImpl extends ServiceImpl<SiteUserRoleDao, SiteUserRole> implements SiteUserRoleService {

    @Autowired
    private SiteUserDao siteUserDao;

    @Autowired
    private SiteRoleDao siteRoleDao;

    @Override
    public SiteUserRoleVo addSiteUserRole(SiteUserRoleAddDto roleAddDto) {

        String userId = roleAddDto.getUserId();
        SiteUser siteUser = siteUserDao.selectById(userId);
        if (siteUser == null) {
            throw new AmailException(ResultCodeEnum.FAIL, "没有该用户");
        }

        String roleId = roleAddDto.getRoleId();
        SiteRole siteRole = siteRoleDao.selectById(roleId);
        if (siteRole == null) {
            throw new AmailException(ResultCodeEnum.FAIL, "没有该角色");
        }

        LambdaQueryWrapper<SiteUserRole> wrapper = new QueryWrapper<SiteUserRole>().lambda().eq(SiteUserRole::getRoleId, roleId)
                .eq(SiteUserRole::getUserId, userId);
        if (baseMapper.exists(wrapper)) {
            throw new AmailException(ResultCodeEnum.FAIL, "用户已拥有该角色");
        }
        SiteUserRole SiteUserRole = new SiteUserRole();
        SiteUserRole.setRoleId(roleId);
        SiteUserRole.setUserId(userId);
        int insert = baseMapper.insert(SiteUserRole);
        if (insert < 1) {
            throw new AmailException(ResultCodeEnum.SERVICE_ERROR, "新增失败");
        }
        SiteUserRole SiteUserRole1 = baseMapper.selectOne(wrapper);
        return getSiteUserRoleVo(SiteUserRole1);
    }

    @Override
    @Transactional
    public void batchDeleteSiteUserRole(String[] roleIds) {
        List<SiteUserRole> SiteUserRoles = baseMapper.selectBatchIds(Arrays.asList(roleIds));
        if (SiteUserRoles.size() < roleIds.length) {
            throw new AmailException(ResultCodeEnum.FAIL, "请检查输入是否正确");
        }
        int i = baseMapper.deleteBatchIds(Arrays.asList(roleIds));
        if (i < 1) {
            throw new AmailException(ResultCodeEnum.SERVICE_ERROR, "删除失败");
        }
    }

    @Override
    public SiteUserRoleVo getSiteUserRoleVo(String id) {
        SiteUserRole SiteUserRole = baseMapper.selectById(id);
        return getSiteUserRoleVo(SiteUserRole);
    }

    @Override
    public Page<SiteUserRole> findSiteUserRoleListPage(int page, int limit, SiteUserRoleListDto roleListDto) {
        LambdaQueryWrapper<SiteUserRole> wrapper = new QueryWrapper<SiteUserRole>().lambda().like(StrUtil.isBlank(roleListDto.getRoleId()), SiteUserRole::getRoleId, roleListDto.getRoleId())
                .like(!StrUtil.isBlank(roleListDto.getId()), SiteUserRole::getId, roleListDto.getId())
                .like(!StrUtil.isBlank(roleListDto.getUserId()), SiteUserRole::getUserId, roleListDto.getUserId())
                .like(!StrUtil.isBlank(roleListDto.getCreateBy()), SiteUserRole::getCreateBy, roleListDto.getCreateBy())
                .like(!StrUtil.isBlank(roleListDto.getUpdateBy()), SiteUserRole::getUpdateBy, roleListDto.getUpdateBy());
        Page<SiteUserRole> SiteUserRolePage = baseMapper.selectPage(new Page<>(page, limit), wrapper);
        return SiteUserRolePage;
    }

    @Override
    public List<SiteUserRoleVo> findUserRoleByUserId(String userId) {
        LambdaQueryWrapper<SiteUserRole> wrapper = new QueryWrapper<SiteUserRole>().lambda()
                .eq(SiteUserRole::getUserId, userId);
        List<SiteUserRole> siteUserRoles = baseMapper.selectList(wrapper);
        List<String> roleIds = siteUserRoles.stream().map(SiteUserRole::getRoleId).collect(Collectors.toList());
        if (roleIds.isEmpty()) {
            throw new AmailException(ResultCodeEnum.PERMISSION);
        }
        List<SiteRole> siteRoles = siteRoleDao.selectBatchIds(roleIds);
        List<SiteUserRoleVo> collect = siteUserRoles.stream().map(siteUserRole -> {
            for (SiteRole siteRole : siteRoles) {
                if (siteRole.getId().equals(siteUserRole.getRoleId())) {
                    return getSiteUserRoleVoWithRoleName(siteUserRole, siteRole);
                }
            }
            return null;
        }).collect(Collectors.toList());
        return collect;
    }

    private SiteUserRoleVo getSiteUserRoleVo(SiteUserRole siteUserRole) {
        SiteUserRoleVo siteUserRoleVo = new SiteUserRoleVo();
        siteUserRoleVo.setId(siteUserRole.getId());
        siteUserRoleVo.setRoleId(siteUserRole.getRoleId());
        siteUserRoleVo.setUserId(siteUserRole.getUserId());
        siteUserRoleVo.setCreateTime(siteUserRole.getCreateTime());
        siteUserRoleVo.setCreateBy(siteUserRole.getCreateBy());
        siteUserRoleVo.setUpdateTime(siteUserRole.getUpdateTime());
        siteUserRoleVo.setUpdateBy(siteUserRole.getUpdateBy());
        return siteUserRoleVo;
    }

    private SiteUserRoleVo getSiteUserRoleVoWithRoleName(SiteUserRole siteUserRole, SiteRole siteRole) {
        SiteUserRoleVo siteUserRoleVo = getSiteUserRoleVo(siteUserRole);
        siteUserRoleVo.setRoleName(siteRole.getRoleName());
        return siteUserRoleVo;
    }
}

