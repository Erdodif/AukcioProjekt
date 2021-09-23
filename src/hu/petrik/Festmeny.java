package hu.petrik;

import java.time.LocalDateTime;

public class Festmeny {
    private String cim;
    private String festo;
    private String stilus;
    private int licitekSzama;
    private int legmagasabbLicit;
    private LocalDateTime legutolsoLicitIdeje;
    private boolean elkelt;

    public Festmeny(String cim, String festo, String stilus) {
        this.cim = cim;
        this.festo = festo;
        this.stilus = stilus;
        licitekSzama = 0;
        legmagasabbLicit = 0;
        legutolsoLicitIdeje = null;
        elkelt = false;
    }

    public String getFesto() {
        return festo;
    }

    public String getStilus() {
        return stilus;
    }

    public int getLicitekSzama() {
        return licitekSzama;
    }

    public int getLegmagasabbLicit() {
        return legmagasabbLicit;
    }

    public LocalDateTime getLegutolsoLicitIdeje() {
        return legutolsoLicitIdeje;
    }

    public boolean getElkelt() {
        return elkelt;
    }

    public void setElkelt(boolean elkelt) {
        this.elkelt = elkelt;
    }

    public void licit() {
        licit(10);
    }

    public void licit(int mertek) {
        if (mertek > 100 || mertek < 10) {
            System.out.println("Nem elfogadott licitlépcső (10 és 100 között kelle lennie)");
        } else {
            if (!getElkelt()) {
                if (licitekSzama == 0) {
                    legmagasabbLicit = 100;
                } else {
                    legmagasabbLicit = (int) (getLegmagasabbLicit() * (mertek / 100)) / 100 * 100;
                }
                this.licitekSzama++;
                legutolsoLicitIdeje = LocalDateTime.now();
            } else {
                System.out.println("A festmény már elkelt!");
            }
        }
    }

    @Override
    public String toString() {
        String kiad = "elkelt";
        String ido = "Nincs licit";
        if(legutolsoLicitIdeje != null) {
            ido = legutolsoLicitIdeje.toString();
        }
        if (!getElkelt()) {
            kiad = (legmagasabbLicit + "$ - " + ido + "(összesen: " + licitekSzama + " db)");
        }
        return festo + " : " + cim + "(" + stilus + ")\n" + kiad;
    }
}
