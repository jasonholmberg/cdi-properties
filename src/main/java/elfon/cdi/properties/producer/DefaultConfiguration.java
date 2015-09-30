package elfon.cdi.properties.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The default configuration. This configuration expects that a system property
 * with the name {@code 'config.base.name'} has set holding the base name of the
 * properties file. If the properties file is name {@code messages.properties}
 * then the base name is: {@code 'messages'}.
 * 
 * @author Jason Holmberg
 *
 */
public class DefaultConfiguration extends AbstractConfiguration implements Configuration {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultConfiguration.class);
    static final String DEFAULT_BASE_NAME = "cdi-properties";
    static final String SYS_PROP_CONFIG_BASE_NAME = "config.base.name";
    static final String SYS_PROP_CONFIG_DIR_PATH = "config.dir.path";
    
    public DefaultConfiguration() {
    }
    
  @Override
  protected Logger logger() {
    return LOG;
  }

  /* (non-Javadoc)
   * @see hci.cdi.properties.producer.Configuration#getConfigDirectory()
   */
  @Override
  public String getConfigDirPath() {
    String sysPropConfigDirPath = getSysProp(SYS_PROP_CONFIG_DIR_PATH);
    return sysPropConfigDirPath;
  }
  
  @Override
  public String getConfigBaseName() {
    String sysPropConfigBaseName = getSysProp(SYS_PROP_CONFIG_BASE_NAME);
    if (sysPropConfigBaseName != null && !sysPropConfigBaseName.trim().isEmpty()) {
      return sysPropConfigBaseName;
    } else {
      logger().warn(String.format("System property %s not set, falling back to default base name: %s", SYS_PROP_CONFIG_BASE_NAME, DEFAULT_BASE_NAME));
      return DEFAULT_BASE_NAME;
    }
  }

  /* (non-Javadoc)
   * @see hci.cdi.properties.producer.Configuration#getCacheRefreshInterval()
   */
  @Override
  public long getCacheRefreshInterval() {
    return 0;
  }
}
