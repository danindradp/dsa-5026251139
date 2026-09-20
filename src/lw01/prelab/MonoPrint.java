package lw01.prelab;

public class MonoPrint extends PrintJob {
    private static final int TARIF_PER_HALAMAN = 500;

    public MonoPrint(String id, int pages) {
        super(id, pages);
    }

    @Override
    public int calculateCharge() {
        return getPages() * TARIF_PER_HALAMAN;
    }

    @Override
    public String label() {
        return "Mono";
    }
}
