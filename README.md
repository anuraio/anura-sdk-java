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

// AnuraDirect constructor takes one boolean parameter - whether to use HTTPS or not for API calls to Anura Direct.
AnuraDirect direct = new AnuraDirect(true);
```

### Create an DirectRequest object for AnuraDirect client

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