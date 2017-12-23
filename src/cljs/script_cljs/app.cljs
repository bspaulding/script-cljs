(ns script-cljs.app
  (:require [cljs.tools.reader :refer [read-string]]
            [cljs.js :refer [empty-state eval js-eval]]))

(extend-type js/NodeList
  ISeqable
  (-seq [array] (array-seq array 0)))

(defn eval-str [s]
  (eval (empty-state)
        (read-string s)
        {:eval       js-eval
         :source-map true
         :context    :expr}
        (fn [result] result)))

(defn eval-script [script]
  (.then
    (.then
      (js/fetch (.-src script))
      #(.text %))
    #(eval-str %)))

(defn init []
  (let [scripts (.querySelectorAll js/document "script[type='text/cljs']")]
    (doall (map eval-script scripts))))



