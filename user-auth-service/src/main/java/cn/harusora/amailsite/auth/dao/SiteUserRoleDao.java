package cn.harusora.amailsite.auth.dao;

import cn.harusora.amailsite.auth.entity.SiteUserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户角色表(SiteUserRole)表数据库访问层
 *
 * @author makejava
 * @since 2024-01-09 12:28:39
 */
@Mapper
public interface SiteUserRoleDao extends BaseMapper<SiteUserRole> {

}

