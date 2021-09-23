package hu.petrik;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static List<Festmeny> festmenyek = new ArrayList();

    private static ArrayList<Festmeny> beolvas(String eleres) {
        ArrayList<Festmeny> kiad = new ArrayList();
        try {
            FileReader fr = new FileReader(eleres);
            BufferedReader br = new BufferedReader(fr);
            String sor = br.readLine();
            while (sor != null) {
                String[] adatok = sor.split(";");
                kiad.add(new Festmeny(adatok[1], adatok[0], adatok[2]));
                sor = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("Hiba: (" + e + ")");
        }
        return kiad;
    }

    private static void init() {
        festmenyek.add(new Festmeny("Kenyerek az égen", "Ismeretlen", "Kenyér-Barokk"));
        festmenyek.add(new Festmeny("Jelenés", "Vollai Ferenc", "modern"));
        festmenyek.addAll(beolvas("festmenyek.csv"));
        for (int i = 0; i < 20; i++) {
            int randomIndex = (int) (Math.random() * festmenyek.size());
            int randomErtek = (int) (Math.random() * 90 + 10);
            festmenyek.get(randomIndex).licit(randomErtek);
        }
    }

    private static void felhasznaloiInterakcio() {
        int bevitel = -1;
        Scanner sc = new Scanner(System.in);
        while (bevitel != 0) {
            for (Festmeny festmeny : festmenyek) {
                System.out.println(festmeny);
            }
            System.out.print("Melyikre szeretne licitálni? (1 - " + festmenyek.size() + "): ");
            bevitel = sc.nextInt();
            if (bevitel != 0 && bevitel < festmenyek.size() + 1) {
                System.out.print("Hány százalékos licitlépcsőt kíván meglépni? (10%-100%): ");
                int dontes = -1;
                while (dontes > 100 || dontes < 10) {
                    dontes = sc.nextInt();
                    if (dontes > 100 || dontes < 10) {
                        System.out.println("Hibás érték! (10-100)");
                    }
                }
            }
        }
    }

    private static void ertekel() {
        int nemkeltek = 0;
        for (Festmeny festmeny : festmenyek) {
            if (festmeny.getLicitekSzama() > 0) {
                festmeny.setElkelt(true);
            } else {
                nemkeltek++;
            }
            System.out.println(festmeny);
        }
        int index = 0;
        while (index < festmenyek.size() && festmenyek.get(index).getLicitekSzama() != 10) {
            index++;
        }
        if (index < festmenyek.size()) {
            System.out.println("Van olyan festmény, amire több mint 10 licit érkezett.");
        } else {
            System.out.println("Nem volt olyan festmény, amire több mint 10 licit érkezett.");
        }
        System.out.println(nemkeltek + " darab el nem kelt festmény volt");
    }

    public static void main(String[] args) {
        init();
        felhasznaloiInterakcio();
        System.out.println("Az aukció lezárult");
        ertekel();
    }
}
