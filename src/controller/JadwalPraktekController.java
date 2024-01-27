package controller;

import model.JadwalPraktekModel;
import entity.JadwalPraktek;

import java.util.List;

public class JadwalPraktekController {
    JadwalPraktekModel jadwalPraktekModel;
    public JadwalPraktekController(){
        jadwalPraktekModel = new JadwalPraktekModel();
    }

    public void tambahJadwalPraktek(String nameDokter, String hari, String jamPraktek, String poli) {
        int id = JadwalPraktekModel.getLastId();
        jadwalPraktekModel.tambahJadwalPraktek(id, nameDokter, hari, jamPraktek, poli);
    }

    public void updateJadwalPraktek(int id, String namaDokter, String hari, String jamPraktek, String poli) {
        jadwalPraktekModel.updateJadwalPraktek(id, namaDokter, hari, jamPraktek, poli);
    }

    public JadwalPraktek getJadwalPraktek(String namaDokter){
        return jadwalPraktekModel.getJadwalPraktek(namaDokter);
    }

    public List<JadwalPraktek> getAllJadwalPraktek() {
        return jadwalPraktekModel.getAllJadwalPraktek();
    }
}

