def pprint(lst):
    for sublst in lst:
        for num in sublst:
            print(str(num).rjust(2, " "), end=",")
        print()
        
board = [
    [0,0,0,0,0],
    [0,0,0,0,0],
    [0,0,0,0,0],
    [0,0,0,0,0],
    [0,0,0,0,0]
]
board2 = [
    list(range(i*5,(i+1)*5)) for i in range(5)
]
def transposeX(row, col):
    return row, 4-col

def transposeY(row, col):
    return 4-row, col

def transposeMainDiagonal(row, col):
    return col, row

def transposeOtherDiagonal(row, col):
    return transposeX(*transposeY(*transposeMainDiagonal(row, col)))
def transpose(lst, func):
    newBoard = [
        [0,0,0,0,0] for i in range(5)
    ]
    for i in range(5):
        for j in range(5):
            tx, ty = func(i,j)
            newBoard[tx][ty] = lst[i][j]
    return newBoard
    
print("NORMAL")
pprint(board2)

print("\nTranspose X")
pprint(transpose(board2, transposeX))

print("\nTranspose Y")
pprint(transpose(board2, transposeY))

print("\nTranspose Main")
pprint(transpose(board2, transposeMainDiagonal))

print("\nTranspose Other")
pprint(transpose(board2, transposeOtherDiagonal))
transpositions = [
    lambda x, y: transposeX(x,y),
    lambda x, y: transposeY(x,y),
    lambda x, y: transposeX(*transposeY(x, y)),
    lambda x, y: transposeMainDiagonal(x, y),
    lambda x, y: transposeX(*transposeMainDiagonal(x, y)),
    lambda x, y: transposeY(*transposeMainDiagonal(x, y)),
    lambda x, y: transposeX(*transposeY(*transposeMainDiagonal(x, y)))
]

secondMoveTable = [list() for _ in range(25)]
print(secondMoveTable)
print("ArraySeq(")
for move in range(25):
    row, col = move // 5, move%5
    found = {(row, col)}
    smallest = set()
    
    good_transpositions = []
    for transposition in transpositions:
        if transpose(board2, transposition)[row][col] == board2[row][col]:
            good_transpositions.append(transposition)
    
    for newmove in range(25):
        
        newrow, newcol = newmove // 5, newmove%5
        newrowcol = (newrow, newcol)

        if newrowcol not in found:
            found.add(newrowcol)
            smallest.add(newrowcol)
            
        for transp in good_transpositions:
            found.add(transp(newrow, newcol))

    smallest = list(map(str, sorted(list(map(lambda x: x[0]*5 + x[1], smallest)))))
    
    print(f"Random.shuffle(IndexedSeq({', '.join(smallest)})), // {move}")
    
print(")")