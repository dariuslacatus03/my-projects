from domain.Cell import Cell


class Board:
    def __init__(self):
        self.cells = [[Cell(row, col) for col in range(10)] for row in range(10)]

    def set_cell(self, row, col, status):
        self.cells[row][col].status = status


