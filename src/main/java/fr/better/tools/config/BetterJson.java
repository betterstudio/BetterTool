package fr.better.tools.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.better.tools.deprecated.Instantiaters;

import java.io.*;

public class BetterJson<T> {

    private final Gson gson;

    public BetterJson() {

        gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
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

    public T load(String name, Class<T> tClass){
        return load(new File(Instantiaters.getPlugin().getDataFolder() + "/" + name + ".json"), tClass);
    }

    public T load(String name, Class<T> tClass, T tDefault){
        T t = load(new File(Instantiaters.getPlugin().getDataFolder() + "/" + name + ".json"), tClass);
        if(t == null)t = tDefault;
        return t;
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

    public void save(String name, T content){
        save(new File(Instantiaters.getPlugin().getDataFolder() + "/" + name + ".json"), content);
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