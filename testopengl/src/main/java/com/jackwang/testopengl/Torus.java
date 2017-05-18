package com.jackwang.testopengl;

import android.content.Context;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by wangwei on 17/5/12.
 */

public class Torus {

    private List<String> verticesList;
    private List<String> facesList;

    public Torus(Context context) throws IOException {
        verticesList = new ArrayList<>();
        facesList = new ArrayList<>();

        // Open the OBJ file with a Scanner
        Scanner scanner = new Scanner(context.getAssets().open("torus.obj"));

// Loop through all its lines
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if(line.startsWith("v ")) {
                // Add vertex line to list of vertices
                verticesList.add(line);
            } else if(line.startsWith("f ")) {
                // Add face line to faces list
                facesList.add(line);
            }
        }

// Close the scanner
        scanner.close();
    }

}
