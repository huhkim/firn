(defn render-site-map
  [sm]
  (->> sm
    (sort-by :firn-order)
    (map #(vector :div.pb1 [:a {:href (% :path)} (% :title)]))))

(defn tags
  [{:keys [site-map site-tags partials]}]
  (let [{:keys [head nav footer]} partials]
    (head
     [:body
      (nav)
      [:main
       [:article.def-wrapper
        [:aside#sidebar.def-sidebar
         (render-site-map site-map)]
        [:div.def-content
         [:h1 "Tags"]
         (for [[tag-name tags] site-tags]
           [:div
            [:h2 {:id tag-name :class "firn-tag-heading"} tag-name]
            (for [tag tags]
              [:div
               [:a {:href (tag :headline-link)}
                (tag :from-file) " - "
                (tag :from-headline)]])])
         (footer)]]]])))
