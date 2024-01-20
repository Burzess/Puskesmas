package controller;

import model.JadwalPraktekModel;
import node.JadwalPraktek;

import java.util.List;

public class JadwalPraktekController {
    JadwalPraktekModel jadwalPraktekModel;
    public JadwalPraktekController(){
        jadwalPraktekModel = new JadwalPraktekModel();
    }

    public void tambahJadwalPraktek(String nameDokter, String hari, String jamPraktek) {
        int id = JadwalPraktekModel.getLastId();
        jadwalPraktekModel.tambahJadwalPraktek(nameDokter, hari, jamPraktek);
    }

    public void updateJadwalPraktek(int id, String namaDokter, String hari, String jamPraktek) {
        jadwalPraktekModel.updateJadwalPraktek(id, namaDokter, hari, jamPraktek);
    }

    public List<JadwalPraktek> getAllJadwalPraktek() {
        return jadwalPraktekModel.getAllJadwalPraktek();
    }
}

