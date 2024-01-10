package cn.harusora.amailsite.auth.dao;

import cn.harusora.amailsite.auth.entity.SiteRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色表(SiteRole)表数据库访问层
 *
 * @author makejava
 * @since 2024-01-09 12:24:51
 */
@Mapper
public interface SiteRoleDao extends BaseMapper<SiteRole> {

}

