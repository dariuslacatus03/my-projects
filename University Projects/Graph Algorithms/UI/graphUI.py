from Domain.Directed_Cost_Graph import *
from Domain.Undirected_Graph import *



def graphMenu():
    print("""Graph Menu. Available commands:
    1. Create graph
    2. Create random graph
    3. Read a graph from a file
    4. Get the number of vertices of the graph
    5. Get the number of edges of the graph
    6. Check if an edge exists
    7. Get the in degree of a vertex
    8. Get the out degree of a vertex
    9. Get the cost of an edge
    10. Set the cost of an edge
    11. Add edge to graph
    12. Remove edge from graph
    13. Parse the set of vertices
    14. Parse the set of outbound edges of a vertex
    15. Parse the set of inbound edges of a vertex
    16. Write the graph to a file
    17. Find the lowest length path between two vertices
    18. Find the lowest cost path between two vertices
    19. Find the minimum spanning tree of an undirected graph
    20. Exit
    """)

def graphUI():
    graph = None
    while (True):
        graphMenu()
        command = input("Your command: ")
        try:
            if command == '1':
                vertices = int(input("Give the number of vertices: "))
                graph = Directed_Cost_Graph(vertices)
            elif command == '2':
                vertices = int(input("Give the number of vertices: "))
                edges = int(input("Give the number of edges: "))
                graph = create_random_graph(vertices, edges)
            elif command == '3':
                file_name = input("Give the name of the file you want to read from: ")
                graph = read_graph_file(file_name)
            elif command == '4':
                print(graph.getNumberOfVertices())
            elif command == '5':
                print(graph.getNumberOfEdges())
            elif command == '6':
                source = int(input("Give the source vertex: "))
                destination = int(input("Give the destination vertex: "))
                print(f"Edge exist: {graph.isEdge(source, destination)}")
            elif command == '7':
                vertex = int(input("Give vertex: "))
                print(f"In degree of the vertex: {graph.getInDegree(vertex)}")
            elif command == '8':
                vertex = int(input("Give vertex: "))
                print(f"Out degree of the vertex: {graph.getOutDegree(vertex)}")
            elif command == '9':
                source = int(input("Give the source vertex: "))
                destination = int(input("Give the destination vertex: "))
                print(f"The cost of the edge: {graph.getCost((source, destination))}")
            elif command == '10':
                source = int(input("Give the source vertex: "))
                destination = int(input("Give the destination vertex: "))
                cost = int(input("Give the cost you want to set: "))
                graph.setCost((source, destination), cost)
            elif command == '11':
                source = int(input("Give the source vertex: "))
                destination = int(input("Give the destination vertex: "))
                cost = int(input("Give the cost you want to set: "))
                if (graph.addEdge(source, destination, cost) == True):
                    print("The vertex was added.")
                else:
                    print("The vertex already exists.")
            elif command == '12':
                source = int(input("Give the source vertex: "))
                destination = int(input("Give the destination vertex: "))
                graph.removeEdge(source, destination)
            elif command == '13':
                for vertex in graph.parseVertices():
                    print(vertex)
            elif command == '14':
                source = int(input("Give the source vertex: "))
                for destination in graph.parseOutDestination(source):
                    print(destination)
            elif command == '15':
                destination = int(input("Give the destination vertex: "))
                for source in graph.parseInSource(destination):
                    print(source)
            elif command == '16':
                file_name = input("Enter filename: ")
                write_graph_file(graph, file_name)
            elif command == '17':
                startVertex = int(input("Give starting vertex: "))
                endVertex = int(input("Give ending vertex: "))
                shortestPath = graph.findLowestLengthPath(startVertex, endVertex)
                print(f"Minimum length path: {shortestPath}\nMinimum length: {len(shortestPath)}\n")
            elif command == '18':
                startVertex = int(input("Give starting vertex: "))
                endVertex = int(input("Give ending vertex: "))
                minimumCostPath = graph.findMinimumCostWalk(startVertex, endVertex)
                cost = 0
                for i in range(len(minimumCostPath) - 1):
                    cost = cost + graph.getCost((minimumCostPath[i], minimumCostPath[i+1]))
                print(minimumCostPath)
                print(cost)
            elif command == '19':
                undirectedGraph = createSmallUndirectedGraph()
                print("\nYour graph: \n")
                printUndirectedGraph(undirectedGraph)
                print("\nMinimum spanning tree: \n")
                minSpanTree = kruskal(undirectedGraph)
                print(minSpanTree)
            elif command == '20':
                print("The program will be closed")
                return 0
        except Exception as e:
            print(str(e))
