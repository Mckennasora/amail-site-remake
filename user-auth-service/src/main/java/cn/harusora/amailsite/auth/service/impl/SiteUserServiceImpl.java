package cn.harusora.amailsite.auth.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.harusora.amailsite.auth.dao.SiteUserDao;
import cn.harusora.amailsite.auth.dto.*;
import cn.harusora.amailsite.auth.entity.SiteUser;
import cn.harusora.amailsite.auth.service.SiteUserService;
import cn.harusora.amailsite.auth.vo.SiteUserVo;
import cn.harusora.amailsite.common.exception.AmailException;
import cn.harusora.amailsite.common.result.ResultCodeEnum;
import cn.harusora.amailsite.common.utils.HashPasswordGenerator;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户表(SiteUser)表服务实现类
 *
 * @author makejava
 * @since 2024-01-09 12:17:48
 */
@Service("siteUserService")
public class SiteUserServiceImpl extends ServiceImpl<SiteUserDao, SiteUser> implements SiteUserService {

    @Override
    public SiteUserVo login(SiteUserLoginDto userLoginDto) throws AmailException {
        SiteUser byUsername = baseMapper.selectOne(new QueryWrapper<SiteUser>().lambda().eq(SiteUser::getUsername, userLoginDto.getUsername()));
        if (byUsername == null) {
            throw new AmailException(ResultCodeEnum.FAIL, "用户不存在");
        }

        String encryptionPassword = HashPasswordGenerator.encryptionPassword(
                userLoginDto.getUsername(), userLoginDto.getPassword());
        if (!byUsername.getPassword().equals(encryptionPassword)) {
            throw new AmailException(ResultCodeEnum.FAIL, "用户名与密码不匹配");
        }

        StpUtil.login(byUsername.getId());

        return getSafetyUser(byUsername);
    }


    @Override
    public SiteUserVo register(SiteUserRegisterDto userRegisterDto) {
        String password = userRegisterDto.getPassword();
        String checkPassword = userRegisterDto.getCheckPassword();
        if (!password.equals(checkPassword)) {
            throw new AmailException(ResultCodeEnum.FAIL, "密码不一致");
        }

        String username = userRegisterDto.getUsername();
        String encryptionPassword = HashPasswordGenerator.encryptionPassword(username, password);

        SiteUser user = new SiteUser();
        user.setUsername(username);
        user.setPassword(encryptionPassword);
        user.setUserNickname("");
        user.setGender("");
        user.setUserEmail("");
        user.setUserPhone("");

        synchronized (username.intern()) {
            SiteUser byUsername = baseMapper.selectOne(new QueryWrapper<SiteUser>().lambda().eq(SiteUser::getUsername, userRegisterDto.getUsername()));
            if (byUsername != null) {
                throw new AmailException(ResultCodeEnum.FAIL, "用户名重复");
            }
            try {
                baseMapper.insert(user);
            } catch (Exception e) {
                throw new AmailException(ResultCodeEnum.SERVICE_ERROR, "注册失败");
            }
        }

        //todo 角色信息加载到内存
        //userRoleService.addUserRole(new UserRoleAddDto(new String[]{"6f3f2191"}, save.getId()));

        return getSafetyUser(user);
    }

    @Override
    public void logout() {
        StpUtil.logout();
    }

    @Override
    public void batchDeleteUser(String[] arrID) {
        baseMapper.deleteBatchIds(Arrays.asList(arrID));
    }

    @Override
    public void updateUser(SiteUserUpdateDto siteUserUpdateDto) {
        SiteUser siteUser = new SiteUser();
        siteUser.setId(siteUserUpdateDto.getId());
        siteUser.setUsername(siteUserUpdateDto.getUsername());
        siteUser.setUserNickname(siteUserUpdateDto.getUserNickname());
        siteUser.setGender(siteUserUpdateDto.getGender());
        siteUser.setUserEmail(siteUserUpdateDto.getUserEmail());
        siteUser.setUserPhone(siteUserUpdateDto.getUserPhone());
        int update = baseMapper.updateById(siteUser);
        if (update < 1) {
            throw new AmailException(ResultCodeEnum.FAIL, "更改失败");
        }
    }

    @Override
    public SiteUserVo getSiteUserVo(String id) {
        SiteUser siteUser = baseMapper.selectById(id);
        return getSafetyUser(siteUser);
    }

    @Override
    public Page<SiteUser> Page(int page, int limit, SiteUserListDto siteUserListDto) {
        LambdaQueryWrapper<SiteUser> wrapper = new QueryWrapper<SiteUser>().lambda().like(StrUtil.isBlank(siteUserListDto.getUsername()), SiteUser::getUsername, siteUserListDto.getUsername())
                .like(!StrUtil.isBlank(siteUserListDto.getUsername()), SiteUser::getUsername, siteUserListDto.getUsername())
                .like(!StrUtil.isBlank(siteUserListDto.getId()), SiteUser::getId, siteUserListDto.getId())
                .like(!StrUtil.isBlank(siteUserListDto.getUserNickname()), SiteUser::getUserNickname, siteUserListDto.getUserNickname())
                .like(!StrUtil.isBlank(siteUserListDto.getUserEmail()), SiteUser::getUserEmail, siteUserListDto.getUserEmail())
                .like(!StrUtil.isBlank(siteUserListDto.getGender()), SiteUser::getGender, siteUserListDto.getGender())
                .like(!StrUtil.isBlank(siteUserListDto.getUserPhone()), SiteUser::getUserPhone, siteUserListDto.getUserPhone())
                .like(!StrUtil.isBlank(siteUserListDto.getCreateBy()) , SiteUser::getCreateBy, siteUserListDto.getCreateBy())
                .like(!StrUtil.isBlank(siteUserListDto.getUpdateBy()) , SiteUser::getUpdateBy, siteUserListDto.getUpdateBy());
//                .like((siteUserListDto.getCreateTime()) , SiteUser::getCreateTime, siteUserListDto.getCreateTime())
//                .like((siteUserListDto.getUpdateTime()), SiteUser::getUpdateTime, siteUserListDto.getUpdateTime());

        Page<SiteUser> siteUserPage = baseMapper.selectPage(new Page<>(page, limit), wrapper);
        return siteUserPage;
    }

    @Override
    public void updateSelf(SiteUserUpdateDto siteuserUpdateDto) {
        if (!StpUtil.getLoginId().equals(siteuserUpdateDto.getId())) {
            throw new AmailException(ResultCodeEnum.PERMISSION);
        }
        updateUser(siteuserUpdateDto);
    }

    @Override
    public SiteUserVo getSelfSiteUserVo() {
        return getSafetyUser(baseMapper.selectById((String) StpUtil.getLoginId()));
    }

    private SiteUserVo getSafetyUser(SiteUser byUsername) {
        SiteUserVo siteUserVo = new SiteUserVo();
        siteUserVo.setId(byUsername.getId());
        siteUserVo.setUsername(byUsername.getUsername());
        siteUserVo.setUserNickname(byUsername.getUserNickname());
        siteUserVo.setGender(byUsername.getGender());
        siteUserVo.setUserEmail(byUsername.getUserEmail());
        siteUserVo.setUserPhone(byUsername.getUserPhone());
        siteUserVo.setCreateTime(byUsername.getCreateTime());
        siteUserVo.setCreateBy(byUsername.getCreateBy());
        siteUserVo.setUpdateTime(byUsername.getUpdateTime());
        siteUserVo.setUpdateBy(byUsername.getUpdateBy());
        return siteUserVo;
    }
}

