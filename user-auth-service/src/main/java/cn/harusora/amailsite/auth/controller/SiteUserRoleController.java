package cn.harusora.amailsite.auth.controller;


import cn.harusora.amailsite.auth.dto.SiteUserRoleAddDto;
import cn.harusora.amailsite.auth.dto.SiteUserRoleListDto;
import cn.harusora.amailsite.auth.entity.SiteUserRole;
import cn.harusora.amailsite.auth.service.SiteUserRoleService;
import cn.harusora.amailsite.auth.vo.SiteUserRoleVo;
import cn.harusora.amailsite.common.dto.IdsDeleteDto;
import cn.harusora.amailsite.common.result.Result;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 用户角色表(SiteUserRole)表控制层
 *
 * @author makejava
 * @since 2024-01-09 12:28:39
 */
@RestController
@ApiOperation(value = "用户角色")
@RequestMapping("siteUserRole")
public class SiteUserRoleController {
    private final SiteUserRoleService siteUserRoleService;

    @Autowired
    public SiteUserRoleController(SiteUserRoleService siteUserRoleService) {
        this.siteUserRoleService = siteUserRoleService;
    }

    @ApiOperation(value = "用户绑定角色")
    @PostMapping("/add")
    public Result<SiteUserRoleVo> add(@Valid @RequestBody SiteUserRoleAddDto siteUserRoleAddDto) {
        SiteUserRoleVo siteUserRole = siteUserRoleService.addSiteUserRole(siteUserRoleAddDto);
        return Result.ok(siteUserRole);
    }

    @ApiOperation(value = "用户解绑角色（批量）")
    @DeleteMapping("/")
    public Result<String[]> deleteBatch(@Valid @RequestParam IdsDeleteDto idsDeleteDto) {

        String[] ids = idsDeleteDto.getIds().split(",");
        siteUserRoleService.batchDeleteSiteUserRole(ids);
        return Result.ok(ids);
    }

    @ApiOperation(value = "用户的角色信息")
    @GetMapping("/{id}")
    public Result<SiteUserRoleVo> getInfo(@PathVariable String id) {
        SiteUserRoleVo siteUserRoleInfo = siteUserRoleService.getSiteUserRoleVo(id);
        return Result.ok(siteUserRoleInfo);
    }

    @ApiOperation(value = "用户的角色分页")
    @PostMapping("/{page}")
    public Result<Page<SiteUserRole>> page(@PathVariable int page, @RequestParam(defaultValue = "15") int limit,
                                           @RequestBody SiteUserRoleListDto siteUserRoleListDto) {
        return Result.ok(siteUserRoleService.findSiteUserRoleListPage(page, limit, siteUserRoleListDto));
    }

    @ApiOperation(value = "查找用户角色")
    @GetMapping("/{userId}")
    public Result<List<SiteUserRoleVo>> findUserRoleByUserId(@PathVariable String userId) {
        List<SiteUserRoleVo> roleList = siteUserRoleService.findUserRoleByUserId(userId);
        return Result.ok(roleList);
    }
}

