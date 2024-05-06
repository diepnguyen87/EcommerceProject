package test_data.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DataObjectBuilder {

    public static <T> T buildDataObject(String JsonFilePath, Class<T> dataType) {
        T returnedData = null;
        String absoluteJsonFilePath = System.getProperty("user.dir").concat(JsonFilePath);

        try (Reader reader = Files.newBufferedReader(Paths.get(absoluteJsonFilePath))) {
            Gson gson = new Gson();
            returnedData = gson.fromJson(reader, dataType);
        } catch (IOException | NullPointerException e) {
            throw new RuntimeException(e.getMessage());
        }

        return returnedData;
    }

    public static <T> T buildDataObject(String JsonFilePath, TypeToken<T> dataTypeToken) {
        T returnedData = null;
        String absoluteJsonFilePath = System.getProperty("user.dir").concat(JsonFilePath);

        try (Reader reader = Files.newBufferedReader(Paths.get(absoluteJsonFilePath))) {
            Gson gson = new Gson();
            returnedData = gson.fromJson(reader, dataTypeToken.getType());
        } catch (IOException | NullPointerException e) {
            throw new RuntimeException(e.getMessage());
        }

        return returnedData;
    }
}
