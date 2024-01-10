package cn.harusora.amailsite.auth.service.impl;

import cn.harusora.amailsite.auth.dao.SiteUserRoleDao;
import cn.harusora.amailsite.auth.entity.SiteUserRole;
import cn.harusora.amailsite.auth.service.SiteUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 用户角色表(SiteUserRole)表服务实现类
 *
 * @author makejava
 * @since 2024-01-09 12:28:39
 */
@Service("siteUserRoleService")
public class SiteUserRoleServiceImpl extends ServiceImpl<SiteUserRoleDao, SiteUserRole> implements SiteUserRoleService {

}

