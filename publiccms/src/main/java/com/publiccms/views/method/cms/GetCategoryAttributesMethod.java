package com.publiccms.views.method.cms;

import static com.publiccms.common.tools.ExtendUtils.getExtendMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.publiccms.entities.cms.CmsCategoryAttribute;
import com.publiccms.logic.service.cms.CmsCategoryAttributeService;
import com.sanluan.common.base.BaseMethod;

import freemarker.template.TemplateModelException;

/**
 *
 * GetCategoryAttributesMethod
 * 
 */
@Component
public class GetCategoryAttributesMethod extends BaseMethod {

    @SuppressWarnings("unchecked")
    @Override
    public Object exec(@SuppressWarnings("rawtypes") List arguments) throws TemplateModelException {
        Integer[] ids = getIntegerArray(0, arguments);
        if (notEmpty(ids)) {
            Map<String, Map<String, String>> resultMap = new HashMap<String, Map<String, String>>();
            for (CmsCategoryAttribute entity : service.getEntitys(ids)) {
                Map<String, String> map = getExtendMap(entity.getData());
                map.put("title", entity.getTitle());
                map.put("keywords", entity.getKeywords());
                map.put("description", entity.getDescription());
                resultMap.put(String.valueOf(entity.getCategoryId()), map);
            }
            return resultMap;
        }
        return null;
    }
    
    @Override
    public boolean needAppToken() {
        return false;
    }

    @Override
    public int minParamtersNumber() {
        return 1;
    }

    @Autowired
    private CmsCategoryAttributeService service;
}
