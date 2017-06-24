(ns mwe-cors-bug.core
  (:require [reagent.core :as reagent]
            [re-frame.core :as re-frame]
            [mwe-cors-bug.events]
            [mwe-cors-bug.subs]
            [mwe-cors-bug.views :as views]
            [mwe-cors-bug.config :as config]
            [day8.re-frame.http-fx]))


(defn dev-setup []
  (when config/debug?
    (enable-console-print!)
    (println "dev mode")))

(defn mount-root []
  (re-frame/clear-subscription-cache!)
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:export init []
  (re-frame/dispatch-sync [:initialize-db])
  (dev-setup)
  (mount-root))
