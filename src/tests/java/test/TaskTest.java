package test;

import com.cgvsu.model.Model;
import com.cgvsu.model.Polygon;
import com.cgvsu.math.Vector3f;
import com.cgvsu.programm.Task6;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

public class TaskTest {

    private Model testModel;
    private Polygon polygon1;
    private Polygon polygon2;
    private Polygon polygon3;
    private Polygon polygon4;
    private Polygon polygon5;
    private Polygon polygon6;

    @BeforeEach
    void setUp() {
        testModel = new Model();

        testModel.vertices.add(new Vector3f(0, 0, 0));
        testModel.vertices.add(new Vector3f(1, 0, 0));
        testModel.vertices.add(new Vector3f(1, 1, 0));
        testModel.vertices.add(new Vector3f(0, 1, 0));
        testModel.vertices.add(new Vector3f(0, 0, 1));
        testModel.vertices.add(new Vector3f(0, 1, 1));
        testModel.vertices.add(new Vector3f(1, 0, 1));
        testModel.vertices.add(new Vector3f(1, 1, 1));

        polygon1 = new Polygon();
        polygon1.setVertexIndices(new ArrayList<>(Arrays.asList(0, 1, 2, 3)));

        polygon2 = new Polygon();
        polygon2.setVertexIndices(new ArrayList<>(Arrays.asList(1, 2, 6, 7)));

        polygon3 = new Polygon();
        polygon3.setVertexIndices(new ArrayList<>(Arrays.asList(0, 1, 4, 6)));

        polygon4 = new Polygon();
        polygon4.setVertexIndices(new ArrayList<>(Arrays.asList(0, 3, 4, 5)));

        polygon5 = new Polygon();
        polygon5.setVertexIndices(new ArrayList<>(Arrays.asList(2, 3, 5, 7)));

        polygon6 = new Polygon();
        polygon6.setVertexIndices(new ArrayList<>(Arrays.asList(4, 5, 6, 7)));

        testModel.polygons.add(polygon1);
        testModel.polygons.add(polygon2);
        testModel.polygons.add(polygon3);
        testModel.polygons.add(polygon4);
        testModel.polygons.add(polygon5);
        testModel.polygons.add(polygon6);
    }

    @Test
    void testRemovePolygons_RemoveSinglePolygonWithoutVertices() {
        ArrayList<Integer> indicesToRemove = new ArrayList<>(Arrays.asList(2));

        Model result = Task6.removePolygons(testModel, indicesToRemove, false);

        assertEquals(5, result.polygons.size(), "Должно остаться 5 полигонов");
        assertEquals(8, result.vertices.size(), "Вершины не должны быть удалены");

        assertEquals(polygon1.getVertexIndices(), result.polygons.get(0).getVertexIndices());
        assertEquals(polygon3.getVertexIndices(), result.polygons.get(1).getVertexIndices());
        assertEquals(polygon4.getVertexIndices(), result.polygons.get(2).getVertexIndices());
        assertEquals(polygon5.getVertexIndices(), result.polygons.get(3).getVertexIndices());
        assertEquals(polygon6.getVertexIndices(), result.polygons.get(4).getVertexIndices());
    }

    @Test
    void testRemovePolygons_RemoveMultiplePolygonsWithoutVertices() {
        ArrayList<Integer> indicesToRemove = new ArrayList<>(Arrays.asList(1, 3));

        Model result = Task6.removePolygons(testModel, indicesToRemove, false);

        assertEquals(4, result.polygons.size(), "Должно остаться 4 полигона");

        assertEquals(polygon2.getVertexIndices(), result.polygons.get(0).getVertexIndices());
        assertEquals(polygon4.getVertexIndices(), result.polygons.get(1).getVertexIndices());
        assertEquals(polygon5.getVertexIndices(), result.polygons.get(2).getVertexIndices());
        assertEquals(polygon6.getVertexIndices(), result.polygons.get(3).getVertexIndices());
    }

    @Test
    void testRemovePolygons_RemoveAllPolygonsWithoutVertices() {
        ArrayList<Integer> indicesToRemove = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));

        Model result = Task6.removePolygons(testModel, indicesToRemove, false);

        assertEquals(0, result.polygons.size(), "Все полигоны должны быть удалены");
        assertEquals(8, result.vertices.size(), "Вершины должны остаться");
    }

    @Test
    void testRemovePolygons_RemoveSinglePolygonWithVertices() {
        ArrayList<Integer> indicesToRemove = new ArrayList<>(Arrays.asList(1));

        Model result = Task6.removePolygons(testModel, indicesToRemove, true);

        assertEquals(5, result.polygons.size(), "Должно остаться 5 полигонов");
        assertTrue(result.vertices.size() <= 8, "Неиспользуемые вершины должны быть удалены");
    }

    @Test
    void testRemovePolygons_RemoveNoPolygons() {
        ArrayList<Integer> indicesToRemove = new ArrayList<>();

        Model result = Task6.removePolygons(testModel, indicesToRemove, false);

        assertEquals(6, result.polygons.size(), "Все полигоны должны остаться");
        assertEquals(testModel.vertices.size(), result.vertices.size(), "Все вершины должны остаться");
    }

    @Test
    void testRemovePolygons_WithInvalidIndices() {
        ArrayList<Integer> indicesToRemove = new ArrayList<>(Arrays.asList(7, 10));

        Model result = Task6.removePolygons(testModel, indicesToRemove, false);

        assertEquals(6, result.polygons.size(), "Все полигоны должны остаться");
    }

    @Test
    void testRemovePolygons_RemoveMultiplePolygonsWithVertices() {
        ArrayList<Integer> indicesToRemove = new ArrayList<>(Arrays.asList(1, 3));

        Model result = Task6.removePolygons(testModel, indicesToRemove, true);

        assertEquals(4, result.polygons.size(), "Должно остаться 4 полигона");
        assertTrue(result.vertices.size() <= 8, "Неиспользуемые вершины должны быть удалены");
    }
}
