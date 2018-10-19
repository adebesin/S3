(ns s3.core
  (:import [software.amazon.awssdk.services.s3 S3AsyncClient]))

(def async-client (.build (S3AsyncClient/builder)))

(defn abort-multipart-upload
  [bucket key upload-id request-payer]
  (.abortMultipartUpload async-client))


