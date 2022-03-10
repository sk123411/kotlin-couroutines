package com.example.flipkarttest

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flipkarttest.adapter.ScoreListAdapter
import com.example.flipkarttest.model.CustomPlayerMatch
import io.paperdb.Paper
import java.io.IOException

class PlayerDetailActivity() : AppCompatActivity() {


    var playerId:String?=""
    var playerName:String?=""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_detail)



        Paper.init(applicationContext)
        var list: List<CustomPlayerMatch>? = Paper.book().read("cc")
        playerId = intent.getStringExtra("id")
        playerName = intent.getStringExtra("name")

        val scoreListView = findViewById<RecyclerView>(R.id.scoreList)
        val playerNameText = findViewById<TextView>(R.id.pp)


        playerNameText?.text = playerName
        scoreListView.apply {


            layoutManager = LinearLayoutManager(this@PlayerDetailActivity)
            adapter =  ScoreListAdapter(list,playerId!!)


        }


//
//
//        var scorePlayerModel = mutableListOf<ScorePlayerModel?>()
//        var singleScorePlayerModel:ScorePlayerModel?=null

//        playerModelList.forEach {
//
//            if(it.id==player){
//
//
//                scoreModelList.forEach {s ->
//
//
//                    if(s.player1.id==player){
//                        singleScorePlayerModel?.currentPlayer = it.name
//                        singleScorePlayerModel?.currentPlayerScore = s.player1.score
//
//
//                    }
//
//
//                }
//
//
//            }else {
//
//                scoreModelList.forEach {s ->
//
//
//                        singleScorePlayerModel?.otherPlayer = it.name
//                        singleScorePlayerModel?.otherPlayerScore = s.player2.score
//
//
//
//
//
//                }
//
//            }
//
//            scorePlayerModel.add(singleScorePlayerModel)
//
//
//
//        }






    }


    fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

}