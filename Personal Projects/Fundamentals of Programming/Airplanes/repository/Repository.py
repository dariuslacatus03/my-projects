from domain.BigBoard import BigBoard
from domain.Player import Player

class Repository:
    def __init__(self):
        self.player1 = Player()
        self.player2 = Player()
        self.board = BigBoard()


