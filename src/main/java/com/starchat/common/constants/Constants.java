package com.starchat.common.constants;

import lombok.experimental.UtilityClass;

/**
 * Constant Class
 *
 * @author Jack
 */
@UtilityClass
public class Constants {

    @UtilityClass
    public static final class Redis {
        public static final String KEY_CHECK_CODE = "starchat:check_code:";
        public static final String KEY_WS_USER_HEARTBEAT = "starchat:ws:user:heartbeat";
        public static final String KEY_WS_TOKEN = "starchat:ws:token:";
        public static final String KEY_WS_TOKEN_USERID = "starchat:ws:token:userid";


        public static final long EXPIRE_TIME_10_SECONDS = 10;
        public static final long EXPIRE_TIME_30_SECONDS = 30;
        public static final long EXPIRE_TIME_1_MINUTE = 60;
        public static final long EXPIRE_TIME_2_MINUTES = 120;
        public static final long EXPIRE_TIME_5_MINUTES = 300;
        public static final long EXPIRE_TIME_10_MINUTES = 600;
        public static final long EXPIRE_TIME_15_MINUTES = 900;
        public static final long EXPIRE_TIME_30_MINUTES = 1800;
        public static final long EXPIRE_TIME_1_HOUR = 3600;
        public static final long EXPIRE_TIME_2_HOURS = 7200;
        public static final long EXPIRE_TIME_6_HOURS = 21600;
        public static final long EXPIRE_TIME_12_HOURS = 43200;
        public static final long EXPIRE_TIME_1_DAY = 86400;
        public static final long EXPIRE_TIME_2_DAYS = 172800;
        public static final long EXPIRE_TIME_1_WEEK = 604800;
    }

    @UtilityClass
    public static final class Captcha {
        public static final String CHECK_CODE = "checkCode";
        public static final String CHECK_CODE_KEY = "checkCodeKey";
    }

    @UtilityClass
    public static final class Length {
        public static final int _11 = 11;
        public static final int _20 = 20;
    }

    @UtilityClass
    public static final class Status {
        public static final boolean TRUE = true;
        public static final boolean FALSE = false;
    }
}
