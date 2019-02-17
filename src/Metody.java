import java.util.ArrayList;
import java.util.Scanner;

public class Metody {
    public static void main(String[] args) {
        Interfejs interfejs = new Interfejs();
        Budynek tartak = new Tartak(1);
        Budynek kamieniolom = new Kamieniolom(1);
        Budynek gospodarstwo = new Gospodarstwo(1);


        Scanner scanner = new Scanner(System.in);
        for(;;) {
            interfejs.dodajTekstNaglowkowy(Surowce.iloscSurowcow());
            interfejs.dodajTekstAkapitowy(tartak.toString());
            interfejs.dodajTekstAkapitowy(kamieniolom.toString());
            interfejs.dodajTekstAkapitowy(gospodarstwo.toString());
            interfejs.rysujInterfejs();
            interfejs.usunTekstAkapitowy();
            interfejs.usunTekstNaglowkowy();

            String daneWejscowe = "";
            while(!daneWejscowe.equals("t") && !daneWejscowe.equals("k") && !daneWejscowe.equals("g") && !daneWejscowe.equals("n")) {
                daneWejscowe = scanner.next();
            }
            switch (daneWejscowe) {
                case "t":
                    tartak.addPoziom();
                    break;
                case "k":
                    kamieniolom.addPoziom();
                    break;
                case "g":
                    gospodarstwo.addPoziom();
                    break;
            }
            tartak.addSurowiec();
            kamieniolom.addSurowiec();
            gospodarstwo.addSurowiec();
        }
    }
}

//klasa zajmująca sie obsługą interfejsu użytkownika
class Interfejs {
    private ArrayList<String> tekstNaglowkowy = new ArrayList<>();
    private ArrayList<String> tekstAkapitowy = new ArrayList<>();
    private String tekstStopki = "";
    int szerokoscOknaInterfejsu;

    Interfejs(){}

    public void dodajTekstNaglowkowy(String tekst) {
        tekstNaglowkowy.add(tekst);
    }
    public void dodajTekstAkapitowy(String tekst) {
        tekstAkapitowy.add(tekst);
    }
    public void ustawTekstStopki(String tekst) {
        tekstStopki = tekst;
    }
    public void usunTekstNaglowkowy() {
        tekstNaglowkowy.clear();
    }
    public void usunTekstAkapitowy() {
        tekstAkapitowy.clear();
    }
    public void usunTekstStopki() {
        tekstStopki = null;
    }
    
    private void ustawSzerokoscOknaInterfejsu() {
        szerokoscOknaInterfejsu = dlugoscNajdluzszejLinijki() + 4;
    }
    
    private int dlugoscNajdluzszejLinijki() {
        int najdluzszaLinijka = 10;
        for (String tekstNaglowka : tekstNaglowkowy) {
            if(tekstNaglowka.length() > najdluzszaLinijka) najdluzszaLinijka = tekstNaglowka.length();
        }
        for (String tekstAkapitu : tekstAkapitowy) {
            if(tekstAkapitu.length() > najdluzszaLinijka) najdluzszaLinijka = tekstAkapitu.length();
        }
        if(tekstStopki.length() > najdluzszaLinijka) najdluzszaLinijka = tekstStopki.length();
        return najdluzszaLinijka;
    }

