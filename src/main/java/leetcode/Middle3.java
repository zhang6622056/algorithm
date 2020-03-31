package leetcode;


/***
 *
 * 中等难度3
 *
 * 计算数字 k 在 0 到 n 中的出现的次数，k 可能是 0~9 的一个值。
 *
 * 样例
 * 样例 1：
 *
 * 输入：
 * k = 1, n = 1
 * 输出：
 * 1
 * 解释：
 * 在 [0, 1] 中，我们发现 1 出现了 1 次 (1)。
 * 样例 2：
 *
 * 输入：
 * k = 1, n = 12
 * 输出：
 * 5
 * 解释：
 * 在 [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12] 中，我们发现 1 出现了 5 次 (1, 10, 11, 12)(注意11中有两个1)。
 *
 *
 * @author Nero
 * @date 2020-01-16
 * @param: null
 * @return
 */
public class Middle3 {








    /***
     * 解题思想
     * 1- 利用除法将位数减少，比如123/10 = 12
     * 2- 利用取余获取个位数的数字，
     * 3- 以此类推
     * @author Nero
     * @date 2020-01-19
     * @param: k
     * @param: n
     * @return int
     */
    public static int digitCounts(int k, int n) {
        int res = 0;
        for (int i = 0 ; i <= k ; i++){
            int curNumber = i;


            //- 大于1位数的情况,首先先取除个位数以外的判定
            while(curNumber / 10 > 0){
                int curPositionIndex =  curNumber % 10;
                if (curPositionIndex == k){
                    res++;
                }
                curNumber = curNumber / 10;
            }

            //- 1位数的情况
            if (curNumber == k){
                res++;
            }
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println(digitCounts(1,12));
    }








}
