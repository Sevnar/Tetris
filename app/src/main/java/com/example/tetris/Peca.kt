package com.example.tetris

class Peca {

    var x = 1
    var y = 11

    var linha = 4
    var coluna = 4

    var type: Int = 0

    constructor(type: Int){
        this.type = type
        createPeca(type)
    }

    var peca = Array(linha) {
        Array(coluna){0}
    }



    fun createPeca(type:Int){

        when(type){
            //I-block
            1 -> {
                this.peca[1][0] = 1
                this.peca[1][1] = 1
                this.peca[1][2] = 1
                this.peca[1][3] = 1
            }
            //O-block
            2 -> {
                this.peca[1][1] = 1
                this.peca[1][2] = 1
                this.peca[2][1] = 1
                this.peca[2][2] = 1
            }
            //Tzinho <3
            3 -> {
                this.peca[1][0] = 1
                this.peca[1][1] = 1
                this.peca[1][2] = 1
                this.peca[2][1] = 1
            }
            //J-block
            4 -> {
                this.peca[1][0] = 1
                this.peca[2][0] = 1
                this.peca[2][1] = 1
                this.peca[2][2] = 1
            }
            //L-block
            5 -> {
                this.peca[2][0] = 1
                this.peca[2][1] = 1
                this.peca[2][2] = 1
                this.peca[1][2] = 1
            }
            //S-block
            6 -> {
                this.peca[2][0] = 1
                this.peca[2][1] = 1
                this.peca[1][1] = 1
                this.peca[1][2] = 1
            }
            //Z-block
            7 -> {
                this.peca[1][0] = 1
                this.peca[1][1] = 1
                this.peca[2][1] = 1
                this.peca[2][2] = 1
            }
        }

    }

    fun verifyMoveRight(board: Array<Array<Int>>):Boolean{
        for(i in 0 until linha){
            for(j in 0 until coluna){
                if(this.peca[i][j] == 1){
                    if(board[this.x+i][this.y+j+1] != 0)
                        return false
                }
            }
        }

        return true
    }

    fun moveRight(){
        y++
    }

    fun verifyMoveLeft(board: Array<Array<Int>>):Boolean{
        for(i in 0 until linha){
            for(j in 0 until coluna){
                if(this.peca[i][j] == 1){
                    if(board[this.x+i][this.y+j-1] != 0)
                        return false
                }
            }
        }

        return true
    }

    fun moveLeft(){
        y--
    }

    fun verifyMoveDown(board:Array<Array<Int>>): Boolean {

        for(i in 0 until linha){
            for(j in 0 until coluna){
                if(this.peca[i][j] == 1){
                    if(board[this.x+i+1][this.y+j] != 0){
                        return false
                    }
                }
            }
        }

        return true
    }

    fun moveDown(){
      this.x++
    }

    fun verifyPecaI(board:Array<Array<Int>>):Boolean{

        for(i in 0 until linha){
            for(j in 0 until coluna){
                if(this.peca[i][j] == 1){
                    if(verifyMoveRight(board) && verifyMoveLeft(board) && verifyMoveDown(board)){
                        if(board[this.x+i+2][this.y+j] != 0
                                || board[this.x+i][this.y+j-2] != 0
                                || board[this.x+i][this.y+j+2] != 0){
                            return false
                        }
                    }else{
                        return false
                    }
                }
            }
        }
        return true
    }

    fun verifyRotatePeca(board:Array<Array<Int>>){

        if(this.type != 1){
            if(verifyMoveRight(board) && verifyMoveLeft(board) && verifyMoveDown(board)){
                rotatePeca()
            }
        } else {
            if(verifyPecaI(board)){
                rotatePeca()
            }
        }
    }

    fun rotatePeca(){

        var temp = Array(linha) {
            Array(coluna) { 0 }
        }

        for (i in 0 until linha) {
            var aux = 0
            for (j in coluna - 1 downTo 0) {
                temp[i][aux] = peca[j][i]
                aux++
            }
        }

        peca = temp
    }







    fun printPeca(board:Array<Array<Int>>):Array<Array<Int>>{
        for(i in 0 until linha) {
            for (j in 0 until coluna) {
                if(this.peca[i][j] == 1){
                    board[this.x+i][this.y+j] = 1
                }
            }
        }

        return board
    }
}