(defn year-end-evaluation
  []
  (if (> (rand) 0.5)
    "You get a raise!"
    "Better luck next year"))
(year-end-evaluation)

(def great-baby-name "Rosanthony")

(let [great-baby-name "Bloodthunder"]
  great-baby-name)

(println great-baby-name)

;; ## Living with Immutable Data Structures
;; ### Recursion instead of for/while

(defn sum
  ([vals] (sum vals 0))
  ([vals accumulating-total]
    (if (empty? vals)
      accumulating-total
      (sum (rest vals) (+ (first vals) accumulating-total)))))

(defn sum
  ([vals] (sum vals 0))
  ([vals acummulating-total]
    (if (empty? vals)
      acummulating-total
      (recur (rest vals) (+ (first vals) acummulating-total)))))

(sum [0 1 2 3 ])

;; ### Function Composition Instead of Attribute Mutation

(require '[clojure.string :as s])
(defn clean
  [text]
  (s/replace (s/trim text) #"lol" "LOL"))

(clean "My boa constrictor is so sassy lol!  ")

;; ## Cool Things to Do with Pure Functions

;; f1(f2(fn(x1, x2, ... xn)))
((comp inc *) 2 3)


(def character
  {:name "Smooches McCutes"
   :attributes {:intelligence 10
                :strength 4
                :dexterity 5}})
(def intelligence (comp :intelligence :attributes))
(def c-int (comp :intelligence :attributes))
(def c-str (comp :strength :attributes))
(def c-dex (comp :dexterity :attributes))

(c-int character)

(defn spell-slots
  [char]
  (int (inc (/ (c-int char) 2))))

(spell-slots character)

(def spell-slots-comp (comp int inc #(/ % 2) c-int))

(defn two-comp
  [f g]
  (fn [& args]
    (f (apply g args))))

;; ### memoize

(defn sleepy-identity
  "Returns the given value after 1 second"
  [x]
  (Thread/sleep 1000)
  x)
(sleepy-identity "Mr. Fantastico")

(def memo-sleepy-identity (memoize sleepy-identity))

(memo-sleepy-identity "Mr. Fantastico")



(defn tri*
  ([] (tri* 0 1))
  ([sum n]
    (let [new-sum (+ sum n)]
      (cons new-sum (lazy-seq (tri* new-sum (inc n)))))))

(def tri (tri*))

(take 5 tri)

(defn triangular?
  [n]
  (= n (last (take-while #(>= n %) tri))))

(triangular? 6)

(defn row-tri
  [n]
  (last (take n tri)))

(row-tri 1000)

(defn row-num
  [pos]
  (inc (count (take-while #(>= pos %) tri))))

(row-num 0)

(assoc-in {} [:cookie :monster :vocals] "Finntroll")
(get-in {:cookie {:monster {:vocals "Finntroll"}}} [:cookie :monster])
(assoc-in {} [1 :connections 4] 2)

(defn connect
  "Form a mutual connection between two positions"
  [board max-pos pos neighbor destination]
  (if (<= destination max-pos)
    (reduce (fn [new-board [p1 p2]]
              (assoc-in new-board [p1 :connections p2] neighbor))
            board
            [[pos destination] [destination pos]])
    board))

(connect {} 15 1 2 4)

((fn [[first & rest]]
  (println first)
  (println rest)) [1 2 3])

(defn equilibrium-index
  ([numbers]
    (let [first-number (first numbers) last-numbers (rest numbers)]
      (equilibrium-index 0 first-number last-numbers 0 (reduce + last-numbers))))
  ([index first-number last-numbers left-sum right-sum]
    (println (str "index= " index " fn= " first-number " ln=" last-numbers " ls= " left-sum " rs= " right-sum))
    (if (= left-sum right-sum)
      index
      (if (not-empty last-numbers)
        (let [
          new-first (first last-numbers)
          new-last-numbers (rest last-numbers)]
          (recur (inc index) new-first new-last-numbers
            (+ left-sum first-number) (- right-sum new-first)))
        -1))))

(equilibrium-index [-1 3 -4 5 1 -6 2 1 1])

(reduce + [])

