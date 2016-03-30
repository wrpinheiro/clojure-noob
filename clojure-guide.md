# Clojure Guide

## The tools

### Leiningen: build tool for clojure

  > `lein` is the main command.

  * create a new application  `lein new app <application-name>`
  * run the application `lein run`
  * generate a `JAR` file `lein uberjar`

### REPL is an interactive environment
  > REPL means Read, Evaluate, Print & Loop
  > `lein repl` runs the REPL environment

## The Language

### Control flow

* `(if <boolean form> <then form> <optional else form>)`
* `(when <boolean form> <sentences>)`
* `(do <sentences>)`

### True, false & nil

`nil?` - check if something is `nil`
Both `false` and `nil` represent logic falsiness

   > this sentence prints `this is false`
   > `(if nil "this is true" "this is false")`

Equality operator: `=`
   > `=> (= 1 1)`
   > `false`

`or`: return the first truthy value or the last non truthy value.
   > `(or 42 true)` returns `42`
   > `(or false nil)` returns `nil`
   > `(or nil false)` returns `false`

`and`: return the last truthy value or the first non truthy value.
   > `(and :a :b)` returns `:b`
   > `(and true nil true)` returns `nil`
   > `(and true false true)` returns `false`

### Binding names to values

`def` is used to bind names to values

`(def my_vector [:a :b :c])`

```clojure
(defn error-message
  [severity]
  (str "OH GOD! IT'S A DISASTER! WE'RE "
    (if (= severity :mild)
      "MILDLY INCONVENIENCED!"
      "DOOOOOOOMED!")))
```

### Data Structures

Maps:

