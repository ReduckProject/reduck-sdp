package net.reduck.sdp.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Gin
 * @since 2023/5/29 16:55
 */
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
public @interface Sensitive {
}
