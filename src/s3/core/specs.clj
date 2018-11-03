(ns s3.core.specs
  (:require [clojure.spec.alpha :as spec.alpha])
  (:import (software.amazon.awssdk.auth.credentials
             ProfileCredentialsProvider)
           (software.amazon.awssdk.services.s3.model
             RequestPayer)))

(spec.alpha/def ::Bucket string?)
(spec.alpha/def ::Key string?)
(spec.alpha/def ::UploadId string?)
(spec.alpha/def ::RequestPayer string?)                     ;TODO create RequestPayer instance check
(spec.alpha/def ::Profile string?)
(spec.alpha/def ::type keyword?)

(spec.alpha/def ::MultipartUploadAbortRequest
  (spec.alpha/keys :req
                   [::Bucket
                    ::Key
                    ::UploadId
                    ::RequestPayer
                    ::Profile
                    ::type]))

(spec.alpha/fdef s3.core/creds
                 :args
                 (spec.alpha/cat :name string?))

(spec.alpha/fdef s3.core/multipart-upload
                 :args (spec.alpha/cat :args ::MultipartUploadAbortRequest))


