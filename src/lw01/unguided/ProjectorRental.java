public class ProjectorRental extends Rental {

    private static final int RATE_FIRST_3_DAYS = 60000;
    private static final int RATE_SUBSEQUENT_DAYS = 45000;
    private static final int SETUP_FEE = 20000;

    public ProjectorRental(String id, int days) {
        super(id, days);
    }

    @Override
    public int calculateCharge() {
        int days = getDays();
        if (days <= 3) {
            return days * RATE_FIRST_3_DAYS + SETUP_FEE;
        } else {
            return (3 * RATE_FIRST_3_DAYS) + ((days - 3) * RATE_SUBSEQUENT_DAYS) + SETUP_FEE;
        }
    }

    @Override
    public String label() {
        return "Projector";
    }
}
