package com.starchat.utils;

import com.starchat.common.constants.Constants;
import com.starchat.common.enums.MessagingAppFeatureEnum;
import org.apache.commons.lang3.RandomStringUtils;

public class IdUtil {
    public static String generateUserId() {
        return MessagingAppFeatureEnum.FRIEND.getPrefix() + RandomStringUtils.randomNumeric(Constants.Length._11);
    }
}
