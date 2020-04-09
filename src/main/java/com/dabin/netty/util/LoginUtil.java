package com.dabin.netty.util;


import com.dabin.netty.attribute.Attributes;
import io.netty.channel.Channel;
import io.netty.util.Attribute;

/**
 * @ClassName:LoginUtil
 * @author: dabin
 * @date: 2020/4/823:58
 */
public class LoginUtil {
    public static void markAsLogin(Channel channel) {
        channel.attr(Attributes.LOGIN).set(true);
    }

    public static boolean hasLogin(Channel channel) {
        Attribute<Boolean> booleanAttribute = channel.attr(Attributes.LOGIN);
        return booleanAttribute.get() != null;
    }
}
