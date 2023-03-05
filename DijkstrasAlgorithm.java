package AI_Practicals.Dijkstra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

class Node implements Comparator<Node> {
    int vertex;
    int cost;

    Node() {

    }

    public Node(int vertex, int cost) {
        this.vertex = vertex;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "[ " + vertex + ", " + cost + "]";
    }

    @Override
    public int compare(Node o1, Node o2) {
        if (o1.cost < o2.cost)
            return -1;

        if (o1.cost > o2.cost)
            return 1;
        return 0;
    }

}

public class Dijkstra {

    static void shortestPath(int src, ArrayList<ArrayList<Node>> list, int noOfNodes) {
        int path[] = new int[noOfNodes];
        int dis[] = new int[noOfNodes];
        for (int i = 0; i < noOfNodes; i++) {
            dis[i] = Integer.MAX_VALUE; // initial infinite value
        }
        dis[src] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>(noOfNodes, new Node());
        pq.add(new Node(src, 0));

        int p = 0;
        while (pq.size() != 0) {
            Node node = pq.remove();
            for (Node n : list.get(node.vertex)) {
                if (dis[node.vertex] + n.cost < dis[n.vertex]) {
                    dis[n.vertex] = dis[node.vertex] + n.cost;
                    pq.add(new Node(n.vertex, dis[n.vertex]));
                }
            }
        }

        int i = 0;
        for (int a : dis) {
            System.out.println(0 + " --> " + i + " Min Cost is: " + a);
            i++;
        }

    }

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
        shortestPath(0, list, noOfNodes);

    }

}
