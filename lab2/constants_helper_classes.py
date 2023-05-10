from collections import defaultdict
import os
import json
import sys

WIN = "WIN"
LOSE = "LOSE"
DRAW = "DRAW"
WIN_ERROR = "WIN_ERROR"
LOSE_ERROR = "LOSE_ERROR"
BAD_PORT = "BAD_PORT"
UNEXPECTED_ERROR = "UNEXPECTED_ERROR"

CODE_TO_NAME = {
    "100": WIN,
    "200": LOSE,
    "300": DRAW,
    "400": WIN_ERROR,
    "500": LOSE_ERROR
}

OPPOSITE = {
    LOSE: WIN,
    WIN: LOSE,
    DRAW: DRAW,
    LOSE_ERROR: WIN_ERROR,
    WIN_ERROR: LOSE_ERROR
}

def get_inner_statistics():
    return {
        WIN:0,
        LOSE:0,
        DRAW:0,
        WIN_ERROR:0,
        LOSE_ERROR:0,
        BAD_PORT:0
    }

def get_inner_statistics2():
    return defaultdict(int)

def get_base_statistics():
    return {
        "1": get_inner_statistics2(),
        "2": get_inner_statistics2()
    }
    
class DirectoryManager:
    previous_path = os.getcwd()
    
    @staticmethod
    def enter(path: str):
        os.chdir(path)
    
    @staticmethod
    def go_back():
        os.chdir(DirectoryManager.go_back)

class Client:
    def __init__(self, name: str, run, player: int):
        self.name = name
        self.run_ = run
        self.player = player
    
    def run(self, PORT, DEPTH):
        return self.run_(PORT, DEPTH, self.player)

    
class Config:
    IP = "127.0.0.1"
    AMOUNT = 100 * 2
    DEBUG = len(sys.argv) >= 2
    PORT_START = 8500
    MAX_PORT = 9000
    DEPTH_START = 3
    
def write_to_file(text: str, path: str):
    with open(path, 'w+', encoding='utf-8') as file:
        file.write(text)

def json_dump(obj, path: str):
    write_to_file(json.dumps(obj, indent=4), path)

    

