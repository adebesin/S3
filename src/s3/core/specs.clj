(ns s3.core.specs
  (:require [clojure.spec.alpha :as s]))

(s/def ::Bucket string?)
(s/def ::Key string?)
(s/def ::UploadId string?)
(s/def ::RequestPayer string?)
(s/def ::Profile string?)

(s/def ::name string?)
(s/fdef creds
        :args (s/cat :name ::name))

(s/def ::Bar string?)
(s/def ::Baz string?)
(s/def ::X (3))

(s/valid? (s/keys :req-un [::Bar ::Baz]) {:Bar "" :Baz ""})

(defn foo [{:keys [Bar Baz] :as x}]
  (str Bar Baz))


(s/fdef foo :args (s/cat :x ))

(s/valid? (s/keys :req []))

