package com.example.tetris

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.GridLayout
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import com.example.tetris.databinding.ActivityTabuleiroBinding
import kotlin.random.Random

class TabuleiroActivity : AppCompatActivity() {

    val LINHA = 36
    val COLUNA = 26
    var running = true
    var speed:Long = 300

    lateinit var binding:ActivityTabuleiroBinding

    var peca = Peca(1)

    inner class Ponto(var x:Int,var y:Int){
        fun moveDown(){
            x++
        }
    }

    var board = Array(LINHA) {
        Array(COLUNA){0}
    }

    var boardView = Array(LINHA){
        arrayOfNulls<ImageView>(COLUNA)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_tabuleiro)

        binding.moveEsquerdaButton.setOnClickListener {
            if(peca.verifyMoveLeft(board)) {
                peca.moveLeft()
            }
        }

        binding.moveDireitaButton.setOnClickListener {
            if(peca.verifyMoveRight(board)) {
                peca.moveRight()
            }
        }

        binding.moveBaixoButton.setOnClickListener {
            if(peca.verifyMoveDown(board)){
                peca.moveDown()
            }
        }

        binding.girarButton.setOnClickListener {
            peca.verifyRotatePeca(board)
        }

        var gridboard = findViewById<GridLayout>(R.id.gridboard)
        gridboard.rowCount = LINHA
        gridboard.columnCount = COLUNA

        val inflater = LayoutInflater.from(this)

        for (i in 0 until LINHA) {
            for (j in 0 until COLUNA) {
                boardView[i][j] = inflater.inflate(R.layout.inflate_image_view, gridboard, false) as ImageView
                gridboard.addView(boardView[i][j])
            }
        }

        gameRun()
    }

    fun gameRun(){
        Thread{
            while(running){
                Thread.sleep(speed)
                runOnUiThread{
                    //limpa tela
                    for (i in 0 until LINHA) {
                        for (j in 0 until COLUNA) {
                                if((i == 0 || i == LINHA-1) || (j == 0 || j == COLUNA-1)){
                                    boardView[i][j]!!.setImageResource(R.drawable.gray)
                                    board[i][j] = 2
                                }else if(board[i][j] == 1){
                                    boardView[i][j]!!.setImageResource(R.drawable.white)
                                }else{
                                    boardView[i][j]!!.setImageResource(R.drawable.black)
                                }
                        }
                    }

                    //move peça atual
                    if(peca.verifyMoveDown(board)){
                        peca.moveDown()
                    }else{
                        board = peca.printPeca(board)
                        peca = Peca(Random.nextInt(1, 8))
                    }
                    //print peça
                    try {
                        for(i in 0 until peca.linha){
                            for(j in 0 until peca.coluna){
                                if(peca.peca[i][j] == 1){
                                    boardView[peca.x+i][peca.y+j]!!.setImageResource(R.drawable.white)
                                }
                            }
                        }


                    }catch (e:ArrayIndexOutOfBoundsException ) {
                        running = false
                    }
                }
            }
        }.start()
    }
}