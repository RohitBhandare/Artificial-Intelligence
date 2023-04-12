package AI_Practicals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
        queue.add(0);
        while (!queue.isEmpty()) {
            int curr = queue.remove();
            if (visited[curr] == false) {
                visited[curr] = true;
                bfs.add(curr);
                if (curr == goalNode) {
                    System.out.println("\n\nGoal State " + curr + " Found !!!!");
                    break;
                }
                for (int i = 0; i < list.get(curr).size(); i++) {
                    int vertex = list.get(curr).get(i).vertex;
                    if (!visited[vertex]) {
                        queue.add(vertex);
                    }
                }
            }

            System.out.println();
            System.out.print("Visited List: [ ");
            for (int i = 0; i < n; i++) {
                if (visited[i]) {
                    System.out.print(i + " ");
                }
            }
            System.out.print("] ");

            System.out.print("\tFringe List: [");
            for (int vertex : queue) {
                System.out.print(vertex + " ");
            }
            System.out.print("] ");
        }
        return bfs;

    }

    void DFS(ArrayList<ArrayList<Node>> list, boolean visited[], int src) {

        if (visited[src] == false) {
            visited[src] = true;
            System.out.print(" " + src);

            for (int i = 0; i < list.get(src).size(); i++) {
                Node n = list.get(src).get(i);
                DFS(list, visited, n.vertex);
            }
        }

    }
}

public class BFS {
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

        for (int i = 0; i < noOfNodes; i++) {
            System.out.println(i + " --> " + list.get(i));
        }
        boolean[] visited = new boolean[noOfNodes];
        Graph graph = new Graph();
        List<Integer> bfs = new ArrayList<>();
        int goalNode = 3;
        bfs = graph.BFS(list, noOfNodes, visited, bfs, goalNode);
        System.out.println("\nBFS: " + bfs.toString());
        boolean[] visited1 = new boolean[noOfNodes];
        System.out.println();
        System.out.print("DFS: ");
        graph.DFS(list, visited1, 0);

    }

}

/*
 * OUTPUT
 * 0 --> [[ 1, 5], [ 2, 8]]
 * 1 --> [[ 0, 5], [ 3, 2], [ 2, 9]]
 * 2 --> [[ 0, 8], [ 1, 9], [ 3, 6]]
 * 3 --> [[ 1, 2], [ 2, 6]]
 * 
 * Visited List: [ 0 ] Fringe List: [1 2 ]
 * Visited List: [ 0 1 ] Fringe List: [2 3 2 ]
 * Visited List: [ 0 1 2 ] Fringe List: [3 2 3 ]
 * 
 * Goal State 3 Found !!!!
 * 
 * BFS: [0, 1, 2, 3]
 * 
 * DFS: 0 1 3 2
 */
