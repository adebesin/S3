(ns s3.core
  (:import
    (software.amazon.awssdk.services.s3
      S3AsyncClient
      DefaultS3AsyncClient)
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
      ServerSideEncryption
      PutBucketInventoryConfigurationRequest
      InventoryConfiguration
      PutBucketLifecycleConfigurationRequest
      BucketLifecycleConfiguration
      PutBucketLoggingRequest
      BucketLoggingStatus
      PutBucketMetricsConfigurationRequest
      MetricsConfiguration
      PutBucketNotificationConfigurationRequest
      NotificationConfiguration
      PutBucketNotificationRequest
      NotificationConfigurationDeprecated
      PutBucketLifecycleRequest
      LifecycleConfiguration
      PutBucketPolicyRequest
      PutBucketReplicationRequest
      ReplicationConfiguration
      PutBucketRequestPaymentRequest
      RequestPaymentConfiguration
      PutBucketTaggingRequest
      PutBucketVersioningRequest
      VersioningConfiguration
      PutBucketWebsiteRequest
      WebsiteConfiguration
      RestoreObjectRequest
      RestoreRequest
      UploadPartRequest
      UploadPartCopyRequest
      CreateBucketRequest
      CreateBucketConfiguration
      DeleteBucketRequest
      DeleteBucketAnalyticsConfigurationRequest
      DeleteBucketCorsRequest
      DeleteBucketInventoryConfigurationRequest
      DeleteBucketLifecycleRequest
      DeleteBucketMetricsConfigurationRequest
      DeleteBucketPolicyRequest
      DeleteBucketReplicationRequest
      DeleteBucketTaggingRequest
      DeleteBucketWebsiteRequest
      DeleteObjectRequest
      DeleteObjectTaggingRequest
      DeleteObjectsRequest Delete
      GetObjectRequest
      GetObjectAclRequest
      GetObjectTaggingRequest
      GetObjectTorrentRequest)
    (software.amazon.awssdk.auth.credentials
      ProfileCredentialsProvider)
    (software.amazon.awssdk.core.async
      AsyncRequestBody
      AsyncResponseTransformer)
    (java.time
      Instant)
    (java.io
      File)
    (java.util.concurrent
      CompletableFuture)))

(defmulti put-bucket :type)
(defmulti put-object :type)
(defmulti copy-object :type)
(defmulti upload-part :type)
(defmulti restore-object :type)
(defmulti multipart-upload :type)
(defmulti create-bucket :type)
(defmulti delete-bucket :type)
(defmulti delete-object :type)
(defmulti delete-objects :type)
(defmulti get-object :type)

(defn- ^ProfileCredentialsProvider creds
  [^String name]
  (.build
    (-> (ProfileCredentialsProvider/builder)
        (.profileName name))))

(defn- ^DefaultS3AsyncClient client
  ([]
   (.build
     (S3AsyncClient/builder)))
  ([^String name]
   (.build
     (-> (S3AsyncClient/builder)
         (.credentialsProvider
           (creds name))))))

(defmethod ^CompletableFuture multipart-upload
  :AbortRequest
  [{:keys
        [^String Bucket
         ^String Key
         ^String UploadId
         ^RequestPayer RequesterPayer
         ^String Profile]
    :or
        {^RequestPayer RequesterPayer (RequestPayer/REQUESTER)
         ^String Profile              "default"}
    :as args}]
  (.abortMultipartUpload
    (client Profile)
    ^AbortMultipartUploadRequest
    (.build
      (->
        (AbortMultipartUploadRequest/builder)
        (.bucket Bucket)
        (.key Key)
        (.uploadId UploadId)
        (.requestPayer RequesterPayer)))))

