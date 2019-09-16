package testHello;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BATTMD {
   public static void main(String[] args) {
      List<Integer> nums1 = Arrays.asList(1,2,3,4,5); /////记住这个初始化
      int[] nums = {1,2,2,3,4,5,5};
      //oddAndEven(nums);
      Integer[] nums1Array = nums1.toArray(new Integer[nums1.size()]);//注意如果是int[] 会出错。
      oddAndEven(nums1Array);
      //for(int num: nums1Array)
      //System.out.println(num);
      String s = "abcbaa";
      //System.out.println(maxLenOfNoRepeatSubString(s));
      System.out.println(allPlindromePartiiton(s).toString());
   }
   /**
    * 数组中奇数在左，偶数在右
    * @param nums
    */
   public static void oddAndEven(Integer[] nums) {
      int i=0, j=nums.length-1; 
      while(i<j) {
         while(i<j && nums[i] %2 !=0) i++; 
         while(i<j && nums[j] %2 ==0) j--;
         int tmp = nums[i];
         nums[i] = nums[j];
         nums[j] = tmp;
         i++; 
         j--;
      }
   }
   
  
   /**
    * 输入一个字符串，返回不包括重复字符的subset最大长度
    * abcabcbb->3; bbbbb ->1
    * j表示以i开始的最长不重复字符。如果charAt[j]已经有了，i到下一个， set removecharAt[i]
    */
   public static int maxLengthNorepeatSubstring(String s) {
      if(s.length()<=1) return s.length();
      Set<Character> set = new HashSet<>();
      int i=0, j=0;
      int maxLen = 0;
      while(i<s.length() && j< s.length()) {
         if(!set.contains(s.charAt(j))) {
            set.add(s.charAt(j));
            j++;
            maxLen = Math.max(maxLen, j-i);
         }
         else {
            set.remove(s.charAt(i));
            i++;
         }
      }
      return maxLen;
   }
   /**
    * 输入一个字符串，返回不包括重复字符的subset最大长度
    * 使用map感觉更高效
    */
   public static int maxLenOfNoRepeatSubString(String s) {
      if(s.length()<=1) return s.length();
      int maxLen = 0;
      Map<Character, Integer> map = new HashMap<>();
      for(int i=0, j=0; i < s.length(); i++) {
         if(map.containsKey(s.charAt(i))) {
            j = Math.max(j, map.get(s.charAt(i)) + 1); //abcbaa,当i=3, j=2 直接设到b以后的元素
         }
         map.put(s.charAt(i), i);
         maxLen = Math.max(maxLen, i-j+1);
      }
      return maxLen;
   }
   /**
    * x的平方根，精度到0.01
    */
   
   /**
    * INput一个string， aacab, 求所有可能的分割使得每一个substring都是回文
    * [["a", "a", "c", "a", "b"],
    *  ["a", "aca", "b"],
    *  ["aa", "c", "a", "b"]]
    */
   public static List<List<String>> allPlindromePartiiton(String s) {
      List<List<String>> result = new ArrayList<>();
      List<String> tmpList = new ArrayList<>();
      backTracking(result, tmpList, s, 0);
      return result;
   }
   private static void backTracking(List<List<String>> result, List<String> tmpList, String s, int start) {
      if(start == s.length())  result.add(new ArrayList<>(tmpList));
      else {
         for(int i=start; i<s.length(); i++) {
            if(isPalindrome(s, start, i)) {
               tmpList.add(s.substring(start, i+1));
               backTracking(result, tmpList, s, i+1);
               tmpList.remove(tmpList.size()-1);
            }
         }
      }
   }
   private static boolean isPalindrome(String s, int begin, int end) {
      if(begin > end) return false;
      while(begin < end) {
         if (s.charAt(begin) != s.charAt(end)) return false;
         begin ++;
         end --;
      }
      return true;
   }
   /**
    * 最大连续子串的和
    */
   public static int maxSubArray(int[] nums) {
      
   }
   /**
    * 最长公共子串
    */
   
   /**
    * input是一个数组，和target， 返回所有相加等于target的子序列。每个数只能用一次
    */
   
   /**
    * 一个字符串，一个单词字典，把字符串分成若干个子串，每个子串都在字典中，返回多少种分割办法
    * 139
    */
   
   /**
    * 字符串左移K位， space complexity O(1) time O(n)
    */

}
