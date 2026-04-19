public class TestPassingParameter {
	public static void swap(DigitalVideoDisc dvd1, DigitalVideoDisc dvd2) {
		DigitalVideoDisc temp = dvd1;
		dvd1 = dvd2;
		dvd2 = temp;
	}

	public static void main(String[] args) {
		DigitalVideoDisc dvd1 = new DigitalVideoDisc("Animation", "The Lion King", 19.95f);
		DigitalVideoDisc dvd2 = new DigitalVideoDisc("Science Fiction", "Star Wars", 24.95f);

		System.out.println("Before swap:");
		System.out.println("dvd1 title: " + dvd1.getTitle());
		System.out.println("dvd2 title: " + dvd2.getTitle());

		swap(dvd1, dvd2);

		System.out.println("After swap:");
		System.out.println("dvd1 title: " + dvd1.getTitle());
		System.out.println("dvd2 title: " + dvd2.getTitle());
	}
}