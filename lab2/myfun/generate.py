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
    
def printStates(main_list, filename):
    text = ""
    text += "Array(\n"
    state_i = 0
    for state in main_list:
        text += "\t"
        text += "("
        
        text += ", ".join([str(tile[0]*5 + tile[1]).rjust(2) for tile in state])

        text += "),"
        state_i += 1
        if state_i % 2 == 0:
            text += "\n"
        else:
            text += " "
    
    text += "\n)"
    with open(filename, 'w+') as file:
        file.write(text)
    
printStates(winStates, "win_scalastates.txt")
printStates(loseStates, "lose_scalastates.txt")
"""
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

print(winStatesPerMove)

def printStates(main_list, filename):
    text = "ArraySeq(\n"
    for rowcol in range(25):
        row = rowcol // 5
        col = rowcol % 5
        key = f"{row}{col}"

        
        text += "List("
        for i, state in enumerate(main_list[key]):
            text += "Array(" 
            text += ", ".join("(" + f"{tile[0] * 5 + tile[1]}" + ")" for tile in state)
            text += ")"
            if i != len(main_list[key]) - 1:
                text += ", "
        text += ")"
        if rowcol != 44:
            text += ","
        text += "\n"
    text += ")"
    
        
    with open(filename, 'w+') as file:
        file.write(text)

#printStates(winStatesPerMove,"win_scala.txt")
#printStates(loseStatesPerMove,"lose_scala.txt")
    
    
"""
        