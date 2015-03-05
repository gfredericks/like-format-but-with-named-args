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
