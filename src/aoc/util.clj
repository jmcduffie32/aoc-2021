(ns aoc.util
  (:require [clojure.java.io :as io]))

(defn parse-number-list-file [filename]
  (->> filename
       io/resource
       slurp
       clojure.string/split-lines
       (mapv #(Integer/parseInt %))))
