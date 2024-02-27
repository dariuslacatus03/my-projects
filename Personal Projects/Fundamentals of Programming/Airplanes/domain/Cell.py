class Cell:
    def __init__(self, row, col):
        self.row = row
        self.col = col
        self.status = '.  .'

    def __str__(self):
        return self.status

