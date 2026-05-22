package hust.soict.vp.aims.media;

public class DigitalVideoDisc extends Disc implements Playable {
    private static int nbDigitalVideoDiscs = 0;

    public DigitalVideoDisc() {
        super();
        nbDigitalVideoDiscs++;
    }

    public DigitalVideoDisc(String title) {
        super(title);
        nbDigitalVideoDiscs++;
    }

    public DigitalVideoDisc(String category, String title, float cost) {
        super(title, category, cost);
        nbDigitalVideoDiscs++;
    }

    public DigitalVideoDisc(String director, String category, String title, float cost) {
        super(title, category, cost, 0, director);
        nbDigitalVideoDiscs++;
    }

    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        super(title, category, cost, length, director);
        nbDigitalVideoDiscs++;
    }

    public static int getNbDigitalVideoDiscs() {
        return nbDigitalVideoDiscs;
    }

    @Override
    public void play() {
        if (getLength() <= 0) {
            System.out.println("The DVD cannot be played because its length is not positive.");
            return;
        }

        System.out.println("Playing DVD: " + getTitle());
        System.out.println("DVD length: " + getLength());
    }

    @Override
    public String toString() {
        return String.format(
                "DVD - id: %d - %s - %s - %s - %d: %.2f $",
                getId(),
                getTitle() != null ? getTitle() : "",
                getCategory() != null ? getCategory() : "",
                getDirector() != null ? getDirector() : "",
                getLength(),
                getCost()
        );
    }
}
