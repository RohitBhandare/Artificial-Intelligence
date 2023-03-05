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
