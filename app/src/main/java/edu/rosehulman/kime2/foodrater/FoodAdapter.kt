package edu.rosehulman.kime2.foodrater

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import java.util.*

class FoodAdapter(var context: Context, var manager: RecyclerView.LayoutManager): RecyclerView.Adapter<FoodViewHolder>() {

    val defaultFoodsAndName = HashMap<String, Int>()
    val foods = ArrayList<Food>()
    var layoutManager = manager

    init {
        defaultFoodsAndName.put("banana", R.mipmap.banana)
        defaultFoodsAndName.put("broccoli", R.mipmap.broccoli)
        defaultFoodsAndName.put("homemade bread", R.mipmap.bread)
        defaultFoodsAndName.put("chicken", R.mipmap.chicken)
        defaultFoodsAndName.put("chocolate", R.mipmap.chocolate)
        defaultFoodsAndName.put("ice cream", R.mipmap.icecream)
        defaultFoodsAndName.put("lima beans", R.mipmap.limabeans)
        defaultFoodsAndName.put("steak", R.mipmap.steak)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): FoodViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.row_view, p0, false)
        return FoodViewHolder(view, this)
    }

    override fun getItemCount(): Int {
        return foods.size
    }

    override fun onBindViewHolder(p0: FoodViewHolder, p1: Int) {
        p0.bind(foods[p1])
    }

    fun addFood(food: String) {
        val image = defaultFoodsAndName[food]
        var newFood = Food(food, image!!, 0f)
        foods.add(0, newFood)
        notifyItemInserted(0)
    }

    fun saveNewRating(adapterPosition: Int, rating: Float) {
        val food = foods[adapterPosition]
        food.rating = rating
    }

    fun removeItem(position: Int) {
        foods.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, foods.size)
    }

    fun addRandomFood() {
        var randNum = Random().nextInt(8)
        var count = 0

        for ((name) in defaultFoodsAndName) {
            if (count == randNum) {
                addFood(name)
                manager.scrollToPosition(0)
            }
            count = count + 1
        }
    }
}