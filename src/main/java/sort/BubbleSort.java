package sort;

import java.util.Arrays;




/**
 *
 * 冒泡排序- 每一次循环都将当前最大的数放到数组末尾，下一次循环次数就减去一个数值的长度，少循环一个。
 *
 * - 外层逐一减少length，每次匹配一次就将当前数据最大的数放到最后
 * - 内存为每一次当前匹配。比如有数组为8长度的值，内层最大则为(总长度-当已处理循环过的次数)
 *
 *
 *
 * @author Nero
 * @date 2019-10-02
 * *@param: null
 * @return
 */
public class BubbleSort {


    public static void main(String[] args) {
        int[] a = new int[]{10,2,11,13,5,7,45,100};
        bubbleSort(a);

        for (int i=0;i<a.length;i++){
            System.out.println(a[i]);
        }


    }




    private static void bubbleSort(int[] arr){


        //外层循环，每一次减少最后一个
        for (int i = arr.length ; i > 0 ; i--){
            //内存循环，将当前范围最大的数移到最后
            for (int j = 0 ; j < i ; j++){

                int plus  = j + 1;
                if (plus >= i) {
                    // 最后一条
                    break;
                }

                //-调换位置
                if (arr[j] > arr[plus]){
                    int temp = arr[plus];
                    arr[plus] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }







}
