import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Reader {
    public static void main(String[] args) throws IOException, FileNotFoundException{
        ArrayList<Email> emails = new ArrayList<Email>(5);
        ArrayList<Email> trainData = new ArrayList<Email>();
        ArrayList<Email> testData = new ArrayList<Email>();
        
        String filePath = "spam_or_not_spam.csv"; // Replace with your file path

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] strs = line.split(",");
                try{
                    Email tempMail = new Email(strs[0].split(" "), Boolean.parseBoolean(strs[1]));
                    emails.add(tempMail);
                }
                catch(java.lang.ArrayIndexOutOfBoundsException e){
                    emails.add(new Email());
                }
            }

        }
        
        Collections.shuffle(emails);
        for(int i = 0; i<emails.size();i++){
            int mod = i%10;
            if(mod<8){
                trainData.add(emails.get(i));
            }
            else{
                testData.add(emails.get(i));
            }
        }

        boolean[] bools = new boolean[testData.size()];
        int count = 0;
        Test test = new Test(trainData);
        for(int i = 0; i<bools.length;i++){
            bools[i] = test.doTests(testData.get(i), count);
            count++;
        }

        int sum = 0;

        for(int i = 0;i<testData.size();i++){
            if((bools[i]&&testData.get(i).getSpam())||(!bools[i]&&!testData.get(i).getSpam())){
                sum++;
            }   
        }   
        
        System.out.println("Percent Correct:" + ((double)100*(double)sum/(double)testData.size()));
    }   
}
