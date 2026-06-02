package hust.soict.vp.aims.media;

import java.util.Comparator;
import java.util.Objects;

public abstract class Media {
    private static int nbMedia = 0;

    private int id;
    private String title;
    private String category;
    private float cost;

    public static final Comparator<Media> COMPARE_BY_TITLE_COST =
            new MediaComparatorByTitleCost();

    public static final Comparator<Media> COMPARE_BY_COST_TITLE =
            new MediaComparatorByCostTitle();

    public Media() {
        nbMedia++;
        this.id = nbMedia;
    }

    public Media(String title) {
        this();
        this.title = title;
    }

    public Media(String title, String category, float cost) {
        this(title);
        this.category = category;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public static int getNbMedia() {
        return nbMedia;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public float getCost() {
        return cost;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public boolean isMatch(String title) {
        if (this.title == null || title == null) {
            return false;
        }

        String mediaTitle = normalize(this.title);
        String inputTitle = normalize(title);

        if (mediaTitle.contains(inputTitle) || inputTitle.contains(mediaTitle)) {
            return true;
        }

        String[] tokens = inputTitle.split(" ");
        for (String token : tokens) {
            if (!token.trim().isEmpty() && !mediaTitle.contains(token)) {
                return false;
            }
        }
        return true;
    }

    private String normalize(String s) {
        return s.trim().toLowerCase().replaceAll("\\s+", " ");
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Media)) {
            return false;
        }

        Media other = (Media) obj;
        return Objects.equals(this.title, other.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    @Override
    public String toString() {
        return String.format(
                "Media - id: %d - %s - %s: %.2f $",
                id,
                title != null ? title : "",
                category != null ? category : "",
                cost
        );
    }
}
