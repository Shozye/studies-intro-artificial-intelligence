/****************************
Maciej Gębala (CC BY-NC 4.0)
Plansza ver. 0.1
2023-03-30
****************************/
#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include "globals.h"
#include "structs.h"


int getBoard(Pair pair) {
  return board[pair.row][pair.col];
}

void setupChildren(){
  for(int i=0; i<5; i++){
    for(int j=0; j<5; j++){
      possible_moves[i*5+j]=i*10+j;
    }
  }
}


void shuffleChildren() {
  for (int i = 25-1; i >= 1; i--) {
    int j = rand() % (i+1);

    int swap = possible_moves[j];
    possible_moves[j] = possible_moves[i];
    possible_moves[i] = swap;
  }
}

void setBoard()
{
  for(int i=0; i<5; i++)
    for(int j=0; j<5; j++)
      board[i][j]=NONE;
}

void printBoard()
{
  printf("  1 2 3 4 5\n");
  for(int i=0; i<5; i++) {
    printf("%d",i+1);
    for(int j=0; j<5; j++ )
      switch(board[i][j]) {
        case 0: printf(" -"); break;
        case 1: printf(" X"); break;
        case -1: printf(" O"); break;
      }
    printf("\n");
  }
  printf("\n");
}

bool setMove(int move, int player){
  int i = (move/10);
  int j = (move%10);
  if( (i<0) || (i>4) || (j<0) || (j>4) ) return false; 
  if( board[i][j]!=0 ) return false;
  board[i][j] = player;
  return true;
}

void fastSetMove(int row, int col, int player){
  board[row][col] = player;
}

bool winCheck(int player)
{
  bool w=false;
  for(int i=0; i<28; i++)
    if( (board[win[i][0][0]][win[i][0][1]]==player) && (board[win[i][1][0]][win[i][1][1]]==player) && (board[win[i][2][0]][win[i][2][1]]==player) && (board[win[i][3][0]][win[i][3][1]]==player) )
      w=true;
  return w;
}

bool loseCheck(int player)
{
  bool l=false;
  for(int i=0; i<48; i++)
    if( (board[lose[i][0][0]][lose[i][0][1]]==player) && (board[lose[i][1][0]][lose[i][1][1]]==player) && (board[lose[i][2][0]][lose[i][2][1]]==player) )
      l=true;
  return l;
}
