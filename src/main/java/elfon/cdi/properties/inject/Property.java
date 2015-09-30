package elfon.cdi.properties.inject;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.enterprise.util.Nonbinding;
import javax.inject.Qualifier;

import elfon.cdi.properties.producer.ConfigurationProducer;
import elfon.cdi.properties.producer.impl.DefaultConfigurationProducer;

/**
 * String-based qualifier for injecting runtime properties.
 * 
 * <p>
 * Example usage:
 * 
 * <pre>
 * &#064;Inject
 * &#064;Property("my.property.name")
 * private String myFeild;
 * 
 * &#064;Inject
 * &#064;Property(name = "alt.message", producer = AlternateConfigurationProducer.class)
 * private String altMessage;
 * </pre>
 * 
 * @author Jason Holmberg
 *
 */
@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
public @interface Property {
  Class<? extends ConfigurationProducer>producer() default DefaultConfigurationProducer.class;

  @Nonbinding
  String value() default "";

  @Nonbinding
  String name() default "";

  @Nonbinding
  boolean required() default false;

  @Nonbinding
  String defaultValue() default "UNKNOWN";
}
