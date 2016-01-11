(ns clojure-noob.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "I'm a little teapot!"))

(defn train
  []
  (println "cool cool"))

(train)

(str "this " "is " "a " "text")

(if true "is true" "is false")
(if false "is true" "is false")
(if false "is false")

(if false
  (do (println "Success")
    "By Zeus's hammer")
  (do (println "Failure!")
    "By Aquaman's Trident"))

(when true
  (println "Success")
  "Bu Zeus's hammer")

(if nil "this is true" "this is false")

(= 1 1)
(= 1 false)

(or false nil :large_I_mean_venti :why_cant_I_just_say_large)
(or nil false)

(or :a)

(and :a :b)
(and true nil true)
(and true false true)

(def my_vector [:a :b :c])

my_vector



severity = :mild
error_message = "OH GOD! IT'S A DISASTER! WE'RE "
if severity == :mild
  error_message = error_message + "MILDLY INCONVENIENCED!"
else
  error_message = error_message + "DOOOOOOOMED!"
end


(defn error-message
  [severity]
  (str "OH GOD! IT'S A DISASTER! WE'RE "
    (if (= severity :mild) "MILDLY INCONVENIENCED!" "DOOOOOOOMED!")))


(def my-map {:first-name "Charlie" :last-name "Brown"})

{:name {:first-name "Charlie" :last-name "Brown"}}

(println {:name {:first-name "Charlie" :last-name "Brown"}})


(hash-map :a 1 "b" 2)

(get {:a 1 :b 2} :b)
(get {:a 1 :b {:c 3}} :b)
(get (get {:a 1 :b {:c 3}} :b) :c)


(get {:a 1 :b 2} :c)
(get {:a 1 :b 2} :c 45)


(get-in {:a 1 :b {:c 3}} [:b :c])

({:name "The Human Coffeepot", :other-thing "Whaaat?"} :name)
({:name "The Human Coffeepot"}, "Whaaat?", :other-thing)

(class [3 2 1])

(get-in [{:a 1} 2 1] [0 :a])

'(1 2 3 4)
(nth '(:a :b :c) 0)

(get (nth (list 1 "two" {3 4}) 2) 3)

(set [3 3 3 4 4])

(+ 1 2 3)

(first [1 2 3])

(or + -)
(and + -)

((and (= 1 1) +) 1 2 3)

("test" 1 2 3)

(inc 1.1)
(inc -2)

(defn too-enthusiastic
  "Return a cheer that might be a bit too enthusiastic"
  [name]
  (str "Oh. My God! " name " yeah!"))

(too-enthusiastic "joseph")

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

(multi-arity-function 1 2 3)


(defn variable-arity
  [& arguments]
  (clojure.string/join "," arguments))

(variable-arity "first" "second" "third")

(defn mixed-args-with-rest
  [first second & rest]
  (str "firsrt is " first ". Second is " second ". And rest is" (clojure.string/join "," rest)))

(mixed-args-with-rest "first" "second" "third" "four")


(defn sum-terms
  [[first-term & rest]]
    (if (empty? rest) first-term (+ first-term (sum-terms rest))))


(sum-terms [1 2 3])


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


((fn [x] (* x 3)) 8)
(#(* %1 3) 8)
(#(* % 3) 8)
(#(* %1 %2) 5 4)
(map #(* % 2) [2 4 8])

(def ex-anonymous-function #(* % 3))
(ex-anonymous-function 5)

(#(clojure.string/join ", " %&) ["a" "b" "c"]) ;; %& is the rest

(defn divider
  [divisor]
  #(/ % divisor))

(def divisor_by_8 (divider 8))

(divisor_by_8 24)


(def x 2)
(let [x 3] (println x))
(println x)

(def fruits ["apple" "pineapple" "mango" "blueberry"])
(let [some-fruits (take 2 fruits)] some-fruits)

(let [[first-fruit second-fruit & other-fruits] fruits]
  (str "first fruit: " first-fruit " sec fruit: " second-fruit
    " other fruits: " (clojure.string/join ", " other-fruits)))

(into [] #{:a :b})
(into (sorted-map) [ [:a 1] [:c 3] [:b 2] ] )
(into (sorted-map) [ {:a 1} {:c 3} {:b 2} ] )
(into () '(1 2 3))

(loop [iteration 0]
  (println (str "It: " iteration))
  (if (>= iteration 3)
    (println "Goodbye")
    (recur (inc iteration))))

(defn recursive-printer
  ([]
    (recursive-printer 0))
  ([iteration]
    (println iteration)
    (if (>= iteration 3)
      (println "Goodbye")
      (recursive-printer (inc iteration)))))

(recursive-printer)


(class #"regular-expression")
(re-find #"^left-" "left-eye")
(re-find #"^left-" "cleft-chin")
(re-find #"^left-" "wongleblart")

(def part {:name "head" :size 3})
(println part)

(:name part)


### The Shire’s Next Top Model

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

(reduce + [1 2 3 4])
(reduce + 10 [1 2 3 4])
(reduce (fn [rest first] (into rest [(* first 2)])) [] [1 2 3 4])



(defn better-symmetrize-body-parts
  [asym-body-parts]
  (reduce (fn [final-body-parts part]
    (into final-body-parts (set [part (matching-part part)]))) [] asym-body-parts))

(better-symmetrize-body-parts asym-hobbit-body-parts)


  (loop [remaining-asym-parts asym-body-parts
    final-body-parts []]
    (if (empty? remaining-asym-parts)
      final-body-parts
      (let [[part & remaining] remaining-asym-parts]
        (recur remaining
          (into final-body-parts (set [part (matching-part part)])))))))

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


(map :size [{:size 1} {:size 2}])

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




(= '(1 2 3 4) (conj '(2 3 4) 1))

(= (#(println (str "Hello, " % "!")) "Dave") "Hello, Dave!")

(first (reverse [1 2 3]))

(get '(4 5 6 7) 2)

(defn power
  ([x y] (power x y (bigint 1))
  ([x y current]
  (if (= y 0)
    current
    (if (> y 0)
      (recur x (- y 1) (* x current))
      (recur x (+ y 1) (/ current x))))))

(power 2 60)

