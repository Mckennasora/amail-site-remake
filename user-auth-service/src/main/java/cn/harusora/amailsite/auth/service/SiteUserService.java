package cn.harusora.amailsite.auth.service;

import cn.harusora.amailsite.auth.dto.*;
import cn.harusora.amailsite.auth.entity.SiteUser;
import cn.harusora.amailsite.auth.vo.SiteUserVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 用户表(SiteUser)表服务接口
 *
 * @author makejava
 * @since 2024-01-09 12:17:48
 */
public interface SiteUserService extends IService<SiteUser> {

    SiteUserVo login(SiteUserLoginDto userLoginDto);

    SiteUserVo register(SiteUserRegisterDto userRegisterDto);

    void logout();

    void batchDeleteUser(String[] arrID);

    void updateUser(SiteUserUpdateDto siteUserUpdateDto);

    SiteUserVo getSiteUserVo(String id);

    Page<SiteUser> Page(int page, int limit, SiteUserListDto siteUserListDto);

    void updateSelf(SiteUserUpdateDto siteuserUpdateDto);

    SiteUserVo getSelfSiteUserVo();

}

