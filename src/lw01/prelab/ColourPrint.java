package lw01.prelab;

public class ColourPrint extends PrintJob {
    private static final int BATAS_HALAMAN_MURAH = 10;
    private static final int TARIF_10_PERTAMA = 1500;
    private static final int TARIF_SETELAH_10 = 1000;
    private static final int BIAYA_SETUP = 2000;

    public ColourPrint(String id, int pages) {
        super(id, pages);
    }

    @Override
    public int calculateCharge() {
        // pisah dulu halaman yang masuk tarif 1500 sama yang kena 1000
        int halamanPertama = Math.min(getPages(), BATAS_HALAMAN_MURAH);
        int halamanSisa = getPages() - halamanPertama;

        return (halamanPertama * TARIF_10_PERTAMA)
                + (halamanSisa * TARIF_SETELAH_10)
                + BIAYA_SETUP;
    }

    @Override
    public String label() {
        return "Colour";
    }
}
