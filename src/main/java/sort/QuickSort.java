package sort;

public class QuickSort {


    public static void main(String[] args) {
        int[] arr = new int[]{4,2,1,5,7,10};

        quickSort(arr,0,arr.length-1);

        for (int i = 0 ; i < arr.length ; i++){
            System.out.println(arr[i]);
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








    /***
     *
     * 2020-03-30 解决k大元素。应用倒序快排。
     * 快排在数据量大的时候，依然不是最好的解决方案。
     * 需要从头到尾梳理一下快速排序过程
     * 每一次是定位了base在该数组中正确的位置，然后左右两边再次递归。
     * @author Nero
     * @date 2020-03-31
     * *@param: null
     * @return
     */
//    private static void quickSort(int[] arr,int leftIndex,int rightIndex){
//
//        if(leftIndex >= rightIndex) {return;};
//
//        int currentL = leftIndex;int currentR = rightIndex;
//        int base = arr[leftIndex];
//        while(currentL < currentR){
//
//            while(currentL < currentR && arr[currentR] < base){
//                currentR--;
//            }
//            arr[currentL] = arr[currentR];
//
//            while(currentL < currentR && arr[currentL] > base ){
//                currentL++;
//            }
//            arr[currentR] = arr[currentL];
//        }
//        arr[currentL] = base;
//
//
//        quickSort(arr,leftIndex,currentL-1);
//        quickSort(arr,currentL+1,rightIndex);
//    }





}
