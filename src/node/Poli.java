package node;

import java.util.ArrayList;

public class Poli {
    public int idPoli;
    public String namaPoli;
    public ArrayList<Dokter> dokters;
    public ArrayList<Antrian> antrians = new ArrayList<>();

    public Poli(int idPoli, String namaPoli) {
        this.idPoli = idPoli;
        this.namaPoli = namaPoli;
        this.dokters = new ArrayList<>();
//        this.antrians = new ArrayList<>();
    }

    public void addDokter(Dokter dokter){
        this.dokters.add(dokter);
    }

    public void addAntrian(Antrian antrian) {
        this.antrians.add(antrian);
    }
}
