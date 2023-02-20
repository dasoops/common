package com.dasoops.common.util;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @title UrlUtil
 * @classPath com.dasoops.common.util.UrlUtil
 * @author DasoopsNicole@Gmail.com
 * @date 2023/01/06
 * @version 1.0.0
 * @description url工具
 */
public class UrlUtil {

    /**
     * URL 前缀表示文件: "file:"
     */
    public static final String FILE_URL_PREFIX = "file:";
    /**
     * URL 前缀表示jar: "jar:"
     */
    public static final String JAR_URL_PREFIX = "jar:";

    public static URL filePathToUrl(String filePath) throws MalformedURLException {
        return new URL(FILE_URL_PREFIX + filePath);
    }

}
