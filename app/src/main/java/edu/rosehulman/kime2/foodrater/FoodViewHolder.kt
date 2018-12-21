package edu.rosehulman.kime2.foodrater

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView

class FoodViewHolder(itemView: View, var adapter: FoodAdapter): RecyclerView.ViewHolder(itemView) {

    val imageView = itemView.findViewById<ImageView>(R.id.imageView)
    val foodTextView = itemView.findViewById<TextView>(R.id.food_name_view)
    val ratingBarView = itemView.findViewById<RatingBar>(R.id.ratingBar)

    init {
        ratingBarView.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            adapter.saveNewRating(adapterPosition, rating)
        }
    }

    fun bind(food: Food) {
        imageView.setImageResource(food.resourceID)
        foodTextView.text = food.name
        ratingBarView.rating = food.rating
    }
}