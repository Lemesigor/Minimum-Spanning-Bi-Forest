
package pbgm;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author igor lemes
 */
public class PBGM {
private int numeroVertices = 5; 
private int custoFinal = 0;
private int maiorValor = 0;


public PBGM ( int v){
    this.numeroVertices = v;
}
  
    // A utility function to find the vertex with minimum key 
    // value, from the set of vertices not yet included in MST 
    int minKey(int key[], Boolean mstSet[]) 
    { 
        // Initialize min value 
        int min = Integer.MAX_VALUE, min_index = -1; 
  
        for (int v = 0; v < numeroVertices; v++) 
            if (mstSet[v] == false && key[v] < min) { 
                min = key[v]; 
                min_index = v; 
            } 
  
        return min_index; 
    } 
  
    // A utility function to print the constructed MST stored in 
    // parent[] 
    void printMST(int parent[], int graph[][]) {
        System.out.println("Edge \tWeight");
        for (int i = 1; i < numeroVertices; i++) {

            this.custoFinal += graph[i][parent[i]];
            if (this.maiorValor < graph[i][parent[i]]) {
                this.maiorValor = graph[i][parent[i]];
            }
            
            System.out.println(parent[i] + " - " + i + "\t" + graph[i][parent[i]]);
        }
        System.out.println("Maior valor encontrado é: " + this.maiorValor + " -- " + "Custo total: " + this.custoFinal);
    }
  
    // Function to construct and print MST for a graph represented 
    // using adjacency matrix representation 
    void primMST(int graph[][]) 
    { 
        // Array to store constructed MST 
        int parent[] = new int[numeroVertices]; 
  
        // Key values used to pick minimum weight edge in cut 
        int key[] = new int[numeroVertices]; 
  
        // To represent set of vertices not yet included in MST 
        Boolean mstSet[] = new Boolean[numeroVertices]; 
  
        // Initialize all keys as INFINITE 
        for (int i = 0; i < numeroVertices; i++) { 
            key[i] = Integer.MAX_VALUE; 
            mstSet[i] = false; 
        } 
  
        // Always include first 1st vertex in MST. 
        key[0] = 0; // Make key 0 so that this vertex is 
        // picked as first vertex 
        parent[0] = -1; // First node is always root of MST 
  
        // The MST will have V vertices 
        for (int count = 0; count < numeroVertices - 1; count++) { 
            // Pick thd minimum key vertex from the set of vertices 
            // not yet included in MST 
            int u = minKey(key, mstSet); 
  
            // Add the picked vertex to the MST Set 
            mstSet[u] = true; 
  
            // Update key value and parent index of the adjacent 
            // vertices of the picked vertex. Consider only those 
            // vertices which are not yet included in MST 
            for (int v = 0; v < numeroVertices; v++) 
  
                // graph[u][v] is non zero only for adjacent vertices of m 
                // mstSet[v] is false for vertices not yet included in MST 
                // Update the key only if graph[u][v] is smaller than key[v] 
                if (graph[u][v] != 0 && mstSet[v] == false && graph[u][v] < key[v]) { 
                    parent[v] = u; 
                    key[v] = graph[u][v]; 
                } 
        } 
  
        // print the constructed MST 
        printMST(parent, graph); 
    } 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        PBGM t = new PBGM(5); 
        int graph[][] = new int[][] { { 0, 2, 0, 6, 0 }, 
                                      { 2, 0, 3, 8, 5 }, 
                                      { 0, 3, 0, 0, 7 }, 
                                      { 6, 8, 0, 0, 9 }, 
                                      { 0, 5, 7, 9, 0 } }; 
        
        System.out.println(graph.length);
        
        int numVert;

        Scanner ler = new Scanner(System.in); // pega o número de vértices
        numVert = Integer.parseInt(ler.nextLine());
            
        
        
        
        
		
        for (int i = 0; i < numVert; i++) {
			         System.out.println(ler.nextLine());
		}

		ler.close();    
  
        // Print the solution 
        t.primMST(graph); 
    }
    
}
