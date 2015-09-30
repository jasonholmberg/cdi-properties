package elfon.cdi.properties.producer.impl;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.enterprise.inject.spi.Producer;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import elfon.cdi.properties.inject.Property;
import elfon.cdi.properties.producer.Configuration;
import elfon.cdi.properties.producer.DefaultConfiguration;

/**
 * The default configuration produced invoked when injecting a {@link Property}.
 * This {@link Producer} relies on the {@link DefaultConfiguration} for the
 * property config file name.
 * 
 * @author Jason Holmberg
 * 
 * @see AbstractConfigurationProducer
 * @see DefaultConfiguration
 */
public class DefaultConfigurationProducer extends AbstractConfigurationProducer {

  private static final Logger LOG = LoggerFactory.getLogger(DefaultConfigurationProducer.class);

  @Inject
  DefaultConfiguration defaultConfiguration;

  @Override
  @Produces
  @Property(producer = DefaultConfigurationProducer.class)
  public String produce(InjectionPoint ip) throws IllegalStateException {
    return getPropVal(ip);
  }

  @Override
  public Configuration getConfiguration() {
    return defaultConfiguration;
  }

  @Override
  protected Logger logger() {
    return LOG;
  }

}
