#pragma once
#include <chrono>
#include <sys/time.h>
#include <ctime>
#include "structs.h"


int alphabetaMove(int depth, int init_player);
int getTerminatedValue(int winner, int depth);
int getWinner(Pair last_move);
int checkLose(int move);
int checkWin(int move);
Variation MinimaxAB(int depth, Pair last_move, int alpha, int beta, bool maximizingPlayer);
int64_t getTime();