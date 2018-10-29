(ns s3.core.specs
  (:require [clojure.spec.alpha :as s]))

(s/def ::Bucket string?)
(s/def ::Key string?)
(s/def ::UploadId string?)
(s/def ::RequestPayer string?)
(s/def ::Profile string?)

