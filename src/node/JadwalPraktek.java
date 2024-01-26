package node;

public class JadwalPraktek {
    int id;
    private String namaDokter;
    private String hari;
    private String jamPraktek;
    private String poli;

    public JadwalPraktek(int id, String namaDokter, String hari, String jamPraktek, String poli) {
        this.id = id;
        this.namaDokter = namaDokter;
        this.hari = hari;
        this.jamPraktek = jamPraktek;
        this.poli = poli;
    }

    public String getPoli() {
        return poli;
    }

    public void setPoli(String poli) {
        this.poli = poli;
    }

    public String getNamaDokter() {
        return namaDokter;
    }

    public void setNamaDokter(String namaDokter) {
        this.namaDokter = namaDokter;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public String getJamPraktek() {
        return jamPraktek;
    }

    public void setJamPraktek(String jamPraktek) {
        this.jamPraktek = jamPraktek;
    }
}
