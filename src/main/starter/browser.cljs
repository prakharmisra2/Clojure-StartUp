(ns starter.browser
  (:require [reagent.core :as r]
            [reagent.dom :as rdom]))

;;greeting and printing hello name
(defn greet [name]
  [:div
   [:p.someclass 
    [:strong "Hello "]
    [:span {:style {:color "red"}} name] "!"]])

;; making list 

(defn lister [items]
  [:ul 
   (for [item items]
     [:li "Item no: " item])])

;; making counter when button clicked counter increased

(def counter (r/atom 0))

(defn counting-fun []
  [:div 
   "The atom " [:code "counter "] "has value: "
   @counter ". "
   [:input {:type "button" :value "click me!"
            :on-click (fn [] 
                        (if (> @counter 5)
                          (reset! counter 0)
                          (swap! counter inc)))}]])

(defn timer []
  (let [seconds (r/atom 0)]
    (fn [] (js/setTimeout #(swap! seconds inc) 1)
      [:div "Seconds passed:   " @seconds])))

;;Makin a timer
(def miliSeconds (r/atom 0))

(def seconds (r/atom 0))

(def minutes (r/atom 0))

(def hours (r/atom  0))

(defn manager []
   (swap! miliSeconds inc)
  (if (= @miliSeconds 100)
    (do
      (swap! seconds inc)
      (reset! miliSeconds 0)))
  (if (= @seconds 60)
    (do
      (swap! minutes inc)
      (reset! seconds 0)))
  (if (= @minutes 60)
    (do
      (swap! hours inc)
      (reset! minutes 0))))

(def lap [])

(defn timer2 []
  
  (fn [] 
   (js/setTimeout manager 1) 
 ;;   (manager)
    (js/console.log "rerender")
    [:div 
     [:p.someclass [:span {:style {:color "red"}} [:strong " Timer"]]]
     @hours " : " @minutes " : " @seconds "     " @miliSeconds "       "
     [:input {:type "button" :value "reset"
              :on-click (fn [] (reset! seconds 0)
                          (reset! minutes 0))}]
     #_[:input {:type "button" :value "Lap" 
              :on-click (fn []
                          (let [hr  @hours
                                min @minutes
                                sec @seconds]
                           [:div
                            [:p "lap !" hr " : " min " : " sec]]))}]
     ]))


(defn init []
  
  [:div

   [timer2]
   
  ;[timer]

   ;;[:p "here"]
   ;;[counting-fun]
   ;;[:p.someclass [:strong "Here is the list example!!!"] ]
   ;;[lister (range 5)]
   ;;[greet "pralksj"]
  ;; [:p "Here in parent "]
  ;; [simpleChild]
   ]
  
  )

(defn simpleChild []
  [:div
   [:h3 "I am a component!"]
   [:p.someclass
    "I have " [:strong "bold"]
    [:span {:style {:color "red"}} " and red"]
    " text."]])

(defn start []
  (rdom/render [init] (js/document.getElementById "app")))

(start)