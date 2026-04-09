package com.solvd.university.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class FileWordCounter {

    public static void countSpecialWords(String inputPath, String outputPath) throws IOException {

        File inputFile = new File(inputPath);
        String content = FileUtils.readFileToString(inputFile, StandardCharsets.UTF_8);

        String[] words = StringUtils.split(content);

        int count = 0;

        for (String word : words) {
            // ejemplo: contar palabras largas (+7 letras)
            if (word.length() > 7) {
                count++;
            }
        }

        String result = "File: " + inputFile.getName()
                + " | Special words (>7 letters): " + count + "\n";

        File outputFile = new File(outputPath);

        // append = true → NO borra lo anterior
        FileUtils.writeStringToFile(outputFile, result, StandardCharsets.UTF_8, true);
    }
}