# like-format-but-with-named-args

A Clojure library with a function that is like `clojure.core/format`
but uses named args instead of positional args.

## Obtention

Leiningen dependency coordinates:

```
[com.gfredericks/like-format-but-with-named-args "0.1.0"]
```

## Usage

``` clojure
(require '[com.gfredericks.like-format-but-with-named-args :refer [named-format]])

(named-format "Hello my name is %name~s." {:name "Joe Biden"})
;; => "Hello my name is Joe Biden."
```

## License

Copyright © 2015 Gary Fredericks

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
