package controller;

import model.ModelPasien;
import node.Pasien;

public class PasienController {
    private ModelPasien modelPasien;

    public PasienController(){
        this.modelPasien = new ModelPasien();
    }

    public void tambahPasien(String namaPasien, String alamat, String gender, String TTL, String NIK, String BPJS) {
        int idPasien = modelPasien.getListPaien().size() + 1;
        modelPasien.tambahPasien(new Pasien(idPasien,  namaPasien, alamat, gender, TTL,  NIK, BPJS));
    }

    public Pasien getPasien(String target) {
        return modelPasien.search(target);
    }
}
