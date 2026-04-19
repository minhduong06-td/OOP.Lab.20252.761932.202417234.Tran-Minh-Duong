package hust.soict.vp.aims.disc;

public class DigitalVideoDisc {
    private static int nbDigitalVideoDiscs = 0;

    private final int id;
    private String title;
    private String category;
    private String director;
    private int length;
    private float cost;

    public DigitalVideoDisc() {
        nbDigitalVideoDiscs++;
        this.id = nbDigitalVideoDiscs;
    }

    public DigitalVideoDisc(String title) {
        this();
        this.title = title;
    }

    public DigitalVideoDisc(String category, String title, float cost) {
        this();
        this.category = category;
        this.title = title;
        this.cost = cost;
    }

    public DigitalVideoDisc(String director, String category, String title, float cost) {
        this();
        this.director = director;
        this.category = category;
        this.title = title;
        this.cost = cost;
    }

    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        this();
        this.title = title;
        this.category = category;
        this.director = director;
        this.length = length;
        this.cost = cost;
    }

    public static int getNbDigitalVideoDiscs() {
        return nbDigitalVideoDiscs;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public String getDirector() {
        return director;
    }

    public int getLength() {
        return length;
    }

    public float getCost() {
        return cost;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public boolean isMatch(String title) {
        if (this.title == null || title == null) {
            return false;
        }

        String normalizedDiscTitle = normalize(this.title);
        String normalizedInputTitle = normalize(title);

        if (normalizedDiscTitle.contains(normalizedInputTitle)
                || normalizedInputTitle.contains(normalizedDiscTitle)) {
            return true;
        }

        String[] tokens = normalizedInputTitle.split(" ");
        for (String token : tokens) {
            if (!token.isBlank() && !normalizedDiscTitle.contains(token)) {
                return false;
            }
        }
        return true;
    }

    private String normalize(String s) {
        return s.trim().toLowerCase().replaceAll("\\s+", " ");
    }

    @Override
    public String toString() {
        return String.format(
                "DVD - %s - %s - %s - %d: %.2f $",
                title != null ? title : "",
                category != null ? category : "",
                director != null ? director : "",
                length,
                cost
        );
    }
}