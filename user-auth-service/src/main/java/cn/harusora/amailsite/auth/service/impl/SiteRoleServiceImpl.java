package cn.harusora.amailsite.auth.service.impl;

import cn.harusora.amailsite.auth.dao.SiteRoleDao;
import cn.harusora.amailsite.auth.dto.SiteRoleAddDto;
import cn.harusora.amailsite.auth.dto.SiteRoleListDto;
import cn.harusora.amailsite.auth.dto.SiteRoleUpdateDto;
import cn.harusora.amailsite.auth.entity.SiteRole;
import cn.harusora.amailsite.auth.service.SiteRoleService;
import cn.harusora.amailsite.auth.vo.SiteRoleVo;
import cn.harusora.amailsite.common.exception.AmailException;
import cn.harusora.amailsite.common.result.ResultCodeEnum;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * 角色表(SiteRole)表服务实现类
 *
 * @author makejava
 * @since 2024-01-09 12:24:51
 */
@Service("siteRoleService")
public class SiteRoleServiceImpl extends ServiceImpl<SiteRoleDao, SiteRole> implements SiteRoleService {

    @Override
    public SiteRoleVo addSiteRole(SiteRoleAddDto roleAddDto) {

        LambdaQueryWrapper<SiteRole> wrapper = new QueryWrapper<SiteRole>().lambda().eq(SiteRole::getRoleName, roleAddDto.getRoleName());
        if (baseMapper.exists(wrapper)) {
            throw new AmailException(ResultCodeEnum.FAIL, "该角色已存在");
        }
        SiteRole siteRole = new SiteRole();
        siteRole.setRoleName(roleAddDto.getRoleName());
        int insert = baseMapper.insert(siteRole);
        if (insert < 1) {
            throw new AmailException(ResultCodeEnum.SERVICE_ERROR, "新增失败");
        }
        SiteRole siteRole1 = baseMapper.selectOne(wrapper);
        return getSiteRoleVo(siteRole1);
    }

    @Override
    @Transactional
    public void batchDeleteSiteRole(String[] roleIds) {
        List<SiteRole> siteRoles = baseMapper.selectBatchIds(Arrays.asList(roleIds));
        if (siteRoles.size() < roleIds.length) {
            throw new AmailException(ResultCodeEnum.FAIL, "请检查输入是否正确");
        }
        int i = baseMapper.deleteBatchIds(Arrays.asList(roleIds));
        if (i < 1) {
            throw new AmailException(ResultCodeEnum.SERVICE_ERROR, "删除失败");
        }
    }

    @Override
    public void updateSiteRole(SiteRoleUpdateDto roleUpdateDto) {
        SiteRole siteRole = new SiteRole();
        siteRole.setId(roleUpdateDto.getId());
        siteRole.setRoleName(roleUpdateDto.getRoleName());

        int i = baseMapper.updateById(siteRole);
        if (i < 1) {
            throw new AmailException(ResultCodeEnum.SERVICE_ERROR, "更新失败");
        }
    }

    @Override
    public SiteRoleVo getSiteRoleVo(String id) {
        SiteRole siteRole = baseMapper.selectById(id);
        return getSiteRoleVo(siteRole);
    }

    @Override
    public Page<SiteRole> findSiteRoleListPage(int page, int limit, SiteRoleListDto roleListDto) {
        LambdaQueryWrapper<SiteRole> wrapper = new QueryWrapper<SiteRole>().lambda().like(StrUtil.isBlank(roleListDto.getRoleName()), SiteRole::getRoleName, roleListDto.getRoleName())
                .like(!StrUtil.isBlank(roleListDto.getId()), SiteRole::getId, roleListDto.getId())
                .like(!StrUtil.isBlank(roleListDto.getCreateBy()), SiteRole::getCreateBy, roleListDto.getCreateBy())
                .like(!StrUtil.isBlank(roleListDto.getUpdateBy()), SiteRole::getUpdateBy, roleListDto.getUpdateBy());
        Page<SiteRole> siteRolePage = baseMapper.selectPage(new Page<>(page, limit), wrapper);
        return siteRolePage;
    }

    private SiteRoleVo getSiteRoleVo(SiteRole siteRole) {
        SiteRoleVo siteRoleVo = new SiteRoleVo();
        siteRoleVo.setId(siteRole.getId());
        siteRoleVo.setRoleName(siteRole.getRoleName());
        siteRoleVo.setCreateTime(siteRole.getCreateTime());
        siteRoleVo.setCreateBy(siteRole.getCreateBy());
        siteRoleVo.setUpdateTime(siteRole.getUpdateTime());
        siteRoleVo.setUpdateBy(siteRole.getUpdateBy());
        return siteRoleVo;
    }
}

