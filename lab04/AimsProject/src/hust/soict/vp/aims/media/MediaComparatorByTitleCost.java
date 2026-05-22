package hust.soict.vp.aims.media;

import java.util.Comparator;

public class MediaComparatorByTitleCost implements Comparator<Media> {
    @Override
    public int compare(Media m1, Media m2) {
        String title1 = m1.getTitle() == null ? "" : m1.getTitle();
        String title2 = m2.getTitle() == null ? "" : m2.getTitle();

        int titleCompare = title1.compareToIgnoreCase(title2);
        if (titleCompare != 0) {
            return titleCompare;
        }

        return Float.compare(m2.getCost(), m1.getCost());
    }
}