    public void rysujInterfejs() {
        ustawSzerokoscOknaInterfejsu();
        rysujLiniePozioma();
        wypiszTekstNaglowkowy();
        rysujGrubaPrzerwe();
        wypiszTekstAkapitowy();
        rysujCienkaPrzerwe();
        wypiszTekstWyrownanyDoLewej("[T] Rozbuduj tartak");
        wypiszTekstWyrownanyDoLewej("[K] Rozbuduj kamieniolom");
        wypiszTekstWyrownanyDoLewej("[G] Rozbuduj gospodarstwo");
        rysujLiniePozioma();
    }
    private void rysujLiniePozioma() {
        System.out.print("+");
        for (int i = 0; i < szerokoscOknaInterfejsu; i++) {
            System.out.print("=");
        }
        System.out.print("+\n");
    }
    private void rysujCienkaPrzerwe() {
        System.out.print("+--");
        for (int i = 2; i < szerokoscOknaInterfejsu-2; i++) {
            System.out.print(" ");
        }
        System.out.print("--+\n");
    }
    private void rysujGrubaPrzerwe() {
        System.out.print("+");
        for (int i = 0; i < szerokoscOknaInterfejsu; i++) {
            System.out.print("-");
        }
        System.out.print("+\n");
    }
    private void wypiszTekstNaglowkowy() {
        for(String tekstNaglowka : tekstNaglowkowy) {
            wypiszTekstWysrodkowany(tekstNaglowka.toString());
        }
    }
    private void wypiszTekstAkapitowy() {
        for(String tekstAkapitu : tekstAkapitowy) {
            wypiszTekstWyrownanyDoLewej(tekstAkapitu.toString());
        }
    }
    private void wypiszTekstWysrodkowany(String tekst) {
        System.out.print("|");
        postawSpacjeZLewejDoWysrodkowaniaTekstu(tekst.length());
        System.out.print(tekst);
        postawSpacjeZPrawejDoWysrodkowaniaTekstu(tekst.length());
        System.out.print("|\n");
    }
    private void postawSpacjeZLewejDoWysrodkowaniaTekstu(int dlugoscTekstu) {
        int ileSpacjiDoPostawienia = (szerokoscOknaInterfejsu) / 2 - dlugoscTekstu / 2;
        for (int i = 0; i < ileSpacjiDoPostawienia; i++) {
            System.out.print(" ");
        }
    }
    private void postawSpacjeZPrawejDoWysrodkowaniaTekstu(int dlugoscTekstu) {
        int ileSpacjiPostawione = (szerokoscOknaInterfejsu) / 2 - dlugoscTekstu / 2;
        for (int i = 0; i < szerokoscOknaInterfejsu - ileSpacjiPostawione - dlugoscTekstu; i++) {
            System.out.print(" ");
        }
    }

    private void wypiszTekstWyrownanyDoLewej(String tekst) {
        System.out.print("|  " + tekst);
        for (int i = 0; i < szerokoscOknaInterfejsu - 2 - tekst.length(); i++) {
            System.out.print(" ");
        }
        System.out.print("|\n");
    }
}
class Surowce {
    static int drewno = 20;
    static int kamien = 20;
    static int zboze = 20;

    public static String iloscSurowcow() {
        return "Drewno: " + drewno + "   Kamien: " + kamien + "   Zboże: " + zboze;
    }
}
abstract class Budynek {
    private int koszt = 0;
    protected int poziom;
    protected int przychod = 0;
    protected int kosztDrewno, kosztKamien, kosztZboze;

    Budynek(int poziom) {
        this.poziom = poziom;
        przychod = policzPrzychod(poziom);
        kosztDrewno = policzKoszt(poziom);
        kosztKamien = policzKoszt(poziom);
        kosztZboze = policzKoszt(poziom);
    }



    public int getKoszt() {
        return koszt;
    }
    public int getPoziom() {
        return poziom;
    }
    public void addPoziom() {
        if(czyMaszSurowceNaRozbudowe()) {
            zaplacKosztRozbudowy();
            poziom++;
            przychod = policzPrzychod(poziom);
            kosztDrewno = policzKoszt(poziom);
            kosztKamien = policzKoszt(poziom);
            kosztZboze = policzKoszt(poziom);
        }
    }
    protected boolean czyMaszSurowceNaRozbudowe() {
        if(Surowce.drewno >= kosztDrewno && Surowce.kamien >= kosztKamien && Surowce.zboze >= kosztZboze) {
            return true;
        }
        return false;
    }
    protected void zaplacKosztRozbudowy() {
        Surowce.zboze -= kosztZboze;
        Surowce.drewno -= kosztDrewno;
        Surowce.kamien -= kosztKamien;
    }
    public int getPrzychod() {
        policzPrzychod(poziom);
        return przychod;
    }
    public abstract void addSurowiec();

    protected int policzPrzychod(int poziom) {
        int przychod = 0;
        for (int i = 0; i < poziom; i++) {
            przychod = (int)(przychod * 1.5) + 2;
        }
        return przychod;
    }
    private int policzKoszt(int poziom) {
        int koszt = 0;
        for (int i = 0; i < poziom; i++) {
            koszt = (int)(koszt * 1.5) + 10;
        }
        return koszt;
    }
    protected abstract int policzKosztDrewna(int poziom);
    protected abstract int policzKosztKamienia(int poziom);
    protected abstract int policzKosztZboza(int poziom);
}
class Tartak extends Budynek {
    Tartak(int poziom) {
        super(poziom);
    }
    public void addSurowiec() {
        Surowce.drewno += getPrzychod();
    }
    public String toString() {
        return "Tartak:         Poziom: " + getPoziom() + " zapewnia przychód " + getPrzychod() + " drewna.   (Koszt: D-" + kosztDrewno + " K-" + kosztKamien + " G-" + kosztZboze + ")";
    }

