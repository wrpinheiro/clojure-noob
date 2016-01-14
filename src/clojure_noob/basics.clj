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

