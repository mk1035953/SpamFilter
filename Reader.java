import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Reader {
    public static void main(String[] args) throws FileNotFoundException{
        File file = new File("spam_or_not_spam.csv");
        Scanner sc = new Scanner(file);
        ArrayList<Email> emails = new ArrayList<Email>();
        ArrayList<Email> trainData = new ArrayList<Email>();
        ArrayList<Email> testData = new ArrayList<Email>();

        while(sc.hasNextLine()){
            String[] strs = sc.nextLine().split(",");
            emails.add(new Email(strs[0].split(" "), Boolean.parseBoolean(strs[1])));
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
        for(int i = 0; i<bools.length;i++){
            Test test = new Test(trainData);
            bools[i] = test.doTests(testData.get(i));
        }
    }   
    
}
