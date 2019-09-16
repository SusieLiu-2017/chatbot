package testHello;

import java.util.*;

public class BackTracing {
   static Map<String, String> map = new HashMap<String, String>() {
      {
         put("2", "abc");
         put("3", "def");
         put("4", "ghi");
         put("5", "jkl");
         put("6", "mno");
         put("7", "pqrs");
         put("8", "tuv");
         put("9", "wxyz");
   } 
   };
   public static void main(String[] args) {
      String digits = "234";
      //System.out.println(letterCombinations(digits).toString());
      //System.out.println(letterCombinationsIternative17(digits).toString());
      //System.out.println(generateParentheses(3).toString());
      int[] candidates1 = {2,5,1};
      int[] candidates2 = {2,5,2};
      int target =5;
      //System.out.println(permutations(candidates1).toString());
      //System.out.println("==============");
      System.out.println(permutationII(candidates2).toString());
      int n=4, k=2;
      //System.out.println("77 ,the result is  \n" + combinations77(n,k).toString());
      int[] nums78 = {1,4,3};
      //System.out.println("78 ,the result is  \n" + subsets(nums78).toString());
      int[] nums90 = {1,2,2};
      //System.out.println("90 ,the result is  \n" + subsetsWithDup(nums90).toString());
      String string131 = "aab";
      System.out.println("131 ,the result is  \n" + partition(string131).toString());
      int k216=3, n216=9;
      //System.out.println("216, the result is \n" + combinationSumIII216(k216,n216).toString());
      int n401 = 1;
      //System.out.println("401, the result is \n" + readBinaryWatch401(n401).toString());
       String s = "a1b2";
       //System.out.println("784, the result is \n" + letterCasePermutation(s));
      
   }
   
   /**784: Letter case permutation
    * input: a1b2. output: [A1B2, A1b2, a1B2, a1b2]
    * input: 3z4. output: ["3z4", "3Z4"]
    */
   public static List<String> letterCasePermutation(String s) {
      List<String> result = new ArrayList<>();
      //backTracking784(result, 0, s.toCharArray());
      dfs(result, 0, s.toCharArray());
      return result;
   }
   /*'abc'
    * [ABC, ABc, AbC, Abc, aBC, aBc, abC, abc]
    */
   private static void backTracking784(List<String> result, int start, char[] sArr) {
      if(start==sArr.length) result.add(new String(sArr));//
      else {
         if(Character.isLetter(sArr[start])) {//is letter
            sArr[start] = Character.toUpperCase(sArr[start]);
            backTracking784(result, start+1,sArr);
            sArr[start] = Character.toLowerCase(sArr[start]);
            backTracking784(result, start+1, sArr);
         }
      
         else 
            backTracking784(result, start+1, sArr);// is digit
   }
   }
   /*
    * upper cae a-z: 65-90
    * lower case:    97-122
    * i 代表处理第i个字符
    */
   private static void dfs(List<String> ans, int i, char[] S) {
      if(i==S.length) ans.add(new String(S));
      else {
         dfs(ans, i+1, S);//不管是字母还是数字，都是自己，移到下一位
         //if(!Character.isLetter(S[i])) return; //不是字母直接return
         if(Character.isLetter(S[i])) {
         S[i] ^=1<<5;   //如果是字母处理第二个branch先toggle一下然后递归，递归完以后在toggle回来。省去了对字符串的copy
         dfs(ans, i+1, S);
         S[i] ^=1<<5;
         }
      }
   }
   /**401 easy Binary watch: n=1 meaning 1 light on
    * output: [0:32, 0:16, 0:08, 0:04, 0:02, 0:01, 8:00, 4:00, 2:00, 1:00]
    * 看评论可以优化还有更好的办法，没有看懂
    */
   public static List<String> readBinaryWatch401(int num) {
      List<String> result = new ArrayList<>();
      int[] hourArr = new int[] {8,4,2,1};
      int[] minArr = new int[] {32,16,8,4,2,1};
      for(int i=0; i<=num; i++) {
         List<Integer> listHour = generateDigit(hourArr, i);
         List<Integer> listMin = generateDigit(minArr, num-i);
         for(int num1:listHour) {
            if(num1>=12) continue;
            for(int num2:listMin) {
               if(num2>=60) continue;
               result.add(num1+":" + (num2<10? "0" + num2:num2));
            }
            
         }
      }
      return result;
   }
   private static List<Integer> generateDigit(int[] nums, int count) {
      List<Integer> res = new ArrayList<>();
      generateDigitHelper(nums, count, 0, 0 ,res);
      return res;
   }
   private static void generateDigitHelper(int[] nums, int count, int pos, int sum, List<Integer> res) {
      if(count==0) res.add(sum);
      else {
         for(int i=pos; i< nums.length; i++) 
            generateDigitHelper(nums, count - 1, i + 1, sum + nums[i], res); 
      }
   }
   
   
   /** 131: palindrome partitioning： 
    * Given a string s, partition s such that every substring of the partition is a palindrome
    * input: "aab" output: [[a, a, b], [aa, b]]
    */
   public static List<List<String>> partition(String s) {
      List<List<String>> result = new ArrayList<>();
      List<String> tempList= new ArrayList<>();
      backTracking131(result, tempList, s, 0);
      return result;
   }
   private static void backTracking131(List<List<String>> result, List<String> tempList, String s, int start) {
      if(start==s.length()) result.add(new ArrayList<>(tempList));
      else {
         for(int i = start; i<s.length(); i++) {
            if(isPalindra(s,start,i)) {
               tempList.add(s.substring(start, i+1));
               backTracking131(result, tempList, s, i+1);
               tempList.remove(tempList.size()-1);
            }
         }
      }
   }
   
