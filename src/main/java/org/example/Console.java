package org.example;

import java.util.*;
import java.util.stream.Collectors;

class console {
    public static void main(String[] args) {
        String input = "1,99,2,3,4,4,6,7,8,8,10";
        String[] numbers = input.split(",");
        Set<String> set = new LinkedHashSet<>(Arrays.asList(numbers));
        System.out.println(set);
        Set<String> set2 = new HashSet<>(Arrays.asList(numbers));
        System.out.println(set2);
        List<Integer> nums = new Random().ints(10,-10, 30).boxed().collect(Collectors.toList());
        System.out.println(nums);
        List<Integer> newCollection = new ArrayList<>();
        for (int i = 0; i < nums.size(); i++) {
            if (nums.get(i) > 5) {
                newCollection.add(i);
            }
        }
        System.out.println(newCollection);
        nums.removeIf(x -> x > 20);
        System.out.println(nums);
        //var it = nums.iterator();
        //while (it.hasNext()) {
        //    if (it.next() > 20) {
        //        it.remove();
        //    }
        //}
        nums.sort(null);
        System.out.println(nums);
    }
}
