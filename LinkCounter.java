import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * ITEC 3150 - HW2
 *
 * Student File (YOU complete this file)
 *
 * Do not modify Website or WebsiteBuilder (except the package).
 *
 * Task Summary:
 * 1) Run WebsiteBuilder to create links.dat from links.txt
 * 2) Read Website objects from links.dat using ObjectInputStream until EOFException
 * 3) Store objects in ArrayList<Website>
 * 4) Print all Website objects using toString()
 * 5) Build a HashSet<String> of unique website names and print it
 * 6) Build HashMap<String, HashSet<String>> name -> unique URLs
 * 7) Build HashMap<String, Integer> name -> count of unique URLs
 * 8) Print the final counts
 */
public class LinkCounter {

    // Store the Website objects from the file for later processing
    private ArrayList<Website> sites = new ArrayList<>();

    public static void main(String[] args) {
        LinkCounter app = new LinkCounter();

        // TODO 1: read links.dat into app.sites
        app.readBinaryFile("links.dat");

        // TODO 2: print all Website objects
        app.printWebsites();

        // TODO 3: build a set of unique names and print it
        HashSet<String> names = app.buildNameSet();
        app.printNames(names);

        // TODO 4: build a HashMap<String, HashSet<String>> name -> unique URLs
        HashMap<String, HashSet<String>> nameToUrls = app.buildHashMap(names);

        // TODO 5: build a HashMap<String, Integer> name -> count
        HashMap<String, Integer> counts = app.buildCountMap(nameToUrls);

        // TODO 6: print final counts
        app.printCounts(counts);
    }

    /**
     * Read Website objects from a binary file.
     * Use EOFException to stop reading.
     */
    private void readBinaryFile(String fileName) {
        // TODO: Use ObjectInputStream + FileInputStream
        // Read objects in a loop and add each Website to sites
        //
        // HINT:
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(fileName))) {
        while (true) {
        Website w = (Website) input.readObject();
        sites.add(w);
        }
        } catch (EOFException eof) {
        //     // end of file (normal)
        } catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
        }
    }

    /**
     * Print all Website objects from the ArrayList.
     */
    private void printWebsites() {
        // TODO: loop through sites and print each Website object
        for (Website w : sites) {
            System.out.println(w);
        }
    }

    /**
     * Build a HashSet of unique website names.
     */
    private HashSet<String> buildNameSet() {
        HashSet<String> names = new HashSet<>();
        for (Website w : sites) {
            names.add(w.getName());
        }
        // TODO: Create HashSet<String> and add w.getName() for each Website in sites
        return names;
    }

    /**
     * Print the unique names set.
     */
    private void printNames(HashSet<String> names) {
        // TODO: Print the names set (format similar to sample output)
        // Example:
        System.out.println("\tNames");
        for (String name : names) {
            System.out.println(name);
        }
    }

    /**
     * Build HashMap<String, HashSet<String>> mapping name -> set of unique URLs.
     * (Suggested method in assignment.)
     */
    private HashMap<String, HashSet<String>> buildHashMap(HashSet<String> names) {
        HashMap<String, HashSet<String>> map = new HashMap<>();
        for (String name : names) {
            HashSet<String> urls = new HashSet<>();
            for (Website w : sites) {
                if (w.getName().equals(name)) {
                    urls.add(w.getURL());
                }
            }
            map.put(name, urls);
        }
        return map;
    }
        // TODO:
        // For each name in names:
        //   Create HashSet<String> urls = new HashSet<>();
        //   Loop through sites:
        //       If Website name matches, add Website URL to urls
        //   Put (name, urls) into HashMap

    /**
     * Build HashMap<String, Integer> mapping name -> count of unique URLs.
     */
    private HashMap<String, Integer> buildCountMap(HashMap<String, HashSet<String>> hashed) {
        HashMap<String, Integer> counts = new HashMap<>();
        for (String name : hashed.keySet()) {
            int count = hashed.get(name).size();
            counts.put(name, count);
        }
        return counts;
    }
        // TODO:
        // For each entry (name -> urlSet) in hashed:
        //    count = urlSet.size()
        //    put (name, count) into counts HashMap

    /**
     * Print the final counts.
     */
    private void printCounts(HashMap<String, Integer> counts) {
        System.out.println("\tWebsite counts");
        System.out.println("Site\t\tCount");
        for (String name : counts.keySet()) {
            System.out.println(name + "\t\t" + counts.get(name));
        }
        // TODO:
        // Print a header like:
        // System.out.println("\tWebsite counts");
        // System.out.println("Site\t\tCount");
        // Then print each site and count.
    }
}
