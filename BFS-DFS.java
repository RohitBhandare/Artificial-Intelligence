package AI_Practicals;

import java.util.ArrayList;
import java.util.LinkedList;
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
    void BFS(ArrayList<ArrayList<Node>> list, int n, boolean visited[]) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        System.out.print("BFS: ");
        while (!queue.isEmpty()) {
            int curr = queue.remove();
            if (visited[curr] == false) {
                visited[curr] = true;
                System.out.print(" " + curr);

                for (int i = 0; i < list.get(curr).size(); i++) {
                    queue.add(list.get(curr).get(i).vertex);
                }
            }
        }
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
        graph.BFS(list, noOfNodes, visited);
        boolean[] visited1 = new boolean[noOfNodes];
        System.out.println();
        System.out.print("DFS: ");
        graph.DFS(list, visited1, 0);

    }

}
