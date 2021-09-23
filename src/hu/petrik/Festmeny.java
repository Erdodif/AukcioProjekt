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

    static private int trancsiroz(int mit) {
        String mi = "" + mit;
        double szamolas = Math.round((double) mit / Math.pow(10, mi.length() - 2));
        return (int) (szamolas * Math.pow(10, mi.length() - 2));
    }

    static private int szazalekol(int mit, int szazalek) {
        return trancsiroz((int) (mit + (mit / 100.0 * szazalek)));
    }

    public void licit(int mertek) {
        if (mertek <= 100 && mertek >= 10) {
            double szazalek = mertek / 100.0 + 1;
            if (!getElkelt()) {
                if (licitekSzama == 0) {
                    legmagasabbLicit = 100;
                } else {
                    legmagasabbLicit = szazalekol(getLegmagasabbLicit(), mertek);
                }
            }
            this.licitekSzama++;
            legutolsoLicitIdeje = LocalDateTime.now();
        }
    }

    @Override
    public String toString() {
        String kiad = "elkelt " + legmagasabbLicit + "$-ért";
        String ido = "Nincs licit";
        boolean nincsLicit = true;
        if (legutolsoLicitIdeje != null) {
            ido = legutolsoLicitIdeje.toString();
            nincsLicit = false;
        }
        if (!getElkelt()) {
            kiad = (legmagasabbLicit + "$ - " + ido + (nincsLicit ? "" : "(összesen: " + licitekSzama + " db)"));
        }
        return festo + " : " + cim + "(" + stilus + ")\n" + kiad;
    }
}
