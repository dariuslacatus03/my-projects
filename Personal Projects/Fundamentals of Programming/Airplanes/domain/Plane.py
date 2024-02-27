class Plane:
    def __init__(self, hrow, hcol, dir):
        self.head = [hrow, hcol]
        self.dir = dir
        self.body = self.generate_body(hrow, hcol, dir)

    def generate_body(self, r, c, dir):
        body = [[r, c]]
        if dir == 'left':
            body.append([r, c - 1])
            body.append([r + 1, c - 1])
            body.append([r + 2, c - 1])
            body.append([r - 1, c - 1])
            body.append([r - 2, c - 1])
            body.append([r, c - 2])
            body.append([r, c - 3])
            body.append([r + 1, c - 3])
            body.append([r - 1, c - 3])
        elif dir == 'right':
            body.append([r, c + 1])
            body.append([r + 1, c + 1])
            body.append([r + 2, c + 1])
            body.append([r - 1, c + 1])
            body.append([r - 2, c + 1])
            body.append([r, c + 2])
            body.append([r, c + 3])
            body.append([r + 1, c + 3])
            body.append([r - 1, c + 3])
        elif dir == 'down':
            body.append([r + 1, c])
            body.append([r + 1, c - 1])
            body.append([r + 1, c - 2])
            body.append([r + 1, c + 1])
            body.append([r + 1, c + 2])
            body.append([r + 2, c])
            body.append([r + 3, c])
            body.append([r + 3, c - 1])
            body.append([r + 3, c + 1])
        elif dir == 'up':
            body.append([r - 1, c])
            body.append([r - 1, c - 1])
            body.append([r - 1, c - 2])
            body.append([r - 1, c + 1])
            body.append([r - 1, c + 2])
            body.append([r - 2, c])
            body.append([r - 3, c])
            body.append([r - 3, c - 1])
            body.append([r - 3, c + 1])
        return body

