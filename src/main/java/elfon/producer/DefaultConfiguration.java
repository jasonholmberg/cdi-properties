package elfon.producer;

import java.util.Locale;
import java.util.ResourceBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultConfiguration
  implements Configuration
{

  private static final Logger LOG = LoggerFactory.getLogger(DefaultConfiguration.class);

  public DefaultConfiguration()
  {
  }

  @Override
  public ResourceBundle getBundle()
  {
    LOG.debug("Getting default bundle");
    return ResourceBundle.getBundle(getConfigFileName(), new ResourceBundle.Control()
    {
      @Override
      public long getTimeToLive(String baseName, Locale locale)
      {
        LOG.debug("Default bundle getTTL called");
        return 0;
      }

      @Override
      public boolean needsReload(String baseName,
                                 Locale locale,
                                 String format,
                                 ClassLoader loader,
                                 ResourceBundle bundle,
                                 long loadTime)
      {
        boolean needsReload = super.needsReload(baseName, locale, format, loader, bundle, loadTime);
        LOG.debug("Need Reload: " + needsReload + " loadTime: " + loadTime);
        return needsReload;
      }
    });
  }

  @Override
  public String getConfigFileName()
  {
    return System.getProperty("config.file.name");
  }

}
