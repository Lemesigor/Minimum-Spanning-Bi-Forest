package pbgm;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author igor lemes
 */
public class PBGM {
    
    //Number of vertice received in the first line
    private int totalVertice;
    //Sum of all edges in the spanning tree
    private int finalCost = 0;
    //Biggest value found until now 
    private int biggerValue = 0;
    private int graph[][];

    public PBGM(int v) {
        this.totalVertice = v;
    }
    
    /*
    Constructor of adjacency matrix of the graph 
    */
    public void createGraph() {
        this.graph = new int[this.totalVertice][this.totalVertice];
    }
    
    /*
    Convert the inpunt from String to int and put it in the correct line o the graph
    */
    
    public void setGraphLine(String line[], int lineIndex){
        for(int i = 0; i < this.totalVertice;i ++){
            this.graph[lineIndex][i] = Integer.parseInt(line[i]);
        }
    }
    
    /*
    Just a function to print the graph
    */
    public void printGraph(){
        for(int i= 0; i < this.totalVertice ; i++){
            for(int j = 0; j < this.totalVertice ; j++){
                System.out.print(this.graph[i][j] + " ");
            }
            System.out.println(" ");
        }
    }
    
    
    /*
    Function to see the vertice with minimum cost not included yet in the final solution
    */
    int minKey(int key[], Boolean mstSet[]) {

        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < totalVertice; v++) {
            if (mstSet[v] == false && key[v] < min) {
                min = key[v];
                min_index = v;
            }
        }

        return min_index;
    }

    /*
    Function to generate ONLY the total cost of the bi-forest as output of the program
    To do that, the for loop go trough the MST generetaed by prim's algorithm and "cut" the highst value of a 
    edge, creating then a forest with EXACTLY two conected components
    */
    void printResult(int parent[], int graph[][]) {
        System.out.println("Edge \tWeight");
        for (int i = 1; i < totalVertice; i++) {

            this.finalCost += graph[i][parent[i]];
            if (this.biggerValue < graph[i][parent[i]]) {
                this.biggerValue = graph[i][parent[i]];
            }

            System.out.println(parent[i] + " - " + i + "\t" + graph[i][parent[i]]);
        }
        
        int custoMinimo = this.finalCost - this.biggerValue;
        System.out.println("Maior valor encontrado é: " + this.biggerValue + " -- " + "Custo total: " + this.finalCost +
                "  Custo minimo total = " +custoMinimo);
    }

    
    /*
    Function that implements the PRIM's Minimum Spanning tree algorithm and print the total cost of Prims
    soluction used in a bi-forest
    */
    void primMST() {
        // Array to store constructed MST 
        int parent[] = new int[totalVertice];

        // Key values used to pick minimum weight edge in cut 
        int key[] = new int[totalVertice];

        // To represent set of vertices not yet included in MST 
        Boolean mstSet[] = new Boolean[totalVertice];

        // Initialize all keys as INFINITE 
        for (int i = 0; i < totalVertice; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }

        // Always include first 1st vertex in MST. 
        key[0] = 0; 
        
        parent[0] = -1; 


        for (int count = 0; count < totalVertice - 1; count++) {
            // Pick thd minimum key vertex from the set of vertices 
            // not yet included in MST 
            int u = minKey(key, mstSet);

            // Add the picked vertex to the MST Set 
            mstSet[u] = true;

            // Update key value and parent index of the adjacent 
            // vertices of the picked vertex. Consider only those 
            // vertices which are not yet included in MST 
            for (int v = 0; v < totalVertice; v++) 
                
            // Update the key only if graph[u][v] is smaller than key[v] 
            {
                if (this.graph[u][v] != -1 && mstSet[v] == false && this.graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = this.graph[u][v];
                }
            }
        }

 
        printResult(parent, graph);
    }



    
    public static void main(String[] args) throws IOException {
       
        Scanner ler = new Scanner(System.in); // Take the first line containing the total of vertices
        
        int numVert = Integer.parseInt(ler.nextLine()); 
        
        
        PBGM pbgm = new PBGM (numVert); // Initialize the object with the total number of vertices
        pbgm.createGraph();
        
        
        /*
        Loop to convert every line of the input to a Integer Array (passed to the adjacency matrix)
        */
        for (int i = 0; i < numVert; i++) {
            String string = ler.nextLine();
            String dividida[] = string.split(" ");
            pbgm.setGraphLine(dividida, i);
        
        }
        pbgm.printGraph();
        ler.close();

        // Execute the prim's algorithm and print the result
        pbgm.primMST();
    }

}
