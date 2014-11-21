/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elfon.producer;

import java.util.Locale;
import java.util.ResourceBundle;
import org.slf4j.Logger;

/**
 *
 * @author holmberg
 */
public abstract class AbstractConfiguration implements Configuration {

    public AbstractConfiguration() {
    }

    @Override
    public ResourceBundle getBundle() {
        logger().debug("Getting default bundle");
        return ResourceBundle.getBundle(getConfigFileName(), new ResourceBundle.Control() {
            @Override
            public long getTimeToLive(String baseName, Locale locale) {
                logger().debug("Default bundle getTTL called");
                return 0;
            }

            @Override
            public boolean needsReload(String baseName, Locale locale, String format, ClassLoader loader, ResourceBundle bundle, long loadTime) {
                boolean needsReload = super.needsReload(baseName, locale, format, loader, bundle, loadTime);
                logger().debug("Need Reload: " + needsReload + " loadTime: " + loadTime);
                return needsReload;
            }
        });
    }
    
    protected abstract Logger logger();
    
}
