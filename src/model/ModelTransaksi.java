package model;

import com.google.gson.reflect.TypeToken;
import modelGSON.ModelGSON;
import node.Transaksi;

import java.util.ArrayList;

public class ModelTransaksi {
    private static ArrayList<Transaksi> listTransaksi;
    private ModelGSON modelGSON;

    public ModelTransaksi() {
        modelGSON = new ModelGSON<Transaksi>("src/DB/transaksi.json");
        listTransaksi = modelGSON.readFromFile(new TypeToken<ArrayList<Transaksi>>() {}.getType());
        if (listTransaksi == null) listTransaksi = new ArrayList<>();
    }

    public void addTrasnsaksi(Transaksi transaksi) {
        listTransaksi.add(transaksi);
        modelGSON.writeToFile(listTransaksi);
    }

    public ArrayList<Transaksi> getListTransaksi() {
        return listTransaksi;
    }

    public int getLastId(){
        if (listTransaksi == null) {
            return 0;
        } else {
            return listTransaksi.size();
        }
    }

}
