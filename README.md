# Anura SDK for Java
The **Anura SDK for Java** makes it easy for developers to access Anura Direct within their Java code, and begin analyzing their traffic. You can get started in minutes by installing the SDK with Maven, Gradle, or building and installing a JAR file from our source code.

## Getting Started
1. **Have an open active account with Anura**. You can see more about Anura's offerings [here.](https://www.anura.io/product#plans-pricing)
2. **Minimum Requirements** - To use the SDK, you will need **Java >=8**.
3. **Install the SDK**
4. View our [**Quick Examples**](#quick-examples) to immediately begin using the SDK!

## Installing the SDK
### With Maven
The recommended way to use the SDK in your project is by consuming it from Maven Central. You can add it to your project by adding the following:
```xml
<dependency>
    <groupId>io.anura</groupId>
    <artifactId>anura-sdk-java</artifactId>
    <version>0.0.1</version>
</dependency>
```

### Building From Source
Once you clone the repository, navigate to `/anura-sdk-java` and build using the appropriate command according to your operating system:

Linux/Mac:
```shell
./mvnw clean install
```

Windows:
```shell
./mvnw.cmd clean install
```

Once installed, you are now able to use it within any of your Java projects by adding the installed JAR to your project.


## Quick Examples

### Create the Anura Direct client
```java
// Import all SDK classes, exceptions, and the HashMap class
import io.anura.sdk.*;
import io.anura.sdk.exceptions.*;
import java.util.HashMap;

// AnuraDirect constructor takes one parameter (a boolean) - whether to use HTTPS or not for API calls to Anura Direct.
// If false is given to the constructor, the API client will use HTTP instead of HTTPS.
AnuraDirect direct = new AnuraDirect(true);
```

### Create a DirectRequest object for AnuraDirect client

```java
DirectRequestBuilder builder = new DirectRequestBuilder();
DirectRequest request = builder
        .setInstanceId("your-instance-id")                  // required
        .setIpAddress("visitors-ip-address")                // required
        .setSource("your-source-value")                     // optional
        .setCampaign("your-campaign-value")                 // optional
        .setUserAgent("visitors-user-agent")                // optional
        .setAppId("visitors-app-id")                        // optional
        .setDeviceId("visitors-device-id")                  // optional
        .setAdditionalData(new HashMap<String, String>())   // optional
        .build();
```

### Add Additional Data to a DirectRequest object
Any time you need to add/update your additional data, pass a new `HashMap` to the request's `setAdditionalData()` method:
```java
HashMap<String,String> additionalData = new HashMap<String, String>();
additionalData.put("1", "your-value-here");

request.setAdditionalData(additionalData);
```

### Remove Additional Data
To remove all additional data that you have added, pass an empty `HashMap` to the request's `setAdditionalData()` method:
```java
request.setAdditionalData(new HashMap<String, String>());
```

### Get a result from Anura Direct
```java
DirectResult result;
try {
    result = direct.getResult(request);
} catch (IOException e) {
    // Handle IOException
} catch (InterruptedException e) {
    // Handle InterruptedException  
} catch (AnuraClientException e) {
    // Handle any 4XX responses
} catch (AnuraServerException e) {
    // Handle any 5XX responses
} catch (AnuraException e) {
    /**
     * Handle any other exceptions that may have occurred.
     * Since AnuraClientException & AnuraServerException are children of AnuraException, 
     * feel free to remove those handling blocks if your handling logic is the same for both.
     */
}

// We successfully got a result! See below for available methods for DirectResult objects.

// result.isSuspect();
// result.isNonSuspect();
// result.isMobile();

// String visitorResult = result.getResult();
// String[] ruleSets = result.getRuleSets();
// String trafficType = result.getInvalidTrafficType();


```
## API Reference
### AnuraDirect
Can get results from Anura Direct. These results are fetched using Direct's `/direct.json` API endpoint.

#### Methods
**`DirectResult getResult(DirectRequest request)`**
- Gets a result from AnuraDirect. Throws an exception if an error occurs throughout the result fetching process.
- Exceptions Thrown:
    - `IOException`: if an I/O error occurs when sending or receiving from Anura Direct
    - `InterruptedException`: if the request/response process with the Anura Direct API is interrupted
    - `AnuraException`: if a 4XX, 5XX, or any unknown response is returned from Anura Direct

**`boolean isUseHttps()`**
- Returns whether the AnuraDirect client is using HTTPS or HTTP when calling the Anura Direct API

**`void setUseHttps(boolean useHttps)`**
- Sets the AnuraDirect to use the HTTPS or HTTP endpoint for calling the Anura Direct API.

### DirectResult
The result upon a successful call to `getResult()` from the `AnuraDirect` client. It contains not only the result from Anura Direct, but some other methods to help you use the result.

#### Methods
**`boolean isSuspect()`**
- Returns whether the visitor is deemed to be suspect.

**`boolean isNonSuspect()`**
- Returns whether the visitor is deemed to be non-suspect.

**`Boolean isMobile()`**
- Returns whether the visitor is deemed to be from a mobile device.

**`String getResult()`**
- Returns the result value received from Anura Direct.

**`String getRuleSets()`**
- Returns which rule set(s) indicated that a visitor should be deemed suspect.
- Returning rule sets requires **return rule sets** to be enabled. You can reach out to [support](mailto:support@anura.io) about enabling or disabling **return rule sets**.

**`String getInvalidTrafficType()`**
- Returns which invalid traffic type indicated that a visitor should be deemed suspect.
- Returning invalid traffic type requires **return invalid traffic type** to be enabled. You can reach out to [support](mailto:support@anura.io) to have **return invalid traffic type** enabled.

### DirectRequest
A POJO that represents an API request to be sent to the Anura Direct API.

#### Methods
**`String getInstanceId()`**
- Returns the Instance ID that is set within the `DirectRequest` object.

**`String getSource()`**
- Returns the source that is set within the `DirectRequest` object.

**`String getCampaign()`**
- Returns the campaign that is set within the `DirectRequest` object.

**`String getIpAddress()`**
- Returns the IP Address that is set within the `DirectRequest` object.

**`String getUserAgent()`**
- Returns the user agent that is set within the `DirectRequest` object.

**`String getAppId()`**
- Returns the application package identifier that is set within the `DirectRequest` object.

**`String getDeviceId()`**
- Returns the device identifier that is set within the `DirectRequest` object.

**`HashMap<String, String> getAdditionalData()`**
- Returns the additional data that is set within the `DirectRequest` object.

**`void setInstanceId(String instanceId)`**
- Sets the Instance ID of the `DirectRequest` object to the `instanceId` value passed.

**`void setSource(String source)`**
- Sets the source of the `DirectRequest` object to the `source` value passed.

**`void setCampaign(String campaign)`**
- Sets the campaign of the `DirectRequest` object to the `campaign` value passed.

**`void setIpAddress(String ipAddress)`**
- Sets the IP address of the `DirectRequest` object to the `ipAddress` passed.

**`void setUserAgent(String userAgent)`**
- Sets the user agent of the `DirectRequest` object to the `userAgent` passed.

**`void setAppId(String appId)`**
- Sets the application package identifier of the `DirectRequest` object to the `appId` passed.

**`void setDeviceId(String deviceId)`**
- Sets the device identifier of the `DirectRequest` object to the `deviceId` passed.'

**`void setAdditionalData(HashMap<String, String> additionalData)`**
- Sets the additional data of the `DirectRequest` object to the `additionalData` passed.

### DirectRequestBuilder
A builder class for creating a `DirectRequest`.

#### Methods
**`DirectRequest build()`**
- Builds and returns the `DirectRequest` that was configured and built.

**`DirectRequestBuilder setInstanceId(String instanceId)`**
- Sets the Instance ID for the `DirectRequest` to be built. Necessary for all requests used by `AnuraDirect`.

**`DirectRequestBuilder setSource(String source)`**
- Sets the source for the `DirectRequest` to be built.

**`DirectRequestBuilder setCampaign(String campaign)`**
- Sets the campaign for the `DirectRequest` to be built.

**`DirectRequestBuilder setIpAddress(String ipAddress)`**
- Sets the IP address for the `DirectRequest` to be built.

**`DirectRequestBuilder setUserAgent(String userAgent)`**
- Sets the user agent for the `DirectRequest` to be built.

**`DirectRequestBuilder setAppId(String appId)`**
- Sets the application package identifier for the `DirectRequest` to be built.

**`DirectRequestBuilder setDeviceId(String deviceId)`**
- Sets the device identifier for the `DirectRequest` to be built.

**`DirectRequestBuilder setAdditionalData(HashMap<String, String> additionalData)`**
- Sets the additional data for the `DirectRequest` to be built.