   private static boolean isPalindra(String s, int start, int end) {
      if(start==end) return true;
      while(start < end) {
         if(s.charAt(start ++) != s.charAt(end--))  return false;
      }
      return true;
   }
  
   /**90. subsetII: integers may dup, return the power set
    * input:[1,2,2]
    */
   public static List<List<Integer>> subsetsWithDup(int[] nums) {
      List<List<Integer>> result = new ArrayList<>();
      Arrays.sort(nums); //for dupped array, it need to sort
      backTracking90(result, new ArrayList<Integer>(), nums, 0);
      return result;
   }
   private static void backTracking90(List<List<Integer>> result, List<Integer> temp, int[] nums, int start) {
      result.add(new ArrayList<>(temp));
      for(int i=start; i<nums.length; i++) {
      //   if(i!=0 && nums[i] !=nums[i-1])  {
            if(i>start && nums[i] ==nums[i-1]) continue;  //deal with dup element
            temp.add(nums[i]);
            backTracking90(result, temp, nums, i+1);
            temp.remove(temp.size()-1);
      }
   }
   
   
   /**78. Subsets: distinct integers, return all possible subsets(the power set)
    * input: nums=[1,2,3] output:[[], [1], [1, 2], [1, 2, 3], [1, 3], [2], [2, 3], [3]]
    * n个数的combination。 n:0,1,..
    */
   public static List<List<Integer>> subsets(int[] nums) {
      List<List<Integer>> result = new ArrayList<>();
      //Arrays.sort(nums);
      backTracking78(result, new ArrayList<Integer>(), nums, 0);
      return result;
   }
   
