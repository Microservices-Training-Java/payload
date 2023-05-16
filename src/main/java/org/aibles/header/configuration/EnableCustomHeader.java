package org.aibles.header.configuration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.aibles.header.dto.Payload;
import org.springframework.context.annotation.Import;

@Import({HeaderConfiguration.class, Payload.class})
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface EnableCustomHeader {

}
