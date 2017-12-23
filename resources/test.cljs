(ns test)

(let [c (.. js/document (createElement "DIV"))]
  (aset c "innerHTML" "<p>i'm dynamically created from test.cljs again</p>")
  (.. js/document (getElementById "container") (appendChild c)))

(.log js/console "hello from test.cljs")

