(ns s3.core
  (:import
    (software.amazon.awssdk.services.s3
      S3AsyncClient)
    (software.amazon.awssdk.services.s3.model
      AbortMultipartUploadRequest
      CompleteMultipartUploadRequest
      CopyObjectRequest
      PutObjectRequest
      PutObjectAclRequest
      ObjectCannedACL
      RequestPayer
      PutObjectTaggingRequest
      Tagging
      PutBucketAccelerateConfigurationRequest
      AccelerateConfiguration
      PutBucketAclRequest
      AccessControlPolicy
      BucketCannedACL
      PutBucketAnalyticsConfigurationRequest
      AnalyticsConfiguration
      PutBucketCorsRequest
      CORSConfiguration
      MetadataDirective
      ServerSideEncryption)
    (software.amazon.awssdk.auth.credentials
      ProfileCredentialsProvider)
    (software.amazon.awssdk.core.async
      AsyncRequestBody)
    (java.time
      Instant)
    (java.io
      File)))

(defmulti put-bucket :Type)
(defmulti put-object :Type)

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
    {^RequestPayer RequesterPayer (RequestPayer/REQUESTER)
     ^String Profile              "default"}}]
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
    {^RequestPayer RequestPayer (RequestPayer/REQUESTER)
     ^String Profile            "default"}}]
  (.completeMultipartUpload
    (client Profile)
    (.build
      (->
        (CompleteMultipartUploadRequest/builder)
        (.bucket Bucket)
        (.key Key)
        (.uploadId UploadId)
        (.requestPayer RequestPayer)))))

(defn copy-object
  [{:keys
    [^String CopySource
     ^String Bucket
     ^String CopySourceIfMatch
     ^String CopySourceIfNoneMatch
     ^String Key
     ^Instant CopySourceIfModifiedSince
     ^Instant CopySourceIfUnmodifiedSince
     ^String CopySourceSseCustomerAlgorithm
     ^String GrantWriteAcp
     ^String GrantReadAcp
     ^String GrantRead
     ^String GrantFullControl
     ^String CacheControl
     ^String ContentDisposition
     ^String ContentEncoding
     ^String ContentLanguage
     ^Instant Expires
     ^Instant Metadata
     ^MetadataDirective MetadataDirective
     ^ServerSideEncryption ServerSideEncryption
     ^String SseCustomerKey
     ^String ContentType
     ^ObjectCannedACL Acl
     ^String RequestPayer
     ^String Profile]
    :or
    {^RequestPayer RequestPayer (RequestPayer/REQUESTER)
     ^String Profile            "default"}}]
  (.copyObject
    (client Profile)
    (.build
      (->
        (CopyObjectRequest/builder)
        (.copySource CopySource)
        (.copySourceIfMatch CopySourceIfMatch)
        (.copySourceSSECustomerAlgorithm CopySourceSseCustomerAlgorithm)
        (.copySourceIfModifiedSince CopySourceIfModifiedSince)
        (.copySourceIfNoneMatch CopySourceIfNoneMatch)
        (.copySourceIfUnmodifiedSince CopySourceIfUnmodifiedSince)
        (.grantWriteACP GrantWriteAcp)
        (.grantReadACP GrantReadAcp)
        (.grantRead GrantRead)
        (.grantFullControl GrantFullControl)
        (.acl Acl)
        (.cacheControl CacheControl)
        (.contentDisposition ContentDisposition)
        (.contentEncoding ContentEncoding)
        (.contentLanguage ContentLanguage)
        (.contentType ContentType)
        (.expires Expires)
        (.metadata Metadata)
        (.metadataDirective MetadataDirective)
        (.serverSideEncryption ServerSideEncryption)
        (.sseCustomerKey SseCustomerKey)
        ;;TODO add remaining method calls + rename keys below to be  more descriptive
        (.key Key)
        (.bucket Bucket)
        (.requestPayer RequestPayer)))))

(defmethod put-object :Request
  [{:keys
    [^File FromFile
     ^String Bucket
     ^ObjectCannedACL ObjectCannedAcl
     ^String CacheControl
     ^String ContentDisposition
     ^String ContentEncoding
     ^String ContentLanguage
     ^String ContentMD5
     ^String ContentType
     ^Instant Expires
     ^String GrantFullControl
     ^String GrantRead
     ^String GrantReadAcp
     ^String GrantWriteAcp
     ^String StorageClass
     ^String WebsiteRedirectLocation
     ^String SseCustomerAlgorithm
     ^String SseCustomerKey
     ^String ServerSideEncryption
     ^String SseKmsKeyId
     ^String Key
     ^String RequestPayer
     ^String Profile]
    :or
    {^RequestPayer RequestPayer (RequestPayer/REQUESTER)
     ^String Profile            "default"}}]
  (.putObject
    (client Profile)
    (.build
      (->
        (PutObjectRequest/builder)
        (.key Key)
        (.bucket Bucket)
        (.acl ObjectCannedAcl)
        (.cacheControl CacheControl)
        (.contentDisposition ContentDisposition)
        (.contentEncoding ContentEncoding)
        (.contentLanguage ContentLanguage)
        (.contentMD5 ContentMD5)
        (.contentType ContentType)
        (.expires Expires)
        (.grantFullControl GrantFullControl)
        (.grantRead GrantRead)
        (.grantReadACP GrantReadAcp)
        (.grantWriteACP GrantWriteAcp)
        (.serverSideEncryption ServerSideEncryption)
        (.storageClass StorageClass)
        (.websiteRedirectLocation WebsiteRedirectLocation)
        (.requestPayer RequestPayer)
        (.sseCustomerAlgorithm SseCustomerAlgorithm)
        (.sseCustomerKey SseCustomerKey)
        (.ssekmsKeyId SseKmsKeyId)))
    (AsyncRequestBody/fromFile FromFile)))

