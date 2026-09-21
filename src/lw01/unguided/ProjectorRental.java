package lw01.unguided;

public class ProjectorRental extends Rental {
    private static final int BATAS_HARI_MAHAL = 3;
    private static final int TARIF_3_HARI_PERTAMA = 60000;
    private static final int TARIF_SETELAH_3_HARI = 45000;
    private static final int BIAYA_SETUP = 20000;

    public ProjectorRental(String id, int days) {
        super(id, days);
    }

    @Override
    public int calculateCharge() {
        int hariPertama = Math.min(getDays(), BATAS_HARI_MAHAL);
        int hariSisa = getDays() - hariPertama;

        return (hariPertama * TARIF_3_HARI_PERTAMA)
                + (hariSisa * TARIF_SETELAH_3_HARI)
                + BIAYA_SETUP;
    }

    @Override
    public String label() {
        return "Projector";
    }
}
