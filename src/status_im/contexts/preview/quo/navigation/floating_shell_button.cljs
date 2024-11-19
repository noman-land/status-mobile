(ns status-im.contexts.preview.quo.navigation.floating-shell-button
  (:require
    [quo.core :as quo]
    [reagent.core :as reagent]
    [status-im.contexts.preview.quo.preview :as preview]))

(def descriptor
  [{:key :show-search? :type :boolean}
   {:key :show-mention? :type :boolean}
   {:key     :scroll-type
    :type    :select
    :options [{:key :notification-up}
              {:key :notification-down}
              {:key :scroll-to-bottom}]}])

(defn mock-data
  [{:keys [show-search? show-mention? scroll-type]}]
  (cond-> {}
    show-search?
    (assoc :search {:on-press #()})
    show-mention?
    (assoc :mention {:on-press #() :count 6 :customization-color :turquoise})
    (= scroll-type :notification-up)
    (assoc :notification-up {:on-press #() :count 8})
    (= scroll-type :notification-down)
    (assoc :notification-down {:on-press #() :count 8})
    (= scroll-type :scroll-to-bottom)
    (assoc :scroll-to-bottom {:on-press #()})))

(defn view
  []
  (let [state (reagent/atom {:scroll-type :notification-down})]
    (fn []
      [preview/preview-container
       {:state                     state
        :descriptor                descriptor
        :component-container-style {:padding-vertical 60
                                    :align-items      :center}}
       [quo/floating-shell-button (mock-data @state) nil]])))