* `{}` empty map
* `{:first-name "Charlie" :last-name "Brown"}`
* `{plus-function +}`
* `{:name {:first-name "Charlie" :last-name "Brown"}}`
* `(hash-map :a 1 "b" 2)`: this creates a new map
* `(get {:a 1 :b 2} :b)` returns `2`
* `(get {:a 1 :b {:c 3}} :b)` returns `{:c 3}`
* `(get (get {:a 1 :b {:c 3}} :b) :c)` returns `3`
* `(get-in {:a 1 :b {:c 3}} [:b :c])` also return `3`
* `(get {:a 1 :b 2} :c)` returns `nil`
* `(get {:a 1 :b 2} :c 45) returns `45`
* `({:name "The Human Coffeepot"} :name)` a map can be treated as a function with key as parameter.

Keywords:

* `:a`, `: this-is-a-keyword`, `:_?`
* `(:a {:a 1 :b 2 :c 3})` this is equivalent of `(get {:a 1 :b 2 :c 3} : a)`
* can have a default value `(:d {:a 1 :b 2 :c 3} "No gnome knows homes like Noah knows")`

Vectors:

* `[3 2 1]`
* `(get [3 2 1] 0)`
* `(get [{:a 1} 2 1] 0)` returns `{:a 1}`
* `(get-in [{:a 1} 2 1] [0 :a]` returns `1`
* `(vector "creepy" "full" "moon")` create a vector with `vector`
* `(conj [1 2 3] 4)` add a new element at the end of the vector

Lists

* `'(1 2 3 4)`
* `(nth '(:a :b :c) 0)`
* `(list 1 "two" {3 4})`
* `(conj '(1 2 3) 4)`
* `(get (nth (list 1 "two" {3 4}) 2) 3)`
* `get` can't be used with Lists!! **The access to an element in a List is slower than in a Vector**

Sets

* `#{"kurt vonnegut" 20 :icicle}`
* `(hash-set 1 1 2 2)`
* `(conj #{:a :b} :b)`
* `(set [3 3 3 4 4])` create a set from a List or Vector. Note that duplicates are removed.
* `(contains? #{:a :b} :a)`
* `(contains? #{nil} nil)`
* Set as keyword: `(:a #{:a :b})`
* `(get #{:a :b} :a)`
* `(get #{:a :b} "kurt vonnegut")`

### Functions

Defining functions: `defn`

```
(defn function-name
  docstring
  [args]
  body
  )
  ```

Multi-arity-function

```
(defn multi-arity-function
  ([x]
  (println (str "x value is " x)))
  ([x y]
  (do
    (multi-arity-function x)
    (println (str "y value is " y))))
  ([x y z]
  (do
    (multi-arity-function x y)
    (println (str "z value is " z)))))
```

Variable arity

```
(defn variable-arity
  [& arguments]
  (clojure.string/join "," arguments))

(variable-arity "first" "second" "third")
```

Mixed fixed args with rest

```
(defn mixed-args-with-rest
  [first second & rest]
  (str "firsrt is " first ". Second is " second ". And rest is" (clojure.string/join "," rest)))

(mixed-args-with-rest "first" "second" "third" "four")
```

Destructuring

```
(defn sum-terms
  [[first-term & rest]]
    (if (empty? rest) first-term (+ first-term (sum-terms rest))))


(sum-terms [1 2 3])
```

Map destructuring

```
(defn print-dimensions
  [{width :x height :y}]
  (println (str "width " width))
  (println (str "height " height)))

(defn print-original-dimensions
  [{:keys [x y]}]
  (println (str "width " x))
  (println (str "height " y)))

(print-dimensions {:x 10 :y 5 :color :blue})
(print-original-dimensions {:x 10 :y 5 :color :blue})
```

Anonymous function

```clojure
((fn [x] (* x 3)) 8)
(#(* %1 3) 8)
(#(* % 3) 8)
(#(* %1 %2) 5 4)
(map #(* % 2) [2 4 8])

(def ex-anonymous-function #(* % 3))
(ex-anonymous-function 5)

;; %& is the rest
(#(clojure.string/join ", " %&) ["a" "b" "c"])
```

Returning Functions

```
(defn divider
  [divisor]
  #(/ % divisor))

(def divisor_by_8 (divider 8))

(divisor_by_8 24)
```

Let

```
(def x 2)
(let [x 3] (println x))
(println x)

(def fruits ["apple" "pineapple" "mango" "blueberry"])
(let [some-fruits (take 2 fruits)] some-fruits)

(let [[first-fruit second-fruit & other-fruits] fruits]
  (str "first fruit: " first-fruit " sec fruit: " second-fruit
    " other fruits: " (clojure.string/join ", " other-fruits)))
```

into

Add elements to a collection.

```clojure
(into [] #{:a :b})
(into (sorted-map) [ [:a 1] [:c 3] [:b 2] ] )
(into (sorted-map) [ {:a 1} {:c 3} {:b 2} ] )
(into () '(1 2 3))
```

Loop

```
(loop [iteration 0]
  (println (str "It: " iteration))
  (if (>= iteration 3)
    (println "Goodbye")
    (recur (inc iteration))))
```
```
(defn recursive-printer
  ([]
    (recursive-printer 0))
  ([iteration]
    (println iteration)
    (if (>= iteration 3)
      (println "Goodbye")
      (recursive-printer (inc iteration)))))

(recursive-printer)
```

Regular Expression

```
(class #"regular-expression")
(re-find #"^left-" "left-eye")
(re-find #"^left-" "cleft-chin")
(re-find #"^left-" "wongleblart")
```

### The Shire’s Next Top Model

```
(def asym-hobbit-body-parts [{:name "head" :size 3}
                             {:name "left-eye" :size 1}
                             {:name "left-ear" :size 1}
                             {:name "mouth" :size 1}
                             {:name "nose" :size 1}
                             {:name "neck" :size 2}
                             {:name "left-shoulder" :size 3}
                             {:name "left-upper-arm" :size 3}
                             {:name "chest" :size 10}
                             {:name "back" :size 10}
                             {:name "left-forearm" :size 3}
                             {:name "abdomen" :size 6}
                             {:name "left-kidney" :size 1}
                             {:name "left-hand" :size 2}
                             {:name "left-knee" :size 2}
                             {:name "left-thigh" :size 4}
                             {:name "left-lower-leg" :size 3}
                             {:name "left-achilles" :size 1}
                             {:name "left-foot" :size 2}])

(defn matching-part
  [part]
  {:name (clojure.string/replace (:name part) #"^left-" "right-")
   :size (:size part)})

(defn symmetrize-body-parts
  [asym-body-parts]
  (loop [remaining-asym-parts asym-body-parts
    final-body-parts []]
    (if (empty? remaining-asym-parts)
      final-body-parts
      (let [[part & remaining] remaining-asym-parts]
        (recur remaining
          (into final-body-parts (set [part (matching-part part)])))))))

(symmetrize-body-parts asym-hobbit-body-parts)
```

Reduce

```clojure
(reduce + [1 2 3 4])
(reduce + 10 [1 2 3 4])
(reduce (fn [rest first] (into rest [(* first 2)])) [] [1 2 3 4])
```

Simpler version of `symmetrize-body-parts`
```
(defn better-symmetrize-body-parts
  [asym-body-parts]
  (reduce (fn [final-body-parts part]
    (into final-body-parts (set [part (matching-part part)]))) [] asym-body-parts))

(better-symmetrize-body-parts asym-hobbit-body-parts)

```

```
(defn hit
  [asym-body-parts]
  (let [sym-parts (better-symmetrize-body-parts asym-body-parts)
        body-part-size-sum (reduce + (map :size sym-parts))
        target (rand body-part-size-sum)]
    (loop [[part & remaining] sym-parts
           accumulated-size (:size part)]
      (if (> accumulated-size target)
        part
        (recur remaining (+ accumulated-size (:size (first remaining))))))))

(hit asym-hobbit-body-parts)
```

### Functions in Depth

#### Map

```clojure
(map inc [1 2 3])

(map str ["a" "b" "c"] ["A" "B" "C"])

(def human-consumption   [8.1 7.3 6.6 5.0])
(def critter-consumption [0.0 0.2 0.3 1.1])

(defn unify-data-diet
  [human critter]
  {:humar human :critter critter})

(map unify-data-diet human-consumption critter-consumption)

(def sum #(reduce + %))
(def avg #(/ (sum %) (count %)))
(defn stats
  [numbers]
  (map #(% numbers) [sum count avg]))

(stats [1 2 3 5])
(stats [3 4 10])

(def identities
  [{:alias "Batman" :real "Bruce Wayne"}
   {:alias "Spider-Man" :real "Peter Parker"}
   {:alias "Santa" :real "Your mom"}
   {:alias "Easter Bunny" :real "Your dad"}])

(map :real identities)
```

#### Reduce

Increments each value in a `map`.

```clojure
(defn map-transform
  [new-map [key value]]
  (assoc new-map key (inc value)))

(reduce map-transform {} {:min 20 :max 40})
```

#### take, drop, take-while, drop-while

```clojure
(take 3 [1 2 3 4 5])
=> (1 2 3)

(drop 3 [1 2 3 4 5])
=> (4 5)

(take-while #(< % 3) [1 2 3 4 5])
=> (1 2)

(take-while #(> % 3) [1 2 3 4 5])
=> ()

(drop-while #(< % 3) [1 2 3 4 5])
=> (3 4 5)
```

```clojure
(def food-journal
  [{:month 1 :day 1 :human 5.3 :critter 2.3}
   {:month 1 :day 2 :human 5.1 :critter 2.0}
   {:month 2 :day 1 :human 4.9 :critter 2.1}
   {:month 2 :day 2 :human 5.0 :critter 2.5}
   {:month 3 :day 1 :human 4.2 :critter 3.3}
   {:month 3 :day 2 :human 4.0 :critter 3.8}
   {:month 4 :day 1 :human 3.7 :critter 3.9}
   {:month 4 :day 2 :human 3.7 :critter 3.6}])

(drop-while #(< (:month %) 2)
  (take-while #(< (:month %) 3) food-journal))
```

#### filter, some

```clojure
(filter #(> (:human %) 5) food-journal)
=> ({:month 1, :day 1, :human 5.3, :critter 2.3} {:month 1, :day 2, :human 5.1, :critter 2.0})

(some #(> (:human %) 5.5) food-journal)
=> nil

(some #(and (> (:human %) 5) %) food-journal)
=> {:month 1, :day 1, :human 5.3, :critter 2.3}
```

#### sort, sort-by

```clojure
(sort [3 1 2])
=> (1 2 3)

(sort '(3 1 2))
=> (1 2 3)

(sort-by count ["aaa" "c" "bb"])
=> ("c" "bb" "aaa")
```

#### concat

```clojure
(concat [1 2] [3 4])
=> (1 2 3 4)

(concat {:a 1} {:b 2})
=> ([:a 1] [:b 2])
```

### Lazy Seqs
map first calls seq on the collection you pass to it

```clojure
(def vampire-database
  {0 {:makes-blood-puns? false, :has-pulse? true  :name "McFishwich"}
   1 {:makes-blood-puns? false, :has-pulse? true  :name "McMackson"}
   2 {:makes-blood-puns? true,  :has-pulse? false :name "Damon Salvatore"}
   3 {:makes-blood-puns? true,  :has-pulse? true  :name "Mickey Mouse"}})

(defn vampire-related-details
  [social-security-number]
  (Thread/sleep 1000)
  (get vampire-database social-security-number))

(defn vampire?
  [record]
  (and (:makes-blood-puns? record)
    (not (:has-pulse? record))
    record))

(defn identify-vampire
  [social-security-numbers]
  (first (filter vampire?
                 (map vampire-related-details social-security-numbers))))

(identify-vampire [0 1 2 3])

(time (def mapped-details (map vampire-related-details (range 0 1000000))))
=> "Elapsed time: 32087.814263 msecs"
=> {:makes-blood-puns? false, :has-pulse? true, :name "McFishwich"}

;; the collection returned by `mapped-details` is already4 realized, so another call to `mapped-details` will return very fast
(time (first mapped-details))
=> "Elapsed time: 0.22914 msecs"
=> {:makes-blood-puns? false, :has-pulse? true, :name "McFishwich"}


## Infinite Sequences


```clojure
(take 8
  (repeat "na"))

(concat
  (take 8
    (repeat "na"))["Batman"])
```

```clojure
(take 3 (repeatedly (fn [] (rand-int 10))))
```

(defn even-numbers
  ([] (even-numbers 0))
  ([n] (cons n (lazy-seq (even-numbers (+ n 2))))))

(take 10 (even-numbers))
(take 10 (even-numbers 2))

(def fib-seq
  ((fn fseq [a b] (lazy-seq (cons a (fseq b (+ a b))))) 1 1))

(take 2 fib-seq)

## The abstract Collection

_The sequence abstraction is about operating on members individually, whereas the collection abstraction is about the data structure as a whole._


(empty? [])
(empty? ["no"])

### `into` and `cons`

(def ls (map identity {:sunlight-reaction "Glitter!"})) ;; map returns a seq data structure
(def m (into {} (map identity {:sunlight-reaction "Glitter"}))) ;; into converts the seq back into a map

(class ls) ;; ls is a lazy seq
(class m) ;; m is a map

(map identity [:garlic :sesame-oil :fried-eggs])
(into [] (map identity [:garlic :sesame-oil :fried-eggs]))

(map identity [:garlic-clove :garlic-clove])
(into #{} (map identity [:garlic-clove :garlic-clove]))

(into {:favorite-emotion "gloomy"} [[:sunlight-reaction "Glitter"]])
(into ["cherry"] '("pine" "spruce"))

(into {:favorite-animal "kitty"} {:least-favorite-smell "dog"
  :relationship-with-teenager "creepy"})

(conj [0] [1]) ;; => [0 [1]]
(conj [0] 1) ;; => [0 1]

(conj [0] 1 2 3 4)
(conj {:time "midnight"} [:place "cemetarium"])


(defn my-conj
  [target & additions]
  (into target additions))

(my-conj [0] 1 2 3)

## `apply` and `partial`

Apply explodes a seq in rest params

(max 1 2 3) ;; => 3
(max [1 2 3]) ;; => [1 2 3]
(apply max [1 2 3]) ;; => 3. This is equivalent of (max 1 2 3)


(defn my-into
  [target additions]
  (apply conj target additions))

(my-into {:c 3} {:a 1 :b 2})

(def add10 (partial + 10))
(add10 5)

(def add-missing-elements
  (partial conj ["water" "earth" "air"]))

(add-missing-elements "unobtainium" "adamantium")

(defn my-partial
  [partialized-fn & args]
  (fn [& more-args]
    (apply partialized-fn (into args more-args))))

(def add20 (my-partial + 20))

(add20 30)

## `complement`

(defn my-complement [func]
  (fn [& args]
    (not (apply func args))))

(def my-pos? (my-complement neg?))

(my-pos? 1)
(my-pos? -1)
