package node;

public class Transaksi {
    public int idTransaksi;
    public Antrian antrean;
    public Pasien pasien;
    public Poli poli;
    int total;
    String descriptio;

    public Transaksi(int idTransaksi, Antrian antrean, Pasien pasien, Poli poli, int total, String descriptio) {
        this.idTransaksi = idTransaksi;
        this.antrean = antrean;
        this.pasien = pasien;
        this.poli = poli;
        this.total = total;
        this.descriptio = descriptio;
    }

    public Transaksi(int id, Antrian antrean, Pasien pasien, Poli poli) {
        this.idTransaksi = id;
        this.antrean = antrean;
        this.pasien = pasien;
        this.poli = poli;
    }

    public void viewTransaction(){
        System.out.println("      Detail Transaksi");
        System.out.println("Nomor Antrean : " + antrean.index);
        System.out.println("Nama Pasien   : " + pasien.namaPasien);
        System.out.println("NIK Pasien    : " + pasien.NIK);
        System.out.println("Poli          : " + poli.namaPoli);
    }
}
