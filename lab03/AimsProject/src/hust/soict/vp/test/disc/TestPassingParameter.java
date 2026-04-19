package hust.soict.vp.test.disc;

import hust.soict.vp.aims.disc.DigitalVideoDisc;

public class TestPassingParameter {
    public static void swap(DigitalVideoDisc dvd1, DigitalVideoDisc dvd2) {
        String tempTitle = dvd1.getTitle();
        String tempCategory = dvd1.getCategory();
        String tempDirector = dvd1.getDirector();
        int tempLength = dvd1.getLength();
        float tempCost = dvd1.getCost();

        dvd1.setTitle(dvd2.getTitle());
        dvd1.setCategory(dvd2.getCategory());
        dvd1.setDirector(dvd2.getDirector());
        dvd1.setLength(dvd2.getLength());
        dvd1.setCost(dvd2.getCost());

        dvd2.setTitle(tempTitle);
        dvd2.setCategory(tempCategory);
        dvd2.setDirector(tempDirector);
        dvd2.setLength(tempLength);
        dvd2.setCost(tempCost);
    }

    public static void changeTitle(DigitalVideoDisc dvd, String title) {
        String oldTitle = dvd.getTitle();
        dvd.setTitle(title);
        System.out.println("The old title is: " + oldTitle);
    }

    public static void main(String[] args) {
        DigitalVideoDisc jungleDVD = new DigitalVideoDisc("Jungle");
        DigitalVideoDisc cinderellaDVD = new DigitalVideoDisc("Cinderella");

        System.out.println("Before swap:");
        System.out.println("jungle dvd title: " + jungleDVD.getTitle());
        System.out.println("cinderella dvd title: " + cinderellaDVD.getTitle());

        swap(jungleDVD, cinderellaDVD);

        System.out.println("After swap:");
        System.out.println("jungle dvd title: " + jungleDVD.getTitle());
        System.out.println("cinderella dvd title: " + cinderellaDVD.getTitle());

        changeTitle(jungleDVD, cinderellaDVD.getTitle());

        System.out.println("After changing title:");
        System.out.println("jungle dvd title: " + jungleDVD.getTitle());
    }
}