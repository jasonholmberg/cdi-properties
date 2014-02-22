package elfon.producer.impl;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;

import elfon.inject.Property;
import elfon.producer.Configuration;
import elfon.producer.DefaultConfiguration;

public class DefaultConfigurationProducer
  extends AbstractConfigurationProducer
{

  @Inject DefaultConfiguration defaultConfiguration;
  
  @Override
  @Produces
  @Property(producer = DefaultConfigurationProducer.class)
  public String produce(InjectionPoint ip)
    throws IllegalStateException
  {
    return getPropVal(ip);
  }
 
  @Override
  public Configuration getConfiguration()
  {
    return defaultConfiguration;
  }
  
}
