package com.cyber.algo;

import java.util.*;

import static java.util.Collections.emptySet;

public class DijkstraSearch {

    final Map<String, Integer> vertexWeightMap = new HashMap<>();
    final Map<String, String> vertexParentMap = new HashMap<>();
    final Set<String> vertices = new HashSet<>();

    /*
     Поиск в ширину для заполнения весов узлов
     Найти узел с наименьшей стоимостью среди необработанных
     Перебрать соседей текущего узла
     Если к соседу проще добраться через текущий узел:
       Обновляем вес соседа
       Текущий узел становится родителем для соседа
       Отмечаем текущий узел как обработанный
     */
    public void bfs(Map<String, Set<Arc>> graph, String startNode) {
        reset(graph.keySet(), startNode);

        while (!vertices.isEmpty()) {
            String node = getNextMinimalNode();
            int currentNodeWeight = vertexWeightMap.getOrDefault(node, Integer.MAX_VALUE);

            for (Arc childArc : graph.getOrDefault(node, emptySet())) {
                int childNewWeight = currentNodeWeight + childArc.weight();
                int childActualWeight = vertexWeightMap.getOrDefault(childArc.target(), Integer.MAX_VALUE);

                if (childNewWeight < childActualWeight) {
                    vertexWeightMap.put(childArc.target(), childNewWeight);
                    vertexParentMap.put(childArc.target(), node);
                }
            }
            vertices.remove(node);
        }
    }

    private void reset(Collection<String> nodes, String startNode) {
        vertexParentMap.clear();
        vertexWeightMap.clear();
        vertices.clear();
        vertices.addAll(nodes);
        vertexWeightMap.put(startNode, 0);
    }

    private String getNextMinimalNode() {
        int value = Integer.MAX_VALUE;
        String nodeName = null;
        for (String node : vertices) {
            int nodeWeight = vertexWeightMap.getOrDefault(node, Integer.MAX_VALUE);
            if (nodeWeight < value) {
                value = nodeWeight;
                nodeName = node;
            }
        }
        return nodeName;
    }

    public Deque<String> getTargetPath(String node) {
        Deque<String> resultPath = new LinkedList<>();
        do {
            resultPath.addFirst(node);
        } while ((node = vertexParentMap.get(node)) != null);
        return resultPath;
    }

    public int getTargetWeigh(String node) {
        return vertexWeightMap.getOrDefault(node, Integer.MAX_VALUE);
    }

}
