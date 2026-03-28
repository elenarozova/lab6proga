package data;

public class Generate {
    private static int idi;

    public static Integer generateId() {
        return idi++;  // возвращает и увеличивает
    }

    public static void reset() {
        idi = 1;
    }
    public static void setIdi(int id){idi = id;}

    public static int getIdi() {
        return idi;
    }
}
