package elfon.producer;

import javax.enterprise.inject.spi.InjectionPoint;

public interface ConfigurationProducer {
    String produce(InjectionPoint ip);
    Configuration getConfiguration();
}
