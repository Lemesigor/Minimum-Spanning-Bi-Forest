# PBGM ( Minimum Spanning Bi-Forest)

PBGM is a application that implements the Prim’s Minimum Spanning Tree algorithm  to a variation of this problem in which the return of the algorithm is not the total cost of a tree, but a forest with exactly TWO  connected components.
It's part of the final avaliation of Graph Theory class in Federal University of Rio Grande Do Sul. 


## Usage
Just compile the code using the makefile

### Input :
The input has to be a Adjacency matrix graph \[i]\[j] formatted as the following example:

```
7 
-1 4 -1 9 -1 -1 -1
4 -1 1 -1 1 -1 -1
-1 1 -1 -1 4 3 -1
9 -1 -1 -1 2 -1 1
-1 1 4 2 -1 5 2
-1 -1 3 -1 5 -1 -1
-1 -1 -1 1 2 -1 -1
```
 --> The first line indicate the number of vertices of the graph;

 --> If graph\[i]\[j] = -1, it means there's no edge connecting vertices i and j;

--> If graph\[i]\[j] >= 0, it indicates the cost of the edge connecting vertices i and j;
There is severeal tests in the /testFiles directory

### Output :
The output is a single number that indicates the the total cost of the Bi forest;
