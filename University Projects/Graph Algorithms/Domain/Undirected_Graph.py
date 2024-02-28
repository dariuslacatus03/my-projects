


class Undirected_Graph:
    def __init__(self, n):
        '''Constructs an undirected graph with n vertices numbered
        from 0 to n-1 and no edges'''
        if isinstance(n, int):
            vertices = range(n)
        else:
            vertices = n
        self._cost = {}
        self._neighbors = {}
        for vertex in vertices:
            self._neighbors[vertex] = []


    def addEdge(self, source, destination, cost):
        '''Adds an edge from source to destination.
        Returns True on succes, False if the edge already exists or coordinates are invalid'''
        if self.isEdge(source, destination):
            return False
        self._neighbors[source].append(destination)
        self._neighbors[destination].append(source)
        self._cost[(source, destination)] = cost
        self._cost[(destination, source)] = cost
        return True

    def isEdge(self, source, destination):
        '''Checks if an edge already exists or if it can exist'''
        if source > len(self._neighbors) or destination > len(self._neighbors):
            raise Exception("Invalid source or destination.")
        return destination in self._neighbors[source]


    def parseVertices(self):
        '''Returns something that can be iterated and produces all
        the vertices of the graph'''
        return list(self._neighbors.keys())

    def parseN(self, source):
        return list(self._neighbors[source])

    def parseNOut(self, source):
        return self.parseN(source)

    def parseNIn(self, source):
        return self.parseN(source)

    def cost(self, source, destination):
        """Returns the cost of the edge (x,y)
        """
        return self._cost[(source, destination)]

def printUndirectedGraph(undirectedGraph):
    print("Outbound")
    for vertex in undirectedGraph.parseVertices():
        print(vertex, ":", end='')
        for destination in undirectedGraph.parseNOut(vertex):
            print(f"({destination}, {undirectedGraph.cost(vertex, destination)}) ", end='')
        print()
    print("Inbound")
    for vertex in undirectedGraph.parseVertices():
        print(vertex, ":", end='')
        for destination in undirectedGraph.parseNIn(vertex):
            print(f"({destination}, {undirectedGraph.cost(destination, vertex)}) ", end = '')
        print()

def createSmallUndirectedGraph():
    g = Undirected_Graph(range(1, 7))
    lst = [(1,2,3), (1,3,2), (1,4,4), (2,3,2), (2,6,1), (3,4,4),(3,5,3),(3,6,2),(4,5,5),(5,6,5)]
    for x, y, c in lst:
        g.addEdge(x, y, c)
    return g

class DisjointSets:
    def __init__(self, elements):
        '''Creates a set of sets where each element in 'elements' is the sole member of
        its own set
        '''
        self.__parent = {}
        self.__height = {}  # height of the tree rooted in the key vertex;
        # no longer maintained for non-roots

        for element in elements:
            self.__parent[element] = None
            self.__height[element] = 0

    def __root(self, x):
        while self.__parent[x] is not None:
            x = self.__parent[x]
        return x

    def join(self, x, y):
        '''If x and y are in the same set, returns False. Otherwise, joins together
        the sets of x and of y and returns True.
        '''
        rootOfX = self.__root(x)
        rootOfY = self.__root(y)
        heightOfX = self.__height[rootOfX]
        heightOfY = self.__height[rootOfY]
        if rootOfX == rootOfY:
            return False
        if heightOfX < heightOfY:
            self.__parent[rootOfX] = rootOfY
        elif heightOfX > heightOfY:
            self.__parent[rootOfY] = rootOfX
        else:
            self.__parent[rootOfY] = rootOfX
            self.__height[rootOfX] += 1
        return True

def kruskal(graph):
    '''Constructs the minimum spaning tree for the given graph. Precond: g is connected.
    Returns the list of edges for the tree.
    '''
    edges = []
    for vertex in graph.parseVertices():
        for destination in graph.parseN(vertex):
            if vertex < destination:
                edges.append((vertex, destination, graph.cost(vertex, destination)))
    comp = lambda t: t[2]
    edges.sort(key=comp)
    tree = []
    disjoint_sets = DisjointSets(graph.parseVertices())
    for vertex, destination, c in edges:
        if disjoint_sets.join(vertex, destination):
            tree.append((vertex, destination))
    if len(tree) < len(graph.parseVertices()) - 1:
        return None
    return tree