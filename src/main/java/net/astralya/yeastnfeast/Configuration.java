package net.astralya.yeastnfeast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Configuration {

    // --- Functional Blocks ---
    public int treeTapDripDelay = 7200;

    private static final int MIN_DRIP = 20;
    private static final int MAX_DRIP = 24000;

    private static final Path CONFIG_PATH = FabricLoader.getInstance()
            .getConfigDir().resolve("yeastnfeast-common.json");

    private static Configuration INSTANCE;

    public static Configuration get() {
        if (INSTANCE == null) load();
        return INSTANCE;
    }

    public static void load() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            if (Files.exists(CONFIG_PATH)) {
                INSTANCE = gson.fromJson(Files.readString(CONFIG_PATH), Configuration.class);
                if (INSTANCE == null) {
                    INSTANCE = new Configuration();
                    INSTANCE.validate();
                    save();
                } else {
                    INSTANCE.validate();
                }
            } else {
                INSTANCE = new Configuration();
                INSTANCE.validate();
                save();
            }
        } catch (IOException e) {
            e.printStackTrace();
            INSTANCE = new Configuration();
            INSTANCE.validate();
        }
    }

    public static void save() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            Files.writeString(CONFIG_PATH, gson.toJson(get()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void validate() {
        if (treeTapDripDelay < MIN_DRIP) treeTapDripDelay = MIN_DRIP;
        if (treeTapDripDelay > MAX_DRIP) treeTapDripDelay = MAX_DRIP;
    }
}