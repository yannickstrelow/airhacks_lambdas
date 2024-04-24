package airhacks.s3.control;

import software.amazon.awscdk.services.s3.Bucket;
import software.constructs.Construct;

public interface Buckets {
    

    public static Bucket create(Construct scope){
        return Bucket.Builder
        .create(scope,"JaxBucket")
        .eventBridgeEnabled(true)
        .build();
    }
}
