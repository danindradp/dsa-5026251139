package lw01.prelab;

public abstract class PrintJob implements Chargeable {
    private String id;
    private int pages;

    protected PrintJob(String id, int pages) {
        // halaman nol atau negatif langsung ditolak
        if (pages <= 0) {
            throw new IllegalArgumentException("pages harus positif");
        }
        this.id = id;
        this.pages = pages;
    }

    public String getId() {
        return id;
    }

    public int getPages() {
        return pages;
    }

    // tiap subclass hitung tarifnya sendiri-sendiri
    @Override
    public abstract int calculateCharge();

    // overload: biaya total untuk sejumlah salinan
    public int calculateCharge(int copies) {
        if (copies <= 0) {
            throw new IllegalArgumentException("copies harus positif");
        }
        return copies * calculateCharge();
    }

    public String label() {
        return "Print";
    }

    // sengaja ga di-override di subclass, biar label() sama calculateCharge() yang milih sendiri
    public String summary() {
        return id + " | " + label() + " | " + calculateCharge();
    }
}
