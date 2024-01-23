package node;

import java.util.ArrayList;

public class Poli {
    public int idPoli;
    public String namaPoli;
    public ArrayList<Dokter> dokters;
    public ArrayList<Antrian> antrians;

    public Poli(int idPoli, String namaPoli) {
        this.idPoli = idPoli;
        this.namaPoli = namaPoli;
        this.antrians = new ArrayList<>();
        this.dokters = new ArrayList<>();
    }

    public void addDokter(Dokter dokter){
        this.dokters.add(dokter);
    }

    public void addAntrian(Antrian antrian) {
        this.antrians.add(antrian);
    }
}
