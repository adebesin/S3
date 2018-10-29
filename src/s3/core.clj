(ns s3.core
  (:import
    (software.amazon.awssdk.services.s3
      S3AsyncClient)
    (software.amazon.awssdk.services.s3.model
      AbortMultipartUploadRequest
      CompleteMultipartUploadRequest
      CopyObjectRequest PutObjectRequest)
    (software.amazon.awssdk.auth.credentials
      ProfileCredentialsProvider)
    (software.amazon.awssdk.core.async
      AsyncRequestBody)
    (java.time
      Instant)
    (java.io
      File)))

(defmulti copy-object :Type)

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
         (.credentialsProvider
           (creds name))))))

(defn abort-multipart-upload
  [{:keys
    [^String Bucket
     ^String Key
     ^String UploadId
     ^String RequesterPayer
     ^String Profile]
    :or
    {^String RequesterPayer "requester"
     ^String Profile         "default"}}]
  (.abortMultipartUpload
    (client Profile)
    (.build
      (->
        (AbortMultipartUploadRequest/builder)
        (.bucket Bucket)
        (.key Key)
        (.uploadId UploadId)
        (.requestPayer RequesterPayer)))))

(defn complete-multipart-upload
  [{:keys
    [^String Bucket
     ^String Key
     ^String UploadId
     ^String RequestPayer
     ^String Profile]
    :or
    {^String RequestPayer "requester"
     ^String Profile       "default"}}]
  (.completeMultipartUpload
    (client Profile)
    (.build
      (->
        (CompleteMultipartUploadRequest/builder)
        (.bucket Bucket)
        (.key Key)
        (.uploadId UploadId)
        (.requestPayer RequestPayer)))))

(defmethod copy-object :IfMatch
  [{:keys
    [^String Source
     ^String Bucket
     ^String Match
     ^String Key
     ^String RequestPayer
     ^String Profile]
    :or
    {^String Profile       "default"
     ^String RequestPayer "requester"}}]
  (.copyObject
    (client Profile)
    (.build
      (->
        (CopyObjectRequest/builder)
        (.copySourceIfMatch Match)
        (.key Key)
        (.bucket Bucket)
        (.copySource Source)
        (.requestPayer RequestPayer)))))

(defmethod copy-object :Source
  [{:keys
    [^String Source
     ^String Bucket
     ^String Key
     ^String RequestPayer
     ^String Profile]
    :or
    {^String RequestPayer "requester"
     ^String Profile       "Profile"}}]
  (.copyObject
    (client Profile)
    (.build
      (->
        (CopyObjectRequest/builder)
        (.copySource Source)
        (.key Key)
        (.bucket Bucket)
        (.requestPayer RequestPayer)))))

(defmethod copy-object :IfModifiedSince
  [{:keys
    [^String Source
     ^String Bucket
     ^String Key
     ^Instant Instant
     ^String RequestPayer
     ^String Profile]
    :or
    {^String RequestPayer "requester"
     ^String Profile       "Profile"}}]
  (.copyObject
    (client Profile)
    (.build
      (->
        (CopyObjectRequest/builder)
        (.copySourceIfModifiedSince
          Instant)
        (.copySource Source)
        (.key Key)
        (.bucket Bucket)
        (.requestPayer RequestPayer)))))

(defmethod copy-object :IfNoneMatch
  [{:keys
    [^String Source
     ^String Bucket
     ^String Key
     ^String Match
     ^String RequestPayer
     ^String Profile]
    :or
    {^String RequestPayer "requester"
     ^String Profile       "Profile"}}]
  (.copyObject
    (client Profile)
    (.build
      (->
        (CopyObjectRequest/builder)
        (.copySourceIfNoneMatch Match)
        (.copySource Source)
        (.key Key)
        (.bucket Bucket)
        (.requestPayer RequestPayer)))))

(defmethod copy-object :IfUnmodifiedSince
  [{:keys
    [^String Source
     ^String Bucket
     ^String Key
     ^Instant Instant
     ^String RequestPayer
     ^String Profile]
    :or
    {^String RequestPayer "requester"
     ^String Profile       "Profile"}}]
  (.copyObject
    (client Profile)
    (.build
      (->
        (CopyObjectRequest/builder)
        (.copySourceIfUnmodifiedSince Instant)
        (.copySource Source)
        (.key Key)
        (.bucket Bucket)
        (.requestPayer RequestPayer)))))

(defmethod copy-object :SSECustomAlgorithm
  [{:keys
    [^String Source
     ^String Bucket
     ^String Key
     ^String Algorithm
     ^String RequestPayer
     ^String Profile]
    :or
    {^String RequestPayer "requester"
     ^String Profile       "Profile"}}]
  (.copyObject
    (client Profile)
    (.build
      (->
        (CopyObjectRequest/builder)
        (.copySourceSSECustomerAlgorithm Algorithm)
        (.copySource Source)
        (.key Key)
        (.bucket Bucket)
        (.requestPayer RequestPayer)))))

(defn put-object
  [{:keys
    [^File File
     ^String Bucket
     ^String Acl
     ^String CacheControl
     ^String ContentDisposition
     ^String ContentEncoding
     ^String ContentLanguage
     ^String ContentMD5
     ^String ContentType
     ^Instant Instant
     ^String GrantFullControl
     ^String GrantRead
     ^String GrantReadAcp
     ^String GrantWriteAcp
     ^String StorageClass
     ^String WebsiteRedirectLocation
     ^String SseCustomerAlgorithm
     ^String SseCustomerKey
     ^String Sse
     ^String SseKmsKeyId
     ^String Key
     ^String RequestPayer
     ^String Profile]
    :or
    {^String RequestPayer "requester"
     ^String Profile       "Profile"}}]
  (.putObject
    (client Profile)
    (.build
      (->
        (PutObjectRequest/builder)
        (.key Key)
        (.bucket Bucket)
        (.acl Acl)
        (.cacheControl CacheControl)
        (.contentDisposition ContentDisposition)
        (.contentEncoding ContentEncoding)
        (.contentLanguage ContentLanguage)
        (.contentMD5 ContentMD5)
        (.contentType ContentType)
        (.expires Instant)
        (.grantFullControl GrantFullControl)
        (.grantRead GrantRead)
        (.grantReadACP GrantReadAcp)
        (.grantWriteACP GrantWriteAcp)
        (.serverSideEncryption Sse)
        (.storageClass StorageClass)
        (.websiteRedirectLocation WebsiteRedirectLocation)
        (.requestPayer RequestPayer)
        (.sseCustomerAlgorithm SseCustomerAlgorithm)
        (.sseCustomerKey SseCustomerKey)
        (.ssekmsKeyId SseKmsKeyId)))
    (AsyncRequestBody/fromFile File)))


