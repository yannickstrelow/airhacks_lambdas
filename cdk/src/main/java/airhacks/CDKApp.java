package airhacks;

import java.util.Map;

import airhacks.functionurl.boundary.FunctionURLStack;
import software.amazon.awscdk.App;

public interface CDKApp {

    static void main(String... args) {

        var app = new App();
        var appName = "cdk";

        new FunctionURLStack.Builder(app, appName)
                .functionName("airhacks_Darkness")
                .configuration(Map.of("message","hello, jax"))
                .functionZip("../jax/target/function.zip")
                .build();
        app.synth();
    }
}
