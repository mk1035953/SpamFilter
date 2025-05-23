import java.util.HashMap;

public class Email {
    private boolean isEmpty;
    private boolean isSpam;
    private String[] words;
    private HashMap<String,Integer> freq;

    public Email(){
        words = new String[0];
        isEmpty = true;
        isSpam = true;
    }
    public Email(String[] text, boolean spam){
        words = new String[text.length];
        isEmpty = false;
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

    public boolean isEmpty(){return isEmpty;}
    public int freqOf(String word){return freq.get(word);}
    public int getWordCount(){return words.length;}
    public String[] getWords(){
        if(words.length<=0){
            String[] str = {};
            return str;
        }
        return words;
    }
    public boolean getSpam(){return isSpam;}
    public HashMap<String,Integer> getFreq(){return freq;}
}
