package server.managers;

public class Generate {
    private  int idi;
    public Generate(){
        this.idi = 1;
    }

    public  Integer generateId() {
        return idi++;  // возвращает и увеличивает
    }

    public  void reset() {
        idi = 1;
    }
    public  void setIdi(int id){idi = id;}

    public int getIdi() {
        return idi;
    }
}
