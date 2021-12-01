(ns aoc.day1
  (:require [aoc.util :as util]))

(def measurements (util/parse-number-list-file "day1.txt"))

(defn count-increases [a]
  (->> a
       (partition 2 1)
       (filter #(< (first %) (second %)))
       count))

(defn count-window-increases [a]
  (->> a
       (partition 3 1)
       (filter #(= (count %) 3))
       (map #(apply + %))
       count-increases))
