public class Main1{
    public static String pairSumSorted(int[] nums, int target){
        int left = 0;
        int right = nums.length - 1;

        while (left < right){
            int sum = nums[left] + nums[right];
            if (sum == target){
                return "(" + nums[left] + "," + nums[right] + ")";
            }
            else if(sum < target){
                left++;
            }
            else{
                right--;
            }
        }
        return "Not Found";
    }

    public static void main(String[] args){
        int[] nums = {-4, -1, 0, 3, 5, 9};
        int target = 4;

        System.out.println(pairSumSorted(nums, target));

        int[] nums2 = {1, 2, 3};
        int target2 = 100;

        System.out.println(pairSumSorted(nums2, target2));
    }
}