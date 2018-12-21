package edu.rosehulman.kime2.foodrater

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import android.support.v7.widget.RecyclerView.ViewHolder
import android.support.v7.widget.RecyclerView



class MainActivity : AppCompatActivity() {

    lateinit var adapter: FoodAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        var manager = LinearLayoutManager(this)
        adapter = FoodAdapter(this, manager)
        recycler_view.layoutManager = manager
        recycler_view.setHasFixedSize(true)
        recycler_view.adapter = adapter
        adapter.addFood("steak")
        adapter.addFood("banana")
        adapter.addFood("ice cream")

        fab.setOnClickListener { view ->
            adapter.addRandomFood()
        }
        initSwipe()
    }

    private fun initSwipe() {
        val simpleItemTouchCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: ViewHolder): Int {
                return ItemTouchHelper.Callback.makeMovementFlags(0, ItemTouchHelper.RIGHT)
            }

            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition

                if (direction == ItemTouchHelper.RIGHT) {
                    adapter.removeItem(position)
                }
            }
        }
        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        val recyclerView = findViewById(R.id.recycler_view) as RecyclerView
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
