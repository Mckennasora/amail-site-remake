//package cn.harusora.amailsite.auth.controller;
//
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.baomidou.mybatisplus.extension.api.ApiController;
//import com.baomidou.mybatisplus.extension.api.R;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import cn.harusora.amailsite.auth.entity.SiteUserRole;
//import cn.harusora.amailsite.auth.service.SiteUserRoleService;
//import org.springframework.web.bind.annotation.*;
//
//import javax.annotation.Resource;
//import java.io.Serializable;
//import java.util.List;
//
///**
// * 用户角色表(SiteUserRole)表控制层
// *
// * @author makejava
// * @since 2024-01-09 12:28:39
// */
//@RestController
//@RequestMapping("siteUserRole")
//public class SiteUserRoleController {
//    /**
//     * 服务对象
//     */
//    @Resource
//    private SiteUserRoleService siteUserRoleService;
//
//    /**
//     * 分页查询所有数据
//     *
//     * @param page         分页对象
//     * @param siteUserRole 查询实体
//     * @return 所有数据
//     */
//    @GetMapping
//    public R selectAll(Page<SiteUserRole> page, SiteUserRole siteUserRole) {
//        return success(this.siteUserRoleService.page(page, new QueryWrapper<>(siteUserRole)));
//    }
//
//    /**
//     * 通过主键查询单条数据
//     *
//     * @param id 主键
//     * @return 单条数据
//     */
//    @GetMapping("{id}")
//    public R selectOne(@PathVariable Serializable id) {
//        return success(this.siteUserRoleService.getById(id));
//    }
//
//    /**
//     * 新增数据
//     *
//     * @param siteUserRole 实体对象
//     * @return 新增结果
//     */
//    @PostMapping
//    public R insert(@RequestBody SiteUserRole siteUserRole) {
//        return success(this.siteUserRoleService.save(siteUserRole));
//    }
//
//    /**
//     * 修改数据
//     *
//     * @param siteUserRole 实体对象
//     * @return 修改结果
//     */
//    @PutMapping
//    public R update(@RequestBody SiteUserRole siteUserRole) {
//        return success(this.siteUserRoleService.updateById(siteUserRole));
//    }
//
//    /**
//     * 删除数据
//     *
//     * @param idList 主键结合
//     * @return 删除结果
//     */
//    @DeleteMapping
//    public R delete(@RequestParam("idList") List<Long> idList) {
//        return success(this.siteUserRoleService.removeByIds(idList));
//    }
//}
//
