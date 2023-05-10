#include "board.h"
#include <limits.h>
#include "structs.h"


struct Variation alphabeta(short depth, struct Pair* last_move,  int alpha, int beta, bool maximizingPlayer) {
    struct Variation ret;
    int winner = isTerminal(last_move);
    if (winner != 0){
        struct Variation ret = { calculateTerminal(winner)*(depth+1) , NULL };
        return ret;
    } else if ( depth == 0 ) {
        struct Variation ret = { eval(), NULL };
        return ret;
    }
    if (maximizingPlayer){
        struct Variation ret = { INT_MIN, NULL};
        
    } else {
        struct Variation ret = { INT_MAX, NULL};
        return ret;
    }
}


int alphabeta(int depth){
    alphabeta(depth, NULL, INT_MIN, INT_MAX, true);
}
