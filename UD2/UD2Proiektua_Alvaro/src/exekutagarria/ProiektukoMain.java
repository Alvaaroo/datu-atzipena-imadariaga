package exekutagarria;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.Playlist;
import model.PlaylistTrack;
import model.Track;

import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ProiektukoMain {

    public static SessionFactory sf = new Configuration().configure().buildSessionFactory();

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int aukera;
        boolean blukea = true;
        while (blukea) {

            System.out.println("Menua:");
            System.out.println("--------------------------");
            System.out.println("0.-Programa amaitu");
            System.out.println("1.-Playlist bat sortu");
            System.out.println("2.-Playlist-ak ikusi");
            System.out.println("3.-Playlist bat borratu");
            System.out.println("4.-Abestiak ikusi izenaren lehenengo letraren arabera");
            System.out.println("5.-Playlist baten izena aldatu");
            System.out.println("6.-Playlist batean abestia sartu");
            System.out.println("7.-Playlist baten abestiak ikusi");
            System.out.println("8.-Abestiak ikusi zuk nahi duzun kantitatearen arabera");

            System.out.println("---------------------------------------------------------");
            System.out.println("Aukeratu zenbaki bat: ");
            aukera = in.nextInt();
            switch (aukera) {
                case 0:
                    System.out.println("Eskerrik asko programa erabiltzeagatik!!!");
                    blukea = false;
                    break;
                case 1:
                    System.out.println("Idatzi sortu nahi duzun playlistaren izena: ");
                    String izena = in.next();
                    System.out.println("---------------------------------------------------------");
                    playlistSortu(new Playlist(1, izena));
                    System.out.println("Sortu egin da " + izena + " playlista.");
                    break;
                case 2:
                    System.out.println("Hauek dira sortuta daunde playlistak: ");
                    System.out.println("---------------------------------------------------------");
                    playlistIkusi();
                    break;
                case 3:
                    System.out.println("Idatzi borratu nahi duzun playlistaren id-a: ");
                    int borratuId = in.nextInt();
                    System.out.println("---------------------------------------------------------");
                    playlistEzabatu(borratuId);
                    break;
                case 4:
                    System.out.println("Sartu abestiaren izenaren lehengo letra: ");
                    String letra = in.next();
                    System.out.println("---------------------------------------------------------");
                    datuakIkusiTrack(letra);
                    break;
                case 5:
                    System.out.println("Sartu aldatu nahi duzun playlistaren id: ");
                    int aldatuId = in.nextInt();
                    System.out.println("Sartu playlistaren izen berria: ");
                    String aldatuIzena = in.next();
                    System.out.println("---------------------------------------------------------");
                    playlistIzenaAldatu(aldatuId, aldatuIzena);
                    System.out.println("Playlist-aren izena aldatu egin da");
                    break;
                case 6:
                    System.out.println("Sartu playlistaren izena: ");
                    String playlistIzena = in.next();
                    System.out.println("Sartu abestiaren izena: ");
                    String abestiIzena = in.next();
                    abestiaGehituPlaylistean(playlistIzena, abestiIzena);
                    break;
                case 7:
                    System.out.println("Sartu playlistaren izena: ");
                    String izenaPlaylist = in.next();
                    playlistEtaAbestiakIkusi(izenaPlaylist);
                    break;
                case 8:
                    System.out.println("Zenbat abesti ikusi nahi dituzu?: ");
                    int abestikop = in.nextInt();
                    datuakIkusiTrackKantitatea(abestikop);

            }
        }
    }

    public static void playlistSortu(Playlist p) {
        Session saioa = null;
        try {
            saioa = sf.openSession();
            saioa.beginTransaction();
            saioa.save(p);
            saioa.getTransaction().commit();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            saioa.close();
        }

    }

    public static void playlistIkusi() {

        Session saioa = sf.openSession();
        saioa.beginTransaction();
        List result = saioa.createQuery("from Playlist").list(); // HQL deitzen dan lengoaia idatziko dugu Querya
        for (Playlist m : (List<Playlist>) result) {
            System.out.println(m.getId()+".- "+m.getName());
        }
        saioa.getTransaction().commit();
        saioa.close();
    }

    public static void playlistEzabatu(int idPlaylist) {
        Session saioa = null;
        Playlist ik = null;
        Transaction tx = null;
        try {
            saioa = sf.openSession();
            tx = saioa.beginTransaction();
            ik = (Playlist) saioa.load(Playlist.class, idPlaylist);
            saioa.delete(ik);
            tx.commit();
            System.out.println("Ezabatu egin da " + idPlaylist + " playlista");
        } catch (ObjectNotFoundException onfe) {
            System.out.println("Playlist hori ez dago");
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            saioa.close();
        }
    }

    public static void datuakIkusiTrack(String letra) {

        Session saioa = sf.openSession();
        saioa.beginTransaction();
        List result = saioa.createQuery("from Track WHERE Name LIKE '" + letra + "%'" + " OR Name LIKE '" + letra.toUpperCase() + "%'").list(); // HQL deitzen dan lengoaia idatziko dugu Querya
        for (Track t : (List<Track>) result) {
            System.out.println(t);
        }
        saioa.getTransaction().commit();
        saioa.close();
    }

    public static void playlistIzenaAldatu(int id, String izena) {
        Session saioa = null;
        Playlist ik = null;
        Transaction tx = null;
        try {
            saioa = sf.openSession();
            tx = saioa.beginTransaction();
            ik = (Playlist) saioa.load(Playlist.class, id);
            ik.setName(izena);
            saioa.save(ik);
            tx.commit();
            System.out.println("Izena aldatu da" + id + " .-ko playlistari");
        } catch (ObjectNotFoundException onfe) {
            System.out.println("Playlist hori ez dago");
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            saioa.close();
        }
    }

    public static void abestiaGehituPlaylistean(String playlistIzena, String trackIzena) {
        Session saioa = null;
        Transaction tx = null;
        int playlistId = 0;
        int trackId = 0;
        try {
            saioa = sf.openSession();
            saioa.beginTransaction();
            List result = saioa.createQuery("from Playlist WHERE Name = '" + playlistIzena + "'").list(); // HQL deitzen dan lengoaia idatziko dugu Querya
            for (Playlist t : (List<Playlist>) result) {
                playlistId = t.getId();

            }
            List result2 = saioa.createQuery("from Track WHERE Name = '" + trackIzena + "'").list(); // HQL deitzen dan lengoaia idatziko dugu Querya
            for (Track t : (List<Track>) result2) {
                trackId = t.getId();

            }
            saioa.save(new PlaylistTrack(playlistId, trackId));
            saioa.getTransaction().commit();
            saioa.close();
            System.out.println("Sartu egin da " + trackIzena + " abestia '" + playlistIzena + "' playlist-ean");

        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    public static void playlistEtaAbestiakIkusi(String playlistIzena) {
        Session saioa = null;
        Transaction tx = null;
        int playlistId = 0;
        int trackId = 0;
        String trackIzena = "";
        int count = 1;
        try {
            saioa = sf.openSession();
            saioa.beginTransaction();

            List result = saioa.createQuery("from Playlist WHERE Name = '" + playlistIzena + "'").list(); // HQL deitzen dan lengoaia idatziko dugu Querya
            for (Playlist t : (List<Playlist>) result) {
                playlistId = t.getId();

            }
            List result2 = saioa.createQuery("from PlaylistTrack WHERE PlaylistId = " + playlistId).list(); // HQL deitzen dan lengoaia idatziko dugu Querya
            List<Integer> trackIds = new ArrayList<>();
            for (PlaylistTrack t : (List<PlaylistTrack>) result2) {
                trackId = t.getTrackId();
                trackIds.add(trackId);

            }
            System.out.println(playlistIzena + ": ");
            System.out.println("--------------------------------------");

            for (int i = 0; i < trackIds.size(); i++) {
                List result3 = saioa.createQuery("from Track WHERE TrackId =" + trackIds.get(i)).list();
                for (Track t : (List<Track>) result3) {
                    trackIzena = t.getName();
                    System.out.println(count + ".- " + trackIzena);
                    count++;
                }
            }

        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    public static void datuakIkusiTrackKantitatea(int zenbat) {

        Session saioa = sf.openSession();
        saioa.beginTransaction();
        List result = saioa.createQuery("from Track").setMaxResults(zenbat).list(); // HQL deitzen dan lengoaia idatziko dugu Querya
        for (Track t : (List<Track>) result) {
            System.out.println(t);
        }
        saioa.getTransaction().commit();
        saioa.close();
    }
}
