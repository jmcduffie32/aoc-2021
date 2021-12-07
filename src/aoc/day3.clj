(ns aoc.day3
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))

(def test-txt
  "00100
11110
10110
10111
10101
01111
00111
11100
10000
11001
00010
01010")

(def codes
  (-> "day3.txt"
      io/resource
      slurp
      str/split-lines))

(defn print-and-pass [x]
  (do (println x)
      x))

(def test-codes
  (-> test-txt
      str/split-lines))

(defn code-frequencies [codes]
  (->> codes
       (map #(str/split % #""))
       (apply (partial map vector))
       (map frequencies)
       (map #(sort-by key %))))

(defn most-common [codes]
  (->> codes
       code-frequencies
       (map (partial apply max-key second))
       (mapcat first)
       str/join))


(defn least-common [codes]
  (->> codes
       code-frequencies
       (map (comp (partial apply min-key second) reverse))
       (mapcat first)
       str/join))

(defn solve []
  (* (Integer/parseInt (most-common codes) 2)
     (Integer/parseInt (least-common codes) 2)))

(comment
  (solve))

(defn find-ogr [i codes]
  (if (= 1 (count codes))
    (first codes)
    (recur (inc i) (filter #(= (nth (most-common codes) i) (nth % i)) codes))))
;; "011111000100"

(defn find-csr [i codes]
  (if (= 1 (count codes))
    (first codes)
    (recur (inc i) (filter #(= (nth (least-common codes) i) (nth % i)) codes))))

(comment
(Integer/parseInt (find-ogr 0 test-codes) 2)
(Integer/parseInt (find-csr 0 test-codes) 2) 
  ,)

;; "111110001110"

(defn solve-2 [codes]
  (* (Integer/parseInt (find-ogr 0 codes) 2)
     (Integer/parseInt (find-csr 0 codes) 2)))

(comment
(def solution (solve-2 codes))
solution
  ,)
