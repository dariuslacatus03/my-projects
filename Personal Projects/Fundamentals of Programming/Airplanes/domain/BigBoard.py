from domain.Cell import Cell
import tabulate as tbl

class BigBoard:
    def __init__(self):
        self.cells = [[Cell(row, col) for col in range(23)] for row in range(11)]
        for i in range(1, 11):
            self.set_cell(0, i, str(i))
            self.set_cell(0, i + 12, str(i))
            self.set_cell(i, 0, str(i))
            self.set_cell(i, 12, str(i))

    def set_cell(self, row, col, status):
        self.cells[row][col].status = status

    def __str__(self):
        return tbl.tabulate(self.cells, tablefmt="grid")

x = BigBoard()
print(x)
