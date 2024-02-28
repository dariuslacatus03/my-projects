from Domain.Directed_Cost_Graph import *
from UI.graphUI import *

graph1 = create_random_graph(7, 20)
graph2 = create_random_graph(6, 30)
write_graph_file(graph1, "random_graph1.txt")
write_graph_file(graph2, "random_graph2.txt")

graphUI()

