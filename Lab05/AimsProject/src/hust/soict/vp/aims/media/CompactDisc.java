package hust.soict.vp.aims.media;

import hust.soict.vp.aims.exception.PlayerException;

import java.util.ArrayList;

public class CompactDisc extends Disc implements Playable {
    private String artist;
    private ArrayList<Track> tracks = new ArrayList<Track>();

    public CompactDisc() {
        super();
    }

    public CompactDisc(String title, String category, String director, String artist, float cost) {
        super(title, category, cost, 0, director);
        this.artist = artist;
    }

    public CompactDisc(String title, String category, String director, String artist, int length, float cost) {
        super(title, category, cost, length, director);
        this.artist = artist;
    }

    public String getArtist() {
        return artist;
    }

    public ArrayList<Track> getTracks() {
        return tracks;
    }

    public void addTrack(Track track) {
        if (track == null) {
            System.out.println("The track is null.");
            return;
        }

        if (tracks.contains(track)) {
            System.out.println("The track already exists in this CD.");
            return;
        }

        tracks.add(track);
        System.out.println("The track has been added.");
    }

    public void removeTrack(Track track) {
        if (!tracks.contains(track)) {
            System.out.println("The track does not exist in this CD.");
            return;
        }

        tracks.remove(track);
        System.out.println("The track has been removed.");
    }

    @Override
    public int getLength() {
        int totalLength = 0;

        for (Track track : tracks) {
            totalLength += track.getLength();
        }

        return totalLength;
    }

    @Override
    public void play() throws PlayerException {
        if (getLength() <= 0) {
            throw new PlayerException("ERROR: CD length is non-positive!");
        }

        System.out.println("Playing CD: " + getTitle());
        System.out.println("CD length: " + getLength());

        for (Track track : tracks) {
            track.play();
        }
    }

    @Override
    public String toString() {
        return String.format(
                "CD - id: %d - %s - %s - artist: %s - director: %s - length: %d: %.2f $",
                getId(),
                getTitle() != null ? getTitle() : "",
                getCategory() != null ? getCategory() : "",
                artist != null ? artist : "",
                getDirector() != null ? getDirector() : "",
                getLength(),
                getCost()
        );
    }
}
