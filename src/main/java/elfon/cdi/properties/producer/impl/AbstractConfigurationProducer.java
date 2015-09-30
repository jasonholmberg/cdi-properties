package elfon.cdi.properties.producer.impl;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.slf4j.Logger;

import elfon.cdi.properties.inject.Property;
import elfon.cdi.properties.producer.ConfigurationProducer;

import javax.enterprise.inject.spi.InjectionPoint;

/**
 * A super-class that provides a convenient method
 * {@link #getPropVal(InjectionPoint)} for validating the {@link InjectionPoint}
 * and looking of the property value through the given {@link Configuration}.
 * 
 * @author Jason Holmberg
 *
 */
public abstract class AbstractConfigurationProducer implements ConfigurationProducer {

  static final String MISSING_RESOURCE_BUNDLE = "Invalid or missing property: [{0}] or properties file: [{1}]";
  static final String INVALID_NAME = "Invalid or missing property: [{0}]";
  static final String REQUIRED_PARAM_MISSING = "No definition found for a required property: [{0}]";

  /**
   * Return the value of the property name given a the {@link InjectionPoint}.
   * If he
   * 
   * @param ip
   * @return The value of the property
   */
  protected String getPropVal(InjectionPoint ip) {
    Property prop = getPropertyAnnotation(ip);
    String propKey = getPropValue(prop);
    logger().debug("Loading value for property: " + propKey);
    if (propKey == null || propKey.trim().isEmpty()) {
      logger().error(MessageFormat.format(INVALID_NAME, propKey ));
      return prop.defaultValue();
    }
    String value;
    try {
      value = getConfiguration().getBundle().getString(propKey);
      if (value == null || value.trim().length() == 0) {
        if (prop.required()) {
          logger().error(MessageFormat.format(REQUIRED_PARAM_MISSING, propKey));
        } else {
          logger().error(MessageFormat.format(INVALID_NAME, new Object[] { propKey }));
        }
        return prop.defaultValue();
      }
      return value;
    } catch (Exception e) {
      logger().error(MessageFormat.format(MISSING_RESOURCE_BUNDLE, propKey, getConfiguration().getConfigBaseName()),e);
      logger().debug("Returning default value for key: " + propKey);
      return prop.defaultValue();
    }
  }

  Property getPropertyAnnotation(InjectionPoint ip) {
    return ip.getAnnotated().getAnnotation(Property.class);
  }

  /**
   * Attempts to look up the property name two ways. First by the
   * {@link Property#name()} and if that doesn't work, then by
   * {@link Property#value()}.
   * 
   * @param prop
   * @return
   */
  private String getPropValue(Property prop) {
    if (prop.value() != null && !prop.value().trim().isEmpty()) {
      return prop.value();
    } else {
      return prop.name();
    }
  }

  protected abstract Logger logger();

}
