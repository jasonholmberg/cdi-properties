package elfon.cdi.properties.producer;

import javax.enterprise.inject.spi.InjectionPoint;

/**
 * The configuration producer interface
 * 
 * @author Jason Holmberg
 *
 */
public interface ConfigurationProducer {
  String produce(InjectionPoint ip);

  Configuration getConfiguration();
}
