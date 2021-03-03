package com.example.tetris

class Peca {

    var x = 1
    var y = 11

    var linha = 2
    var coluna = 4

    constructor(type: Int){
        createPeca(type)
    }

    var peca = Array(2) {
        Array(4){0}
    }



    fun createPeca(type:Int){

        when(type){
            1 -> {
                this.peca[1][0] = 1
                this.peca[1][1] = 1
                this.peca[1][2] = 1
                this.peca[1][3] = 1
            }
            2 -> {
                this.peca[0][1] = 1
                this.peca[0][2] = 1
                this.peca[1][1] = 1
                this.peca[1][2] = 1
            }
            3 -> {
                this.peca[0][0] = 1
                this.peca[0][1] = 1
                this.peca[0][2] = 1
                this.peca[1][1] = 1

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