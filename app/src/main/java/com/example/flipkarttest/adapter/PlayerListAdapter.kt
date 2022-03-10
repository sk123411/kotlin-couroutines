package com.example.flipkarttest.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.flipkarttest.PlayerDetailActivity
import com.example.flipkarttest.R
import com.example.flipkarttest.model.CustomPlayerMatch
import com.example.flipkarttest.model.ScorePlayerModel
import com.example.flipkarttest.model.player.Players
import com.example.flipkarttest.model.player.PlayersItem
import com.example.flipkarttest.model.scores.Scores
import com.example.flipkarttest.onPointsCaluculated
import com.squareup.picasso.Picasso
import io.paperdb.Paper

class PlayerListAdapter(val players: Players, val matches:HashMap<String, List<CustomPlayerMatch>>) : RecyclerView.Adapter<PlayerListAdapter.PlayerViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerListAdapter.PlayerViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.player_item, parent,false)
        return PlayerViewHolder(view)

    }

    override fun onBindViewHolder(holder: PlayerListAdapter.PlayerViewHolder, position: Int) {

        val player = players[position]

        holder.setData(player,matches)


    }

    override fun getItemCount(): Int {
        return players.size
    }

    class PlayerViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        var playerAvatar:ImageView?=null
        var playerName:TextView?=null
        var playerPoints:TextView?=null

        var rootLayout:LinearLayout?=null





        fun setData(playersItem: PlayersItem, matches: HashMap<String, List<CustomPlayerMatch>>){

            playerAvatar = view.findViewById(R.id.player_avatar)
            playerName= view.findViewById(R.id.player_name)
            playerPoints = view.findViewById(R.id.player_points)
            rootLayout = view.findViewById(R.id.rootLayoutPlayer)







            Picasso.get().load(playersItem.icon).into(playerAvatar)
            playerName?.text  = playersItem.name
            var scorePlayerModelList = mutableListOf<ScorePlayerModel?>()
            var scorePlayerModel:ScorePlayerModel?=null


            rootLayout?.setOnClickListener {
                Paper.init(it.context)
                Paper.book().write("cc",matches[playersItem.id.toString()]!! )
                val intent = Intent(it.context,PlayerDetailActivity::class.java)
                intent.putExtra("id",playersItem.id.toString())
                intent.putExtra("name",playersItem.name.toString())

                it.context.startActivity(intent)

            }



        }

    }

}