package com.publiccms.views.directive.sys;

import static com.sanluan.common.tools.RequestUtils.getIpAddress;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.publiccms.common.base.AbstractTemplateDirective;
import com.publiccms.entities.sys.SysAppClient;
import com.publiccms.entities.sys.SysAppClientId;
import com.publiccms.logic.service.sys.SysAppClientService;
import com.sanluan.common.handler.RenderHandler;

/**
 *
 * CreateAppClientDirective
 * 
 */
@Component
public class CreateAppClientDirective extends AbstractTemplateDirective {

    @Override
    public void execute(RenderHandler handler) throws IOException, Exception {
        String uuid = handler.getString("uuid");
        String channel = handler.getString("channel");
        Long userId = handler.getLong("userId");
        if (notEmpty(uuid) && notEmpty(channel)) {
            SysAppClientId sysAppClientId = new SysAppClientId(getSite(handler).getId(), channel, uuid);
            SysAppClient appClient = appClientService.getEntity(sysAppClientId);
            if (null == appClient) {
                appClient = new SysAppClient(sysAppClientId, getDate(), false);
                appClient.setClientVersion(uuid);
                appClient.setLastLoginIp(getIpAddress(handler.getRequest()));
                if (notEmpty(userId)) {
                    appClient.setUserId(userId);
                }
                appClientService.save(appClient);
            } else {
                appClientService.updateLastLogin(sysAppClientId, uuid, getIpAddress(handler.getRequest()));
                appClientService.updateUser(sysAppClientId, userId);
            }
            handler.render();
        }
    }

    @Autowired
    private SysAppClientService appClientService;

    @Override
    public boolean needAppToken() {
        return true;
    }
}