(ns s3.core
  (:import (software.amazon.awssdk.services.s3 S3AsyncClient)
           (software.amazon.awssdk.services.s3.model AbortMultipartUploadRequest ListMultipartUploadsRequest RequestPayer ListObjectsV2Request CompleteMultipartUploadRequest)
           (software.amazon.awssdk.auth.credentials ProfileCredentialsProvider AwsCredentials AwsCredentialsProvider)))

(defn- creds
  [^String name]
  (.build
    (-> (ProfileCredentialsProvider/builder)
        (.profileName name))))

(defn- client
  ([]
   (.build
     (S3AsyncClient/builder)))
  ([^String name]
   (.build
     (-> (S3AsyncClient/builder)
         (.credentialsProvider (creds name))))))

(defn abort-multipart-upload
  [& {:keys
      [^String bucket
       ^String key
       ^String upload-id
       ^String requester-payer
       ^String profile]
      :or
      {profile         "default"
       requester-payer "requester"}}]
  (.abortMultipartUpload
    (client profile)
    (.build
      (->
        (AbortMultipartUploadRequest/builder)
        (.bucket bucket)
        (.key key)
        (.uploadId upload-id)
        (.requestPayer requester-payer)))))

(defn complete-multipart-upload
  [& {:keys
      [^String bucket
       ^String key
       ^String upload-id
       ^String request-payer
       ^String profile]
      :or
      {profile       "default"
       request-payer "requester"}}]
  (.completeMultipartUpload
    (client profile)
    (.build
      (->
        (CompleteMultipartUploadRequest/builder)
        (.bucket bucket)
        (.key key)
        (.uploadId upload-id)
        (.requestPayer request-payer)))))