(defmethod ^CompletableFuture copy-object
  :Request
  [{:keys
    [^String CopySource
     ^String Bucket
     ^String CopySourceIfMatch
     ^String CopySourceIfNoneMatch
     ^String Key
     ^Instant CopySourceIfModifiedSince
     ^Instant CopySourceIfUnmodifiedSince
     ^String CopySourceSseCustomerAlgorithm
     ^String CopySourceSseCustomerKey
     ^String CopySourceSseCustomerKeyMd5
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
     ^String SseKmsKeyId
     ^String StorageClass
     ^String Tagging
     ^String TaggingDirective
     ^String WebsiteRedirectionLocation
     ^String ContentType
     ^ObjectCannedACL ObjectCannedAcl
     ^RequestPayer RequestPayer
     ^String Profile]
    :or
    {^RequestPayer RequestPayer (RequestPayer/REQUESTER)
     ^String Profile            "default"}}]
  (.copyObject
    (client Profile)
    ^CopyObjectRequest
    (.build
      (->
        (CopyObjectRequest/builder)
        (.copySource CopySource)
        (.copySourceIfMatch CopySourceIfMatch)
        (.copySourceSSECustomerAlgorithm CopySourceSseCustomerAlgorithm)
        (.copySourceSSECustomerKey CopySourceSseCustomerKey)
        (.copySourceSSECustomerKeyMD5 CopySourceSseCustomerKeyMd5)
        (.copySourceIfModifiedSince CopySourceIfModifiedSince)
        (.copySourceIfNoneMatch CopySourceIfNoneMatch)
        (.copySourceIfUnmodifiedSince CopySourceIfUnmodifiedSince)
        (.grantWriteACP GrantWriteAcp)
        (.grantReadACP GrantReadAcp)
        (.grantRead GrantRead)
        (.grantFullControl GrantFullControl)
        (.acl ObjectCannedAcl)
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
        (.ssekmsKeyId SseKmsKeyId)
        (.storageClass StorageClass)
        (.tagging Tagging)
        (.taggingDirective TaggingDirective)
        (.websiteRedirectLocation WebsiteRedirectionLocation)
        (.key Key)
        (.bucket Bucket)
        (.requestPayer RequestPayer)))))

(defmethod ^CompletableFuture create-bucket
  :Request
  [{:keys
    [^String Bucket
     ^String GrantWriteAcp
     ^String GrantReadAcp
     ^String GrantRead
     ^String GrantFullControl
     ^CreateBucketConfiguration CreateBucketConfiguration
     ^BucketCannedACL BucketCannedAcl
     ^String Profile]
    :or
    {^String Profile "default"}}]
  (.createBucket
    (client Profile)
    ^CreateBucketRequest
    (.build
      (->
        (CreateBucketRequest/builder)
        (.grantWriteACP GrantWriteAcp)
        (.grantReadACP GrantReadAcp)
        (.grantRead GrantRead)
        (.grantFullControl GrantFullControl)
        (.acl BucketCannedAcl)
        (.createBucketConfiguration CreateBucketConfiguration)
        (.bucket Bucket)))))

(defmethod ^CompletableFuture multipart-upload
  :CompleteRequest
  [{:keys
    [^String Bucket
     ^String Key
     ^String UploadId
     ^RequestPayer RequestPayer
     ^String Profile]
    :or
    {^RequestPayer RequestPayer (RequestPayer/REQUESTER)
     ^String Profile            "default"}}]
  (.completeMultipartUpload
    (client Profile)
    ^CompleteMultipartUploadRequest
    (.build
      (->
        (CompleteMultipartUploadRequest/builder)
        (.bucket Bucket)
        (.key Key)
        (.uploadId UploadId)
        (.requestPayer RequestPayer)))))

(defmethod ^CompletableFuture delete-bucket
  :Request
  [{:keys
    [^String Bucket
     ^String Profile]
    :or
    {^String Profile "default"}}]
  (.deleteBucket
    (client Profile)
    ^DeleteBucketRequest
    (.build
      (->
        (DeleteBucketRequest/builder)
        (.bucket Bucket)))))

