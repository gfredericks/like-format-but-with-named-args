(ns com.gfredericks.like-format-but-with-named-args)

(defn parse-named-format-string
  "Input is a format string similar to that accepted by clojure.core/format, but
  with names on each formatting expression. Returns a function that accepts a map
  and returns a formatted string. E.g.,:

  ((parse-named-format-string \"Hello my name is %name~s.\")
   {:name \"Joe Biden\"})
  => \"Hello my name is Joe Biden.\"

  Names are terminated with a \\~ character, and immediately following the
  \\~ should be a format specifier as per clojure.core/format.

  Names are always interpreted as keywords."
  [format-str]
  (loop [out-str ""
         keys []
         s format-str]
    (if (empty? s)
      (if (empty? keys)
        (constantly (format out-str))
        (let [f (apply juxt keys)]
          (fn [m]
            (apply format out-str (f m)))))
      (let [i (.indexOf s "%")]
        (if (neg? i)
          (recur (str out-str s) keys nil)
          (let [s-before (subs s 0 i)
                s-after (subs s i)]
            (if (= [\% \%] (take 2 s-after))
              (recur (str out-str s-before "%%") keys (subs s-after 2))
              (if-let [[_ keyname remaining] (re-matches #"(?s)%([-_a-zA-Z]+)~(.*)" s-after)]
                (recur (str out-str s-before "%")
                       (conj keys (keyword keyname))
                       remaining)
                (throw (ex-info "Bad format string!" {:arg format-str}))))))))))

(defn named-format
  "Like clojure.core/format but uses named args. See the docstring on
  parse-named-format-string for details."
  [s m]
  ((parse-named-format-string s) m))