   private static void backTracking78(List<List<Integer>> list, ArrayList<Integer> tempList, int[] nums, int start) {
      list.add(new ArrayList<>(tempList));
      for(int i=start; i<nums.length; i++) {
         tempList.add(nums[i]);
         backTracking78(list, tempList, nums, i+1);
         tempList.remove(tempList.size()-1);
      }
   }

   
   /**77: Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
   * Input: n = 4, k = 2  Output: [[1, 2], [1, 3], [1, 4], [2, 3], [2, 4], [3, 4]]
   **/
   public static List<List<Integer>> combinations77(int n, int k) {
      List<List<Integer>> result = new ArrayList<List<Integer>>();
      if(k>n) return result; ///
      backTracking77(result, new ArrayList<>(), n,k, 1);
      return result;
   }
   /**
    * i <= end -k + 1, dont not use i<=n
    * 因为以n=4, k=2为例， 1，2，3，4 当到了4的时候，只有这一个数据了，是不够组成2个的。所以只有1，2，3
    * n=10, k=5为例， 1，2，3，4，5，6，7，8，9，10, 当到了7后面的数据只有4个已经不够了。所以i<=10-5+1=6
    * remember k is the remain nums which is not inserted 
    */
   private static void backTracking77(List<List<Integer>> result, List<Integer> tempComb, int n, int k, int start ) {
      if(k==0) 
         result.add(new ArrayList<Integer>(tempComb));
      else {
         for(int i=start; i<=n-k+1; i++) {
            tempComb.add(i);
            backTracking77(result,tempComb, n, k-1, i+1); //k is the remian number
            tempComb.remove(tempComb.size()-1);
         }
      }
   }
   //60: permutation sequence
   public String getPermutation(int n, int k) {
      int[] candidates = new int[n];
      for(int i=0;i<n;i++) 
         candidates[i]=i+1;
      return "";
      
   }
   //47:permutationsII: nums might *dup*
   //有一些难。多看
   public static List<List<Integer>> permutationII(int[] nums) {
      List<List<Integer>> result = new ArrayList<>();
      boolean[] used = new boolean[nums.length]; //初始化为false
      Arrays.sort(nums); //对于有dup的要sort
      if(nums.length!=0) 
         backTracking47(result, new ArrayList<Integer>(),nums, used);
      return result;
   }
   private static void backTracking47(List<List<Integer>> result, List<Integer> tempPermute, int[] nums, boolean[] used) {
      if(tempPermute.size()==nums.length) 
         result.add(new ArrayList<>(tempPermute));
      else {
         for(int i=0; i<nums.length;i++) {
            if(used[i]) continue;//wrong if tempPermute.contains(nums[i). 因为有重复数据
            if(i>0&& nums[i]==nums[i-1] &&!used[i-1]) continue; //deal with the dup one
            tempPermute.add(nums[i]);
            used[i] = true;
            backTracking47(result, tempPermute, nums,used);
            used[i] = false;
            tempPermute.remove(tempPermute.size()-1);
         }
      }
   }
   //46: permutations: nums are *distinct*
   /*
    * Given a collection of distinct integers, return all possible permutations.
    * Input: [1,2,3]
Output:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
    */
   public static List<List<Integer>> permutations(int[] nums) {
      List<List<Integer>> result = new ArrayList<>();
      if(nums.length!=0) 
         backTracking46(result, new ArrayList<Integer>(),nums);
      return result;
      
   }
   private static void backTracking46(List<List<Integer>> result, List<Integer> tempPermute, int[] nums) {
      if(tempPermute.size()==nums.length) result.add(new ArrayList<>(tempPermute));
      else {
         for(int i=0;i<nums.length; i++) {
            if(tempPermute.contains(nums[i])) continue;//because nums are unique
            tempPermute.add(nums[i]);
            backTracking46(result, tempPermute, nums);
            tempPermute.remove(tempPermute.size()-1);
         }
      }
   }
   /**216: Combination SumIII:Find all possible combinations of k numbers that add up to a number n 
    * given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.
    * input:  k = 3, n = 7 Output: [[1,2,4]]
    *intput: k = 3, n = 9  Output: [[1,2,6], [1,3,5], [2,3,4]]
    */
   public static List<List<Integer>> combinationSumIII216(int k, int n) {
      List<List<Integer>> result = new ArrayList<>();
      if(n<(k*(k+1)/2)) return result;
      backTracking216(result, new ArrayList<>(), k, n, 1);
      return result;
   }
 //why not result.add(tempList): 
 //new ArrayList(comb) will copy comb to a new memory space.
   //Besides, comb will be modified later. 
   //So if you just add comb to list, list will just make a pointer pointing to comb instead of making a new memory space.
   //So if comb is modified, list will be modified too.
 
   private static void backTracking216(List<List<Integer>> result, List<Integer> tempList, int k, int target, int start) {
      if(target<0) return;
      //如果不加上size=k会[[1, 2, 6], [1, 3, 5], [1, 8], [2, 3, 4], [2, 7], [3, 6], [4, 5], [9]]
      if(tempList.size() ==k && target==0) result.add(new ArrayList<>(tempList)); //remember its size not length
      else {
         for(int i=start; i<=9; i++) {  //也可以写成for(int i=start; i<=9&&i<=target; i++)
            if(i<=target) {
            tempList.add(i);
            backTracking216(result, tempList, k, target-i, i+1);
            tempList.remove(tempList.size()-1);
            }
         }
      }
   }
   
