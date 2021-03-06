package com.publiccms.logic.service.sys;

import java.util.Date;

// Generated 2016-1-20 11:19:18 by com.sanluan.common.source.SourceGenerator

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.publiccms.entities.sys.SysUserToken;
import com.publiccms.logic.dao.sys.SysUserTokenDao;
import com.sanluan.common.base.BaseService;
import com.sanluan.common.handler.PageHandler;

/**
 *
 * SysUserTokenService
 * 
 */
@Service
@Transactional
public class SysUserTokenService extends BaseService<SysUserToken> {

    /**
     * @param siteId
     * @param userId
     * @param channel
     * @param orderType
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Transactional(readOnly = true)
    public PageHandler getPage(Integer siteId, Long userId, String channel, String orderType, Integer pageIndex,
            Integer pageSize) {
        return dao.getPage(siteId, userId, channel, orderType, pageIndex, pageSize);
    }
    
    /**
     * @param createDate
     * @return
     */
    public int delete(Date createDate) {
        return dao.delete(createDate);
    }

    @Autowired
    private SysUserTokenDao dao;
    
}