package io.goodforgod.gson.configuration;

import com.google.gson.Gson;
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

    private GsonConfiguration configuration;

    public Gson build() {
        if (configuration == null)
            configuration = getGsonConfiguration();
        return configuration.builder().create();
    }

    private GsonConfiguration getGsonConfiguration() {
        try (InputStream resource = getClass().getClassLoader().getResourceAsStream(PROPERTY_FILE)) {
            if (resource != null) {
                final Properties properties = new Properties();
                properties.load(resource);
                return GsonConfiguration.ofProperties(properties);
            } else {
                return new GsonConfiguration();
            }
        } catch (Exception e) {
            return new GsonConfiguration();
        }
    }
}
