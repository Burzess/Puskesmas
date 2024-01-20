package node;

public class JadwalPraktek {
    int id;
    private String namaDokter;
    private String hari;
    private String jamPraktek;

    public JadwalPraktek(String namaDokter, String hari, String jamPraktek) {
        this.namaDokter = namaDokter;
        this.hari = hari;
        this.jamPraktek = jamPraktek;
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
