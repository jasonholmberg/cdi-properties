package elfon.inject;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.enterprise.util.Nonbinding;
import javax.inject.Qualifier;

import elfon.producer.ConfigurationProducer;
import elfon.producer.impl.DefaultConfigurationProducer;

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface Property {
    Class<? extends ConfigurationProducer> producer() default DefaultConfigurationProducer.class;
    @Nonbinding String name() default "";
    @Nonbinding boolean required() default false;
    @Nonbinding String defaultValue() default "";
}
