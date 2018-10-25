(ns s3.core
  (:import (software.amazon.awssdk.services.s3 S3AsyncClient)
           (software.amazon.awssdk.services.s3.model
             AbortMultipartUploadRequest
             CompleteMultipartUploadRequest
             CopyObjectRequest)
           (software.amazon.awssdk.auth.credentials
             ProfileCredentialsProvider)
           (java.time Instant)))

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
  [{:keys
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
  [{:keys
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

(defmulti copy-object :Type)

(defmethod copy-object :IfMatch
  [{:keys
    [^String profile
     ^String source
     ^String key
     ^String bucket
     ^String request-payer]}]
  (.copyObject
    (client (:profile this))
    (.build
      (->
        (CopyObjectRequest/builder)
        (.copySourceIfMatch source)
        (.key key)
        (.bucket bucket)
        (.requestPayer request-payer)))))

(defmethod copy-object :Source
  [{:keys
    [^String profile
     ^String source
     ^String key
     ^String bucket
     ^String request-payer]}]
  (.copyObject
    (client profile)
    (.build
      (->
        (CopyObjectRequest/builder)
        (.copySource source)
        (.key key)
        (.bucket bucket)
        (.requestPayer request-payer)))))

(defmethod copy-object :IfModifiedSince
  [{:keys
    [^String profile
     ^Instant instant
     ^String bucket
     ^String request-payer]}]
  (.copyObject
    (client profile)
    (.build
      (->
        (CopyObjectRequest/builder)
        (.copySourceIfModifiedSince instant)
        (.key key)
        (.bucket bucket)
        (.requestPayer request-payer)))))

