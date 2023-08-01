package com.fengling.ecserver.netty.server;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author Administrator
 */
@Import(ServerBoot.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnableNettyServer {
}
