package node;

import java.util.ArrayList;

public class Dokter {
    public int idDokter;
    public String namaDokter;
    public int poli;
//    public ArrayList<Jadwal> jadwals;

    public Dokter(int idDokter, String namaDokter, int poli) {
        this.idDokter = idDokter;
        this.namaDokter = namaDokter;
        this.poli = poli;
//        this.jadwals = new ArrayList<>();
    }

//    public Dokter(int idDokter, String namaDokter, int poli, ArrayList<Jadwal> jadwals) {
//        this.idDokter = idDokter;
//        this.namaDokter = namaDokter;
//        this.poli = poli;
//        this.jadwals = jadwals;
//    }

//    public void addSchedule(Jadwal jadwal){
//        this.jadwals.add(jadwal);
//    }
}
