package com.starchat.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatusEnum {
    ACTIVE(1, "Active", "The user is active and can use all the features."),
    INACTIVE(2, "Inactive", "The user is inactive and may have limited access."),
    BANNED(3, "Banned", "The user is banned and cannot access the system."),
    PENDING(4, "Pending", "The user's registration is pending approval."),
    SUSPENDED(5, "Suspended", "The user is temporarily suspended."),
    DELETED(6, "Deleted", "The user account is deleted."),
    ONLINE(7, "Online", "The user is currently online."),
    OFFLINE(8, "Offline", "The user is currently offline."),
    AWAY(9, "Away", "The user is currently away."),
    DO_NOT_DISTURB(10, "Do Not Disturb", "The user does not want to be disturbed."),
    IDLE(11, "Idle", "The user is idle for a period of time.");

    private final int code;
    private final String status;
    private final String description;
}
