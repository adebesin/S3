(ns s3.core.specs
  (:require [clojure.spec.alpha :as s])
  (:import (software.amazon.awssdk.auth.credentials
             ProfileCredentialsProvider)
           (software.amazon.awssdk.services.s3.model
             RequestPayer)))

(s/def ::Bucket string?)
(s/def ::Key string?)
(s/def ::UploadId string?)
(s/def ::RequestPayer string?)                              ;TODO create RequestPayer instance check
(s/def ::Profile string?)
(s/def ::type keyword?)

(s/def ::MultipartUploadAbortRequest
  (s/keys :req
          [::Bucket
           ::Key
           ::UploadId
           ::RequestPayer
           ::Profile
           ::type]))

(s/fdef s3.core/creds
        :args
        (s/cat :name string?))

(s/fdef s3.core/multipart-upload
        :args (s/cat :args ::MultipartUploadAbortRequest))


