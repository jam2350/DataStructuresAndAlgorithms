Graph.java: represents directed, unweighted graphs using adjacency lists. Method computeEuclideanCosts() that computes and sets the cost for each edge using the euclidean (i.e. straight line) distance between the coordinates of the vertices. Method doBfs(String s) that uses Breadth First Search (BFS) to find the unweighted shortest paths (i.e. the paths with the lowest number of edges) from the start vertex with the name s. Method doDijkstra(String s) that uses Dijkstra’s algorithm to find the weighted shortest paths from the start vertex with the name s. Method doPrim(String s) that uses Prim’s algorithm to find a minimum spanning tree starting from s. Method getMinimumSpanningTree(String s) calls doPrim and returns a graph that contains the same vertices as the original graph, but only the edges found by Prim’s algorithm. 

The file ttrvertices.txt contains definitions for a set of cities.

The file ttredges.txt contains definitions for train connections.

MapReader.java: reads in a graph in the format described above and returns a new Graph object. All edges are undirected edges. The weight of each edge is initially set to 1.0. The program is called using 
$ java MapReader ttrvertices.txt ttredges.txt

