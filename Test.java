import java.util.ArrayList;
import java.util.HashMap;
public class Test {
    private ArrayList<Email> spam;
    private ArrayList<Email> notSpam;
    private ArrayList<String> topTen;
    private ArrayList<Integer> topTenFreq;
    
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
        createTopTen();
    }

    public void createTopTen(){
        HashMap<String,Integer> spamMap = new HashMap<String,Integer>();
        HashMap<String,Integer> notSpamMap = new HashMap<String,Integer>();
        
        ArrayList<String> spamWords = new ArrayList<>();
        ArrayList<Integer> spamWordFreq = new ArrayList<>();
        ArrayList<String> hamWords = new ArrayList<>();
        ArrayList<Integer> hamWordFreq = new ArrayList<>();

        for(int i = 0; i<spam.size();i++){
            String[] words = spam.get(i).getWords();
            HashMap<String,Integer> freq = spam.get(i).getFreq();

            if(words.length<=0){
                continue;
            }

            System.out.println(words[0]);

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
        for(String s: hamWords){
            hamWordFreq.add(notSpamMap.get(s));
        }

        Bubble(spamWords,spamWordFreq);
        Bubble(hamWords,hamWordFreq);

        removeDupes(spamWords, hamWords, spamWordFreq);
        topTen = spamWords;
        topTenFreq = spamWordFreq;
    }
    
    public double uniqueWords(Email mail){
        if(mail.isEmpty()){
            return 0;
        }
        double ret = 0.9;

        String[] words = mail.getWords();
        int[] freqs = new int[words.length];
        for(int i = 0; i<words.length;i++){
            freqs[i] = mail.freqOf(words[i]);
        }

        if(topTen.size()<=0){
            return 0;
        }
        else if(topTen.size()<10){
            double retSum = 0;
            double retWords = ret/topTen.size();
            int maxFreq = 0;
            for(int i:topTenFreq){
                maxFreq+=i;
            }

            double[] mailFreqs = new double[freqs.length];
            for(int i = 0; i<freqs.length;i++){
                mailFreqs[i] = freqs[i];
            }
            double mailAvgFreq = DataAnalysis.avg(mailFreqs);

            for(int i = 0; i<topTen.size();i++){
                for(int j = 0; j<words.length;j++){
                    if(words[j].equals(topTen.get(i))){
                        retSum += retWords*((freqs[j]+topTenFreq.get(i))/maxFreq)*(freqs[j]/mailAvgFreq);
                    }
                }
            }
            return retSum;
        }

        double retSum = 0;
        double retWords = ret/10;
        int maxFreq = 0;
        for(int i = 0; i<10;i++){
            maxFreq+=topTenFreq.get(i);
        }

        double[] mailFreqs = new double[freqs.length];
        for(int i = 0; i<freqs.length;i++){
            mailFreqs[i] = freqs[i];
        }
        double mailAvgFreq = DataAnalysis.avg(mailFreqs);

        for(int i = 0; i<10;i++){
            for(int j = 0; j<words.length;j++){
                if(words[j].equals(topTen.get(i))){
                    retSum += retWords*((freqs[j]+topTenFreq.get(i))/maxFreq)*(freqs[j]/mailAvgFreq);
                }
            }
        }
        return retSum;
    }

    public void removeDupes(ArrayList<String> arr1, ArrayList<String> arr2, ArrayList<Integer> arr3){
        for(int i = 0; i<arr1.size();i++){
            for(int j = 0; j<arr2.size();j++){
                if(arr1.get(i).equals(arr2.get(j))){
                    arr1.remove(i);
                    arr3.remove(i);
                }
            }
        }
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
        int swaps = 1;
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
    public boolean doTests(Email mail, int mod){
        try{
        if(mail.isEmpty()){
            return true;
        }
        double ret = uniqueWords(mail);
        if(wordCount(mail)){
            ret+=0.1;
        }
        return ret>.75;
        }catch(java.lang.ArrayIndexOutOfBoundsException e){
            return true;
        }
    }

}
