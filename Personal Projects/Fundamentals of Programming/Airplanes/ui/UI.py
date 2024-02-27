from services.Services import Services
from repository.Repository import Repository
from domain.Plane import Plane


class UI:
    def __init__(self):
        self.repository = Repository()
        self.functions = Services(self.repository)

    def start(self):
        name1 = input("Player 1. Give your name: ")
        n = 1
        print(self.functions.display_player_board(self.repository.player1))
        while n <= 3:
            print(f"{name1}, place your plane number {n}")
            head = input("Give the coordinates of the head(x, y): ")
            coord = head.split(', ')
            dir = input("Give its direction(left, right, up or down): ")
            try:
                if coord[0].isnumeric() and coord[1].isnumeric():
                    self.functions.place_plane(int(coord[0]), int(coord[1]), dir, self.repository.player1)
                    plane = Plane(int(coord[0]), int(coord[1]), dir)
                    self.repository.player1.list_of_planes.append(plane)
                    print(self.functions.display_player_board(self.repository.player1))
                else:
                    raise Exception("Invalid coordinates. Make sure they are numeric values.")
            except Exception as e:
                print(str(e))
                n -= 1
            n += 1
        n = 1
        for i in range(200):
            print('\n')
        print(f"{name1}, your planes are all placed.")
        name2 = input("Player 2. Give your name: ")
        print(self.functions.display_player_board(self.repository.player2))
        while n <= 3:
            print(f"{name2}, place your plane number {n}")
            head = input("Give the coordinates of the head(x, y): ")
            coord = head.split(', ')
            dir = input("Give its direction(left, right, up or down): ")
            try:
                if coord[0].isnumeric() and coord[1].isnumeric():
                    self.functions.place_plane(int(coord[0]), int(coord[1]), dir, self.repository.player2)
                    plane = Plane(int(coord[0]), int(coord[1]), dir)
                    self.repository.player2.list_of_planes.append(plane)
                    print(self.functions.display_player_board(self.repository.player2))
                else:
                    raise Exception("Invalid coordinates. Make sure they are numeric values.")
            except Exception as e:
                print(str(e))
                n -= 1
            n += 1
        for i in range(200):
            print('\n')
        print(f"{name2}, your planes are all placed.")
        for i in range(10):
            for j in [10, 11]:
                self.repository.board.cells[i][j] = '||'
        player = self.repository.player2
        hs1 = 0
        hs2 = 0
        while True:
            if player == self.repository.player2:
                player = self.repository.player1
                print(self.functions.display_board_to_hit())
                print(f"{name1}! Your turn to attack.")
                coord = input("Give coordinates: ")
                coord = coord.split(', ')
                try:
                    if coord[0].isnumeric() and coord[1].isnumeric():
                        hit = self.functions.verif_hit(coord[0], coord[1], self.repository.player2)
                        print(hit)
                        if hit == 0:
                            self.repository.board.cells[int(coord[0])][int(coord[1]) + 12].status = 'miss'
                        elif hit == 1:
                            self.repository.board.cells[int(coord[0])][int(coord[1]) + 12].status = '·X·'
                        elif hit == 2:
                            self.repository.board.cells[int(coord[0])][int(coord[1]) + 12].status = 'HS'
                            hs2 += 1
                            if hs2 == 3:
                                print(self.functions.display_board_to_hit())
                                print(self.functions.display_player_board(self.repository.player2))
                                print(f"All {name2}'s planes are dead. {name1} won the game!")
                                return 0
                    else:
                        raise Exception("Invalid coordinates.")
                except Exception as e:
                    print(str(e))
            else:
                player = self.repository.player2
                print(self.functions.display_board_to_hit())
                print(f"{name2}! Your turn to attack.")
                coord = input("Give coordinates: ")
                coord = coord.split(', ')
                try:
                    if coord[0].isnumeric() and coord[1].isnumeric():
                        hit = self.functions.verif_hit(coord[0], coord[1], self.repository.player1)
                        print(hit)
                        if hit == 0:
                            self.repository.board.cells[int(coord[0])][int(coord[1])].status = 'miss'
                        elif hit == 1:
                            self.repository.board.cells[int(coord[0])][int(coord[1])].status = '·X·'
                        elif hit == 2:
                            self.repository.board.cells[int(coord[0])][int(coord[1])].status = 'HS'
                            hs1 += 1
                            if hs1 == 3:
                                print(self.functions.display_board_to_hit())
                                print(self.functions.display_player_board(self.repository.player1))
                                print(f"All {name1}'s planes are dead. {name2} won the game!")
                                return 0
                    else:
                        raise Exception("Invalid coordinates.")
                except Exception as e:
                    print(str(e))
