package org.refactorizando;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.nio.file.Path;

public class SimpleS3Demo {
    private static final String BUCKET = "course-bucket-demo-1";// here goes the bucket name

    /**
     * Going to try to write and read into a S3 bucket for demo purpose
     * @param args
     */
    public static void main(String[] args) {
        try(S3Client s3Client =S3Client.builder().region(Region.US_EAST_1).build())//try to create the s3 client
        {
            PutObjectRequest putRequest = PutObjectRequest
                    .builder()
                    .bucket(BUCKET) // provide bucket to connect
                    .key("public/testingFile.txt") // The (future) name of the object in the bucket allocated at "s3://course-bucket-demo-1/public/testingFile.txt"
                    .build();

            s3Client.putObject(putRequest, Path.of("testingFile.txt"));// write to the s3 bucket

            GetObjectRequest getRequest = GetObjectRequest.builder()
                    .bucket(BUCKET) //same as in write
                    .key("public/02-aws.png")
                    .build();
            s3Client.getObject(getRequest,Path.of("aws.png"));// read object from s3 bucket

        }
    }

}