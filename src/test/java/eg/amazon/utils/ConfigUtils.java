package eg.amazon.utils;


import java.util.Properties;

public class ConfigUtils {
    private Properties properties;
    private static ConfigUtils configUtils;

    private ConfigUtils() {
        String env = System.getProperty("env", "PRODUCTION");
        switch (env) {
            case "TESTING" ->
                    properties = PropertiesUtils.loadProperties("src/test/java/eg/amazon/config/testing.properties");
            case "PRODUCTION" ->
                    properties = PropertiesUtils.loadProperties("src/test/java/eg/amazon/config/production.properties");
            default -> throw new RuntimeException("Environment isn't supported");
        }
    }

    public static ConfigUtils getInstance() {
        if (configUtils == null) {
            configUtils = new ConfigUtils();
        }
        return configUtils;
    }

    public String getBaseUrl() {
        String prop = properties.getProperty("baseUrl");
        if (prop != null) return prop;
        throw new RuntimeException("Couldn't find the base url in the property file");
    }
}
