package io.goodforgod.gson.configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.InputStream;
import java.util.Properties;

/**
 * Simple {@link Gson} factory implementation that builds instance of Gson from property file:
 * gson.properties
 *
 * @author Anton Kurako (GoodforGod)
 * @since 06.10.2021
 */
public class GsonFactory {

    private static final String PROPERTY_FILE = "gson.properties";

    private Properties properties;
    private GsonConfiguration configuration;

    /**
     * @return Gson built with {@link GsonConfiguration} as base
     */
    public Gson build() {
        return builder().create();
    }

    /**
     * @return Gson built with {@link GsonConfiguration} as base
     */
    public GsonBuilder builder() {
        if (properties == null)
            this.properties = getProperties();

        if (configuration == null)
            this.configuration = GsonConfiguration.ofProperties(properties);

        return configuration.builder();
    }

    private Properties getProperties() {
        try (InputStream resource = getClass().getClassLoader().getResourceAsStream(PROPERTY_FILE)) {
            if (resource != null) {
                final Properties resourceProperties = new Properties();
                resourceProperties.load(resource);
                return resourceProperties;
            } else {
                return new Properties();
            }
        } catch (Exception e) {
            return new Properties();
        }
    }
}
