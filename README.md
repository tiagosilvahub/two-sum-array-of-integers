# two-sum-array-of-integers
Given an array of integers, return indices of the two numbers such that they add up to a specific target. https://leetcode.com/problems/two-sum/ 

You may assume that each input would have exactly one solution, and you may not use the same element twice.

Example:
```
  Given nums = [2, 7, 11, 15], target = 9,

  Because nums[0] + nums[1] = 2 + 7 = 9,
  return [0, 1].
```

We can understnad the problem description as: there's a combination of 2 integers in the given array that sum to the target.

Immediately, we find a solution:

```
    public int[] solve(int[] nums, int target) {
        for(int i = 0; i < nums.length; i++) {
            for(int j = 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target && i!=j) {
                    return new int[]{i,j};
                }
            }
        }
        return new int[]{0,0};
    }
```

This solution has O(n^2) time complexity. It solves the problem, but it is not very interesting.

However, we can quickly reason that there should be a better solution. 

But intuitively, we can see that we are checking combinations that would never work.

For example, for a target of 9, any number that is bigger than 9 won't be part of the solution. It is a waste of time to sum that number with every other number.

Rather than trying all combinations, we should know what we are looking for. Complements. If we see a 2 and the target is 9, we will solve the problem if we find a 7. 2 and 7 being complements to reach 9.

So while going over the array, we should store the complements we would need to reach our target, then for each new array position we check if we just found a complement:

```
    public int[] solve(int[] nums, int target) {
        HashSet<Integer> hash = new HashSet<>();
        for(int i = 0; i < nums.length; i++) {
            if(hash.contains(target - nums[i])){
                int j = i;
                while(nums[--i] != target - nums[j]){} //trace back the array position
                return new int[]{i,j};
            }
            hash.add(nums[i]);
        }
        return new int[]{0,0};
    }
```

We have reached O(n) time. Thanks to hash operations, checking if the complement of a number was previously found can be done in amortized O(1) time. 

But we can do better, by simply storing the array position with the complement, we can immediately return the solution when we find a complement pair instead of tracing back:


```
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
            if(map.containsKey(target - nums[i])){
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i],i);
        }
        return new int[]{0,0};
```
