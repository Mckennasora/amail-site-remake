package cn.harusora.amailsite.auth.controller;


import cn.harusora.amailsite.auth.dto.SiteRoleAddDto;
import cn.harusora.amailsite.auth.dto.SiteRoleListDto;
import cn.harusora.amailsite.auth.dto.SiteRoleUpdateDto;
import cn.harusora.amailsite.auth.entity.SiteRole;
import cn.harusora.amailsite.auth.service.SiteRoleService;
import cn.harusora.amailsite.auth.vo.SiteRoleVo;
import cn.harusora.amailsite.common.dto.IdsDeleteDto;
import cn.harusora.amailsite.common.result.Result;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 角色表(SiteSiteRole)表控制层
 *
 * @author makejava
 * @since 2024-01-09 12:24:51
 */
@RestController
@RequestMapping("siteSiteRole")
public class SiteRoleController{
    public final SiteRoleService roleService;

    @Autowired
    public SiteRoleController(SiteRoleService roleService) {
        this.roleService = roleService;
    }

    //    @ApiOperation(value = "新增角色")
    @PostMapping("/add")
    public Result<SiteRoleVo> add(@Valid @RequestBody SiteRoleAddDto roleAddDto) {
        SiteRoleVo role = roleService.addSiteRole(roleAddDto);
        return Result.ok(role);
    }
    
    @DeleteMapping("/")
    public Result<String[]> deleteBatch(@Valid @RequestParam IdsDeleteDto idsDeleteDto) {

        String[] ids = idsDeleteDto.getIds().split(",");
        roleService.batchDeleteSiteRole(ids);
        return Result.ok(ids);
    }

    @PutMapping("/")
    public Result<Boolean> update(@Valid @RequestBody SiteRoleUpdateDto roleUpdateDto) {
        roleService.updateSiteRole(roleUpdateDto);
        return Result.ok(true);
    }

    @GetMapping("/{id}")
    public Result<SiteRoleVo> getInfo(@PathVariable String id) {
        SiteRoleVo roleInfo = roleService.getSiteRoleVo(id);
        return Result.ok(roleInfo);
    }

    @PostMapping("/{page}")
    public Result<Page<SiteRole>> page(@PathVariable int page, @RequestParam(defaultValue = "15") int limit,
                                       @RequestBody SiteRoleListDto roleListDto) {
        return Result.ok(roleService.findSiteRoleListPage(page, limit, roleListDto));
    }
}

