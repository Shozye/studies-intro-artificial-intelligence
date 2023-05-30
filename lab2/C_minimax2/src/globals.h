#pragma once
#include <vector>
#include "structs.h"

extern int board[5][5];
extern int possible_moves[25];

extern int X;
extern int O;
extern int NONE;

extern Pair INVALID;
extern int INIT_PLAYER;

extern int win[28][4][2];
extern int lose[48][3][2];
extern std::vector<std::vector<Pair>> possibleWinAfterMove[45];
extern std::vector<std::vector<Pair>> possibleLoseAfterMove[45];