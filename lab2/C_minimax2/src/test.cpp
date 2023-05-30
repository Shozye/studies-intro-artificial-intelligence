#include<stdio.h>
#include "globals.h"
#include "board_functions.h"
#include "algorithm.h"

#include <iostream>
#include <string> 

int main(){
    
    shuffleChildren(); shuffleChildren(); shuffleChildren();
    const int INF = 2e9;
    int newboard[5][5] = {
        {X,0,X,X,0},
        {0,0,0,0,0},
        {0,0,X,O,0},
        {0,0,0,O,0},
        {0,0,O,0,0}
    };

    for (int i = 0; i < 5; i++){
        for(int j = 0; j < 5; j++){
            board[i][j] = 0 ;
        }
    }

    INIT_PLAYER = O;
    auto start = getTime();
    Variation ret = MinimaxAB(10, INVALID, -INF, INF, true);
    auto interval = getTime() -start;
    printf("Found move: %d %d, value: %d in %ldms\n", ret.pair.row, ret.pair.col, ret.value, interval);
    
   
}