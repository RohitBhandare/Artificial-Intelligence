package AI_Practicals.AStar;

import java.util.*;

public class AStar {

    static class Edge {
        int destination;
        int weight;

        public Edge(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }

    static class Node implements Comparable<Node> {
        int vertex;
        int costSoFar;
        int estimatedTotalCost;

        public Node(int vertex, int costSoFar, int estimatedTotalCost) {
            this.vertex = vertex;
            this.costSoFar = costSoFar;
            this.estimatedTotalCost = estimatedTotalCost;
            System.out.println(estimatedTotalCost);
        }

        public int compareTo(Node other) {
            return Integer.compare(this.costSoFar + this.estimatedTotalCost,
                    other.costSoFar + other.estimatedTotalCost);
        }
    }

    public static void main(String[] args) {
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            graph.add(new ArrayList<>());
        }
        graph.get(0).add(new Edge(1, 5));
        graph.get(0).add(new Edge(2, 8));

        graph.get(1).add(new Edge(0, 5));
        graph.get(1).add(new Edge(3, 2));
        graph.get(1).add(new Edge(2, 9));

        graph.get(2).add(new Edge(0, 8));
        graph.get(2).add(new Edge(1, 9));
        graph.get(2).add(new Edge(3, 6));

        graph.get(3).add(new Edge(1, 2));
        graph.get(3).add(new Edge(2, 6));
        int start = 0, goal = 4;
        int[] distances = aStar(graph, start, goal);
        System.out.println(Arrays.toString(distances));
    }

    public static int[] aStar(List<List<Edge>> graph, int start, int goal) {
        int n = graph.size();
        int[] distances = new int[n];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[start] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>(n);
        pq.add(new Node(start, 0, heuristic(start, goal)));
        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            int u = curr.vertex;
            int costSoFar = curr.costSoFar;
            if (u == goal) {
                break;
            }
            for (Edge edge : graph.get(u)) {
                int v = edge.destination;
                int weight = edge.weight;
                int newCost = costSoFar + weight;
                if (newCost < distances[v]) {
                    distances[v] = newCost;
                    pq.add(new Node(v, newCost, heuristic(v, goal)));
                }
            }
        }
        return distances;
    }

    public static int heuristic(int u, int goal) {
        // Use Manhattan distance as the heuristic function
        int x1 = u % 3, y1 = u / 3;
        int x2 = goal % 3, y2 = goal / 3;
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}

/*
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

class Node implements Comparable<Node> {
    int vertex;
    int cost;
    int heuristic; // heuristic value for A*

    Node() {

    }

    public Node(int vertex, int cost, int heuristic) {
        this.vertex = vertex;
        this.cost = cost;
        this.heuristic = heuristic;
    }

    @Override
    public String toString() {
        return "[ " + vertex + ", " + cost + ", " + heuristic + "]";
    }

    @Override
    public int compareTo(Node o) {
        int f1 = cost + heuristic;
        int f2 = o.cost + o.heuristic;
        if (f1 < f2)
            return -1;
        else if (f1 > f2)
            return 1;
        else
            return 0;
    }
}

public class AStar {

    static void shortestPath(int src, ArrayList<ArrayList<Node>> list, int noOfNodes, int dest) {
        int path[] = new int[noOfNodes];
        int dis[] = new int[noOfNodes];
        int heuristic[] = { 4, 2, 4, 0 }; // heuristic values for nodes
        for (int i = 0; i < noOfNodes; i++) {
            dis[i] = Integer.MAX_VALUE; // initial infinite value
            path[i] = -1; // path not determined yet
        }
        dis[src] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(src, 0, heuristic[src]));
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (node.vertex == dest) {
                break; // destination node found
            }
            for (Node n : list.get(node.vertex)) {
                int newCost = dis[node.vertex] + n.cost;
                if (newCost < dis[n.vertex]) {
                    dis[n.vertex] = newCost;
                    path[n.vertex] = node.vertex; // update path
                    pq.add(new Node(n.vertex, newCost, heuristic[n.vertex]));
                }
            }
        }

        int i = 0;
        for (int a : dis) {
            System.out.println(src + " --> " + i + " Total Cost is: " + a);
            i++;
        }

        i = 0;
        System.out.print("Shortest Path: ");
        while (dest != src) {
            System.out.print(dest + " <-- ");
            dest = path[dest];
        }
        System.out.print(src);
    }

    public static void main(String[] args) {
        int noOfNodes = 4;
        ArrayList<ArrayList<Node>> list = new ArrayList<>();
        for (int i = 0; i < noOfNodes; i++)
            list.add(new ArrayList<>());
        list.get(0).add(new Node(1, 5, 4));
        list.get(0).add(new Node(2, 1, 4));

        list.get(1).add(new Node(0, 5, 2));
        list.get(1).add(new Node(3, 2, 0));
        list.get(1).add(new Node(2, 9, 4));

        list.get(2).add(new Node(0, 8, 2));
        list.get(2).add(new Node(1, 9, 0));
        list.get(2).add(new Node(3, 6, 0));
        
        list.get(3).add(new Node(1, 2,0));
        list.get(3).add(new Node(2, 6,0));
        
        for (int i = 0; i < noOfNodes; i++) {
            System.out.println(i + " --> " + list.get(i));
        }
        shortestPath(0, list, noOfNodes,3);
    }
}
*/
