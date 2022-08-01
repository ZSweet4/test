package com.luckyboy.ui.cucumber.utils;

import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;

public class EnvironmentProperties {
    private static EnvironmentVariables env = SystemEnvironmentVariables.createEnvironmentVariables();

    public static String getPropertyValue(String propertyName) {
        return EnvironmentSpecificConfiguration.from( env ).getProperty( propertyName );
    }

}
