package elfon.producer.impl;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;

import elfon.inject.Property;
import elfon.producer.Configuration;
import elfon.producer.DefaultConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultConfigurationProducer
        extends AbstractConfigurationProducer {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultConfigurationProducer.class);

    @Inject
    DefaultConfiguration defaultConfiguration;

    @Override
    @Produces
    @Property(producer = DefaultConfigurationProducer.class)
    public String produce(InjectionPoint ip)
            throws IllegalStateException {
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
