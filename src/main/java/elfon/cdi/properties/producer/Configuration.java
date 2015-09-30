package elfon.cdi.properties.producer;

import java.util.ResourceBundle;

/**
 * The interface for CDI properties Configurations.
 * 
 * @author Jason Holmberg
 *
 */
public interface Configuration {
  /**
   * Returns the {@link ResourceBundle} from which properties are read.
   * @return the {@link ResourceBundle}
   * @throws Exception 
   */
  ResourceBundle getBundle() throws Exception;

  /**
   * Return the path to the local directory when the properties file is stored. For 
   * example: {@code C:\properties}
   * 
   * @return path to properties directory
   */
  String getConfigDirPath();
  
  /**
   * Return the base name of the localized properties file. For example, if your properties file is 
   * named {@code 'messages.properties'} then the base name is {@code 'messages'}
   * @return
   */
  String getConfigBaseName();
  
  /**
   * {@link ResourceBundle}s are used to read properties and as such the bundles may be cached. By 
   * default this method will return {@link ResourceBundle.Control#TTL_NO_EXPIRATION_CONTROL}, meaning 
   * that the bundle is only read once and cached. You can also set this to a positive {@code int} 
   * for the number of milliseconds that the bundle can remain in the cache before it is validated again.
   * <p>
   * For example, returning {@code 300000} will set the bundle to be re-validated after 5 minutes.
   * <p>
   * Returning a value of {@code 0} will cause the bundle to be validated each time it is read from 
   * and will only reload if the backing properties file has changed.
   * <p>
   * Returning a value id {@code -1} or {@link ResourceBundle.Control#TTL_DONT_CACHE} will cause the 
   * bundle to never be cached.
   * <p>
   * Returning a value of {@code -2} or {@link ResourceBundle.Control#TTL_NO_EXPIRATION_CONTROL} will
   * cause the cache to never expire.  It will be load once. This would be the most efficient for 
   * properties that never or almost never change at runtime.
   * 
   * @return the cache refresh interval
   */
  default long getCacheRefreshInterval() {
    return ResourceBundle.Control.TTL_NO_EXPIRATION_CONTROL;
  }
}
