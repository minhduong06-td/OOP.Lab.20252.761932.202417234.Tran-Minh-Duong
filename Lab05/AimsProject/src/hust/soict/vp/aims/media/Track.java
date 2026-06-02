package hust.soict.vp.aims.media;

import hust.soict.vp.aims.exception.PlayerException;

import java.util.Objects;

public class Track implements Playable {
    private String title;
    private int length;

    public Track() {
    }

    public Track(String title, int length) {
        this.title = title;
        this.length = length;
    }

    public String getTitle() {
        return title;
    }

    public int getLength() {
        return length;
    }

    @Override
    public void play() throws PlayerException {
        if (length <= 0) {
            throw new PlayerException("ERROR: Track length is non-positive!");
        }

        System.out.println("Playing track: " + title);
        System.out.println("Track length: " + length);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Track)) {
            return false;
        }

        Track other = (Track) obj;
        return Objects.equals(this.title, other.title)
                && this.length == other.length;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, length);
    }

    @Override
    public String toString() {
        return String.format(
                "Track - %s - %d",
                title != null ? title : "",
                length
        );
    }
}
