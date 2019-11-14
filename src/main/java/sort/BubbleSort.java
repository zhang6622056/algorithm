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
        //冒泡排序
//        int[] a = new int[]{10,2,11,13,5,7,45,100};
//        bubbleSort(a);

        //快速排序
//        int[] a = new int[]{2,4,5,1,3,7,6};
//        quickSort(a,0,a.length-1);


        //插入排序
        int[] a = new int[]{2,4,5,1,3,7,6};
        insertSort(a);
        System.out.println(Arrays.toString(a));


    }



    /***
     *
     * 冒泡排序
     * @author Nero
     * @date 2019-10-10
     * *@param: arr
     * @return void
     */
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




    /***
     *
     * 快速排序
     * @author Nero
     * @date 2019-10-10
     * @param: arr
     * @param: leftIndex
     * @param: rightIndex
     * @return void
     */
    private static void quickSort(int[] arr,int leftIndex,int rightIndex){

        //-随着每一次递归，left-- 和right ++ ,最后左右两侧的下标都会越界
        //下标可能会越界，因为每次递归都会进行left-- 和right++,当越界的时候也就比较完了。
        if (leftIndex < rightIndex){

            int currentL = leftIndex;int currentR = rightIndex;
            int base = arr[leftIndex];


            //如果下标重合，则结束
            while(currentL < currentR){
                //- 如果右侧大于左边，则认为有序，则继续往左查找
                while(currentL < currentR && arr[currentR] > base){
                    currentR--;
                }
                //- 找到比base小的数，则将right设置到left的位置
                arr[currentL] = arr[currentR];

                //- 从左往右查询，如果小于则认为有序，则继续往右查找
                while(currentL < currentR && arr[currentL] < base){
                    currentL++;
                }
                //- 找到比base大的数，则将left设置到right的位置
                arr[currentR] = arr[currentL];
            }

            //-查找完毕，重合则认为本次查找完毕,将base设置到重合的位置
            arr[currentL] = base;


            quickSort(arr,leftIndex,currentL - 1);
            quickSort(arr,currentL + 1,rightIndex);
        }
    }




    /**
     *
     * 插入排序
     * @author Nero
     * @date 2019-10-12
     * *@param: arr
     * @param: insertvalue
     * @return void
     */
    private static void insertSort(int[] arr){
        //- 外层循环为待排序的集合，从1开始，依次插入，
        // 因默认第0个元素为已排序好的元素
        for (int i = 1 ; i < arr.length ; i++){

            //- 只有比有序数组最后一个元素小的时候才会排序，
            // 大的时候认为是有序的，不用排
            if (arr[i] < arr[i-1]){
                int tempforcompare = arr[i];

                //- 已排序好的数组下标，为当前要插入元素的下标前一个，依次递减比较
                //- 单个元素插入一个数组正确位置，关键理解下标
                //- 如果要插入的元素小于有序集合某个元素，则交换位置
                for (int goodarrindex = i-1 ; goodarrindex >= 0 ; goodarrindex--){
                    if (arr[goodarrindex] > tempforcompare){
                        int t = arr[goodarrindex];
                        arr[goodarrindex] = tempforcompare;
                        arr[goodarrindex+1] = t;
                    }
                }
            }
        }
    }


















}
