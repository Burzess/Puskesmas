package controller;

import model.JadwalPraktekModel;
import node.JadwalPraktek;

import java.util.List;

public class JadwalPraktekController {

    public void tambahJadwalPraktek(String nameDokter, String hari, String jamPraktek) {
        int id = JadwalPraktekModel.getLastId() + 1;
        JadwalPraktekModel.tambahJadwalPraktek(nameDokter, hari, jamPraktek);
    }

    public void updateJadwalPraktek(int id, String namaDokter, String hari, String jamPraktek) {
        JadwalPraktekModel.updateJadwalPraktek(id, namaDokter, hari, jamPraktek);
    }

    public List<JadwalPraktek> getAllJadwalPraktek() {
        return JadwalPraktekModel.getAllJadwalPraktek();
    }
}

