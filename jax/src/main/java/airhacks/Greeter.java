package airhacks;

import java.io.IOException;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.joda.time.IllegalInstantException;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.InvalidObjectStateException;
import software.amazon.awssdk.services.s3.model.NoSuchKeyException;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

@ApplicationScoped
public class Greeter {
    @Inject
    @ConfigProperty(name = "message", defaultValue = "java rocks")
    String message;

    @Inject
    @ConfigProperty(name="BUCKET_NAME")
    String bucketName;

    static S3Client client = S3Client.create();

    public String message() {
        var request = GetObjectRequest.builder().bucket(message)
                .key("message")
                .build();
        try {
            return new String(client.getObject(request).readAllBytes());
        } catch (AwsServiceException | SdkClientException | IOException e) {
            throw new IllegalInstantException("cannot read " + e);
        }
    }

    public void message(String message) {
        var request = PutObjectRequest.builder().key("message").build();
        client.putObject(request, RequestBody.fromString(message));
    }

}
