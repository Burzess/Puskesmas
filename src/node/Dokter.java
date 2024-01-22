package node;

import java.util.ArrayList;

public class Dokter {
    public int idDokter;
    public String namaDokter;
    public ArrayList<JadwalPraktek> jadwalPraktek;

    public Dokter(int idDokter, String namaDokter) {
        this.idDokter = idDokter;
        this.namaDokter = namaDokter;
    }

    public Dokter(int idDokter, String namaDokter, ArrayList<JadwalPraktek> jadwalPraktek) {
        this.idDokter = idDokter;
        this.namaDokter = namaDokter;
        this.jadwalPraktek = jadwalPraktek;
    }
}
