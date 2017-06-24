(ns mwe-cors-bug.events
  (:require [re-frame.core :as re-frame]
            [mwe-cors-bug.db :as db]))

(re-frame/reg-event-db
 :initialize-db
 (fn  [_ _]
   db/default-db))