   //40: combination SUmII: candidate has *dup* ele && each ele only *used once*
   //input: candidates = [10,1,2,7,6,1,5], target = 8
   //output: [[1, 7],[1, 2, 5], [2, 6], [1, 1, 6]]
   public static List<List<Integer>> combinationSumII40(int[] candidate, int target) {
      List<List<Integer>> result = new ArrayList<>();
      Arrays.sort(candidate);
      backTracking40(result, new ArrayList<Integer>(), candidate,target, 0);
      return result;
   }
   private static void backTracking40(List<List<Integer>> result, List<Integer> tempList, int[] candidate, int remain, int start) {
      if(remain<0) return;
      if(remain==0) result.add(new ArrayList<>(tempList));
      else {
         for(int i=start; i<candidate.length; i++) {
            if(i>start && candidate[i]==candidate[i-1]) continue;
            tempList.add(candidate[i]);
             backTracking40(result, tempList, candidate,remain-candidate[i], i+1); //tempList.add 必须在前面操作，如果在调用函数里.add，他是boolean类型
             tempList.remove(tempList.size()-1);
         }
      }
   }
   //39: combination Sum: candidate *no dup* && number can be *used unlimited* times. 
   //Input: candidates = [2,3,6,7], target = 7,
   //output: [ [7],[2,2,3]]
   //candidates = [2,3,5], target = 8,
   //[[2,2,2,2],[2,3,3],[3,5]]
   public static List<List<Integer>> combinationSum39(int[] candidate, int target) {
      List<List<Integer>> result = new ArrayList<>();
      Arrays.sort(candidate);
      backTracking39(result, new ArrayList<Integer>(), candidate,target, 0);
      return result;
   }
   private static void backTracking39(List<List<Integer>> result, List<Integer> tempList, int[] candidate, int remain, int start) {
      if(remain<0) return;
      if(remain==0) result.add(new ArrayList<>(tempList));//记着对于list成员还是list的add时候要new to allocate new memory space
      else {
         for(int i =start; i<candidate.length; i++) { //一开始i=0导致结果又重复的
            tempList.add(candidate[i]);
            backTracking39(result, tempList, candidate, remain-candidate[i], i);//i not i+1 to ensure 
            tempList.remove(tempList.size()-1);//2，2，2，i=1，2223
         }
      }
   }
   
   //22. generate parentheses
   /*
    * given n=3, return all parentheses
    */
   public static List<String> generateParentheses(int n) {
      List<String> result = new ArrayList<>();
      backTracking22(result, "", 0, 0, n);
      return result;
   }
   private static void backTracking22(List<String> result, String generatedString, int open, int close, int n) {
      if(generatedString.length()==2*n)
         result.add(generatedString);
      else {
         if(open <n) backTracking22(result, generatedString+"(", open+1, close, n);
         if(close<open) backTracking22(result, generatedString+")", open, close+1, n);//result add 以后第一次退回是((
      }
   }
   //17. phone dialer letter combination 
   /* 
    * input: string contains digits 2-9: "23" 
    * output: [ad, ae, af, bd, be, bf, cd, ce, cf]
    * note: need check the digits length,不然会把一个空串放到list里面[""]
    */
   
   public static List<String> letterCombinations(String digits) {
      List<String> result = new ArrayList<>();
      if(digits.length()!=0) {
         backTracking("", digits, result);
      }
      return result;
   }
   private static void backTracking(String combination, String nextDigits, List<String> result) {
      if(nextDigits.length()==0)
         result.add(combination);
      else {
         String digit = nextDigits.substring(0, 1);
         String letters = map.get(digit);
         for(int i =0; i< letters.length(); i++) {
            backTracking(combination+letters.substring(i, i+1), nextDigits.substring(1), result);
         }
      }
   }
   /*
    * iterative solution for 17
    */
   public static List<String> letterCombinationsIternative17(String digits) {
      List<String> list = new LinkedList<>();
      //List<String> list = new ArrayList<>(); //也可以，
      if(digits==null || digits.length()==0) return list;
      list.add("");
      char[][] charMap = {{},{},{'a', 'b', 'c'}, {'d','e','f'},{'g','h','i'},{'j','k','l'},{'m','n','o'},{'p','q','r','s'},{'t','u','v'},{'w','x','y','z'}};
      for(Character cha: digits.toCharArray()) {
      //for(int i=0; i<digits.length(); i++) { //"23"
         List<String> nextList = new LinkedList<>();
         //List<String> nextList = new ArrayList<>();
         //int num = digits.charAt(i) - '0';
         int num = cha - '0';
         for(String s:list) { //
            for(int k=0; k<charMap[num].length; k++) //
               nextList.add(s+charMap[num][k]);
         }
         list = nextList;
      }
      return list;
   }
 
}
