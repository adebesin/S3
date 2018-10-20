(ns s3.core
  (:import (software.amazon.awssdk.services.s3 S3AsyncClient)
           (software.amazon.awssdk.services.s3.model AbortMultipartUploadRequest ListMultipartUploadsRequest RequestPayer)
           (software.amazon.awssdk.regions Region)
           (software.amazon.awssdk.auth.credentials ProfileCredentialsProvider)))

(defn credentials-provider
  [^String name]
  (.build
    (-> (ProfileCredentialsProvider/builder)
        (.profileName name))))

(defn async-client
  ([]
   (.build
     (S3AsyncClient/builder)))
  ([^Region region]
   (.build
     (-> (S3AsyncClient/builder)
         (.region region))))
  ([^Region region
    ^String name]
   (.build
     (-> (S3AsyncClient/builder)
         (.region region)
         (.credentialsProvider (credentials-provider name))))))

(defn abort-multipart-upload
  [& {:keys                                                 ;;TODO add request payer
      [^String bucket
       ^String key upload-id]}]
  (->>
    (.build
      (->
        (AbortMultipartUploadRequest/builder)
        (.bucket bucket)
        (.key key)
        (.uploadId upload-id)))
    (.abortMultipartUpload (async-client))))

