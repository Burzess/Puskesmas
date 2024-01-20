package model;

import com.google.gson.reflect.TypeToken;
import modelGSON.ModelGSON;
import node.JadwalPraktek;

import java.util.ArrayList;
import java.util.List;

public class JadwalPraktekModel {
    private static List<JadwalPraktek> listJadwalPraktek;
    private ModelGSON modelGSON;
    public JadwalPraktekModel(){
        modelGSON = new ModelGSON("src/DB/jadwalPraktek.json");
        listJadwalPraktek = modelGSON.readFromFile(new TypeToken<ArrayList<JadwalPraktek>>() {}.getType());
        if (listJadwalPraktek == null) listJadwalPraktek = new ArrayList<>();
    }

    public static void init(){
        tambahJadwalPraktek("Dr. Tata","Senin", "09:00 - 12:00");
        tambahJadwalPraktek("Dr. Tita","Selasa", "09:00 - 12:00");
        tambahJadwalPraktek("Dr. Totot","Sabtu", "09:00 - 12:00");
        tambahJadwalPraktek("Dr. Tati","Minggu", "09:00 - 12:00");
        tambahJadwalPraktek("Dr. Tra","Rabu", "09:00 - 12:00");
    }
    public static void tambahJadwalPraktek(String namaDokter, String hari, String jamPraktek) {
        JadwalPraktek jadwalPraktek = new JadwalPraktek(namaDokter, hari, jamPraktek);
        listJadwalPraktek.add(jadwalPraktek);
    }

    public static List<JadwalPraktek> getAllJadwalPraktek() {
        return listJadwalPraktek;
    }

    public static void updateJadwalPraktek(int index, String namaDokter, String hari, String jamPraktek) {
        if (index >= 0 && index < listJadwalPraktek.size()) {
            JadwalPraktek jadwalPraktek = listJadwalPraktek.get(index);
            jadwalPraktek.setNamaDokter(namaDokter);
            jadwalPraktek.setHari(hari);
            jadwalPraktek.setJamPraktek(jamPraktek);
        }
    }

    public static int getLastId() {
        if (listJadwalPraktek.isEmpty()) return 0;
        return listJadwalPraktek.size();
    }
}
