package elfon.producer.impl;

import java.text.MessageFormat;
import java.util.MissingResourceException;

import org.slf4j.Logger;

import elfon.inject.Property;
import elfon.producer.ConfigurationProducer;
import javax.enterprise.inject.spi.InjectionPoint;

public abstract class AbstractConfigurationProducer
        implements ConfigurationProducer {

    static final String INVALID_NAME = "Invalid property name '{0}'";
    static final String REQUIRED_PARAM_MISSING = "No definition found for a mandatory configuration parameter : '{0}'";

    protected String getPropVal(InjectionPoint ip) {
        Property prop = ip.getAnnotated().getAnnotation(Property.class);
        logger().debug("Loading value for property: " + prop.name());
        if (prop.name() == null || prop.name().length() == 0) {
            return prop.defaultValue();
        }
        String value;
        try {
            value = getConfiguration().getBundle().getString(prop.name());
            if (value == null || value.trim().length() == 0) {
                if (prop.required()) {
                    throw new IllegalStateException(MessageFormat.format(REQUIRED_PARAM_MISSING, new Object[]{prop.name()}));
                } else {
                    return prop.defaultValue();
                }
            }
            return value;
        } catch (MissingResourceException e) {
            logger().error("Invalid or missing property", e);
            if (prop.required()) {
                throw new IllegalStateException(MessageFormat.format(REQUIRED_PARAM_MISSING, new Object[]{prop.name()}));
            }
            return MessageFormat.format(INVALID_NAME, new Object[]{prop.name()});
        }
    }

    protected abstract Logger logger();

}
