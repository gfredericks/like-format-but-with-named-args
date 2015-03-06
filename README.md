# like-format-but-with-named-args

A Clojure library with a function that is like `clojure.core/format`
but uses named args instead of positional args.

## Obtention

Leiningen dependency coordinates:

```
[com.gfredericks/like-format-but-with-named-args "0.1.2"]
```

## Usage

``` clojure
(require '[com.gfredericks.like-format-but-with-named-args :refer [named-format]])

(named-format "Hello my name is %name~s and I am %height~.3f feet tall."
              {:name   "Joe Biden"
               :height (* 2 Math/PI)})
;; => "Hello my name is Joe Biden and I am 6.283 feet tall."

```

## License

Copyright © 2015 Gary Fredericks

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
