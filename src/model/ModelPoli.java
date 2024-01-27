package model;

import com.google.gson.reflect.TypeToken;
import modelGSON.ModelGSON;
import entity.Antrian;
import entity.Poli;

import java.util.ArrayList;

public class ModelPoli {
    private ArrayList<Poli> listPoli;
    private ModelGSON modelGSON;

    public ModelPoli() {
        modelGSON = new ModelGSON<Poli>("src/DB/poli.json");
        listPoli = modelGSON.readFromFile(new TypeToken<ArrayList<Poli>>() {}.getType());
        if (listPoli == null) listPoli = new ArrayList<>();
    }

    public ArrayList<Poli> getListPoli() {
        return listPoli;
    }

    public void tambahPoli(Poli poli) {
        listPoli.add(poli);
        modelGSON.writeToFile(listPoli);
    }

    public Poli searchPoli(String namaPoli){
        for (Poli poli : listPoli){
            if (poli.namaPoli.equals(namaPoli)) {
                return poli;
            }
        }

        return null;
    }

//############## ANTRIAN ##############
    public int getLastAntrian(String namaPoli){
        Poli poli = searchPoli(namaPoli);
        System.out.println(poli.namaPoli);
        int idx;
        if (poli.antrians == null){
            return 1;
        } else {
            return poli.antrians.size()+1;
        }
    }

    public Poli getPoli(String nama) {
        for (Poli poli : listPoli) {
            if (poli.namaPoli.equals(nama)) {
                return poli;
            }
        }
        return null;
    }

    public void addAntrianPoli(String namaPoli, Antrian antrian) {
        Poli poli = getPoli(namaPoli);
        listPoli.get(poli.idPoli).addAntrian(antrian);
        modelGSON.writeToFile(listPoli);
    }

    public Antrian getAntrianPoli(String namaPoli, String pasien){
        for (Poli poli : listPoli){
            if (poli.namaPoli.equals(namaPoli) && poli.antrians != null){
                for (Antrian antrian : poli.antrians){
                    if (antrian.pasien.NIK.equals(pasien)) return antrian;
                }
            }
        }
        return null;
    }

    public void deleteAntrianPoli(String poli) {
        for (Poli polis : listPoli) {
            if (polis.namaPoli.equals(poli)){
                if (!polis.antrians.isEmpty()) {
                    Antrian antrian = listPoli.get(getPoli(poli).idPoli).antrians.getFirst();
                    listPoli.get(polis.idPoli).antrians.remove(antrian);
                }
            }
        }
        modelGSON.writeToFile(listPoli);
    }

    public void addDokter(){}

}
