(ns mwe-cors-bug.views
  (:require [re-frame.core :as re-frame]))

(defn main-panel []
  (let [name (re-frame/subscribe [:name])]
    (fn []
      [:div
       [:div "Hello from " @name]
       [:button {:on-click #(re-frame/dispatch [:get-gnu])} ":get-gnu"]])))
