package elfon.inject;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.enterprise.util.Nonbinding;
import javax.inject.Qualifier;

import elfon.producer.ConfigurationProducer;
import elfon.producer.impl.DefaultConfigurationProducer;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.FIELD})
public @interface Property {
    Class<? extends ConfigurationProducer> producer() default DefaultConfigurationProducer.class;
    @Nonbinding String name() default "";
    @Nonbinding boolean required() default false;
    @Nonbinding String defaultValue() default "";
}
