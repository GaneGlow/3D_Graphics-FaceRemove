package com.cgvsu.programm;

import com.cgvsu.math.Vector3f;
import com.cgvsu.model.Model;
import com.cgvsu.model.Polygon;

import java.util.*;

public class Task6 {

        public static Model removePolygons(Model model, ArrayList<Integer> polygonIndices, boolean removeVertices) {
            Model result = new Model();

            result.vertices = new ArrayList<>(model.vertices);

            result.polygons = new ArrayList<>();
            for (int i = 0; i < model.polygons.size(); i++) {
                if (!polygonIndices.contains(i + 1)) {
                    Polygon original = model.polygons.get(i);
                    Polygon copy = new Polygon();
                    copy.setVertexIndices(new ArrayList<>(original.getVertexIndices()));
                    result.polygons.add(copy);
                }
            }

            if (removeVertices) {
                removeVertices(result);
            }

            return result;
        }

        private static void removeVertices(Model model) {
            Set<Integer> usedVertices = new HashSet<>();

            for (Polygon poly : model.polygons) {
                usedVertices.addAll(poly.getVertexIndices());
            }

            removeExcessElements(model.vertices, usedVertices);

            updatePolygonIdx(model, usedVertices);
        }

        private static void removeExcessElements(ArrayList<Vector3f> list, Set<Integer> usedIdx) {
            ArrayList<Object> newList = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                if (usedIdx.contains(i)) {
                    newList.add(list.get(i));
                }
            }
            list.clear();
            list.addAll((ArrayList) newList);
        }

        private static void updatePolygonIdx(Model model, Set<Integer> usedVertices) {
            Map<Integer, Integer> vertexMap = createIndexMap(usedVertices);

            for (Polygon poly : model.polygons) {
                updateIndexList(poly.getVertexIndices(), vertexMap);
            }
        }

        private static Map<Integer, Integer> createIndexMap(Set<Integer> usedIndices) {
            Map<Integer, Integer> map = new HashMap<>();
            int newIndex = 0;
            List<Integer> sorted = new ArrayList<>(usedIndices);
            Collections.sort(sorted);
            for (int oldIndex : sorted) {
                map.put(oldIndex, newIndex);
                newIndex++;
            }
            return map;
        }

        private static void updateIndexList(ArrayList<Integer> indices, Map<Integer, Integer> indexMap) {
            for (int i = 0; i < indices.size(); i++) {
                int oldIndex = indices.get(i);
                if (indexMap.containsKey(oldIndex)) {
                    indices.set(i, indexMap.get(oldIndex));
                }
            }
        }

}
