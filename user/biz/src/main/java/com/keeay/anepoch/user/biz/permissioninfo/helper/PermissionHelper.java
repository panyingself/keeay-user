package com.keeay.anepoch.user.biz.permissioninfo.helper;

import java.util.UUID;

/**
 * Description:
 *
 * @author -  pany
 * Time - 2024/8/13 - 16:30
 */
public final class PermissionHelper {
    private PermissionHelper() {

    }

    public static String generatePermissionCode() {
        UUID uuid = UUID.randomUUID();
        // 获取 UUID 的字符串表示，并移除连字符
        return uuid.toString().replace("-", "");
    }
}
