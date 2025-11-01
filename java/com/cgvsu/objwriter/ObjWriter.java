package com.cgvsu.objwriter;

import com.cgvsu.model.Model;
import com.cgvsu.model.Polygon;
import com.cgvsu.math.Vector2f;
import com.cgvsu.math.Vector3f;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class ObjWriter {

    public static void write(String filePath, Model model) throws IOException {
        if (model == null) {
            throw new IllegalArgumentException("Model cannot be null");
        }

        if (model.vertices.isEmpty()) {
            throw new IllegalArgumentException("Model must contain at least one vertex");
        }

        if (model.polygons.isEmpty()) {
            throw new IllegalArgumentException("Model must contain at least one polygon");
        }

        StringBuilder content = new StringBuilder();

        // Write vertices
        for (Vector3f vertex : model.vertices) {
            content.append(String.format("v %.6f %.6f %.6f\n", vertex.x, vertex.y, vertex.z));
        }

        // Write texture vertices if they exist
        if (!model.textureVertices.isEmpty()) {
            content.append("\n");
            for (Vector2f texVertex : model.textureVertices) {
                content.append(String.format("vt %.6f %.6f\n", texVertex.x, texVertex.y));
            }
        }

        // Write normals if they exist
        if (!model.normals.isEmpty()) {
            content.append("\n");
            for (Vector3f normal : model.normals) {
                content.append(String.format("vn %.6f %.6f %.6f\n", normal.x, normal.y, normal.z));
            }
        }

        // Write polygons
        content.append("\n");
        for (Polygon polygon : model.polygons) {
            content.append("f ");
            ArrayList<Integer> vertexIndices = polygon.getVertexIndices();
            ArrayList<Integer> textureIndices = polygon.getTextureVertexIndices();
            ArrayList<Integer> normalIndices = polygon.getNormalIndices();

            boolean hasTexture = !textureIndices.isEmpty() && textureIndices.size() == vertexIndices.size();
            boolean hasNormals = !normalIndices.isEmpty() && normalIndices.size() == vertexIndices.size();

            for (int i = 0; i < vertexIndices.size(); i++) {
                int vertexIndex = vertexIndices.get(i) + 1; // OBJ indices start from 1

                if (hasTexture && hasNormals) {
                    int textureIndex = textureIndices.get(i) + 1;
                    int normalIndex = normalIndices.get(i) + 1;
                    content.append(String.format("%d/%d/%d", vertexIndex, textureIndex, normalIndex));
                } else if (hasTexture) {
                    int textureIndex = textureIndices.get(i) + 1;
                    content.append(String.format("%d/%d", vertexIndex, textureIndex));
                } else if (hasNormals) {
                    int normalIndex = normalIndices.get(i) + 1;
                    content.append(String.format("%d//%d", vertexIndex, normalIndex));
                } else {
                    content.append(vertexIndex);
                }

                if (i < vertexIndices.size() - 1) {
                    content.append(" ");
                }
            }
            content.append("\n");
        }

        // Write to file
        Files.writeString(Path.of(filePath), content.toString());
    }

    // Overloaded method for convenience
    public static void write(Path filePath, Model model) throws IOException {
        write(filePath.toString(), model);
    }
}