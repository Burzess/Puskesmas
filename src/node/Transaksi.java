package node;

public class Transaksi {
    public int idTransaksi;
    public Antrian antrean;
    public Pasien pasien;
    public Poli poli;

    public Transaksi(int id, Antrian antrean, Pasien pasien, Poli poli) {
        this.idTransaksi = id;
        this.antrean = antrean;
        this.pasien = pasien;
        this.poli = poli;
    }
}
