import tabulate as table
from domain.Plane import Plane


class Services:
    def __init__(self, repo):
        self.repo = repo

    def display_player_board(self, player):
        return table.tabulate(player.board.cells, tablefmt='simple_grid')

    def display_board_to_hit(self):
        return table.tabulate(self.repo.board.cells, tablefmt='simple_grid')

    def place_plane(self, row, col, direction, player):
        if not (direction == 'left' or direction == 'right' or direction == 'up' or direction == 'down'):
            raise Exception("Invalid direction.")
        else:
            plane = Plane(row, col, direction)
            for el in player.list_of_planes:
                for i in plane.body:
                    for j in el.body:
                        if i == j:
                            raise Exception("Your plane intersects another one. Think about a better option.")
        if direction == 'left' and 2 <= row <= 7 and 3 <= col <= 9:
            player.board.set_cell(row, col, '>')
            player.board.set_cell(row, col - 1, 'A')
            player.board.set_cell(row + 1, col - 1, 'A')
            player.board.set_cell(row + 2, col - 1, 'A')
            player.board.set_cell(row - 1, col - 1, 'A')
            player.board.set_cell(row - 2, col - 1, 'A')
            player.board.set_cell(row, col - 2, 'A')
            player.board.set_cell(row, col - 3, 'A')
            player.board.set_cell(row + 1, col - 3, 'A')
            player.board.set_cell(row - 1, col - 3, 'A')
        elif direction == 'right' and 2 <= row <= 7 and 0 <= col <= 6:
            player.board.set_cell(row, col, '<')
            player.board.set_cell(row, col + 1, 'A')
            player.board.set_cell(row + 1, col + 1, 'A')
            player.board.set_cell(row + 2, col + 1, 'A')
            player.board.set_cell(row - 1, col + 1, 'A')
            player.board.set_cell(row - 2, col + 1, 'A')
            player.board.set_cell(row, col + 2, 'A')
            player.board.set_cell(row, col + 3, 'A')
            player.board.set_cell(row + 1, col + 3, 'A')
            player.board.set_cell(row - 1, col + 3, 'A')
        elif direction == 'down' and 0 <= row <= 6 and 2 <= col <= 7:
            player.board.set_cell(row, col, '^')
            player.board.set_cell(row + 1, col, 'A')
            player.board.set_cell(row + 1, col - 1, 'A')
            player.board.set_cell(row + 1, col - 2, 'A')
            player.board.set_cell(row + 1, col + 1, 'A')
            player.board.set_cell(row + 1, col + 2, 'A')
            player.board.set_cell(row + 2, col, 'A')
            player.board.set_cell(row + 3, col, 'A')
            player.board.set_cell(row + 3, col - 1, 'A')
            player.board.set_cell(row + 3, col + 1, 'A')
        elif direction == 'up' and 3 <= row <= 9 and 2 <= col <= 7:
            player.board.set_cell(row, col, 'v')
            player.board.set_cell(row - 1, col, 'A')
            player.board.set_cell(row - 1, col - 1, 'A')
            player.board.set_cell(row - 1, col - 2, 'A')
            player.board.set_cell(row - 1, col + 1, 'A')
            player.board.set_cell(row - 1, col + 2, 'A')
            player.board.set_cell(row - 2, col, 'A')
            player.board.set_cell(row - 3, col, 'A')
            player.board.set_cell(row - 3, col - 1, 'A')
            player.board.set_cell(row - 3, col + 1, 'A')
        else:
            raise Exception("Invalid coordinates. The plane doesn't fit on the board.")


    def verif_hit(self, row, col, player):
        coord = [int(row), int(col)]
        for el in player.list_of_planes:
            if coord == el.head:
                return 2
            if coord in el.body:
                return 1
        return 0