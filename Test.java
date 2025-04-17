import java.util.ArrayList;
import java.util.HashMap;
public class Test {
    private ArrayList<Email> spam;
    private ArrayList<Email> notSpam;
    
    public Test(ArrayList<Email> training){
        spam = new ArrayList<Email>();
        notSpam = new ArrayList<Email>();

        for(Email e:training){
            if(e.getSpam()){
                spam.add(e);
            }
            else{
                notSpam.add(e);
            }
        }
    }
    
    public double uniqueWords(){
        double ret = 0;

        HashMap<String,Integer> spamMap = new HashMap<String,Integer>();
        HashMap<String,Integer> notSpamMap = new HashMap<String,Integer>();
        
        ArrayList<String> spamWords = new ArrayList<>();
        ArrayList<Integer> spamWordFreq = new ArrayList<>();
        ArrayList<String> hamWords = new ArrayList<>();
        ArrayList<Integer> hamWordFreq = new ArrayList<>();

        for(int i = 0; i<spam.size();i++){
            String[] words = spam.get(i).getWords();
            HashMap<String,Integer> freq = spam.get(i).getFreq();
            for(int j = 0; j<words.length;j++){
                if(spamMap.get(words[j])==null){
                    spamWords.add(words[j]);
                    spamMap.put(words[j],freq.get(words[j]));
                }
                else{
                   spamMap.put(words[j],spamMap.get(words[j])+freq.get(words[j]));
                }
            }
        }
        for(String s: spamWords){
            spamWordFreq.add(spamMap.get(s));
        }

        for(int i = 0; i<notSpam.size();i++){
            String[] words = notSpam.get(i).getWords();
            HashMap<String,Integer> freq = notSpam.get(i).getFreq();
            for(int j = 0; j<words.length;j++){
                if(notSpamMap.get(words[j])==null){
                    hamWords.add(words[j]);
                    notSpamMap.put(words[j],freq.get(words[j]));
                }
                else{
                   notSpamMap.put(words[j],notSpamMap.get(words[j])+freq.get(words[j]));
                }
            }
        }
        for(String s: spamWords){
            hamWordFreq.add(notSpamMap.get(s));
        }

        Bubble(spamWords,spamWordFreq);
        Bubble(hamWords,hamWordFreq);

        
        
        return ret;
    }

    public boolean wordCount(Email mail){
        double[] spamCounts = new double[spam.size()];
        double[] notCounts = new double[notSpam.size()];

        for(int i = 0; i<spam.size();i++){
            spamCounts[i] = spam.get(i).getWordCount();
        }
        for(int i = 0; i<notSpam.size();i++){
            notCounts[i] = notSpam.get(i).getWordCount();
        }   

        double spamAvg = DataAnalysis.avg(spamCounts);
        double spamStdev = DataAnalysis.stdev(spamCounts);
        double notAvg = DataAnalysis.avg(notCounts);
        double notStdev = DataAnalysis.stdev(notCounts);

        if((Math.abs(spamAvg-notAvg)<(spamStdev+notStdev))){
            return false;
        }
        else{
            int test = mail.getWordCount();

            if(test<=(spamAvg+spamStdev)||test>=(spamAvg-spamStdev)){
                return true;
            }
        }

        return false;
    }
    public void Bubble(ArrayList<String> arr1, ArrayList<Integer> arr2){
        int swaps = 0;
        while(swaps !=0){
            swaps = 0;
            for(int i = 0; i<arr1.size()-1;i++){
                if(arr2.get(i) < arr2.get(i+1)){
                    String temp = arr1.get(i);
                    int tempFreq = arr2.get(i);
                    arr1.set(i,arr1.get(i+1));
                    arr2.set(i,arr2.get(i+1));
                    arr1.set(i+1,temp);
                    arr2.set(i+1,tempFreq);
                    swaps++;
                }
                
            }
        }
    }
    public boolean doTests(Email mail){

        return false;
    }

}