(defmethod ^CompletableFuture delete-bucket
  :AnalyticsConfigurationRequest
  [{:keys
    [^String Bucket
     ^String Profile
     ^String Id]
    :or
    {^String Profile "default"}}]
  (.deleteBucketAnalyticsConfiguration
    (client Profile)
    ^DeleteBucketAnalyticsConfigurationRequest
    (.build
      (->
        (DeleteBucketAnalyticsConfigurationRequest/builder)
        (.bucket Bucket)
        (.id Id)))))

(defmethod ^CompletableFuture delete-bucket
  :CorsRequest
  [{:keys
    [^String Bucket
     ^String Profile]
    :or
    {^String Profile "default"}}]
  (.deleteBucketCors
    (client Profile)
    ^DeleteBucketCorsRequest
    (.build
      (->
        (DeleteBucketCorsRequest/builder)
        (.bucket Bucket)))))

(defmethod ^CompletableFuture delete-bucket
  :InventoryConfigurationRequest
  [{:keys
    [^String Bucket
     ^String Id
     ^String Profile]
    :or
    {^String Profile "default"}}]
  (.deleteBucketInventoryConfiguration
    (client Profile)
    ^DeleteBucketInventoryConfigurationRequest
    (.build
      (->
        (DeleteBucketInventoryConfigurationRequest/builder)
        (.id Id)
        (.bucket Bucket)))))

(defmethod ^CompletableFuture delete-bucket
  :LifecycleRequest
  [{:keys
    [^String Bucket
     ^String Profile]
    :or
    {^String Profile "default"}}]
  (.deleteBucketLifecycle
    (client Profile)
    ^DeleteBucketLifecycleRequest
    (.build
      (->
        (DeleteBucketLifecycleRequest/builder)
        (.bucket Bucket)))))

(defmethod ^CompletableFuture delete-bucket
  :MetricsConfigurationRequest
  [{:keys
    [^String Bucket
     ^String Id
     ^String Profile]
    :or
    {^String Profile "default"}}]
  (.deleteBucketMetricsConfiguration
    (client Profile)
    ^DeleteBucketMetricsConfigurationRequest
    (.build
      (->
        (DeleteBucketMetricsConfigurationRequest/builder)
        (.bucket Bucket)
        (.id Id)))))

(defmethod ^CompletableFuture delete-bucket
  :PolicyRequest
  [{:keys
    [^String Bucket
     ^String Profile]
    :or
    {^String Profile "default"}}]
  (.deleteBucketPolicy
    (client Profile)
    ^DeleteBucketPolicyRequest
    (.build
      (->
        (DeleteBucketPolicyRequest/builder)
        (.bucket Bucket)))))

(defmethod ^CompletableFuture delete-bucket
  :ReplicationRequest
  [{:keys
    [^String Bucket
     ^String Profile]
    :or
    {^String Profile "default"}}]
  (.deleteBucketReplication
    (client Profile)
    ^DeleteBucketReplicationRequest
    (.build
      (->
        (DeleteBucketReplicationRequest/builder)
        (.bucket Bucket)))))

(defmethod ^CompletableFuture delete-bucket
  :TaggingRequest
  [{:keys
    [^String Bucket
     ^String Profile]
    :or
    {^String Profile "default"}}]
  (.deleteBucketTagging
    (client Profile)
    ^DeleteBucketTaggingRequest
    (.build
      (->
        (DeleteBucketTaggingRequest/builder)
        (.bucket Bucket)))))

(defmethod ^CompletableFuture delete-bucket
  :WebsiteRequest
  [{:keys
    [^String Bucket
     ^String Profile]
    :or
    {^String Profile "default"}}]
  (.deleteBucketWebsite
    (client Profile)
    ^DeleteBucketWebsiteRequest
    (.build
      (->
        (DeleteBucketWebsiteRequest/builder)
        (.bucket Bucket)))))

(defmethod ^CompletableFuture delete-object
  :Request
  [{:keys
    [^String Bucket
     ^RequestPayer RequestPayer
     ^String Key
     ^String Mfa
     ^String VersionId
     ^String Profile]
    :or
    {^String Profile "default"}}]
  (.deleteObject
    (client Profile)
    ^DeleteObjectRequest
    (.build
      (->
        (DeleteObjectRequest/builder)
        (.bucket Bucket)
        (.requestPayer RequestPayer)
        (.key Key)
        (.mfa Mfa)
        (.versionId VersionId)))))

