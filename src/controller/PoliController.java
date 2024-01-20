package controller;

import model.ModelAntrain;
import model.ModelPoli;
import node.Antrian;
import node.Pasien;
import node.Poli;

import java.util.ArrayList;

public class PoliController {
    private ModelPoli modelPoli;
    private ModelAntrain modelAntrain;
    public PoliController() {
        this.modelPoli = new ModelPoli();
        this.modelAntrain = new ModelAntrain();
    }
    public ArrayList<Poli> getAll(){
        return modelPoli.getListPoli();
    }

    public void tambahPoli(String namaPoli){
        int idPoli = modelPoli.getListPoli().size();
        Poli poli = new Poli(idPoli, namaPoli);
        modelPoli.tambahPoli(poli);
    }

    public boolean addAtnrianPoli(String namaPoli, Pasien pasien){
        boolean status;
        int idx = modelPoli.getLastAntrian(namaPoli);
        Antrian cekAntrian = modelPoli.getAntrianPoli(namaPoli, pasien.NIK);
        if (cekAntrian != null){
            status = false;
        } else {
            Antrian antrian = new Antrian(idx, pasien, namaPoli);
            modelPoli.addAntrianPoli(namaPoli, antrian);
            modelAntrain.addAntrian(antrian);
            System.out.println("berhasil mendapatkan no antrian");
            status = true;
        }

        return status;
    }

    public Antrian getNextAntrian(String namaPoli) {
        for (Poli poli : modelPoli.getListPoli()) {
            if (poli.namaPoli.equals(namaPoli) && !poli.antrians.isEmpty()) {
                Antrian nextAntrian = poli.antrians.getFirst();
                modelPoli.deleteAntrianPoli(poli.namaPoli);
                modelAntrain.deleteAntrian(poli.namaPoli);
                return nextAntrian;
            }
        }
        return null;
    }

    public Antrian getAntrian(String namaPoli, String pasien){
        return modelPoli.getAntrianPoli(namaPoli, pasien);
    }
}
