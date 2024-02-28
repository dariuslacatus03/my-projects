import copy
import random
import time


class Directed_Cost_Graph:
    def __init__(self, n):
        '''Constructs a graph with n vertices numbered
        from 0 to n-1 and no edges'''
        self._outbound = {}
        self._inbound = {}
        self.edges = {}
        for vertex in range(n):
            self._outbound[vertex] = []
            self._inbound[vertex] = []

    def getNumberOfVertices(self):
        '''Returns the number of Vertices of the graph'''
        return len(self._inbound)

    def getNumberOfEdges(self):
        '''Returns the number of edges of the graph'''
        return len(self.edges)

    def getInDegree(self, vertex):
        '''Returns the in degree of vertex'''
        if vertex not in self._inbound.keys():
            raise Exception("Vertex does not exist.")
        return len(self._inbound[vertex])

    def getOutDegree(self, vertex):
        '''Returns the out degree of vertex'''
        if vertex not in self._outbound.keys():
            raise Exception("Vertex does not exist.")
        return len(self._outbound[vertex])

    def getCost(self, edge):
        '''Returns the cost of edge'''
        if edge not in self.edges.keys():
            raise Exception("Edge does not exist.")
        return self.edges[edge]

    def setCost(self, edge, cost):
        '''Sets the cost of edge'''
        if edge not in self.edges.keys():
            raise Exception("Edge does not exist.")
        self.edges[edge] = cost

    def addEdge(self, source, destination, cost):
        '''Adds an edge from source to destination.
        Returns True on succes, False if the edge already exists or coordinates are invalid'''
        if self.isEdge(source, destination):
            return False
        else:
            self._outbound[source].append(destination)
            self._inbound[destination].append(source)
            self.edges[(source, destination)] = cost
        return True

    def isEdge(self, source, destination):
        '''Checks if an edge already exists or if it can exist'''
        if source >= len(self._inbound) or destination >= len(self._inbound):
            raise Exception("Invalid source or destination.")
        return destination in self._outbound[source]

    def removeEdge(self, source, destination):
        '''Removes the edge (source, destination) from the graph'''
        if (source, destination) not in self.edges.keys():
            raise Exception("Edge does not exist.")
        self._outbound[source].remove(destination)
        self._inbound[destination].remove(source)
        self.edges.pop((source, destination))
        return True

    def parseVertices(self):
        '''Returns something that can be iterated and produces all
        the vertices of the graph'''
        return list(self._outbound.keys())

    def parseOutDestination(self, source):
        '''Returns a list of all the destinations of the edges starting
        from source'''
        return list(self._outbound[source])

    def parseInSource(self, destination):
        '''Returns a list of all source nodes that end in destination'''
        return list(self._inbound[destination])

    def printEdges(self):
        '''Prints all the edges and their costs'''
        print("Edges")
        for edge in self.parseVertices():
            print(f"{edge}: {self.edges[edge]}")

    def getCopyOfGraph(self):
        '''Returns a deep copy of the graph'''
        return copy.deepcopy(self)


    def findLowestLengthPath(self, startVertex, endVertex):
        '''Find the lowest length path between two given vertices and returns it as a list'''
        if startVertex not in self._inbound.keys() or endVertex not in self._inbound.keys():
            raise Exception("Make sure the vertices exist!")
        if startVertex == endVertex: # this means we don't have to move at all
            return [startVertex]
        queue = [startVertex] # Initialize a queue in which we'll have all the vertices we've been through
        visited = {startVertex} # Keep track of visited vertices
        parent = {startVertex: None} # Keep track of the parents of all the vertices

        while len(queue) != 0: # Since we'll keep popping and adding neighbors,
            # we keep doing the steps from below until all of them are visited
            current = queue.pop(0) # We pop from the start because otherwise we'll pop the last added element
            # and we don't want that
            for neighbour in self._outbound[current]:
                if neighbour not in visited: # If the neighbour is not visited we add it in the queue and in
                    # the visited list and set its parent to the current vertex
                    queue.append(neighbour)
                    visited.add(neighbour)
                    parent[neighbour] = current
        if endVertex not in parent.keys(): # this means there isn't a path from startVertex to endVertex
            return []

        shortestPath = [endVertex] # initialize the path with the endVertex because we will follow the parent path
        # until we reach to the startVertex. If we choose the start from startVertex, its father is None and we can't
        # reach the endVertex
        while parent[endVertex] != None:
            shortestPath.append(parent[endVertex])
            endVertex = parent[endVertex]
        shortestPath.reverse() # reverse in order to get the path from start to end
        return shortestPath

    def extract_path(self, parent, startVertex, endVertex):
        """
            Extracts the minimum cost path from the 'parent' dictionary.

            Args:
                parent (dict): Dictionary storing the parent vertices for each vertex in the path.
                startVertex: The starting vertex of the path.
                endVertex: The ending vertex of the path.

            Returns:
                list or None: The minimum cost path from 'startVertex' to 'endVertex',
                             or None if no path exists.
            """
        if endVertex not in parent:
            return None
        path = []
        currentVertex = endVertex
        while currentVertex != startVertex:
            path.append(currentVertex)
            currentVertex = parent[currentVertex]
        path.append(startVertex)
        path.reverse()
        return path

    def findMinimumCostWalk(self, startVertex, endVertex):
        """
            Finds the minimum cost path from 'startVertex' to 'endVertex' in the graph.

            Args:
                startVertex: The starting vertex of the path.
                endVertex: The ending vertex of the path.

            Returns:
                list or None: The minimum cost path as a list of vertices, or None if no path exists.
            """
        minCostMatrix = []
        minCostMatrix.append({x : float('inf') for x in self.parseVertices()})
        minCostMatrix[0][startVertex] = 0
        distance = {x : float('inf') for x in self.parseVertices()}
        distance[startVertex] = 0
        previous = {startVertex: None}
        for k in range(1, len(self.parseVertices())):
            currentDistance = minCostMatrix[-1]
            nextDistance = {}
            for vertex in self.parseVertices():
                nextDistance[vertex] = float('inf')
                for inVertex in self.parseInSource(vertex):
                    if nextDistance[vertex] > currentDistance[inVertex] + self.getCost((inVertex, vertex)):
                        nextDistance[vertex] = currentDistance[inVertex] + self.getCost((inVertex, vertex))
                        if nextDistance[vertex] < distance[vertex]:
                            distance[vertex] = nextDistance[vertex]
                            previous[vertex] = inVertex
            minCostMatrix.append(nextDistance)
        return self.extract_path(previous, startVertex, endVertex)


