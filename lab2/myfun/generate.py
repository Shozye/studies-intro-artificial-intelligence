winStates = [
    [(0, 0), (0, 1), (0, 2), (0, 3)],
    [(1, 0), (1, 1), (1, 2), (1, 3)],
    [(2, 0), (2, 1), (2, 2), (2, 3)],
    [(3, 0), (3, 1), (3, 2), (3, 3)],
    [(4, 0), (4, 1), (4, 2), (4, 3)],
    [(0, 1), (0, 2), (0, 3), (0, 4)],
    [(1, 1), (1, 2), (1, 3), (1, 4)],
    [(2, 1), (2, 2), (2, 3), (2, 4)],
    [(3, 1), (3, 2), (3, 3), (3, 4)],
    [(4, 1), (4, 2), (4, 3), (4, 4)],
    [(0, 0), (1, 0), (2, 0), (3, 0)],
    [(0, 1), (1, 1), (2, 1), (3, 1)],
    [(0, 2), (1, 2), (2, 2), (3, 2)],
    [(0, 3), (1, 3), (2, 3), (3, 3)],
    [(0, 4), (1, 4), (2, 4), (3, 4)],
    [(1, 0), (2, 0), (3, 0), (4, 0)],
    [(1, 1), (2, 1), (3, 1), (4, 1)],
    [(1, 2), (2, 2), (3, 2), (4, 2)],
    [(1, 3), (2, 3), (3, 3), (4, 3)],
    [(1, 4), (2, 4), (3, 4), (4, 4)],
    [(0, 1), (1, 2), (2, 3), (3, 4)],
    [(0, 0), (1, 1), (2, 2), (3, 3)],
    [(1, 1), (2, 2), (3, 3), (4, 4)],
    [(1, 0), (2, 1), (3, 2), (4, 3)],
    [(0, 3), (1, 2), (2, 1), (3, 0)],
    [(0, 4), (1, 3), (2, 2), (3, 1)],
    [(1, 3), (2, 2), (3, 1), (4, 0)],
    [(1, 4), (2, 3), (3, 2), (4, 1)]
]

loseStates = [
    [(0, 0), (0, 1), (0, 2)], [(0, 1), (0, 2), (0, 3)], [(0, 2), (0, 3), (0, 4)],
    [(1, 0), (1, 1), (1, 2)], [(1, 1), (1, 2), (1, 3)], [(1, 2), (1, 3), (1, 4)],
    [(2, 0), (2, 1), (2, 2)], [(2, 1), (2, 2), (2, 3)], [(2, 2), (2, 3), (2, 4)],
    [(3, 0), (3, 1), (3, 2)], [(3, 1), (3, 2), (3, 3)], [(3, 2), (3, 3), (3, 4)],
    [(4, 0), (4, 1), (4, 2)], [(4, 1), (4, 2), (4, 3)], [(4, 2), (4, 3), (4, 4)],
    [(0, 0), (1, 0), (2, 0)], [(1, 0), (2, 0), (3, 0)], [(2, 0), (3, 0), (4, 0)],
    [(0, 1), (1, 1), (2, 1)], [(1, 1), (2, 1), (3, 1)], [(2, 1), (3, 1), (4, 1)],
    [(0, 2), (1, 2), (2, 2)], [(1, 2), (2, 2), (3, 2)], [(2, 2), (3, 2), (4, 2)],
    [(0, 3), (1, 3), (2, 3)], [(1, 3), (2, 3), (3, 3)], [(2, 3), (3, 3), (4, 3)],
    [(0, 4), (1, 4), (2, 4)], [(1, 4), (2, 4), (3, 4)], [(2, 4), (3, 4), (4, 4)],
    [(0, 2), (1, 3), (2, 4)], [(0, 1), (1, 2), (2, 3)], [(1, 2), (2, 3), (3, 4)],
    [(0, 0), (1, 1), (2, 2)], [(1, 1), (2, 2), (3, 3)], [(2, 2), (3, 3), (4, 4)],
    [(1, 0), (2, 1), (3, 2)], [(2, 1), (3, 2), (4, 3)], [(2, 0), (3, 1), (4, 2)],
    [(0, 2), (1, 1), (2, 0)], [(0, 3), (1, 2), (2, 1)], [(1, 2), (2, 1), (3, 0)],
    [(0, 4), (1, 3), (2, 2)], [(1, 3), (2, 2), (3, 1)], [(2, 2), (3, 1), (4, 0)],
    [(1, 4), (2, 3), (3, 2)], [(2, 3), (3, 2), (4, 1)], [(2, 4), (3, 3), (4, 2)]
]
from collections import defaultdict

winStatesPerMove = defaultdict(list)
loseStatesPerMove = defaultdict(list)

for row in range(5):
    for col in range(5):
        move = (row,col)
        for state in winStates:
            if move in state:
                winStatesPerMove[str(move[0])+str(move[1])].append(state)
        
        for state in loseStates:
            if move in state:
                loseStatesPerMove[str(move[0])+str(move[1])].append(state)

import json
with open("wins.json", 'w+') as file:
    file.write(json.dumps(winStatesPerMove, indent=4))
with open("loss.json", 'w+') as file:
    file.write(json.dumps(loseStatesPerMove, indent=4))
    
file = open("win.txt", 'w+')
for key,value in winStatesPerMove.items():
    file.write(f"({key[0]}, {key[1]}) -> Seq({', '.join(map(lambda x: 'Seq(' + str(x)[1:-1] + ')', value))})")
    file.write(",\n")
file.close()
    
file = open("lose.txt", 'w+')
for key,value in loseStatesPerMove.items():
    file.write(f"({key[0]}, {key[1]}) -> Seq({', '.join(map(lambda x: 'Seq(' + str(x)[1:-1] + ')', value))})")
    file.write(",\n")
file.close()
        