package com.hw.example.utils.util;

import java.util.UUID;
import java.util.regex.Pattern;

public class Utils {

    public static String webPath = null;

    /**
     * 获取随机字符串
     * @return
     */
    public static String getRandomString() {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replaceAll(Pattern.quote("-"), "");
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 16]);
        }
        return shortBuffer.toString();
    }

    public static String[] chars = new String[] {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
}
