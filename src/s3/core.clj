(ns s3.core
  (:import (software.amazon.awssdk.services.s3 S3AsyncClient)
           (software.amazon.awssdk.services.s3.model AbortMultipartUploadRequest ListMultipartUploadsRequest RequestPayer)
           (software.amazon.awssdk.auth.credentials ProfileCredentialsProvider AwsCredentials AwsCredentialsProvider)))

(defn credentials-provider
  [^String name]
  (.build
    (-> (ProfileCredentialsProvider/builder)
        (.profileName name))))

(defn async-client
  ([]
   (.build
     (S3AsyncClient/builder)))
  ([^String name]
   (.build
     (-> (S3AsyncClient/builder)
         (.credentialsProvider (credentials-provider name))))))

(defn abort-multipart-upload
  [& {:keys                                                 ;;TODO add request payer
      [^String bucket
       ^String key
       upload-id
       ^String profile]
      :or
      {profile "default"}}]
  (->>
    (.build
      (->
        (AbortMultipartUploadRequest/builder)
        (.bucket bucket)
        (.key key)
        (.uploadId upload-id)))
    (.abortMultipartUpload (async-client profile))))

