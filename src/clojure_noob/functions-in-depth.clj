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
(keys my-map)
(conj [] 2)
(seq {:max 30 :min 10})
(assoc {} :max (inc 30) :min 20)
(assoc {} (min my-map))


(defn reduce-function
  [new-vector value]
  (conj new-vector (* value 2)))

(reduce reduce-function [] [1 2 3 4])

(def my-map {:min 20 :max 40})

(defn map-transform
  [new-map [key value]]
  (assoc new-map key (inc value)))

(reduce map-transform {} my-map)

(defn another-reduction-function
  [new-map [key val]]
  (if (> val 4)
    (assoc new-map key val)
    new-map))

(def human-critter-map {:human 4.1 :critter 3.9})

(reduce another-reduction-function {} human-critter-map)

(take 3 [1 2 3 4 5])
(drop 3 [1 2 3 4 5])

(def food-journal
  [{:month 1 :day 1 :human 5.3 :critter 2.3}
   {:month 1 :day 2 :human 5.1 :critter 2.0}
   {:month 2 :day 1 :human 4.9 :critter 2.1}
   {:month 2 :day 2 :human 5.0 :critter 2.5}
   {:month 3 :day 1 :human 4.2 :critter 3.3}
   {:month 3 :day 2 :human 4.0 :critter 3.8}
   {:month 4 :day 1 :human 3.7 :critter 3.9}
   {:month 4 :day 2 :human 3.7 :critter 3.6}])

(println food-journal)

(take-while #(< (:month %) 3) food-journal)
(drop-while #(< (:month %) 3) food-journal)

(drop-while #(< (:month %) 2)
  (take-while #(< (:month %) 3) food-journal))

(filter #(> (:human %) 5) food-journal)

(some #(> (:human %) 5.5) food-journal)

(some #(and (> (:human %) 5) %) food-journal)

(sort [3 1 2])
(sort '(3 1 2))

(sort-by count ["aaa" "c" "bb"])

(concat [1 2] [3 4])

Lazy Seqs

(def vampire-database
  {0 {:makes-blood-puns? false, :has-pulse? true  :name "McFishwich"}
   1 {:makes-blood-puns? false, :has-pulse? true  :name "McMackson"}
   2 {:makes-blood-puns? true,  :has-pulse? false :name "Damon Salvatore"}
   3 {:makes-blood-puns? true,  :has-pulse? true  :name "Mickey Mouse"}})

(println vampire-database)

(defn vampire-related-details
  [social-security-number]
  (Thread/sleep 1000)
  (get vampire-database social-security-number))

(vampire-related-details 0)

(defn vampire?
  [record]
  (and (:makes-blood-puns? record)
       (not (:has-pulse? record))
       record))

(vampire? (vampire-related-details 2))

(defn identify-vampire
  [social-security-numbers]
  (first (filter vampire?
                 (map vampire-related-details social-security-numbers))))

(identify-vampire [0 1 2 3])

(time (vampire-related-details 0))

(time (def mapped-details (map vampire-related-details (range 0 1000000))))

(time (first mapped-details))

(time (identify-vampire (range 0 1000000)))

(concat (take 8 (repeat "na")) ["Batman!"])

Infinite Seqs

(concat (take 2 (repeat "na")) ["Batman!"])

(take 10 (repeatedly (fn [] (rand-int 10))))

(cons 0 '(2 4 6))

(defn positive-numbers
  ([] (positive-numbers 1))
  ([n] (lazy-seq (cons n (positive-numbers (inc n))))))

(defn even-numbers
  ([] (even-numbers 0))
  ([n] (lazy-seq (cons n (even-numbers (+ n 2))))))

(take 10 (positive-numbers))
(take 10 (even-numbers))

;; Collection Abstractions
(empty? [])
(empty? #{:a :b})

(map identity {:sunlight-reaction "Glitter!"})
(into {} (map identity {:sunlight-reaction "Glitter!"}))
(:sunlight-reaction (into {} (map identity {:sunlight-reaction "Glitter!"})))

(into [] (map identity [:garlic :sesame-oil :fried-eggs]))

(identity [:garlic :sesame-oil :fried-eggs])

(map identity [:garlic-clove :garlic-clove])

(into #{} (map identity [:garlic-clove :garlic-clove]))

(into {:favorite-emotion "gloomy"} [[:sunlight-reaction "Glitter!"]])

(class [[:sunlight-reaction "Glitter!"]])

(into ["cherry"] '("pine" "spruce"))

(into {:favorite-animal "kitty"} {:least-favorite-smell "dog"
                                  :relationship-with-teenager "creepy"})

(conj [0] [1])
(into [0] [1])
(conj [0] 1)
(conj [0] 1 2 3 4)

(conj {:time "midnight"} [:place "ye olde cemetarium"])

(defn my-conj
  [target & additions]
  (into target additions))

(my-conj [] 1 2 3)

Function Functions

(max 0 1 2)
(max [0 1 2])

(apply max [0 1 2])

(defn my-into
  [target additions]
  (apply conj target additions))

(my-into [0] [1 2 3])

(def add10 (partial + 10))
(add10 3)

(def add-missing-elements
  (partial conj ["water" "earth" "air"]))

(add-missing-elements "unobtainium" "adamantium")

(defn my-partial
  [partialized-fn & args]
  (fn [& more-args]
    (apply partialized-fn (into args more-args))))

(def add20 (my-partial + 20))

(defn lousy-logger
  [log-level message]
  (condp = log-level
    :warn (clojure.string/lower-case message)
    :emergency (clojure.string/upper-case message)))

(def warn (partial lousy-logger :warn))

(warn "Red light ahead")

(def not-vampire? (complement vampire?))
(defn identify-humans
  [social-security-numbers]
  (filter not-vampire?
          (map vampire-related-details social-security-numbers)))

(defn my-complement [func]
  (fn [& args]
    (not (apply func args))))

(def my-pos? (my-complement neg?))

(my-pos? 1)
(my-pos? -1)

