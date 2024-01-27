package model;

import com.google.gson.reflect.TypeToken;
import modelGSON.ModelGSON;
import entity.Antrian;

import java.util.ArrayList;

public class ModelAntrain {
    private ArrayList<Antrian> listAntrain;
    private ModelGSON modelGSON;

    public ModelAntrain(){
        modelGSON = new ModelGSON<Antrian>("src/DB/antrain.json");
        listAntrain = modelGSON.readFromFile(new TypeToken<ArrayList<Antrian>>() {}.getType());
        if (listAntrain == null) listAntrain = new ArrayList<>();
    }

    public void addAntrian(Antrian antri){
        listAntrain.add(antri);
        modelGSON.writeToFile(listAntrain);
    }

    public void deleteAntrian(String namaPoli){
        if (listAntrain == null){
            return;
        }

        for (Antrian antrian : listAntrain) {
            if (antrian.poli.equals(namaPoli)) {
                listAntrain.remove(antrian);
                modelGSON.writeToFile(listAntrain);
                return;
            }
        }
    }
}
