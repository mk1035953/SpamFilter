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
    
    public boolean uniqueWords(){
        ArrayList<String> spamWords = new ArrayList<>();
        ArrayList<Integer> spamWordFreq = new ArrayList<>();
        ArrayList<String> hamWords = new ArrayList<>();
        ArrayList<Integer> hamWordFreq = new ArrayList<>();

        for(int i = 0; i<spam.size();i++){
            String[] words = spam.get(i).getWords();
            HashMap<String,Integer> freq = spam.get(i).getFreq();
            for(int j = 0; j<words.length;j++){
                if(!spamWords.contains(words[j])){
                    spamWords.add(words[j]);
                    spamWordFreq.add(freq.get(words[j]));
                }
            }
        }
        for(int i = 0; i<notSpam.size();i++){
            String[] words = notSpam.get(i).getWords();
            HashMap<String,Integer> freq = notSpam.get(i).getFreq();
            for(int j = 0; j<words.length;j++){
                if(!hamWords.contains(words[j])){
                    hamWords.add(words[j]);
                    hamWordFreq.add(freq.get(words[j]));
                }
            }
        }

        int swaps = 0;
        while(swaps !=0){
            swaps = 0;
            for(int i = 0; i<spamWords.size();i++){
                for(int j = 0; j<hamWords.size();j++){
                    if(spamWordFreq.get(j) < spamWordFreq.get(j)){
                        String temp = spamWords.get(i);
                        int tempFreq = spamWordFreq.get(i);
                        spamWords.set(i,hamWords.get(j));
                        spamWordFreq.set(i,hamWordFreq.get(j));
                        hamWords.set(j,temp);
                        hamWordFreq.set(j,tempFreq);
                        swaps++;
                    }
                }
            }
        }
        return false;
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
    public boolean doTests(Email mail){
        return false;
    }

}
