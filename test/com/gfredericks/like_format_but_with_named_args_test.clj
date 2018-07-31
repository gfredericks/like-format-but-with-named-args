(ns com.gfredericks.like-format-but-with-named-args-test
  (:require [clojure.test :refer :all]
            [com.gfredericks.like-format-but-with-named-args :refer [named-format]]))


(deftest named-format-test
  (are [s m out] (= out (named-format s m))
       "No formats here." {:twelve 12} "No formats here."
       "This string has a %% in it." {} "This string has a % in it."

       "I use %name~s two times, see also %name~s."
       {:name "Joe"}
       "I use Joe two times, see also Joe."

       "I can also put a number in like %num~.3f."
       {:num 3.14159}
       "I can also put a number in like 3.142."))

(deftest bad-call-test
  (are [s m error-str] (= {:format-string error-str :arg-map m}
                          (try (named-format s m)
                               (catch Exception e (ex-data e))))
    "...Toss %me~d."
    {:me "not a number"}
    "...Toss %d."

    "%what~o?"
    {:what "not an octal"}
    "%o?"

    "I cannot format the %string~s. You'll have to toss %me~d."
    {:string "an actual string" :me :still-not-a-number}
    "I cannot format the %s. You'll have to toss %d."

    "...Don't tell the %runtime~f."
    {:runtime 1} ;; Not a float
    "...Don't tell the %f."))
