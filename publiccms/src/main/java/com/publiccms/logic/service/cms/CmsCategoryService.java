package com.publiccms.logic.service.cms;

// Generated 2015-5-8 16:50:23 by com.sanluan.common.source.SourceGenerator
import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.publiccms.entities.cms.CmsCategory;
import com.publiccms.logic.dao.cms.CmsCategoryDao;
import com.sanluan.common.base.BaseService;
import com.sanluan.common.handler.PageHandler;

/**
 *
 * CmsCategoryService
 * 
 */
@Service
@Transactional
public class CmsCategoryService extends BaseService<CmsCategory> {

    /**
     * @param siteId
     * @param parentId
     * @param queryAll
     * @param typeId
     * @param allowContribute
     * @param hidden
     * @param disabled
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Transactional(readOnly = true)
    public PageHandler getPage(Integer siteId, Integer parentId, Boolean queryAll, Integer typeId, Boolean allowContribute,
            Boolean hidden, Boolean disabled, Integer pageIndex, Integer pageSize) {
        return dao.getPage(siteId, parentId, queryAll, typeId, allowContribute, hidden, disabled, pageIndex, pageSize);
    }

    /**
     * @param parentId
     * @param id
     */
    public void addChildIds(Serializable parentId, Serializable id) {
        if (null != parentId) {
            CmsCategory parent = getEntity(parentId);
            if (null != parent) {
                addChildIds(parent.getParentId(), id);
                String childIds;
                if (notEmpty(parent.getChildIds())) {
                    childIds = parent.getChildIds() + COMMA_DELIMITED + String.valueOf(id);
                } else {
                    childIds = String.valueOf(id);
                }
                updateChildIds(parent.getId(), childIds);
            }
        }
    }

    private String getChildIds(int siteId, Integer parentId) {
        StringBuilder childIds = new StringBuilder();
        @SuppressWarnings("unchecked")
        List<CmsCategory> list = (List<CmsCategory>) getPage(siteId, parentId, false, null, null, null, false, null, null)
                .getList();
        if (0 < list.size()) {
            for (CmsCategory category : list) {
                childIds.append(category.getId());
                String childChildIds = getChildIds(siteId, category.getId());
                if (childChildIds.length() > 0) {
                    childIds.append(childChildIds);
                }
                childIds.append(COMMA_DELIMITED);
            }
            if (0 < childIds.length()) {
                childIds.setLength(childIds.length() - 1);
            }
        }
        return childIds.toString();
    }

    /**
     * @param siteId
     * @param parentId
     */
    public void generateChildIds(int siteId, Integer parentId) {
        if (notEmpty(parentId)) {
            updateChildIds(parentId, getChildIds(siteId, parentId));
            CmsCategory parent = getEntity(parentId);
            if (null != parent) {
                generateChildIds(siteId, parent.getParentId());
            }
        }
    }

    /**
     * @param siteId
     * @param ids
     */
    public void delete(int siteId, Integer[] ids) {
        for (CmsCategory entity : getEntitys(ids)) {
            if (siteId == entity.getSiteId() && !entity.isDisabled()) {
                @SuppressWarnings("unchecked")
                List<CmsCategory> list = (List<CmsCategory>) getPage(siteId, entity.getId(), false, null, null, null, null, null,
                        null).getList();
                for (CmsCategory child : list) {
                    child.setParentId(entity.getParentId());
                }
                generateChildIds(entity.getSiteId(), entity.getParentId());
                entity.setDisabled(true);
            }
        }
    }

    /**
     * @param id
     * @param childIds
     */
    public void updateChildIds(Serializable id, String childIds) {
        CmsCategory entity = getEntity(id);
        if (null != entity) {
            entity.setChildIds(childIds);
        }
    }

    /**
     * @param id
     * @param tagTypeIds
     */
    public void updateTagTypeIds(Serializable id, String tagTypeIds) {
        CmsCategory entity = getEntity(id);
        if (null != entity) {
            entity.setTagTypeIds(tagTypeIds);
        }
    }

    /**
     * @param id
     * @param extendId
     * @return
     */
    public CmsCategory updateExtendId(Integer id, Integer extendId) {
        CmsCategory entity = getEntity(id);
        if (null != entity) {
            entity.setExtendId(extendId);
        }
        return entity;
    }

    /**
     * @param siteId
     * @param id
     * @param parentId
     */
    public void updateParentId(int siteId, Serializable id, Integer parentId) {
        CmsCategory entity = getEntity(id);
        if (null != entity && siteId == entity.getSiteId()) {
            entity.setParentId(parentId);
        }
    }

    /**
     * @param id
     * @param url
     * @param hasStatic
     */
    public void updateUrl(Serializable id, String url, boolean hasStatic) {
        CmsCategory entity = getEntity(id);
        if (null != entity) {
            entity.setUrl(url);
            entity.setHasStatic(hasStatic);
        }
    }

    /**
     * @param id
     * @param num
     */
    public void updateContents(Serializable id, int num) {
        CmsCategory entity = getEntity(id);
        if (null != entity) {
            entity.setContents(entity.getContents() + num);
        }
    }

    @Autowired
    private CmsCategoryDao dao;
}