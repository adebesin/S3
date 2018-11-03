# s3

A Clojure library for Amazon S3

```clojure
;;  Abort multi part upload
(multipart-upload
  {:Bucket    "images"
   :Key       "cats.png"
   :UploadId  "qwerty"
   :Profile   "dev"
   :type      :AbortRequest})

;;  Complete multi part upload
(multipart-upload
  {:Bucket    "images"
   :Key       "cats.png"
   :UploadId  "qwerty"
   :Profile   "dev"
   :type      :CompleteRequest})
 
 