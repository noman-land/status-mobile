(ns status-im.contexts.onboarding.common.carousel.style
  (:require
    [quo.foundations.colors :as colors]
    [react-native.reanimated :as reanimated]))

(defn header-container
  [status-bar-height content-width index header-background]
  {:margin-left      (* content-width index)
   :padding-top      (+ 30 status-bar-height)
   :width            content-width
   :flex-direction   :row
   :background-color (when header-background colors/onboarding-header-black)})

(defn header-text-view
  [window-width]
  {:flex-direction     :column
   :width              window-width
   :padding-horizontal 20})

(def carousel-text
  {:color colors/white})

(def carousel-sub-text
  {:color      colors/white
   :margin-top 2})

(defn progress-bar-item
  [static? end?]
  {:height           2
   :flex             1
   :background-color (if static? colors/white-opa-10 colors/white)
   :margin-right     (if end? 0 8)
   :border-radius    4})

(defn progress-bar
  [width]
  {:position       :absolute
   :top            0
   :width          width
   :flex-direction :row})

(defn dynamic-progress-bar
  [width animate?]
  (let [normal-style {:height        2
                      :border-radius 4
                      :overflow      :hidden
                      :width         width}]
    (if animate?
      (reanimated/apply-animations-to-style
       {:width width}
       normal-style)
      normal-style)))

(defn progress-bar-container
  [progress-bar-width status-bar-height]
  {:position    :absolute
   :width       progress-bar-width
   :margin-left 20
   :top         (+ 12 status-bar-height)})

(defn carousel-container
  [left animate? width]
  (cond->> {:flex-direction :row
            :flex           1
            :width          width
            :margin-left    left}
    animate? (reanimated/apply-animations-to-style {:margin-left left})))
