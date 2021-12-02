(ns aoc.day2
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))

(def test-directions
  "forward 5
down 5
forward 8
up 3
down 8
forward 2")

(def directions
  (-> "day2.txt"
      io/resource
      slurp))

(defn parse-directions-str [s]
  (->> s
       str/split-lines
       (map #(str/split % #" "))
       (map (fn [[dir dist]] {:dir dir :dist (bigint (Integer/parseInt dist))}))
       ))

(defn move [dir-arr]
  (reduce (fn [acc {:keys [dir dist]}]
            (cond-> acc
              (= dir "forward") (update :x #(+ % dist))
              (= dir "up")      (update :y #(- % dist))
              (= dir "down")    (update :y #(+ % dist))))
          {:x 0 :y 0}
          dir-arr))

(defn move-with-aim [dir-arr]
  (reduce (fn [acc {:keys [dir dist]}]
            (cond-> acc
              (= dir "forward") (->
                                 (update :x #(+ % dist))
                                 (update :y #(+ % (* dist (:aim acc)))))
              (= dir "up")      (update :aim #(- % dist))
              (= dir "down")    (update :aim #(+ % dist))))
          {:x 0 :y 0 :aim 0}
          dir-arr))

(defn solve []
  (-> directions
      parse-directions-str
      move
      (#(* (:x %) (:y %)))))

(defn solve-with-aim []
  (-> directions
      parse-directions-str
      move-with-aim
      (#(* (:x %) (:y %)))))
