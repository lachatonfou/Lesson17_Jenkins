package configs;

import java.io.IOException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestConfig {
    String env;
    Properties properties;

    public TestConfig() {
        env = System.getProperty("env", "default");
        properties = getPropertiesByEnv(env);
    }

    public String getBaseUrl() {
        return bazovayaFunkcia("baseUrl");
    }

    public String getUsername() {
        return bazovayaFunkcia("username");
    }

    public String getPassword() {
        return bazovayaFunkcia("password");
    }

    private String bazovayaFunkcia(String vozvraschaemoePole) {
        String pole = properties.getProperty(vozvraschaemoePole);
        assertNotNull(vozvraschaemoePole, String.format("BaseUrl is not found in %s.properties", env));
        System.out.printf("%s: %s%n", vozvraschaemoePole, pole);
        return pole;
    }

    private Properties getPropertiesByEnv(String env) {
        Properties testProperties = new Properties();
        try {
            testProperties.load(getClass().getClassLoader().getResourceAsStream(env + ".properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(String.format("Cannot open %s.properties", env));
        }
        return testProperties;
    }
}
