public class DataAnalysis{
    public static double avg(double[] nums){
        double sum = 0;
        for(int i = 0; i<nums.length;i++){
            sum += nums[i];
        }
        return sum/nums.length;
    }
    public static double stdev(double[] nums){
        double xAvg = avg(nums);
        double sum = 0;

        for(int i = 0; i<nums.length;i++){
            sum+=Math.pow((nums[i]-xAvg),2);
        }

        return sum/(nums.length-1);
    }
}