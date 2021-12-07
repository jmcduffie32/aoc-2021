(ns aoc.day7
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))

(def test-data
  [16 1 2 0 4 2 7 1 2 14])

(def data
  (->
   "day7.txt"
   io/resource
   slurp
   (str/split-lines)
   first
   (str/split #",")
   (->> (map #(Integer/parseInt %)))))

(defn crab-distance [x]
  (apply + (range 1 (inc x))))

(defn distances [data]
  (let [sorted (sort data)]
    (for [i (range (first sorted) (inc (last sorted)))]
      {:position i
       :distance (->> data
                      (map (fn [p] (Math/abs (- p i))))
                      (apply +))})))

(defn crab-distances [data]
  (let [sorted (sort data)]
    (for [i (range (first sorted) (inc (last sorted)))]
      {:position i
       :distance (->> data
                      (map (fn [p] (crab-distance (Math/abs (- p i)))))
                      (apply +))})))

(comment
(apply min-key :distance (distances test-data))
(apply min-key :distance (crab-distances test-data))
data
(apply min-key :distance (distances data))
(crab-distance 4)
(apply min-key :distance (crab-distances data))
  ,)
