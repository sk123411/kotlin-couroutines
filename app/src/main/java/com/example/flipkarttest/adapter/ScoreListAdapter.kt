package com.example.flipkarttest.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.flipkarttest.R
import com.example.flipkarttest.model.CustomPlayerMatch

class ScoreListAdapter(val scorePlayerModel: List<CustomPlayerMatch>?, val id: String) : RecyclerView.Adapter<ScoreListAdapter.ScoreViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreListAdapter.ScoreViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.score_item, parent,false)
        return ScoreViewHolder(view)

    }

    override fun onBindViewHolder(holder: ScoreListAdapter.ScoreViewHolder, position: Int) {

        val scoresItem = scorePlayerModel?.get(position)

        holder.setData(scoresItem,id)


    }

    override fun getItemCount(): Int {
        if (scorePlayerModel != null) {
            return scorePlayerModel.size
        }
        return 0
    }

    class ScoreViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

//        var playerAvatar:ImageView?=null
        var cuurentplayerName:TextView?=null
        var otherplayerName:TextView?=null
        var cuurentplayerPoints:TextView?=null
        var otherplayerPoints:TextView?=null
        var rootLayout:LinearLayout?=null

//        var playerPoints:TextView?=null



        @SuppressLint("ResourceAsColor")
        fun setData(sc:CustomPlayerMatch?, id: String){


            cuurentplayerName = view.findViewById(R.id.current_player_text)
            otherplayerName= view.findViewById(R.id.other_player_text)
            cuurentplayerPoints= view.findViewById(R.id.current_player_score_text)
            otherplayerPoints= view.findViewById(R.id.other_player_score_text)
            rootLayout= view.findViewById(R.id.rootLayout)


            sc?.let {
                if(it.currentPlayerId==id){

                    if(it.currentPlayerScore!!.toInt() > it.otherPlayerScore!!.toInt()){

                        rootLayout?.setBackgroundColor(view.context.resources.getColor(android.R.color.holo_green_light))



                    }else {
                        rootLayout?.setBackgroundColor(view.context.resources.getColor(android.R.color.white))

                    }


                }
                if(it.otherPlayerId==id){

                    if(it.otherPlayerScore!!.toInt() > it.currentPlayerScore!!.toInt()){

                        rootLayout?.setBackgroundColor(view.context.resources.getColor(android.R.color.holo_green_light))


                    }else {
                        rootLayout?.setBackgroundColor(view.context.resources.getColor(android.R.color.white))

                    }


                }

            }

            cuurentplayerName?.text = sc?.currentPlayer
            otherplayerName?.text = sc?.otherPlayer
            cuurentplayerPoints?.text = sc?.currentPlayerScore.toString()
            otherplayerPoints?.text = sc?.otherPlayerScore.toString()








        }

    }

}