    protected int policzKosztDrewna(int poziom) {
        int koszt = 0;
        for (int i = 0; i < poziom; i++) {
            koszt = (int)(koszt * 1.5) + 9;
        }
        return koszt;
    }

    protected int policzKosztKamienia(int poziom) {
        int koszt = 0;
        for (int i = 0; i < poziom; i++) {
            koszt = (int)(koszt * 1.6) + 10;
        }
        return koszt;
    }

    protected int policzKosztZboza(int poziom) {
        int koszt = 0;
        for (int i = 0; i < poziom; i++) {
            koszt = (int)(koszt * 1.7) + 10;
        }
        return koszt;
    }

    public void addPoziom() {
        if(czyMaszSurowceNaRozbudowe()) {
            zaplacKosztRozbudowy();
            poziom++;
            przychod = policzPrzychod(poziom);
            kosztDrewno = policzKosztDrewna(poziom);
            kosztKamien = policzKosztKamienia(poziom);
            kosztZboze = policzKosztZboza(poziom);
        }
    }
}
class Kamieniolom extends Budynek {
    Kamieniolom(int poziom) {
        super(poziom);
    }
    public void addSurowiec() {
        Surowce.kamien += getPrzychod();
    }
    public String toString() {
        return "Kamieniołom:    Poziom: " + getPoziom() + " zapewnia przychód " + getPrzychod() + " kamienia. (Koszt: D-" + kosztDrewno + " K-" + kosztKamien + " G-" + kosztZboze + ")";
    }

    protected int policzKosztDrewna(int poziom) {
        int koszt = 0;
        for (int i = 0; i < poziom; i++) {
            koszt = (int)(koszt * 1.7) + 8;
        }
        return koszt;
    }

    protected int policzKosztKamienia(int poziom) {
        int koszt = 0;
        for (int i = 0; i < poziom; i++) {
            koszt = (int)(koszt * 1.2) + 10;
        }
        return koszt;
    }

    protected int policzKosztZboza(int poziom) {
        int koszt = 0;
        for (int i = 0; i < poziom; i++) {
            koszt = (int)(koszt * 1.6) + 10;
        }
        return koszt;
    }

    public void addPoziom() {
        if(czyMaszSurowceNaRozbudowe()) {
            zaplacKosztRozbudowy();
            poziom++;
            przychod = policzPrzychod(poziom);
            kosztDrewno = policzKosztDrewna(poziom);
            kosztKamien = policzKosztKamienia(poziom);
            kosztZboze = policzKosztZboza(poziom);
        }
    }
}
class Gospodarstwo extends Budynek {
    Gospodarstwo(int poziom) {
        super(poziom);
    }
    public void addSurowiec() {
        Surowce.zboze += getPrzychod();
    }
    public String toString() {
        return "Gospodarstwo:   Poziom: " + getPoziom() + " zapewnia przychód " + getPrzychod() + " zboża.    (Koszt: D-" + kosztDrewno + " K-" + kosztKamien + " G-" + kosztZboze + ")";
    }

    protected int policzKosztDrewna(int poziom) {
        int koszt = 0;
        for (int i = 0; i < poziom; i++) {
            koszt = (int)(koszt * 1.6) + 9;
        }
        return koszt;
    }

    protected int policzKosztKamienia(int poziom) {
        int koszt = 0;
        for (int i = 0; i < poziom; i++) {
            koszt = (int)(koszt * 1.5) + 10;
        }
        return koszt;
    }

    protected int policzKosztZboza(int poziom) {
        int koszt = 0;
        for (int i = 0; i < poziom; i++) {
            koszt = (int)(koszt * 1.4) + 10;
        }
        return koszt;
    }

    public void addPoziom() {
        if(czyMaszSurowceNaRozbudowe()) {
            zaplacKosztRozbudowy();
            poziom++;
            przychod = policzPrzychod(poziom);
            kosztDrewno = policzKosztDrewna(poziom);
            kosztKamien = policzKosztKamienia(poziom);
            kosztZboze = policzKosztZboza(poziom);
        }
    }
}

//to tylko komentarz do cwiczenia git'a
//kolejna linijka kodu