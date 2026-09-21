package lw01.unguided;

public class LaptopRental extends Rental {
    private static final int TARIF_PER_HARI = 40000;
    private static final int BIAYA_SETUP = 10000;

    public LaptopRental(String id, int days) {
        super(id, days);
    }

    @Override
    public int calculateCharge() {
        return (getDays() * TARIF_PER_HARI) + BIAYA_SETUP;
    }

    @Override
    public String label() {
        return "Laptop";
    }
}
