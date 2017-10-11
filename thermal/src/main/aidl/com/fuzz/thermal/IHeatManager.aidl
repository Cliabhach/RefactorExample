// IHeatService.aidl
package com.fuzz.thermal;

/**
 * This is the recommended way to get the current temperature from a different application.
 */
interface IHeatManager {

    /**
     * @return a {@link com.fuzz.thermal.model.Temperature} value
     */
    String getCurrentTemp();
}