(defmethod put-object :AclRequest
  [{:keys
    [^String Bucket
     ^ObjectCannedACL Acl
     ^String Key
     ^String GrantWriteAcp
     ^String GrantWrite
     ^String GrantReadAcp
     ^String GrantFullControl
     ^String ContentMd5
     ^String RequestPayer
     ^String Profile]
    :or
    {^RequestPayer RequestPayer (RequestPayer/REQUESTER)
     ^String Profile            "default"}}]
  (.putObjectAcl
    (client Profile)
    (.build
      (->
        (PutObjectAclRequest/builder)
        (.key Key)
        (.bucket Bucket)
        (.acl Acl)
        (.grantWriteACP GrantWriteAcp)
        (.grantWrite GrantWrite)
        (.grantReadACP GrantReadAcp)
        (.grantFullControl GrantFullControl)
        (.contentMD5 ContentMd5)))))

(defmethod put-object :TaggingRequest
  [{:keys
    [^String Bucket
     ^String Key
     ^Tagging Tagging
     ^String ContentMd5
     ^String VersionId
     ^String RequestPayer
     ^String Profile]
    :or
    {^RequestPayer RequestPayer (RequestPayer/REQUESTER)
     ^String Profile            "default"}}]
  (.putObjectTagging
    (client Profile)
    (.build
      (->
        (PutObjectTaggingRequest/builder)
        (.key Key)
        (.bucket Bucket)
        (.tagging Tagging)
        (.contentMD5 ContentMd5)
        (.versionId VersionId)))))

(defmethod put-bucket :AccelerateConfigurationRequest
  [{:keys
    [^String Bucket
     ^AccelerateConfiguration AccelerateConfiguration
     ^String RequestPayer
     ^String Profile]
    :or
    {^RequestPayer RequestPayer (RequestPayer/REQUESTER)
     ^String Profile            "default"}}]
  (.putBucketAccelerateConfiguration
    (client Profile)
    (.build
      (->
        (PutBucketAccelerateConfigurationRequest/builder)
        (.bucket Bucket)
        (.accelerateConfiguration AccelerateConfiguration)))))

(defmethod put-bucket :AclRequest
  [{:keys
    [^String Bucket
     ^AccessControlPolicy AccessControlPolicy
     ^BucketCannedACL BucketCannedAcl
     ^String ContentMd5
     ^String GrantFullControl
     ^String GrantRead
     ^String GrantReadAcp
     ^String GrantWrite
     ^String GrantWriteAcp
     ^String RequestPayer
     ^String Profile]
    :or
    {^RequestPayer RequestPayer (RequestPayer/REQUESTER)
     ^String Profile            "default"}}]
  (.putBucketAcl
    (client Profile)
    (.build
      (->
        (PutBucketAclRequest/builder)
        (.bucket Bucket)
        (.accessControlPolicy AccessControlPolicy)
        (.acl BucketCannedAcl)
        (.contentMD5 ContentMd5)
        (.grantFullControl GrantFullControl)
        (.grantRead GrantRead)
        (.grantReadACP GrantReadAcp)
        (.grantWrite GrantWrite)
        (.grantWriteACP GrantWriteAcp)))))

(defmethod put-bucket :AnalyticsConfigurationRequest
  [{:keys
    [^String Bucket
     ^AnalyticsConfiguration AnalyticsConfiguration
     ^String Id
     ^String RequestPayer
     ^String Profile]
    :or
    {^RequestPayer RequestPayer (RequestPayer/REQUESTER)
     ^String Profile            "default"}}]
  (.putBucketAnalyticsConfiguration
    (client Profile)
    (.build
      (->
        (PutBucketAnalyticsConfigurationRequest/builder)
        (.bucket Bucket)
        (.analyticsConfiguration AnalyticsConfiguration)
        (.id Id)))))

(defmethod put-bucket :CorsRequest
  [{:keys
    [^String Bucket
     ^CORSConfiguration CorsConfiguration
     ^String ContentMd5
     ^String RequestPayer
     ^String Profile]
    :or
    {^RequestPayer RequestPayer (RequestPayer/REQUESTER)
     ^String Profile            "default"}}]
  (.putBucketCors
    (client Profile)
    (.build
      (->
        (PutBucketCorsRequest/builder)
        (.bucket Bucket)
        (.contentMD5 ContentMd5)
        (.corsConfiguration CorsConfiguration)))))

(defmethod put-bucket :EncryptionRequest
  [{:keys
    [^String Bucket
     ^CORSConfiguration CorsConfiguration
     ^String ContentMd5
     ^String RequestPayer
     ^String Profile]
    :or
    {^RequestPayer RequestPayer (RequestPayer/REQUESTER)
     ^String Profile            "default"}}]
  (.putBucketCors
    (client Profile)
    (.build
      (->
        (PutBucketCorsRequest/builder)
        (.bucket Bucket)
        (.contentMD5 ContentMd5)
        (.corsConfiguration CorsConfiguration)))))


