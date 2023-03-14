package com.app.util;

import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;


public class CommonUtilityMethods {

    public static Long getUserIdFromToken(Authentication authentication) {
        Map<String, Long> map = (Map)((OAuth2Authentication) authentication).getUserAuthentication().getDetails();
        return map.get("user_id");
    }

    public static String getUserNameFromToken(Authentication authentication) {
        Map<String, String> map = (Map)((OAuth2Authentication) authentication).getUserAuthentication().getDetails();
        return map.get("user_name");
    }
}
