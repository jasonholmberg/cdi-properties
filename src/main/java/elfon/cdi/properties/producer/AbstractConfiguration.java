package elfon.cdi.properties.producer;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.ResourceBundle.Control;

import org.slf4j.Logger;

/**
 * A {@link Configuration} super-class with the default implementation for
 * looking up runtime properties from a resource bundle on the class path.
 * 
 * @author Jason Holmberg
 */
public abstract class AbstractConfiguration implements Configuration {

  public AbstractConfiguration() {
  }

  /**
   * If the {@link Configuration#getConfigDirPath()} is not null then attempt to use that path when 
   * loading the {@link ResourceBundle}, otherwise use the default class path.
   * 
   * @see hci.cdi.properties.producer.Configuration#getBundle()
   */
  @Override
  public ResourceBundle getBundle() throws MalformedURLException {
    logger().debug("Getting default bundle");
    if (getConfigDirPath() == null && !getConfigBaseName().trim().isEmpty()) {
      logger().debug("Returning ResourceBundle using the default classloader");
      return ResourceBundle.getBundle(getConfigBaseName(), getControl());
    } else {
      logger().debug("Returning ResourceBundle using URL classloader for path: "+ getConfigDirPath());
      File file = new File(getConfigDirPath());
      URL[] urls = {file.toURI().toURL()};
      ClassLoader loader = new URLClassLoader(urls);
      return ResourceBundle.getBundle(getConfigBaseName(),Locale.getDefault(),loader,getControl());
    }
  }

  protected abstract Logger logger();

  /**
   * A convenience method for looking up System properties.
   * 
   * @param key
   * @return the string value of the system property
   */
  protected String getSysProp(String key) {
    return System.getProperty(key);
  }
  
  /**
   * Returns a {@link ResourceBundle.Control} that sets the bundle to be re-validate each time it is retrieved.
   * @return
   */
  private Control getControl() {
    return new ResourceBundle.Control() {
      @Override
      public long getTimeToLive(String baseName, Locale locale) {
        return getCacheRefreshInterval();
      }

      @Override
      public boolean needsReload(String baseName, Locale locale, String format, ClassLoader loader, ResourceBundle bundle, long loadTime) {
        boolean needsReload = super.needsReload(baseName, locale, format, loader, bundle, loadTime);
        logger().debug("Need Reload: " + needsReload + " loadTime: " + loadTime);
        return needsReload;
      }
    };
  }
}
