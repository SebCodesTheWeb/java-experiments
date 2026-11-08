package textproc;

import java.util.Map;
//import java.util.HashMap;
import java.util.TreeMap;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;

public class GeneralWordCounter implements TextProcessor {
        private Set<String> stopwords;
        private Map<String,Integer> counter = new TreeMap<>();
    
    
    public GeneralWordCounter(Set<String> stopwords){
        this.stopwords = stopwords;
    }

    public void process(String w){
        if (stopwords.contains(w)){
            return;
        }
        if (counter.containsKey(w)){
            int i = counter.get(w);
            i++;
            counter.put(w,i);
    
        }
        else{
            counter.put(w,1);
        }

    }

    public List<Map.Entry<String, Integer>> getWordList() {
        Set <Map.Entry<String, Integer>> wordSet = counter.entrySet();
        List<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordSet);
        return wordList;
    }

    public void report() {
    Set <Map.Entry<String, Integer>> wordSet = counter.entrySet();
    List<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordSet);
    wordList.sort((j1,j2)-> {int result = j2.getValue() - j1.getValue();
        if (result != 0){
            return result;

        }
        else{
            return j1.getKey().compareTo(j2.getKey());
        }
    });
    for (int i = 0; i<5; i++){
        String key = wordList.get(i).getKey();
        Integer value = wordList.get(i).getValue();
        System.out.println(key + ": "+ value);
    }
    }
}