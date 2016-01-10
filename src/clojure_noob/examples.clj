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
