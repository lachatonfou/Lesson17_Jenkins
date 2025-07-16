package configs;

import constants.Constants;
import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:${env}.properties",
        "classpath:default.properties"
})

public interface TestPropertiesConfig extends Config {
    @Key("baseUrl")
    @DefaultValue(Constants.BASE_URL)
    String getBaseUrl();

    @Key("username")
    String getUsername();

    @Key("password")
    String getPassword();

    @Key("apiBaseUrl")
    String getApiBaseUrl();

//    @Key("uiBaseUrl")
//    String getUiBaseUrl();

    @Key("login")
    String getLogin();

    @Key("password_login")
    String getPasswordLogin();

    @Key("selenium.remote.url")
    String getSeleniumRemoteUrl();
    //String remoteUrl = "http://selenium-hub:4444/wd/hub";
}

