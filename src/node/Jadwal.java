package node;

public class Jadwal {
    public int idJadwal;
    public String hari;
    public String jam;
    public String namaDokter;

    public Jadwal(int id, String hari, String jam, String namaDoketer) {
        this.idJadwal = id;
        this.hari = hari;
        this.jam = jam;
        this.namaDokter = namaDoketer;
    }

    public void viewJadwal(){
        System.out.println("Hari : "+hari);
        System.out.println("Jam  : "+jam);
    }
}
