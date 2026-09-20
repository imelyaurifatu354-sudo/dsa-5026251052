public class ColourPrint extends PrintJob {

    private static final int RATE_FIRST_10 = 1500;
    private static final int RATE_BEYOND_10 = 1000;
    private static final int SETUP_FEE = 2000;

    public ColourPrint(String id, int pages) {
        super(id, pages);
    }

    @Override
    public int calculateCharge() {
        int pages = getPages();
        int charge;
        if (pages <= 10) {
            charge = pages * RATE_FIRST_10;
        } else {
            charge = (10 * RATE_FIRST_10) + ((pages - 10) * RATE_BEYOND_10);
        }
        return charge + SETUP_FEE;
    }

    @Override
    public String label() {
        return "Colour";
    }
}
