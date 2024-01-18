package cn.harusora.amailsite.auth.controller;


import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckRole;
import cn.harusora.amailsite.auth.dto.SiteUserListDto;
import cn.harusora.amailsite.auth.dto.SiteUserLoginDto;
import cn.harusora.amailsite.auth.dto.SiteUserRegisterDto;
import cn.harusora.amailsite.auth.dto.SiteUserUpdateDto;
import cn.harusora.amailsite.auth.entity.SiteUser;
import cn.harusora.amailsite.auth.service.SiteUserService;
import cn.harusora.amailsite.auth.vo.SiteUserVo;
import cn.harusora.amailsite.common.dto.IdsDeleteDto;
import cn.harusora.amailsite.common.result.Result;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 用户表(SiteUser)表控制层
 *
 * @author makejava
 * @since 2024-01-09 12:17:47
 */
@RestController
@RequestMapping("/siteUser")
public class SiteUserController {
    /**
     * 服务对象
     */
    private SiteUserService siteUserService;

    @Autowired
    public SiteUserController(SiteUserService siteUserService) {
        this.siteUserService = siteUserService;
    }
    //region 登录相关

    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public Result<SiteUserVo> userLogin(@Valid @RequestBody SiteUserLoginDto userLoginDto) {
        SiteUserVo userInfo = siteUserService.login(userLoginDto);
        return Result.ok(userInfo);
    }
    @ApiOperation(value = "注册")
    @PostMapping("/register")
    public Result<SiteUserVo> userRegister(@Valid @RequestBody SiteUserRegisterDto userRegisterDto) {
        SiteUserVo userInfo = siteUserService.register(userRegisterDto);
        return Result.ok(userInfo);
    }
    @ApiOperation(value = "登出")
    @GetMapping("/logout")
    public Result<Boolean> userLogout() {
        siteUserService.logout();
        return Result.ok(true);
    }

    @GetMapping("/checklogin")
    @SaCheckLogin
    public Result<Boolean> checkLogin() {
        return Result.ok(true);
    }

    @GetMapping("/checkAdmin")
    @SaCheckRole("admin")
    public Result<Boolean> checkAdmin() {
        return Result.ok(true);
    }
    // endregion

    // region 管理员操作
    @DeleteMapping("/")
    @SaCheckRole("admin")
    public Result<String[]> deleteUserBatch(@RequestParam IdsDeleteDto idsDeleteDto) {
        String[] arrID = idsDeleteDto.ids.split(",");
        siteUserService.batchDeleteUser(arrID);
        return Result.ok(arrID);
    }

    @PutMapping("/")
    @SaCheckRole("admin")
    public Result<Boolean> updateUser(@Valid @RequestBody SiteUserUpdateDto siteUserUpdateDto) {
        siteUserService.updateUser(siteUserUpdateDto);
        return Result.ok(true);
    }

    @GetMapping("/{id}")
    @SaCheckRole("admin")
    public Result<SiteUserVo> getUserInfo(@PathVariable String id) {
        SiteUserVo userInfo = siteUserService.getSiteUserVo(id);
        return Result.ok(userInfo);
    }

    @PostMapping("/{page}")
    @SaCheckRole("admin")
    public Result<Page<SiteUser>> userList(@PathVariable int page, @RequestParam(defaultValue = "10") int limit,
                                           @RequestBody SiteUserListDto siteUserListDto) {
        return Result.ok(siteUserService.Page(page, limit, siteUserListDto));
    }

    //endregion

    //region 用户操作
    @PutMapping("/info")
//    @SaCheckRole("user")
    public Result<Boolean> updateSelf(@Valid @RequestBody SiteUserUpdateDto siteuserUpdateDto) {
        siteUserService.updateSelf(siteuserUpdateDto);
        return Result.ok(true);
    }

    @GetMapping("/info")
//    @SaCheckRole("user")
    public Result<SiteUserVo> getSelfInfo() {
        SiteUserVo userInfo = siteUserService.getSelfSiteUserVo();
        return Result.ok(userInfo);
    }

    //endregion

    //todo 注销 批量删除所有信息 应该要用到rabbitmq

}

