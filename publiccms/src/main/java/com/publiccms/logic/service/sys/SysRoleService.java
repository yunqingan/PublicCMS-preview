package com.publiccms.logic.service.sys;

// Generated 2015-7-20 11:46:39 by com.sanluan.common.source.SourceGenerator

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.publiccms.entities.sys.SysRole;
import com.publiccms.logic.dao.sys.SysRoleDao;
import com.sanluan.common.base.BaseService;
import com.sanluan.common.handler.PageHandler;

/**
 *
 * SysRoleService
 * 
 */
@Service
@Transactional
public class SysRoleService extends BaseService<SysRole> {

    /**
     * @param siteId
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Transactional(readOnly = true)
    public PageHandler getPage(Integer siteId, Integer pageIndex, Integer pageSize) {
        return dao.getPage(siteId, pageIndex, pageSize);
    }

    /**
     * @param roleIds
     * @return
     */
    @Transactional(readOnly = true)
    public boolean showAllMoudle(Integer[] roleIds) {
        List<SysRole> list = getEntitys(roleIds);
        for (SysRole role : list) {
            if (role.isOwnsAllRight() || role.isShowAllMoudle()) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param roleIds
     * @return
     */
    @Transactional(readOnly = true)
    public boolean ownsAllRight(Integer[] roleIds) {
        List<SysRole> list = getEntitys(roleIds);
        for (SysRole role : list) {
            if (role.isOwnsAllRight()) {
                return true;
            }
        }
        return false;
    }

    @Autowired
    private SysRoleDao dao;
    
}