/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.webarchiv.webanalyzer.multithread.testobj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author praso
 */
public class MakeTestCrawlURI {

    public String text = "dovednost dovézt abandonovat abdominální mahátma " +
            "slučitelný zakašlání zrušitelný žďár žív žvýkačka this is the" +
            "occasionaly word in english, pure clear colosal track a ješte" +
            "bych chtěl neco říct, jenže já neumím žádne slova po česky a" +
            "právě proto tu teď musím psát takové blbosti" +
            "emaily : email@email.sk praso@alive.cz nexo@askdjflskdf.com" +
            ",oddelene@oddelene.cz a tak a tedko telefonne cisla: " +
            "+421 774 920 666 alebo +420 902 438 336 " +
            " +420234234234 or 234234234 or 23423423423434" +
            "html elements: html <lang=sk/><lang='cs' a lang= sk : lang=cs;" +
            "this should be found xml:lang=\"en\" but this souldn't" +
            "ahreflang=\"en\" žív,žívanému,žívaly,žívala,aaa,aaaa,aaaaa, aaaaaa, při" +
            " podrápaly, podrápanou podrápala podrápal aabbccddeeffgghh aachena " +
            "palaštický žžže žůžo žůrková";

    public SimpleTestCrawlURI createEmptyCrawlURI() {
        try {
            SimpleTestCrawlURI curi = new SimpleTestCrawlURI();
            curi.setUrl(new URL("http://praso.webzdarma.cz/gitara1.flv"));
            curi.setContent(text);
            curi.setOutLinks(new HashSet());
            return curi;
        } catch (MalformedURLException ex) {
            throw new RuntimeException("cannot create URL", ex);
        }
    }

    public SimpleTestCrawlURI createEmptyCrawlURI(String url) {
        try {
            SimpleTestCrawlURI curi = new SimpleTestCrawlURI();
            curi.setContent(text);
            curi.setUrl(new URL(url));
            curi.setOutLinks(new HashSet());
            return curi;
        } catch (MalformedURLException ex) {
            throw new RuntimeException("cannot create URL=" + url);
        }
    }

    /**
     * Creates URLs from file outlinks.txt, stored in folder data.
     * @return set of test curis
     */
    public Set<SimpleTestCrawlURI> createSimpleTestCrawlURIs() {
        Set<SimpleTestCrawlURI> curis = new HashSet<SimpleTestCrawlURI>();

        // read uris from file, also write stripped uris from logs to file
        File file = new File("_anal/outscope_f");
        File out = new File("_anal/out.txt");
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            FileWriter fw = new FileWriter(out);
            BufferedWriter bw = new BufferedWriter(fw);
            String line = null;
            String uri = null;
            int index;
            while ((line = br.readLine()) != null) {
                if ((index = line.indexOf("http")) != -1) {
                    uri = line.substring(index);
                    // add to set curis
                    curis.add(createEmptyCrawlURI(uri));
                    bw.write(uri);
                    bw.newLine();
                }
            }
            bw.close();
            br.close();
            fr.close();
            fw.close();

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return curis;
    }

    /**
     * Creates URLs from file outlinks.txt, stored in folder data.
     * @return set of test curis
     */
    public Set<String> createTestURIs() {
        Set<String> curis = new HashSet<String>();

        // read uris from file, also write stripped uris from logs to file
        File file = new File("_anal/outscope_f");
        File out = new File("_anal/out.txt");
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            FileWriter fw = new FileWriter(out);
            BufferedWriter bw = new BufferedWriter(fw);
            String line = null;
            String uri = null;
            int index;
            while ((line = br.readLine()) != null) {
                if ((index = line.indexOf("http")) != -1) {
                    uri = line.substring(index);
                    // add to set curis
                    curis.add(uri);
                    bw.write(uri);
                    bw.newLine();
                }
            }
            bw.close();
            br.close();
            fr.close();
            fw.close();

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return curis;
    }

    public static void main(String[] args) {
        MakeTestCrawlURI m = new MakeTestCrawlURI();
        m.createTestURIs();
    }
}
