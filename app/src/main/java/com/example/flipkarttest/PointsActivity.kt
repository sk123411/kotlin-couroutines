package com.example.flipkarttest

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flipkarttest.adapter.PlayerListAdapter
import com.example.flipkarttest.model.CustomPlayerMatch
import com.example.flipkarttest.model.player.Players
import com.example.flipkarttest.model.player.PlayersItem
import com.example.flipkarttest.model.scores.Scores
import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*
import org.json.JSONException

import org.json.JSONObject

import org.json.JSONArray
import java.io.IOException
import java.io.InputStream
import kotlin.system.measureTimeMillis
import kotlin.time.milliseconds


class PointsActivity : AppCompatActivity() {


    var onPointsCaluculated: onPointsCaluculated? = null


    @DelicateCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val players = getJsonDataFromAsset(applicationContext, "StarWarsPlayers.json")
        val playersScores = getJsonDataFromAsset(applicationContext, "StarWarsMatches.json")
        val gson = Gson()
        val playerModelList: Players = gson.fromJson(players.toString(), Players::class.java)
        val scoreModelList: Scores = gson.fromJson(playersScores.toString(), Scores::class.java)
        val playerIdWithMatches = HashMap<String, List<CustomPlayerMatch>>()
        val playerNameWithId = HashMap<String, String>()
        val customPostList = mutableListOf<CustomPlayerMatch>()
        val playerListView = findViewById<RecyclerView>(R.id.playerList)


        


        //GLOBALSCOPE WITH GLOBALSCOPE AND ASYNC

//        GlobalScope.launch(Dispatchers.IO) {
//            val time = measureTimeMillis {
//
//                GlobalScope.launch(Dispatchers.IO) {
//                    async {
//                        playerModelList.forEach {
//                            playerNameWithId.put(it.id.toString(), it.name)
//
//                        }
//                    }.await()
//                }
//                GlobalScope.launch(Dispatchers.IO) {
//                    async {
//
//                        scoreModelList.forEach { it ->
//                            var currentPlayerId: String? = ""
//                            var otherPlayerId: String? = ""
//                            var currentPlayer: String? = ""
//                            var otherPlayer: String? = ""
//                            var currentPlayerScore: String? = ""
//                            var otherPlayerScore: String? = ""
//
//                            if (playerNameWithId.containsKey(it.player1.id.toString())) {
//                                currentPlayer = playerNameWithId[it.player1.id.toString()]
//                                currentPlayerScore = it.player1.score.toString()
//                                currentPlayerId = it.player1.id.toString()
//                                otherPlayer = playerNameWithId[it.player2.id.toString()]
//                                otherPlayerScore = it.player2.score.toString()
//                                otherPlayerId = it.player2.id.toString()
//
//                            } else
//                                if (playerNameWithId.containsKey(it.player2.id.toString())) {
//                                    currentPlayer = playerNameWithId[it.player2.id.toString()]
//                                    currentPlayerScore = it.player2.score.toString()
//                                    currentPlayerId = it.player2.id.toString()
//                                    otherPlayer = playerNameWithId[it.player1.id.toString()]
//                                    otherPlayerScore = it.player1.score.toString()
//                                    otherPlayerId = it.player1.id.toString()
//
//
//                                }
//
//                            customPostList.add(
//                                CustomPlayerMatch(
//                                    currentPlayerId,
//                                    otherPlayerId,
//                                    currentPlayer,
//                                    otherPlayer,
//                                    currentPlayerScore,
//                                    otherPlayerScore
//                                )
//                            )
//
//
//                        }
//
//                    }.await()
//
//                }
//                GlobalScope.launch(Dispatchers.IO) {
//                    async {
//
//                        playerNameWithId.forEach { m ->
//
//                            playerIdWithMatches.put(m.key,
//                                customPostList.filter { it.currentPlayerId == m.key || it.otherPlayerId == m.key }
//                                    .toList()
//                            )
//
//                        }
//
//
//                    }.await()
//
//                }
//
//            }
//            Log.d("TAGGGG", "Time taken:${time}")
//
//        }

        Log.d("TAGGGG", "SETTING OPERATION")


