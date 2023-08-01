package com.fengling.ecserver.netty.client;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author Administrator
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ClientBoot.class)
public @interface EnableNettyClient {
}
