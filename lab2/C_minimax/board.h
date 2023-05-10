/****************************
Maciej Gębala (CC BY-NC 4.0)
Plansza ver. 0.1
2023-03-30
****************************/
#pragma once
#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

int board[5][5];

const int win[28][4][2] = { 
  { {0,0}, {0,1}, {0,2}, {0,3} },
  { {1,0}, {1,1}, {1,2}, {1,3} },
  { {2,0}, {2,1}, {2,2}, {2,3} },
  { {3,0}, {3,1}, {3,2}, {3,3} },
  { {4,0}, {4,1}, {4,2}, {4,3} },
  { {0,1}, {0,2}, {0,3}, {0,4} },
  { {1,1}, {1,2}, {1,3}, {1,4} },
  { {2,1}, {2,2}, {2,3}, {2,4} },
  { {3,1}, {3,2}, {3,3}, {3,4} },
  { {4,1}, {4,2}, {4,3}, {4,4} },
  { {0,0}, {1,0}, {2,0}, {3,0} },
  { {0,1}, {1,1}, {2,1}, {3,1} },
  { {0,2}, {1,2}, {2,2}, {3,2} },
  { {0,3}, {1,3}, {2,3}, {3,3} },
  { {0,4}, {1,4}, {2,4}, {3,4} },
  { {1,0}, {2,0}, {3,0}, {4,0} },
  { {1,1}, {2,1}, {3,1}, {4,1} },
  { {1,2}, {2,2}, {3,2}, {4,2} },
  { {1,3}, {2,3}, {3,3}, {4,3} },
  { {1,4}, {2,4}, {3,4}, {4,4} },
  { {0,1}, {1,2}, {2,3}, {3,4} },
  { {0,0}, {1,1}, {2,2}, {3,3} },
  { {1,1}, {2,2}, {3,3}, {4,4} },
  { {1,0}, {2,1}, {3,2}, {4,3} },
  { {0,3}, {1,2}, {2,1}, {3,0} },
  { {0,4}, {1,3}, {2,2}, {3,1} },
  { {1,3}, {2,2}, {3,1}, {4,0} },
  { {1,4}, {2,3}, {3,2}, {4,1} }
};

const int lose[48][3][2] = {
  { {0,0}, {0,1}, {0,2} }, { {0,1}, {0,2}, {0,3} }, { {0,2}, {0,3}, {0,4} }, 
  { {1,0}, {1,1}, {1,2} }, { {1,1}, {1,2}, {1,3} }, { {1,2}, {1,3}, {1,4} }, 
  { {2,0}, {2,1}, {2,2} }, { {2,1}, {2,2}, {2,3} }, { {2,2}, {2,3}, {2,4} }, 
  { {3,0}, {3,1}, {3,2} }, { {3,1}, {3,2}, {3,3} }, { {3,2}, {3,3}, {3,4} }, 
  { {4,0}, {4,1}, {4,2} }, { {4,1}, {4,2}, {4,3} }, { {4,2}, {4,3}, {4,4} }, 
  { {0,0}, {1,0}, {2,0} }, { {1,0}, {2,0}, {3,0} }, { {2,0}, {3,0}, {4,0} }, 
  { {0,1}, {1,1}, {2,1} }, { {1,1}, {2,1}, {3,1} }, { {2,1}, {3,1}, {4,1} }, 
  { {0,2}, {1,2}, {2,2} }, { {1,2}, {2,2}, {3,2} }, { {2,2}, {3,2}, {4,2} }, 
  { {0,3}, {1,3}, {2,3} }, { {1,3}, {2,3}, {3,3} }, { {2,3}, {3,3}, {4,3} }, 
  { {0,4}, {1,4}, {2,4} }, { {1,4}, {2,4}, {3,4} }, { {2,4}, {3,4}, {4,4} }, 
  { {0,2}, {1,3}, {2,4} }, { {0,1}, {1,2}, {2,3} }, { {1,2}, {2,3}, {3,4} }, 
  { {0,0}, {1,1}, {2,2} }, { {1,1}, {2,2}, {3,3} }, { {2,2}, {3,3}, {4,4} }, 
  { {1,0}, {2,1}, {3,2} }, { {2,1}, {3,2}, {4,3} }, { {2,0}, {3,1}, {4,2} }, 
  { {0,2}, {1,1}, {2,0} }, { {0,3}, {1,2}, {2,1} }, { {1,2}, {2,1}, {3,0} }, 
  { {0,4}, {1,3}, {2,2} }, { {1,3}, {2,2}, {3,1} }, { {2,2}, {3,1}, {4,0} }, 
  { {1,4}, {2,3}, {3,2} }, { {2,3}, {3,2}, {4,1} }, { {2,4}, {3,3}, {4,2} }
};

int children[25] = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24};

void shuffleChildren() {
  for (int i = 25-1; i >= 1; i--) {
    int j = rand() % (i+1);

    int swap = children[j];
    children[j] = children[i];
    children[i] = swap;
  }
}

int isTerminal(struct Pair* last_move) {
  if (last_move == NULL){
    return 0;
  } else {
    
  }

}

void setBoard()
{
  for(int i=0; i<5; i++)
    for(int j=0; j<5; j++)
      board[i][j]=0;
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
        case 2: printf(" O"); break;
      }
    printf("\n");
  }
  printf("\n");
}

bool setMove(int move, int player)
{
  int i,j;
  i = (move/10)-1;
  j = (move%10)-1;
  if( (i<0) || (i>4) || (j<0) || (j>4) ) return false; 
  if( board[i][j]!=0 ) return false;
  board[i][j] = player;
  return true;
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
