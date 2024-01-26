package model;

import com.google.gson.reflect.TypeToken;
import modelGSON.ModelGSON;
import node.JadwalPraktek;

import java.util.ArrayList;
import java.util.List;

public class JadwalPraktekModel {
    private static ArrayList<JadwalPraktek> listJadwalPraktek;
    private final ModelGSON modelGSON;
    public JadwalPraktekModel(){
        modelGSON = new ModelGSON("src/DB/jadwalPraktek.json");
        listJadwalPraktek = modelGSON.readFromFile(new TypeToken<ArrayList<JadwalPraktek>>() {}.getType());
        if (listJadwalPraktek == null) listJadwalPraktek = new ArrayList<>();
    }
    public void tambahJadwalPraktek(int id, String namaDokter, String hari, String jamPraktek, String poli) {
        JadwalPraktek jadwalPraktek = new JadwalPraktek(id, namaDokter, hari, jamPraktek, poli);
        listJadwalPraktek.add(jadwalPraktek);
        modelGSON.writeToFile(listJadwalPraktek);
    }

    public ArrayList<JadwalPraktek> getAllJadwalPraktek() {
        return listJadwalPraktek;
    }

    public void updateJadwalPraktek(int index, String namaDokter, String hari, String jamPraktek, String poli) {
        if (index >= 0 && index < listJadwalPraktek.size()) {
            JadwalPraktek jadwalPraktek = listJadwalPraktek.get(index);
            jadwalPraktek.setNamaDokter(namaDokter);
            jadwalPraktek.setHari(hari);
            jadwalPraktek.setJamPraktek(jamPraktek);
            jadwalPraktek.setPoli(poli);
        }
        modelGSON.writeToFile(listJadwalPraktek);
    }

    public JadwalPraktek getJadwalPraktek(String namaDokter){
        for (JadwalPraktek jadwalPraktek : listJadwalPraktek) {
            if (jadwalPraktek.getNamaDokter().equals(namaDokter)) {
                return jadwalPraktek;
            }
        }
        return null;
    }

    public static int getLastId() {
        if (listJadwalPraktek == null) return 0;
        return listJadwalPraktek.size();
    }
}
