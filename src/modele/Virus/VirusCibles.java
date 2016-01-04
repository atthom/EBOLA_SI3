package modele.Virus;

import java.util.HashMap;
import java.util.List;
import java.util.Arrays;


/**
 * Created by Adrien on 04/01/2016.
 */
public class VirusCibles {
    public static final HashMap<String,List<String>> VIRUS ;
    static{
        VIRUS = new HashMap<>();
        VIRUS.put("H1N1",Arrays.asList("Humain","Cochon"));
        VIRUS.put("H5N1",Arrays.asList("Humain","Poulet","Canard"));
    }
}
