package fr.better.tools.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

public class BetterJson<T> {

    private final Gson gson;

    public BetterJson() {

        gson = new GsonBuilder().setPrettyPrinting().serializeNulls().disableHtmlEscaping().create();
    }

    public T load(File file, Class<T> tClass, T tDefault){
        T t = load(file, tClass);
        if(t == null)t = tDefault;
        return t;
    }

    public T load(File file, Class<T> tClass){

        if(fileExist(file, false))return null;

        try {

            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder text = new StringBuilder();

            String line;

            while((line = reader.readLine()) != null){
                text.append(line);
            }

            reader.close();

            return gson.fromJson(text.toString(), tClass);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void save(File file, T content){

        fileExist(file, true);

        try {
            FileWriter writer = new FileWriter(file);

            writer.write(gson.toJson(content));
            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private boolean fileExist(File file, boolean createFile){

        if(!file.getParentFile().exists())file.mkdirs();
        if(!file.exists()) {

            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }
}
