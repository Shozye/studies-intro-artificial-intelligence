#pragma once
#include "structs.h"

void shuffleChildren() ;
void setupChildren();
void setBoard();
void printBoard();
bool setMove(int move, int player);
void fastSetMove(int row, int col, int player);
bool winCheck(int player);
bool loseCheck(int player);
int getBoard(Pair pair);