        //GLOBALSCOPE WITH LAUNCH
        GlobalScope.launch(Dispatchers.IO) {
            val time = measureTimeMillis {

                launch {
                    playerModelList.forEach {
                        playerNameWithId.put(it.id.toString(), it.name)

                    }
                }

                 launch {

                    scoreModelList.forEach { it ->
                        var currentPlayerId: String? = ""
                        var otherPlayerId: String? = ""
                        var currentPlayer: String? = ""
                        var otherPlayer: String? = ""
                        var currentPlayerScore: String? = ""
                        var otherPlayerScore: String? = ""

                        if (playerNameWithId.containsKey(it.player1.id.toString())) {
                            currentPlayer = playerNameWithId[it.player1.id.toString()]
                            currentPlayerScore = it.player1.score.toString()
                            currentPlayerId = it.player1.id.toString()
                            otherPlayer = playerNameWithId[it.player2.id.toString()]
                            otherPlayerScore = it.player2.score.toString()
                            otherPlayerId = it.player2.id.toString()

                        } else
                            if (playerNameWithId.containsKey(it.player2.id.toString())) {
                                currentPlayer = playerNameWithId[it.player2.id.toString()]
                                currentPlayerScore = it.player2.score.toString()
                                currentPlayerId = it.player2.id.toString()
                                otherPlayer = playerNameWithId[it.player1.id.toString()]
                                otherPlayerScore = it.player1.score.toString()
                                otherPlayerId = it.player1.id.toString()


                            }

                        customPostList.add(
                            CustomPlayerMatch(
                                currentPlayerId,
                                otherPlayerId,
                                currentPlayer,
                                otherPlayer,
                                currentPlayerScore,
                                otherPlayerScore
                            )
                        )


                    }

                }

                 launch {

                    playerNameWithId.forEach { m ->

                        playerIdWithMatches.put(m.key,
                            customPostList.filter { it.currentPlayerId == m.key || it.otherPlayerId == m.key }
                                .toList()
                        )

                    }


                }

//                job1.join()
//                job2.join()
//                job3.join()



            }
            Log.d("TAGGGG", "Time taken:${time}")
        }



        //GLOBALSCOPE WITH ASYNC

//        GlobalScope.launch(Dispatchers.IO) {
//            val time = measureTimeMillis {
//
//                async {
//                    playerModelList.forEach {
//                        playerNameWithId.put(it.id.toString(), it.name)
//
//                    }
//                }.await()
//
//                async {
//
//                    scoreModelList.forEach { it ->
//                        var currentPlayerId: String? = ""
//                        var otherPlayerId: String? = ""
//                        var currentPlayer: String? = ""
//                        var otherPlayer: String? = ""
//                        var currentPlayerScore: String? = ""
//                        var otherPlayerScore: String? = ""
//
//                        if (playerNameWithId.containsKey(it.player1.id.toString())) {
//                            currentPlayer = playerNameWithId[it.player1.id.toString()]
//                            currentPlayerScore = it.player1.score.toString()
//                            currentPlayerId = it.player1.id.toString()
//                            otherPlayer = playerNameWithId[it.player2.id.toString()]
//                            otherPlayerScore = it.player2.score.toString()
//                            otherPlayerId = it.player2.id.toString()
//
//                        } else
//                            if (playerNameWithId.containsKey(it.player2.id.toString())) {
//                                currentPlayer = playerNameWithId[it.player2.id.toString()]
//                                currentPlayerScore = it.player2.score.toString()
//                                currentPlayerId = it.player2.id.toString()
//                                otherPlayer = playerNameWithId[it.player1.id.toString()]
//                                otherPlayerScore = it.player1.score.toString()
//                                otherPlayerId = it.player1.id.toString()
//
//
//                            }
//
//                        customPostList.add(
//                            CustomPlayerMatch(
//                                currentPlayerId,
//                                otherPlayerId,
//                                currentPlayer,
//                                otherPlayer,
//                                currentPlayerScore,
//                                otherPlayerScore
//                            )
//                        )
//
//
//                    }
//
//                }.await()
//
//                async {
//
//                    playerNameWithId.forEach { m ->
//
//                        playerIdWithMatches.put(m.key,
//                            customPostList.filter { it.currentPlayerId == m.key || it.otherPlayerId == m.key }
//                                .toList()
//                        )
//
//                    }
//
//
//                }.await()
//
//            }
//            Log.d("TAGGGG", "Time taken:${time}")
//        }



        Log.d("TAGGGG", "SETTING DATA")
        playerListView.apply {

            layoutManager = LinearLayoutManager(this@PointsActivity)
            adapter = PlayerListAdapter(playerModelList, playerIdWithMatches)

        }


    }


    fun getJsonDataFromAsset(context: Context, fileName: String): String? {


        val jsonString: String

        val time = measureTimeMillis {
            try {
                jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }


            } catch (ioException: IOException) {
                ioException.printStackTrace()
                return null
            }

        }

        Log.d("PPPPPP", ": ${time}")


        return jsonString
    }


}