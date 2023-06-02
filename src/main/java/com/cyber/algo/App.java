package com.cyber.algo;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class App {

    public static void main(String[] args) {
        DijkstraSearch dijkstraSearch = new DijkstraSearch();
        Map<String, Set<Arc>> graph = initGraph();

        String startNode = "a";
        dijkstraSearch.bfs(graph, startNode);

        String targetNode = "d";
        System.out.printf("Итоговый путь к '%s' (%d): %s%n",
                targetNode,
                dijkstraSearch.getTargetWeigh(targetNode),
                dijkstraSearch.getTargetPath(targetNode)
        );

    }

    /*
       3 - B - 6
      /    |    \
    A      1     D
      \    |    /
       5 - С - 3
     */
    private static Map<String, Set<Arc>> initGraph() {
        Map<String, Set<Arc>> arcMap = new HashMap<>();
        addRib(arcMap, "a", "b", 3);
        addRib(arcMap, "a", "c", 5);
        addRib(arcMap, "b", "d", 6);
        addRib(arcMap, "b", "c", 1);
        addRib(arcMap, "c", "d", 3);
        return arcMap;
    }

    private static void addRib(Map<String, Set<Arc>> arcMap, String startNode, String targetNode, int weight) {
        arcMap.computeIfAbsent(startNode, k -> new TreeSet<>()).add(new Arc(targetNode, weight));
        arcMap.computeIfAbsent(targetNode, k -> new TreeSet<>()).add(new Arc(startNode, weight));
    }

}
