package hr.ferit.mdudjak.healthdiary;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mario on 29.5.2017..
 */

public class HandleXML {

    private String urlString = null;
    List links,titles,descriptions,pubdates,images;
    private XmlPullParserFactory xmlFactoryObject;
    private String sFailedMessage="OK";
    public volatile boolean parsingComplete = true;

    public HandleXML(String url){
        this.urlString = url;
    }

    public List getLinks(){
        return links;
    }
    public List getTitles(){
        return titles;
    }
    public List getDescriptions(){
        return descriptions;
    }
    public List getPubDates(){
        return pubdates;
    }
    public List getImages(){
        return images;
    }
    public String getsFailedMessage(){return sFailedMessage;}

    public void parseXMLAndStoreIt(XmlPullParser myParser) {
        int event;
        String text=null;
        links = new ArrayList();
        descriptions = new ArrayList();
        titles = new ArrayList();
        pubdates = new ArrayList();
        images = new ArrayList();

        try {
            event = myParser.getEventType();

            while (event != XmlPullParser.END_DOCUMENT) {
                String name=myParser.getName();

                switch (event){
                    case XmlPullParser.START_TAG:
                        break;

                    case XmlPullParser.TEXT:
                        text = myParser.getText();
                        break;

                    case XmlPullParser.END_TAG:

                        if(name.equals("title")){
                            titles.add(text);
                        }

                        else if(name.equals("link")){
                            links.add(text);
                        }
                        else if(name.equals("description")) {

                            String[] descriptionText = text.split("<p>|</p>");
                            String[] strings = text.split("\"");
                            for (String string: strings) {
                                Log.i("STR", "string : " + string);
                                String[] newString = string.split("/");
                                for (String a: newString) {
                                    if (a.equals("images")){  //in your question pix or other words
                                        Log.i("URL", "Image Url is : " + string);
                                        images.add(string);

                                    }
                                }
                            }
                            int i=0;
                            for (String string: descriptionText) {
                                Log.i("DESC", "desc : " + string);
                                if(i++==1)
                                descriptions.add(string);
                            }

                        }

                        else if(name.equals("pubDate")){
                            pubdates.add(text);
                        }

                        break;
                }

                event = myParser.next();
            }

            parsingComplete = false;
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void fetchXML(){
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {

                try {
                    URL url = new URL(urlString);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                    conn.setReadTimeout(3000 /* milliseconds */);
                    conn.setConnectTimeout(4000 /* milliseconds */);
                    conn.setRequestMethod("GET");
                    conn.setDoInput(true);

                    // Starts the query
                    conn.connect();
                    InputStream stream = conn.getInputStream();

                    xmlFactoryObject = XmlPullParserFactory.newInstance();
                    XmlPullParser myparser = xmlFactoryObject.newPullParser();

                    myparser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                    myparser.setInput(stream, null);

                    parseXMLAndStoreIt(myparser);
                    stream.close();
                }

                catch (Exception e) {
                    sFailedMessage= String.valueOf(R.string.connectionTimeoutMessage);
                    parsingComplete=false;
                }
            }
        });
        thread.start();
    }
}