(defmethod ^CompletableFuture delete-object
  :TaggingRequest
  [{:keys
    [^String Bucket
     ^String Key
     ^String VersionId
     ^String Profile]
    :or
    {^String Profile "default"}}]
  (.deleteObjectTagging
    (client Profile)
    ^DeleteObjectTaggingRequest
    (.build
      (->
        (DeleteObjectTaggingRequest/builder)
        (.bucket Bucket)
        (.key Key)
        (.versionId VersionId)))))

(defmethod ^CompletableFuture delete-objects
  :Request
  [{:keys
    [^String Bucket
     ^String Mfa
     ^Delete Delete
     ^RequestPayer RequestPayer
     ^String Profile]
    :or
    {^String Profile "default"}}]
  (.deleteObjects
    (client Profile)
    ^DeleteObjectsRequest
    (.build
      (->
        (DeleteObjectsRequest/builder)
        (.bucket Bucket)
        (.mfa Mfa)
        (.requestPayer RequestPayer)
        (.delete Delete)))))

(defmethod ^CompletableFuture get-object
  :Request
  [{:keys
    [^String Bucket
     ^String Key
     ^String VersionId
     ^String SseCustomerKey
     ^String IfMatch
     ^Instant IfModifiedSince
     ^Instant IfUnModifiedSince
     ^String IfNoneMatch
     ^String Range
     ^String ResponseCacheControl
     ^String ResponseContentDisposition
     ^String ResponseContentEncoding
     ^String ResponseContentLanguage
     ^String ResponseContentType
     ^String SseCustomerAlgorithm
     ^String SseCustomerKeyMd5
     ^Instant ResponseExpires
     ^Integer PartNumber
     ^File ToFile
     ^RequestPayer RequestPayer
     ^String Profile]
    :or
    {^String Profile "default"}}]
  (.getObject
    (client Profile)
    ^GetObjectRequest
    (.build
      (->
        (GetObjectRequest/builder)
        (.bucket Bucket)
        (.key Key)
        (.requestPayer RequestPayer)
        (.versionId VersionId)
        (.sseCustomerKey SseCustomerKey)
        (.ifMatch IfMatch)
        (.ifModifiedSince IfModifiedSince)
        (.ifUnmodifiedSince IfUnModifiedSince)
        (.ifNoneMatch IfNoneMatch)
        (.partNumber PartNumber)
        (.range Range)
        (.responseCacheControl ResponseCacheControl)
        (.responseContentDisposition ResponseContentDisposition)
        (.responseContentEncoding ResponseContentEncoding)
        (.responseContentLanguage ResponseContentLanguage)
        (.responseContentType ResponseContentType)
        (.responseExpires ResponseExpires)
        (.sseCustomerAlgorithm SseCustomerAlgorithm)
        (.sseCustomerKeyMD5 SseCustomerKeyMd5)))
    (AsyncResponseTransformer/toFile ToFile)))

(defmethod ^CompletableFuture get-object
  :AclRequest
  [{:keys
    [^String Bucket
     ^String Key
     ^String VersionId
     ^RequestPayer RequestPayer
     ^String Profile]
    :or
    {^String Profile "default"}}]
  (.getObjectAcl
    (client Profile)
    ^GetObjectAclRequest
    (.build
      (->
        (GetObjectAclRequest/builder)
        (.bucket Bucket)
        (.key Key)
        (.requestPayer RequestPayer)
        (.versionId VersionId)))))

(defmethod ^CompletableFuture get-object
  :TaggingRequest
  [{:keys
    [^String Bucket
     ^String Key
     ^String VersionId
     ^String Profile]
    :or
    {^String Profile "default"}}]
  (.getObjectTagging
    (client Profile)
    ^GetObjectTaggingRequest
    (.build
      (->
        (GetObjectTaggingRequest/builder)
        (.bucket Bucket)
        (.key Key)
        (.versionId VersionId)))))

