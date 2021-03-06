/*
 * Toto bude vstupne rozhranie pre webanalyzer.
 * Poskytuje metody na jeho inicializaciu, pred nou je potrebne vyplnit subor
 * properties, tak aby sa dali nainicializovat vsetky managery.
 */

package cz.webarchiv.webanalyzer.multithread;

import cz.webarchiv.webanalyzer.multithread.analyzer.UrlAnalyzer;
import cz.webarchiv.webanalyzer.multithread.managers.WebAnalyzerManager;
import cz.webarchiv.webanalyzer.multithread.mime.Content;
import java.util.Set;
import org.apache.log4j.Logger;

/**
 *
 * @author praso
 */
public class WebAnalyzer {

    private static final Logger log4j = Logger.getLogger(WebAnalyzer.class);
    private static final WebAnalyzer INSTANCE = new WebAnalyzer();
    
    // todo pridat properties
    
    /**
     * Singleton
     * @return jedina instancia tejto triedy
     */
    public static WebAnalyzer getInstance() {
        return INSTANCE;
    }
    
    /**
     * Private constructor
     */
    private WebAnalyzer() {
        // singleton
    }
    
    /**
     * Inicializuje potrebne objekty.
     */
    public void initialize() throws Exception {
        PropertiesReader.getInstance().loadPropertiesReader();
        WebAnalyzerManager.getInstance().initializeManagers();
    }
    
    /**
     * Uzavrie pouzite streamy a subory.
     */
    public void close() throws Exception {
        WebAnalyzerManager.getInstance().closeManagers();
        WebAnalyzerProperties.getInstance().clearSearchers();
    }
    
    /**
     * Spusti analyzu predanych parametrov, ktore predstavuju nazov url, jej
     * obsah a linky, ktore sa na nej nachadzaju.
     * @param urlName, nazov URL
     * @param urlContent, textovy obsah URL
     * @param urlOutlinks, odkazy najdene v obsahu URL
     * @param contentType, predpokladany contentType danej URL
     * @return true prave ked je URL validna podla nadefinovanych properties, 
     * dosiahla potrebny pocet bodov.
     */
    public boolean run(String urlName, String urlContent, Set<String> urlOutlinks,
            String contentType) {
        // todo otestovat ci nehat jednu metodu alebo volat UrlAnalyzer osobitne
        return (new UrlAnalyzer()).analyze(urlName, urlContent, urlOutlinks,
                contentType);
    }
    
    /**
     * Vrati definovanu hlbku na archivovanie validnej url.
     * @return int hlbka do ktorej sa budu archivovat odkazy z validnej url
     */
    public int getDepthToArchive() {
        return WebAnalyzerProperties.getInstance().getDepthToArchive();
    }
    
    /**
     * Zisti content type url podla nazvu url, presne podla jej koncovky
     * @param url
     * @return true only if url has text contentType
     */
    public boolean isContetTypeText(String url)
    {
        String resultType = (new Content()).getContentType(null, url, null);
        return resultType != null && resultType.indexOf("text") > -1;
    }
    
}
