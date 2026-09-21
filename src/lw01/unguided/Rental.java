public abstract class Rental implements Chargeable {

    private String id;
    private int days;

    protected Rental(String id, int days) {
        if (days <= 0) {
            throw new IllegalArgumentException("Days must be a positive integer");
        }
        this.id = id;
        this.days = days;
    }

    public String getId() {
        return id;
    }
    
    public int getDays() {
        return days;
    }

    public abstract int calculateCharge();
    public int calculateCharge(int units) {
        return 0;
    }

    public String label() {
        return "Rental";
    }

    public String summary() {
       return id + " | " + label() + " | " + calculateCharge();
    }
}
