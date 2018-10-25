(ns s3.core
  (:import (software.amazon.awssdk.services.s3 S3AsyncClient)
           (software.amazon.awssdk.services.s3.model
             AbortMultipartUploadRequest
             CompleteMultipartUploadRequest
             CopyObjectRequest)
           (software.amazon.awssdk.auth.credentials
             ProfileCredentialsProvider
             )))

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
      {requester-payer "requester"
       profile         "default"}}]
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
      {request-payer "requester"
       profile       "default"}}]
  (.completeMultipartUpload
    (client profile)
    (.build
      (->
        (CompleteMultipartUploadRequest/builder)
        (.bucket bucket)
        (.key key)
        (.uploadId upload-id)
        (.requestPayer request-payer)))))

(defn copy-source
  [& {:keys
          [^String copy-source
           ^String bucket
           ^String key
           ^String request-payer
           ^String profile]
      :or {request-payer "requester"
           profile       "default"}}]
  (.copyObject
    (client profile)
    (.build
      (->
        (CopyObjectRequest/builder)
        (.copySource copy-source)
        (.key key)
        (.bucket bucket)
        (.requestPayer request-payer)))))

(defn copy-source-if-match
  [& {:keys
          [^String copy-source
           ^String bucket
           ^String key
           ^String request-payer
           ^String profile]
      :or {request-payer "requester"
           profile       "default"}}]
  (.copyObject
    (client profile)
    (.build
      (->
        (CopyObjectRequest/builder)
        (.copySourceIfMatch copy-source)
        (.key key)
        (.bucket bucket)
        (.requestPayer request-payer)))))


(defn copy-object
  [& {:keys
          [^String profile
           ^CopyObjectRequest request]
      :or {profile "default"}}]
  (.copyObject (client profile) request))

(defn if-match
  [& {:keys
      [^String copy-source
       ^String bucket
       ^String key
       ^String request-payer]
      :or {request-payer "requester"}}]
  (.build
      (->
        (CopyObjectRequest/builder)
        (.copySourceIfMatch copy-source)
        (.key key)
        (.bucket bucket)
        (.requestPayer request-payer))))

(defn source
  [& {:keys
      [^String copy-source
       ^String bucket
       ^String key
       ^String request-payer]
      :or {request-payer "requester"}}]
  (.build
      (->
        (CopyObjectRequest/builder)
        (.copySource copy-source)
        (.key key)
        (.bucket bucket)
        (.requestPayer request-payer))))

