import java.util.HashMap;

public class Email {
    private boolean isSpam;
    private String[] words;
    private HashMap<String,Integer> freq;

    public Email(String[] text, boolean spam){
        isSpam = spam;

        for(int i = 0; i< text.length;i++){
            words[i] = text[i];
        }

        fillmap();
    }

    public void fillmap(){
        freq = new HashMap<>();

        for(String str: words){
            freq.putIfAbsent(str,0);
            freq.put(str,freq.get(str)+1);
        }
    }

    public int freqOf(String word){return freq.get(word);}
    public int getWordCount(){return words.length;}
    public String[] getWords(){return words;}
    public boolean getSpam(){return isSpam;}
    public HashMap<String,Integer> getFreq(){return freq;}
}
