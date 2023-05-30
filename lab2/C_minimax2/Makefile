FLAGS = -W -pedantic -std=c++17 -O3 -g
.PHONY = all clean

all: client test

test: src/test.cpp build/globals.o build/board_functions.o build/algorithm.o
	g++ $(FLAGS) src/test.cpp build/globals.o build/board_functions.o build/algorithm.o -o test

client: src/game_client.cpp build/globals.o build/board_functions.o build/algorithm.o
	g++ $(FLAGS) src/game_client.cpp build/globals.o build/board_functions.o build/algorithm.o -o client

build/algorithm.o : src/algorithm.cpp
	g++ $(FLAGS) -c src/algorithm.cpp -o build/algorithm.o

build/board_functions.o : src/board_functions.cpp
	g++ $(FLAGS) -c src/board_functions.cpp -o build/board_functions.o

build/globals.o : src/globals.cpp
	g++ $(FLAGS) -c src/globals.cpp -o build/globals.o

clean:
	rm -rf build/* client 