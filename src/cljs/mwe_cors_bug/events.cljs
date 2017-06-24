(ns mwe-cors-bug.events
  (:require [re-frame.core :as re-frame]
            [mwe-cors-bug.db :as db]
            [ajax.core :as ajax]))

(re-frame/reg-event-db
  :initialize-db
  (fn  [_ _]
    db/default-db))

(re-frame/reg-event-db
  :good-http-result
  (fn  [_ _]
    {:name "Good http result"}))

(re-frame/reg-event-db
  :bad-http-result
  (fn  [_ _]
    {:name "Bad http result"}))

(re-frame/reg-event-fx
  :get-gnu
  (fn [_ _]
    {:http-xhrio {:method          :get
                  :uri             "https://www.gnu.org/"
                  :timeout         8000                                           ;; optional see API docs
                  :response-format (ajax/json-response-format {:keywords? true})  ;; IMPORTANT!: You must provide this.
                  :on-success      [:good-http-result]
                  :on-failure      [:bad-http-result]}}))
