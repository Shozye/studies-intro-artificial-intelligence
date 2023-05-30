#include <limits.h>
#include <stdio.h>
#include "structs.h"
#include "globals.h"
#include "board_functions.h"
#include <chrono>
#include <sys/time.h>
#include <ctime>
#include <algorithm>
#include <iostream>

const int TERMINATE_CONST = 1e7;
const int INF = 2e9;

int checkWin(int move) {
    std::vector<std::vector<Pair>> winStates = possibleWinAfterMove[move];
    // przechodzimy po wszystkich mozliwych kombinacji ułożeń wygranych dla nowego ruchu
    for(long unsigned int state_i = 0; state_i < winStates.size(); state_i++) {
        std::vector<Pair> winState = winStates[state_i];
        // sprawdzamy czy 4 pola mają takiego samego gracza
        int possible_winner = getBoard(winState[0]);
        if (possible_winner != NONE 
            && possible_winner == getBoard(winState[1])
            && possible_winner == getBoard(winState[2])
            && possible_winner == getBoard(winState[3])
        ) {
            return possible_winner;
        }
    }
    return NONE;
}
int checkLose(int move) {
    std::vector<std::vector<Pair>> loseStates = possibleLoseAfterMove[move];
    // przechodzimy po wszystkich mozliwych kombinacji ułożeń przegranych dla nowego ruchu
    for(long unsigned int state_i = 0; state_i < loseStates.size(); state_i++) {
        std::vector<Pair> loseState = loseStates[state_i];
        // sprawdzamy czy 3 pola mają takiego samego gracza
        int possible_loser = getBoard(loseState[0]);
        if (possible_loser != NONE 
            && possible_loser == getBoard(loseState[1])
            && possible_loser == getBoard(loseState[2])
        ) {
            return possible_loser;
        }
    }
    return NONE;
}
int getWinner(Pair last_move){
    if (last_move.row == INVALID.row) return NONE;
    int val = last_move.row*10 + last_move.col;
    int winner = NONE;
    int loser = checkLose(val);

    // jeśli istnieją 3 pola, które robią przegraną to sprawdź czy istnieje 4 pole które robi wygraną
    if (loser != NONE) winner = checkWin(val);
    if(winner != 0){
        printf("last move: %d % d, winner: %d", last_move.row, last_move.col, winner);
    }
    // jeśli nie ma wygranej, tylko jest przegrana (3 pola) to ustaw winner na przeciwienstwo loser'a
    if (winner == NONE){
        winner = -loser;
    } 
    return winner;
}

int getTerminatedValue(int winner, int depth){
    int val = 1;
    if (winner != INIT_PLAYER){
        val = -1;
    } 
    return TERMINATE_CONST * val * (depth + 1);
}

int evaluate(){
    return 0;
}



Variation MinimaxAB(int depth, Pair last_move, int alpha, int beta, bool maximizingPlayer){
    //int winner = getWinner(last_move);
    int movingPlayer = maximizingPlayer ? INIT_PLAYER : -INIT_PLAYER;
    bool isWinner = winCheck(movingPlayer);
    bool isLoser = loseCheck(movingPlayer);
    //if (winner != 0){ return { getTerminatedValue(winner, depth) , INVALID }; } 
    if (isWinner == true){ return { getTerminatedValue(movingPlayer, depth) , INVALID }; } 
    else if(isLoser == true){
        return { getTerminatedValue(-movingPlayer, depth) , INVALID };
    }else if ( depth == 0 ) { return { evaluate(), INVALID}; }

    Variation value = {maximizingPlayer ? -INF : INF, INVALID};

    
    for (int move: possible_moves){
        int row = move / 10; int col = move % 10; Pair movePair = {row, col};
        if (board[row][col] != NONE) continue;

        fastSetMove(row, col, movingPlayer);
        int minimaxValue = MinimaxAB(depth-1, movePair, alpha, beta, !maximizingPlayer).value;
        fastSetMove(row, col, NONE);

        if (maximizingPlayer){
            if (minimaxValue > value.value ) { value = { minimaxValue, movePair }; }
            alpha = std::max(alpha, minimaxValue);
            if(beta <= alpha){
                break;
            }
            /*
            if (minimaxValue > value.value ) { value = { minimaxValue, movePair }; }
            if (value.value > beta){ break; }
            if (alpha < value.value){ alpha = value.value; }
            */
        } else {
            if (minimaxValue < value.value ) { value = { minimaxValue, movePair }; }
            beta = std::min(beta, minimaxValue);
            if(beta <= alpha){
                break;
            }
            /*
            if (minimaxValue < value.value ) { value = { minimaxValue, movePair }; }
            if (value.value < alpha){ break; }
            if (beta > value.value){ beta = value.value; }
            */
        }
    }
    return value;
}

int64_t getTime(){
    return std::chrono::duration_cast<std::chrono::milliseconds>(std::chrono::system_clock::now().time_since_epoch()).count();
}

int alphabetaMove(int depth, int init_player){
    INIT_PLAYER = init_player;

    auto start = getTime();
    Variation ret = MinimaxAB(depth, INVALID, -INF, INF, true);
    auto interval = getTime() -start;

    printf("Found move: %d %d, value: %d in %ldms\n", ret.pair.row, ret.pair.col, ret.value, interval);
    return (ret.pair.row)*10 + ret.pair.col;
}