def print_graph(graph):
    print("Outbound")
    for source in graph.parseVertices():
        print(f"{source}: ", end='')
        for destination in graph.parseOutDestination(source):
            print(f"{destination} ", end='')
        print()
    print("Inbound")
    for destination in graph.parseVertices():
        print(f"{destination}: ", end='')
        for source in graph.parseInSource(destination):
            print(f"{source} ", end='')
        print()


def parse_graph(graph):
    before = time.time()
    for source in graph.parseVertices():
        for destination in graph.parseOutDestination(source):
            pass
    after = time.time()
    print("Function parseOutDestination: %sms" % ((after - before) * 1000,))
    before = time.time()
    for destination in graph.parseVertices():
        for source in graph.parseInSource(destination):
            pass
    after = time.time()
    print("Function parseInSource: %sms" % ((after - before) * 1000,))


def create_random_graph(nodes, edges, ctor=Directed_Cost_Graph):
    graph = ctor(nodes)
    while edges > 0:
        source = random.randrange(nodes)
        destination = random.randrange(nodes)
        cost = random.randint(1, 100)
        if graph.addEdge(source, destination, cost):
            edges = edges - 1
    return graph


def read_graph_file(file_name):
    file = open(file_name, "r")
    vertices_edges = file.readline()
    vertices_edges = vertices_edges.split()
    vertices = int(vertices_edges[0])
    edges = int(vertices_edges[1])
    graph = Directed_Cost_Graph(vertices)
    source_destination_cost = file.readlines()
    for line in source_destination_cost:
        line = line.split()
        source = int(line[0])
        destination = int(line[1])
        cost = int(line[2])
        graph.addEdge(source, destination, cost)
    return graph


def write_graph_file(graph, file_name):
    file = open(file_name, "w")
    file.write(f"{graph.getNumberOfVertices()} {graph.getNumberOfEdges()}\n")
    for source in graph.parseVertices():
        if graph.getOutDegree(source) != 0:
            for destination in graph.parseOutDestination(source):
                file.write(f"{source} {destination} {graph.getCost((source, destination))}\n")
