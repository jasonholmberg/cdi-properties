package elfon.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultConfiguration extends AbstractConfiguration
        implements Configuration {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultConfiguration.class);

    public DefaultConfiguration() {
    }

    @Override
    protected Logger logger() {
        return LOG;
    }

    @Override
    public String getConfigFileName() {
        return System.getProperty("config.file.name");
    }

}
