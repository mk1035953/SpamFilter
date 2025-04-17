import java.util.ArrayList;
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
        //0,1- spam; 2-3 notSpam
        ArrayList<ArrayList> arrs = new ArrayList<ArrayList>();
        
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
