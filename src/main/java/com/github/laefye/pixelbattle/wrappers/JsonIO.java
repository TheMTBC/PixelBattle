package com.github.laefye.pixelbattle.wrappers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class JsonIO {
    public static void save(JsonObject jsonObject, String filename) {
        try {
            var output = new FileOutputStream(filename);
            output.write(jsonObject.toString().getBytes(StandardCharsets.UTF_8));
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static JsonObject load(String filename) {
        if (!new File(filename).exists()) {
            return null;
        }
        try {
            var reader = new FileReader(filename);
            var jsonObject = JsonParser.parseReader(reader);
            if (jsonObject == null || !jsonObject.isJsonObject()) {
                return null;
            }
            reader.close();
            return jsonObject.getAsJsonObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
