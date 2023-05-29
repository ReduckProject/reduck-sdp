package net.reduck.sdp.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotation;
import com.fasterxml.jackson.annotation.JsonMerge;

import java.lang.annotation.*;

/**
 * @author Gin
 * @since 2023/5/29 17:42
 */
@JacksonAnnotation
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@JsonMerge
@Inherited
public @interface Encrypted {
}
