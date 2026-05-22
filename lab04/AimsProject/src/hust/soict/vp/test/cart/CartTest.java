package hust.soict.vp.test.cart;

import hust.soict.vp.aims.cart.Cart;
import hust.soict.vp.aims.media.DigitalVideoDisc;

public class CartTest {
    public static void main(String[] args) {
        Cart cart = new Cart();

        DigitalVideoDisc dvd1 = new DigitalVideoDisc(
                "The Lion King",
                "Animation",
                "Roger Allers",
                87,
                19.95f
        );

        DigitalVideoDisc dvd2 = new DigitalVideoDisc(
                "Star Wars",
                "Science Fiction",
                "George Lucas",
                87,
                24.95f
        );

        DigitalVideoDisc dvd3 = new DigitalVideoDisc(
                "Animation",
                "Aladin",
                18.99f
        );

        DigitalVideoDisc dvd4 = new DigitalVideoDisc(
                "Christopher Nolan",
                "Science Fiction",
                "Interstellar",
                27.50f
        );

        DigitalVideoDisc dvd5 = new DigitalVideoDisc(
                "Action",
                "Batman Begins",
                21.99f
        );

        cart.addDigitalVideoDisc(dvd1, dvd2);
        cart.addDigitalVideoDisc(dvd3);
        cart.addDigitalVideoDisc(dvd4, dvd5);

        cart.print();

        cart.searchById(dvd2.getId());
        cart.searchByTitle("Star Wars");
        cart.searchByTitle("Harry Potter");

        cart.removeDigitalVideoDisc(dvd3);

        cart.print();
    }
}