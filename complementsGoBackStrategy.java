import java.util.HashSet;

public class complementsGoBackStrategy implements SolutionStrategy {

    @Override
    public int[] solve(int[] nums, int target) {
        HashSet<Integer> hash = new HashSet<>();
        for(int i = 0; i < nums.length; i++) {
            if(hash.contains(target - nums[i])){
                int j = i;
                while(nums[--i] != target - nums[j]){}
                return new int[]{i,j};
            }
            hash.add(nums[i]);
        }
        return new int[]{0,0};
    }
}
