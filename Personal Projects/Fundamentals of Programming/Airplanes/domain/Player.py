from domain.Board import Board

class Player:
    def __init__(self):
        self.board = Board()
        self.board_to_hit = Board()
        self.list_of_planes = []
        self.hits = []

    def set_board(self, board):
        self.board = board

    def set_planes(self, planes):
        self.planes = planes