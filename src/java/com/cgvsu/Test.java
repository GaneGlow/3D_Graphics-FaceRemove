package com.cgvsu;


import com.cgvsu.math.Vector3f;
import com.cgvsu.model.Model;
import com.cgvsu.objreader.ObjReader;
import com.cgvsu.objwriter.ObjWriter;
import com.cgvsu.programm.Task6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class Test {
    public static void main(String[] args) throws IOException {
        System.out.println("##Удаление полигонов из первой модели caracal_cube##");

        System.out.println("1. Чтение модели из файла...");
        Path firstFile = Path.of("C:/Users/0/vsu/3D_Graphics-FaceRemove/models/caracal_cube.obj");
        String strForFirstFile = Files.readString(firstFile);
        Model firstModel = ObjReader.read(strForFirstFile);

        System.out.println("\nКоличество полигонов в исходной модели: " + firstModel.getPolygons().size());
        System.out.println("Количество вершин в исходной модели: " + firstModel.getVertices().size() + "\n");

        ArrayList<Integer> indexes1 = new ArrayList<>(Arrays.asList(2, 3, 6));

        System.out.println("2. Удаление полигонов из модели caracal_cube...");
        Model resultForFirstModel = Task6.removePolygons(firstModel, indexes1, true);

        System.out.println("\nКоличество полигонов в изменённой модели: " + resultForFirstModel.getPolygons().size());
        System.out.println("Количество вершин в изменённой модели: " + resultForFirstModel.getVertices().size() + "\n");

        System.out.println("3. Сохранение модели в файл caracal_cubeResult.obj...");
        ObjWriter.write(resultForFirstModel,"C:/Users/0/vsu/3D_Graphics-FaceRemove/models/caracal_cubeResult.obj");

        System.out.println("Готово!\n");

        System.out.println("-----------------------------------------------\n");

        System.out.println("##Удаление полигонов из второй модели WrapHead##");

        System.out.println("1. Чтение модели из файла...");
        Path secondFile = Path.of("C:/Users/0/vsu/3D_Graphics-FaceRemove/models/WrapHead.obj");
        String strForSecondFile = Files.readString(secondFile);
        Model secondModel = ObjReader.read(strForSecondFile);

        System.out.println("\nКоличество полигонов в исходной модели: " + secondModel.getPolygons().size());
        System.out.println("Количество вершин в исходной модели: " + secondModel.getVertices().size() + "\n");

        ArrayList<Integer> indexes2 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15));

        System.out.println("2. Удаление полигонов из модели WrapHead...");
        Model resultForSecondModel = Task6.removePolygons(secondModel, indexes2, true);

        System.out.println("\nКоличество полигонов в изменённой модели: " + resultForSecondModel.getPolygons().size());
        System.out.println("Количество вершин в изменённой модели: " + resultForSecondModel.getVertices().size() + "\n");

        System.out.println("3. Сохранение модели в файл WrapHeadResult.obj...");
        ObjWriter.write(resultForSecondModel,"C:/Users/0/vsu/3D_Graphics-FaceRemove/models/WrapHeadResult.obj");

        System.out.println("Готово!");
    }
}
