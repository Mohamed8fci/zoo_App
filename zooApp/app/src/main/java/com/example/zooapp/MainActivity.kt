package com.example.zooapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.res.ColorStateListInflaterCompat.inflate
import androidx.core.content.res.ComplexColorCompat.inflate
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.animal_ticket.view.*

class MainActivity : AppCompatActivity() {
    var listOfAnimals = ArrayList<Animal>()
    var adapter:AnimalsAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listOfAnimals.add(
            Animal("Baboon","They are some of the world’s largest monkeys. There are five species of the baboon — olive, yellow, chacma, Guinea, and sacred — scattered across various habitat in Africa and Arabia.",R.drawable.baboon,false))

        listOfAnimals.add(
            Animal("Bulldog","You can’t mistake a Bulldog for any other breed. The loose skin of the head, furrowed brow, pushed-in nose, small ears, undershot jaw with hanging chops on either side.",R.drawable.bulldog,false))

        listOfAnimals.add(
            Animal("Panda","The panda, with its distinctive black and white coat, is adored by the world and considered a national treasure in China.",R.drawable.panda,true))

        listOfAnimals.add(
            Animal("Swallow","Swallows are small birds with dark, glossy-blue backs, red throats, pale underparts and long tail streamers. They are extremely agile in flight and spend most of their time on the wing.",R.drawable.swallow_bird,false))

        listOfAnimals.add(
            Animal("Tiger","The largest of all the Asian big cats, tigers rely primarily on sight and sound rather than smell for hunting.",R.drawable.white_tiger,true))

        listOfAnimals.add(
            Animal("Zebra","Zebras are African equines with distinctive black-and-white striped coats.",R.drawable.zebra,false))

        adapter= AnimalsAdapter(this,listOfAnimals)
        tvListAnimal.adapter=adapter
    }
   // if you want to delete
    fun delete(index:Int){
      listOfAnimals.removeAt(index)
        adapter!!.notifyDataSetChanged()
    }
    //if you duplicat your ticket
    fun add(index: Int){
        listOfAnimals.add(index,listOfAnimals[index])
        adapter!!.notifyDataSetChanged()
    }

    inner class AnimalsAdapter:BaseAdapter{
        var listOfAnimals = ArrayList<Animal>()
        var context:Context?=null
        constructor(context:Context, listOfAnimals : ArrayList<Animal>):super(){
            this.listOfAnimals=listOfAnimals
            this.context=context
        }

        override fun getCount(): Int {
            return listOfAnimals.size
        }

        override fun getItem(p0: Int): Any {
            return listOfAnimals[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            val animal = listOfAnimals[p0]
            if(animal.isKiller==true){
                var inflator =
                    context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

                var myView = inflator.inflate(R.layout.animal_killer_ticket, null);
                myView.tvName.text = animal.name!!
                myView.tvDescription.text = animal.des!!
                myView.ivAnimalImage.setImageResource(animal.image!!)

                myView.ivAnimalImage.setOnClickListener(){


                    val intent = Intent(context,AnimalInfo::class.java)
                    intent.putExtra("name",animal.name!!)
                    intent.putExtra("des",animal.des!!)
                    intent.putExtra("image",animal.image!!)
                    context!!.startActivity(intent)

                }
                return myView

            }else {
                var inflator =
                    context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

                var myView = inflator.inflate(R.layout.animal_ticket, null);
                myView.tvName.text = animal.name!!
                myView.tvDescription.text = animal.des!!
                myView.ivAnimalImage.setImageResource(animal.image!!)
                myView.ivAnimalImage.setOnClickListener(){

                    val intent = Intent(context,AnimalInfo::class.java)
                    intent.putExtra("name",animal.name!!)
                    intent.putExtra("des",animal.des!!)
                    intent.putExtra("image",animal.image!!)
                    context!!.startActivity(intent)


                }
                return myView
            }
        }

    }
}