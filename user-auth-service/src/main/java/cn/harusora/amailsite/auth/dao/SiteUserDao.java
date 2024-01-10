package cn.harusora.amailsite.auth.dao;

import cn.harusora.amailsite.auth.entity.SiteUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户表(SiteUser)表数据库访问层
 *
 * @author makejava
 * @since 2024-01-09 12:17:48
 */
@Mapper
public interface SiteUserDao extends BaseMapper<SiteUser> {

}

