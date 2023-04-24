package AI_Practicals.Final;
import java.util.*;
class Node {
    int vertex;
    int cost;

    public Node(int vertex, int cost) {
        this.vertex = vertex;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "[ " + vertex + ", " + cost + "]";
    }

}

class Graph {

    List<Integer> BFS(ArrayList<ArrayList<Node>> list, int n, boolean visited[], List<Integer> bfs, int goalNode) {
        Queue<Integer> queue = new LinkedList<>();
        ArrayList<Integer> visitedList = new ArrayList<>();
        ArrayList<Integer> fringe = new ArrayList<>();
        queue.add(0);
        while (!queue.isEmpty()) {
            int curr = queue.remove();
            if (visited[curr] == false) {
                visited[curr] = true;
                bfs.add(curr);
                visitedList.add(curr);

                for (int i = 0; i < list.get(curr).size(); i++) {
                    int vertex = list.get(curr).get(i).vertex;
                    if (!visited[vertex]) {
                        queue.add(vertex);
                        if(!fringe.contains(vertex))
                            fringe.add(vertex);
                    }
                }
            }
          
            System.out.print("Visited List: " + visitedList);
            System.out.print("\t\tFringe Queue: " + fringe);
            System.out.println();
            if(!fringe.isEmpty())
                fringe.remove(0);
            
            if (curr == goalNode) {
                System.out.println("\n\nGoal State " + curr + " Found !!!!");
                break;
            }
        }
        return bfs;

    }

    void DFS(ArrayList<ArrayList<Node>> list, boolean visited[], int src, int goalNode) {
        Stack<Integer> stack = new Stack<>();
        stack.push(src);
    
        ArrayList<Integer> visitedList = new ArrayList<>();
        ArrayList<Integer> fringe = new ArrayList<>();
        fringe.add(src);
    
        while (!stack.empty()) {
            int current = stack.pop();
            fringe.remove(fringe.indexOf(current));
            if (!visited[current]) {
                visited[current] = true;
                visitedList.add(current);
              //  System.out.print(" " + current);
    
                for (int i = list.get(current).size() - 1; i >= 0; i--) {
                    Node n = list.get(current).get(i);
                    int next = n.vertex;
                    if (!visited[next] && !fringe.contains(next)) {
                        stack.push(next);
                        fringe.add(next);
                    }
                }
                
            }
            System.out.print("Visited List: " + visitedList);
            System.out.print("\tFringe Stack: " + fringe);
            System.out.println();

            if (current == goalNode) {
                System.out.println("\nGoal State " + current + " Found !!!!");
               
                return;
            }
        }
    }
    
}

public class BFS_DFS {
    public static void main(String[] args) {

        int noOfNodes = 4;

        ArrayList<ArrayList<Node>> list = new ArrayList<>();

        for (int i = 0; i < noOfNodes; i++)
            list.add(new ArrayList<>());

        list.get(0).add(new Node(1, 5));
        list.get(0).add(new Node(2, 8));

        list.get(1).add(new Node(0, 5));
        list.get(1).add(new Node(3, 2));
        list.get(1).add(new Node(2, 9));

        list.get(2).add(new Node(0, 8));
        list.get(2).add(new Node(1, 9));
        list.get(2).add(new Node(3, 6));

        list.get(3).add(new Node(1, 2));
        list.get(3).add(new Node(2, 6));

        System.out.println("Graph Structure: \n");
        for (int i = 0; i < noOfNodes; i++) {
            System.out.println(i + " --> " + list.get(i));
        }
        boolean[] visited = new boolean[noOfNodes];
        Graph graph = new Graph();
        List<Integer> bfs = new ArrayList<>();
        int goalNode = 3;
        System.out.print("\n\n********************* BFS ***************** \n");
        bfs = graph.BFS(list, noOfNodes, visited, bfs, goalNode);
        System.out.println("\nBFS: " + bfs.toString());
        boolean[] visited1 = new boolean[noOfNodes];
        System.out.println();
        System.out.print("********************* DFS ***************** \n\n");
        graph.DFS(list, visited1, 0, goalNode);

    }

}

/*
 * OUTPUT
Graph Structure: 

0 --> [[ 1, 5], [ 2, 8]]
1 --> [[ 0, 5], [ 3, 2], [ 2, 9]]
2 --> [[ 0, 8], [ 1, 9], [ 3, 6]]
3 --> [[ 1, 2], [ 2, 6]]


********************* BFS ***************** 
Visited List: [0]               Fringe Queue: [1, 2]
Visited List: [0, 1]            Fringe Queue: [2, 3]
Visited List: [0, 1, 2]         Fringe Queue: [3]
Visited List: [0, 1, 2, 3]              Fringe Queue: []


Goal State 3 Found !!!!

BFS: [0, 1, 2, 3]

********************* DFS *****************

Visited List: [0]       Fringe Stack: [2, 1]
Visited List: [0, 1]    Fringe Stack: [2, 3]
Visited List: [0, 1, 3] Fringe Stack: [2]

Goal State 3 Found !!!!
 */
