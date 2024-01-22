package controller;

import model.ModelAntrain;
import model.ModelPasien;
import model.ModelPoli;
import model.ModelTransaksi;
import node.Antrian;
import node.Pasien;
import node.Poli;
import node.Transaksi;

import java.util.List;

public class TransaksiController {
    private ModelTransaksi modelTransaksi;
    private ModelPoli modelPoli;
    private ModelPasien modelPasien;

    public TransaksiController(){
        modelTransaksi = new ModelTransaksi();
        modelPoli = new ModelPoli();
        modelPasien = new ModelPasien();
    }

    public void addTransaksi(Antrian antrian){
        Poli poli = modelPoli.getPoli(antrian.poli);
        Pasien pasien = modelPasien.search(antrian.pasien.NIK);
        int id = modelTransaksi.getLastId();
        modelTransaksi.addTrasnsaksi(new Transaksi(id, antrian, pasien, poli));
    }

    public void addTransaksi(Antrian antrian, int total, String deskripsi){
        Poli poli = modelPoli.getPoli(antrian.poli);
        Pasien pasien = modelPasien.search(antrian.pasien.NIK);
        int id = modelTransaksi.getLastId();
        modelTransaksi.addTrasnsaksi(new Transaksi(id, antrian, pasien, poli, total, deskripsi));
    }
    public List<Transaksi> getAllTransaki(){
        return modelTransaksi.getListTransaksi();
    }

    public Transaksi getTransaksiById(int idTransaksi) {
        for (Transaksi transaksi : getAllTransaki()) {
            if (transaksi.idTransaksi == idTransaksi) {
                return transaksi;
            }
        }
        return null;
    }
}
