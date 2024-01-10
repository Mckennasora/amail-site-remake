package cn.harusora.amailsite.auth.service.impl;

import cn.harusora.amailsite.auth.dao.SiteRoleDao;
import cn.harusora.amailsite.auth.entity.SiteRole;
import cn.harusora.amailsite.auth.service.SiteRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 角色表(SiteRole)表服务实现类
 *
 * @author makejava
 * @since 2024-01-09 12:24:51
 */
@Service("siteRoleService")
public class SiteRoleServiceImpl extends ServiceImpl<SiteRoleDao, SiteRole> implements SiteRoleService {

}

