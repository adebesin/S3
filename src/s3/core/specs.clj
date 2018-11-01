(ns s3.core.specs
  (:require [clojure.spec.alpha :as s])
  (:import (software.amazon.awssdk.auth.credentials
             ProfileCredentialsProvider)))

(s/def ::Bucket string?)
(s/def ::Key string?)
(s/def ::UploadId string?)
(s/def ::RequestPayer string?)
(s/def ::Profile string?)

(s/fdef s3.core/creds :args (s/cat :name string?))