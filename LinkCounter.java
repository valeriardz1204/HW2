import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
/**Class: LinkCounter.java
* @author Valeria Rodriguez
* @version 1.0
* Course: ITEC 3150 Spring 2026
* Written: January 8, 2025
*
* This class counts the number of links on a website. The class WebsiteBuilder provides to build links.dat from links.dat.
 The class Website helps to read objects from the file.
*/
public class LinkCounter {

   
    private ArrayList<website> sites = new ArrayList&lt;&gt;();

    public static void main(String[] args) {
        LinkCounter app = new LinkCounter();

        app.readBinaryFile("links.dat");

        app.printWebsites();

        HashSet<string> names = app.buildNameSet();
        app.printNames(names);

        HashMap<string, hashset<string="">&gt; nameToUrls = app.buildHashMap(names);
        HashMap<string, integer=""> counts = app.buildCountMap(nameToUrls);

        app.printCounts(counts);
    }

  
    private void readBinaryFile(String fileName) {
       
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(fileName))) {
        while (true) {
        Website w = (Website) input.readObject();
        sites.add(w);
        }
        } catch (EOFException eof) {
     
        } catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
        }
    }
 
    private void printWebsites() {
        
        for (Website w : sites) {
            System.out.println(w);
        }
    }

    private HashSet<string> buildNameSet() {
        HashSet<string> names = new HashSet&lt;&gt;();
        for (Website w : sites) {
            names.add(w.getName());
        }
        return names;
    }

    private void printNames(HashSet<string> names) {
        System.out.println("\tNames");
        for (String name : names) {
            System.out.println(name);
        }
    }

    private HashMap<string, hashset<string="">&gt; buildHashMap(HashSet<string> names) {
        HashMap<string, hashset<string="">&gt; map = new HashMap&lt;&gt;();
        for (String name : names) {
            HashSet<string> urls = new HashSet&lt;&gt;();
            for (Website w : sites) {
                if (w.getName().equals(name)) {
                    urls.add(w.getURL());
                }
            }
            map.put(name, urls);
        }
        return map;
    }
   
    private HashMap<string, integer=""> buildCountMap(HashMap<string, hashset<string="">&gt; hashed) {
        HashMap<string, integer=""> counts = new HashMap&lt;&gt;();
        for (String name : hashed.keySet()) {
            int count = hashed.get(name).size();
            counts.put(name, count);
        }
        return counts;
    }
        
    private void printCounts(HashMap<string, integer=""> counts) {
        System.out.println("\tWebsite counts");
        System.out.println("Site\t\tCount");
        for (String name : counts.keySet()) {
            System.out.println(name + "\t\t" + counts.get(name));
        }
       
    }
}</string,></string,></string,></string,></string></string,></string></string,></string></string></string></string,></string,></string></website>