(defmethod ^CompletableFuture get-object
  :TorrentRequest
  [{:keys
    [^String Bucket
     ^String Key
     ^RequestPayer RequestPayer
     ^File ToFile
     ^String Profile]
    :or
    {^String Profile "default"}}]
  (.getObjectTorrent
    (client Profile)
    ^GetObjectTorrentRequest
    (.build
      (->
        (GetObjectTorrentRequest/builder)
        (.bucket Bucket)
        (.key Key)
        (.requestPayer RequestPayer)))
    (AsyncResponseTransformer/toFile ToFile)))

(defmethod ^CompletableFuture put-object
  :Request
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
     ^RequestPayer RequestPayer
     ^String Profile]
    :or
    {^RequestPayer RequestPayer (RequestPayer/REQUESTER)
     ^String Profile            "default"}}]
  (.putObject
    (client Profile)
    ^PutObjectRequest
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

(defmethod put-object
  :AclRequest
  [{:keys
    [^String Bucket
     ^ObjectCannedACL Acl
     ^String Key
     ^String GrantWriteAcp
     ^String GrantWrite
     ^String GrantReadAcp
     ^String GrantFullControl
     ^String ContentMd5
     ^RequestPayer RequestPayer
     ^String Profile]
    :or
    {^RequestPayer RequestPayer (RequestPayer/REQUESTER)
     ^String Profile            "default"}}]
  (.putObjectAcl
    (client Profile)
    ^PutObjectAclRequest
    (.build
      (->
        (PutObjectAclRequest/builder)
        (.key Key)
        (.bucket Bucket)
        (.acl Acl)
        (.requestPayer RequestPayer)
        (.grantWriteACP GrantWriteAcp)
        (.grantWrite GrantWrite)
        (.grantReadACP GrantReadAcp)
        (.grantFullControl GrantFullControl)
        (.contentMD5 ContentMd5)))))

(defmethod ^CompletableFuture put-object
  :TaggingRequest
  [{:keys
    [^String Bucket
     ^String Key
     ^Tagging Tagging
     ^String ContentMd5
     ^String VersionId
     ^RequestPayer RequestPayer
     ^String Profile]
    :or
    {^RequestPayer RequestPayer (RequestPayer/REQUESTER)
     ^String Profile            "default"}}]
  (.putObjectTagging
    (client Profile)
    ^PutObjectTaggingRequest
    (.build
      (->
        (PutObjectTaggingRequest/builder)
        (.key Key)
        (.bucket Bucket)
        (.tagging Tagging)
        (.contentMD5 ContentMd5)
        (.versionId VersionId)))))

(defmethod ^CompletableFuture restore-object
  :Request
  [{:keys
    [^String Bucket
     ^String Key
     ^String VersionId
     ^RequestPayer RequestPayer
     ^RestoreRequest RestoreRequest
     ^String Profile]
    :or
    {^RequestPayer RequestPayer (RequestPayer/REQUESTER)
     ^String Profile            "default"}}]
  (.restoreObject
    (client Profile)
    ^RestoreObjectRequest
    (.build
      (->
        (RestoreObjectRequest/builder)
        (.key Key)
        (.bucket Bucket)
        (.versionId VersionId)
        (.requestPayer RequestPayer)
        (.key Key)
        (.restoreRequest RestoreRequest)))))

(defmethod ^CompletableFuture upload-part
  :Request
  [{:keys
    [^String Bucket
     ^String Key
     ^File FromFile
     ^String ContentMd5
     ^RequestPayer RequestPayer
     ^String SseCustomerKey
     ^String UploadId
     ^Integer PartNumber
     ^Long ContentLength
     ^String SseCustomerAlgorithm
     ^String SseCustomerKeyMd5
     ^String Profile]
    :or
    {^RequestPayer RequestPayer (RequestPayer/REQUESTER)
     ^String Profile            "default"}}]
  (.uploadPart
    (client Profile)
    ^UploadPartRequest
    (.build
      (->
        (UploadPartRequest/builder)
        (.key Key)
        (.bucket Bucket)
        (.requestPayer RequestPayer)
        (.contentMD5 ContentMd5)
        (.sseCustomerKey SseCustomerKey)
        (.uploadId UploadId)
        (.contentLength ContentLength)
        (.partNumber PartNumber)
        (.sseCustomerAlgorithm SseCustomerAlgorithm)
        (.sseCustomerKeyMD5 SseCustomerKeyMd5)))
    (AsyncRequestBody/fromFile FromFile)))

(defmethod ^CompletableFuture upload-part
  :CopyRequest
  [{:keys
    [^String Bucket
     ^String Key
     ^RequestPayer RequestPayer
     ^String CopySource
     ^String SseCustomerKey
     ^String CopySourceRange
     ^Instant CopySourceIfModifiedSince
     ^Instant CopySourceIfUnmodifiedSince
     ^String CopySourceSseCustomerAlgorithm
     ^String CopySourceSseCustomerKey
     ^String CopySourceSseCustomerKeyMd5
     ^String CopySourceIfMatch
     ^String UploadId
     ^String CopySourceIfNoneMatch
     ^Integer PartNumber
     ^String SseCustomerAlgorithm
     ^String SseCustomerKeyMd5
     ^String Profile]
    :or
    {^RequestPayer RequestPayer (RequestPayer/REQUESTER)
     ^String Profile            "default"}}]
  (.uploadPartCopy
    (client Profile)
    ^UploadPartCopyRequest
    (.build
      (->
        (UploadPartCopyRequest/builder)
        (.key Key)
        (.bucket Bucket)
        (.copySource CopySource)
        (.copySourceIfMatch CopySourceIfMatch)
        (.copySourceSSECustomerAlgorithm CopySourceSseCustomerAlgorithm)
        (.copySourceSSECustomerKey CopySourceSseCustomerKey)
        (.copySourceSSECustomerKeyMD5 CopySourceSseCustomerKeyMd5)
        (.copySourceIfModifiedSince CopySourceIfModifiedSince)
        (.copySourceIfNoneMatch CopySourceIfNoneMatch)
        (.copySourceIfUnmodifiedSince CopySourceIfUnmodifiedSince)
        (.copySourceRange CopySourceRange)
        (.sseCustomerKey SseCustomerKey)
        (.uploadId UploadId)
        (.partNumber PartNumber)
        (.sseCustomerAlgorithm SseCustomerAlgorithm)
        (.sseCustomerKeyMD5 SseCustomerKeyMd5)
        (.requestPayer RequestPayer)))))

(defmethod ^CompletableFuture put-bucket
  :AccelerateConfigurationRequest
  [{:keys
    [^String Bucket
     ^AccelerateConfiguration AccelerateConfiguration
     ^String Profile]
    :or
    {^String Profile "default"}}]
  (.putBucketAccelerateConfiguration
    (client Profile)
    ^PutBucketAccelerateConfigurationRequest
    (.build
      (->
        (PutBucketAccelerateConfigurationRequest/builder)
        (.bucket Bucket)
        (.accelerateConfiguration AccelerateConfiguration)))))

(defmethod ^CompletableFuture put-bucket
  :AclRequest
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
     ^String Profile]
    :or
    {^String Profile "default"}}]
  (.putBucketAcl
    (client Profile)
    ^PutBucketAclRequest
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

(defmethod ^CompletableFuture put-bucket
  :AnalyticsConfigurationRequest
  [{:keys
    [^String Bucket
     ^AnalyticsConfiguration AnalyticsConfiguration
     ^String Id
     ^String Profile]
    :or
    {^String Profile "default"}}]
  (.putBucketAnalyticsConfiguration
    (client Profile)
    ^PutBucketAnalyticsConfigurationRequest
    (.build
      (->
        (PutBucketAnalyticsConfigurationRequest/builder)
        (.bucket Bucket)
        (.analyticsConfiguration AnalyticsConfiguration)
        (.id Id)))))

(defmethod ^CompletableFuture put-bucket
  :CorsRequest
  [{:keys
    [^String Bucket
     ^CORSConfiguration CorsConfiguration
     ^String ContentMd5
     ^String Profile]
    :or
    {^String Profile "default"}}]
  (.putBucketCors
    (client Profile)
    ^PutBucketCorsRequest
    (.build
      (->
        (PutBucketCorsRequest/builder)
        (.bucket Bucket)
        (.contentMD5 ContentMd5)
        (.corsConfiguration CorsConfiguration)))))

(defmethod ^CompletableFuture put-bucket
  :InventoryConfigurationRequest
  [{:keys
    [^String Bucket
     ^String Id
     ^InventoryConfiguration InventoryConfiguration
     ^String Profile]
    :or
    {^String Profile "default"}}]
  (.putBucketInventoryConfiguration
    (client Profile)
    ^PutBucketInventoryConfigurationRequest
    (.build
      (->
        (PutBucketInventoryConfigurationRequest/builder)
        (.bucket Bucket)
        (.id Id)
        (.inventoryConfiguration InventoryConfiguration)))))

(defmethod ^CompletableFuture put-bucket
  :LifecycleRequest
  [{:keys
    [^String Bucket
     ^LifecycleConfiguration LifecycleConfiguration
     ^String ContentMd5
     ^String Profile]
    :or
    {^String Profile "default"}}]
  (.putBucketLifecycle
    (client Profile)
    ^PutBucketLifecycleRequest
    (.build
      (->
        (PutBucketLifecycleRequest/builder)
        (.bucket Bucket)
        (.lifecycleConfiguration LifecycleConfiguration)
        (.contentMD5 ContentMd5)))))

(defmethod ^CompletableFuture put-bucket
  :LifecycleConfigurationRequest
  [{:keys
    [^String Bucket
     ^BucketLifecycleConfiguration BucketLifecycleConfiguration
     ^String Profile]
    :or
    {^String Profile "default"}}]
  (.putBucketLifecycleConfiguration
    (client Profile)
    ^PutBucketLifecycleConfigurationRequest
    (.build
      (->
        (PutBucketLifecycleConfigurationRequest/builder)
        (.bucket Bucket)
        (.lifecycleConfiguration BucketLifecycleConfiguration)))))

(defmethod ^CompletableFuture put-bucket
  :LoggingRequest
  [{:keys
    [^String Bucket
     ^BucketLoggingStatus BucketLoggingStatus
     ^String Profile]
    :or
    {^String Profile "default"}}]
  (.putBucketLogging
    (client Profile)
    ^PutBucketLoggingRequest
    (.build
      (->
        (PutBucketLoggingRequest/builder)
        (.bucket Bucket)
        (.bucketLoggingStatus BucketLoggingStatus)))))

(defmethod ^CompletableFuture put-bucket
  :MetricsConfigurationRequest
  [{:keys
    [^String Bucket
     ^MetricsConfiguration MetricsConfiguration
     ^String Profile]
    :or
    {^String Profile "default"}}]
  (.putBucketMetricsConfiguration
    (client Profile)
    ^PutBucketMetricsConfigurationRequest
    (.build
      (->
        (PutBucketMetricsConfigurationRequest/builder)
        (.bucket Bucket)
        (.metricsConfiguration MetricsConfiguration)))))

(defmethod ^CompletableFuture put-bucket
  :NotificationRequest
  [{:keys
    [^String Bucket
     ^NotificationConfigurationDeprecated NotificationConfigurationDeprecated
     ^String ContentMd5
     ^String Profile]
    :or
    {^String Profile "default"}}]
  (.putBucketNotification
    (client Profile)
    ^PutBucketNotificationRequest
    (.build
      (->
        (PutBucketNotificationRequest/builder)
        (.bucket Bucket)
        (.contentMD5 ContentMd5)
        (.notificationConfiguration NotificationConfigurationDeprecated)))))

(defmethod ^CompletableFuture put-bucket
  :NotificationConfigurationRequest
  [{:keys
    [^String Bucket
     ^NotificationConfiguration NotificationConfiguration
     ^String Profile]
    :or
    {^String Profile "default"}}]
  (.putBucketNotificationConfiguration
    (client Profile)
    ^PutBucketNotificationConfigurationRequest
    (.build
      (->
        (PutBucketNotificationConfigurationRequest/builder)
        (.bucket Bucket)
        (.notificationConfiguration NotificationConfiguration)))))

(defmethod ^CompletableFuture put-bucket
  :PolicyRequest
  [{:keys
    [^String Bucket
     ^String Policy
     ^String ContentMd5
     ^String Profile]
    :or
    {^String Profile "default"}}]
  (.putBucketPolicy
    (client Profile)
    ^PutBucketPolicyRequest
    (.build
      (->
        (PutBucketPolicyRequest/builder)
        (.bucket Bucket)
        (.policy Policy)
        (.contentMD5 ContentMd5)))))

(defmethod ^CompletableFuture put-bucket
  :ReplicationRequest
  [{:keys
    [^String Bucket
     ^ReplicationConfiguration ReplicationConfiguration
     ^String ContentMd5
     ^String Profile]
    :or
    {^String Profile "default"}}]
  (.putBucketReplication
    (client Profile)
    ^PutBucketReplicationRequest
    (.build
      (->
        (PutBucketReplicationRequest/builder)
        (.bucket Bucket)
        (.replicationConfiguration ReplicationConfiguration)
        (.contentMD5 ContentMd5)))))

(defmethod ^CompletableFuture put-bucket
  :PaymentRequest
  [{:keys
    [^String Bucket
     ^RequestPaymentConfiguration RequestPaymentConfiguration
     ^String ContentMd5
     ^String Profile]
    :or
    {^String Profile "default"}}]
  (.putBucketRequestPayment
    (client Profile)
    ^PutBucketRequestPaymentRequest
    (.build
      (->
        (PutBucketRequestPaymentRequest/builder)
        (.bucket Bucket)
        (.requestPaymentConfiguration RequestPaymentConfiguration)
        (.contentMD5 ContentMd5)))))

(defmethod ^CompletableFuture put-bucket
  :TaggingRequest
  [{:keys
    [^String Bucket
     ^Tagging Tagging
     ^String ContentMd5
     ^String Profile]
    :or
    {^String Profile "default"}}]
  (.putBucketTagging
    (client Profile)
    ^PutBucketTaggingRequest
    (.build
      (->
        (PutBucketTaggingRequest/builder)
        (.bucket Bucket)
        (.tagging Tagging)
        (.contentMD5 ContentMd5)))))

(defmethod ^CompletableFuture put-bucket
  :VersioningRequest
  [{:keys
    [^String Bucket
     ^VersioningConfiguration VersioningConfiguration
     ^String ContentMd5
     ^String Mfa
     ^String Profile]
    :or
    {^String Profile "default"}}]
  (.putBucketVersioning
    (client Profile)
    ^PutBucketVersioningRequest
    (.build
      (->
        (PutBucketVersioningRequest/builder)
        (.bucket Bucket)
        (.versioningConfiguration VersioningConfiguration)
        (.mfa Mfa)
        (.contentMD5 ContentMd5)))))

(defmethod ^CompletableFuture put-bucket
  :WebsiteRequest
  [{:keys
    [^String Bucket
     ^WebsiteConfiguration WebsiteConfiguration
     ^String ContentMd5
     ^String Profile]
    :or
    {^String Profile "default"}}]
  (.putBucketWebsite
    (client Profile)
    ^PutBucketWebsiteRequest
    (.build
      (->
        (PutBucketWebsiteRequest/builder)
        (.bucket Bucket)
        (.websiteConfiguration WebsiteConfiguration)
        (.contentMD5 ContentMd5)))))


