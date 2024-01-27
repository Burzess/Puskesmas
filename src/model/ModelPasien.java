package model;

import com.google.gson.reflect.TypeToken;
import modelGSON.ModelGSON;
import entity.Pasien;

import java.util.ArrayList;

public class ModelPasien {
    private ArrayList<Pasien> listPaien;
    private ModelGSON modelGSON;

    public ModelPasien() {
        modelGSON = new ModelGSON<Pasien>("src/DB/pasien.json");
        listPaien = modelGSON.readFromFile(new TypeToken<ArrayList<Pasien>>() {
        }.getType());

        if (listPaien == null) listPaien = new ArrayList<>();
    }

    public ArrayList<Pasien> getListPaien() {
        return listPaien;
    }

    public void tambahPasien(Pasien pasien) {
        listPaien.add(pasien);
        modelGSON.writeToFile(listPaien);
    }

    public Pasien search(String target) {
        for (Pasien pasien : listPaien) {
            if (pasien.NIK.equals(target) || pasien.BPJS.equals(target)) {
                System.out.println(pasien.namaPasien);
                return pasien;
            }
        }

        return null;
    }
}
