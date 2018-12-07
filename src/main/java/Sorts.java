import java.util.Arrays;

public class Sorts {

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{1, 2, 3, 4};
        Integer left = 0;
        Integer right = arr.length - 1;
        swap(arr, left, right);
        Arrays.asList(arr).stream().forEach(System.out::println);
    }

    public static void swap(Integer[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    /**
     * 冒泡排序: 每次比较相邻元素交换位置
     *
     * @param arr
     * @return
     */
    public static Integer[] bubbleSort(Integer[] arr) {
        int len = arr.length;

        for (int i = 0; i < len; i++) {
            for (int j = 0; i < len - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }

        return arr;
    }


    /**
     * 选择排序: 每次选择剩余无序队列中最小的元素放到有序队列最后面
     *
     * @param arr
     * @return
     */
    public static Integer[] selectionSort(Integer[] arr) {
        int len = arr.length;
        int minIndex;

        for (int i = 0; i < len; i++) {
            minIndex = i;
            for (int j = i + 1; j < len; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            swap(arr, i, minIndex);
        }

        return arr;
    }


    /**
     * 插入排序: 每次选择无序队列中的第一个元素插入有序队列
     *
     * @param arr
     * @return
     */
    public static Integer[] insertSort(Integer[] arr) {
        int len = arr.length;
        int preIndex, current;

        for (int i = 1; i < len; i++) {
            preIndex = i - 1;
            current = arr[i];
            while (preIndex >= 0 && arr[preIndex] > current) {
                arr[preIndex + 1] = arr[preIndex];
                preIndex--;
            }
            arr[preIndex + 1] = current;
        }

        return arr;
    }

    /**
     * 希尔排序: 间隔由大到小将数组划分为不同子序列, 对不同子序列进行排序
     *
     * @param arr
     * @return
     */
    public static Integer[] shellSort(Integer[] arr) {
        int len = arr.length;
        int gap = 1;
        int temp;

        while (gap < len / 3) {
            gap = gap * 3 + 1;
        }

        for (gap = gap; gap > 0; gap = (int) Math.floor(gap / 3)) {
            for (int i = gap; i < len; i++) {
                temp = arr[i];
                int preIndex = i - gap;
                for (preIndex = preIndex; preIndex > 0 && arr[preIndex] > temp; preIndex -= gap) {
                    arr[preIndex + gap] = arr[preIndex];
                }
                arr[preIndex + gap] = temp;
            }
        }
        return arr;
    }

    /**
     * 归并排序: 采用二分法递归的对子序列进行排序，再合并排序后的子序列
     *
     * @param arr
     * @return
     */
    public static Integer[] mergeSort(Integer[] arr) {
        int len = arr.length;
        if (len < 2) {
            return arr;
        }
        int middle = (int) Math.floor(len / 2);

        Integer[] left = Arrays.copyOfRange(arr, 0, middle);
        Integer[] right = Arrays.copyOfRange(arr, middle, len);

        return merge(mergeSort(left), mergeSort(right));
    }

    private static Integer[] merge(Integer[] left, Integer[] right) {
        Integer[] temp = new Integer[left.length + right.length];
        int leftIndex = 0, rightIndex = 0, tempIndex = 0;
        while (leftIndex <= left.length && rightIndex <= right.length) {
            if (left[leftIndex] < right[rightIndex]) {
                temp[tempIndex++] = left[leftIndex++];
            } else {
                temp[tempIndex++] = right[rightIndex++];
            }
        }

        // 剩余部分
        while (leftIndex <= left.length) {
            temp[tempIndex++] = left[leftIndex++];
        }

        while (rightIndex <= right.length) {
            temp[tempIndex++] = right[rightIndex++];
        }

        return temp;
    }

    /**
     * 快速排序: 选择一个基准值，前后两指针相向而行根据与基准值大小关系互相交换位置，递归执行
     *
     * @param arr
     * @return
     */
    public static Integer[] quickSort(Integer[] arr) {
        if (arr.length > 0) {
            return quickSort(arr, 0, arr.length);
        }
        return arr;
    }

    private static Integer[] quickSort(Integer[] arr, int low, int high) {
        if (low > high) {
            return arr;
        }

        int i = low, j = high;
        int key = arr[low];

        while (i < j) {
            while (i < j && arr[j] > key) {
                j--;
            }

            while (i < j && arr[i] <= key) {
                i++;
            }

            if (i < j) {
                swap(arr, i, j);
            }

            // 交换基准值
            swap(arr, low, i);
        }

        quickSort(arr, low, i - 1);
        quickSort(arr, i + 1, high);

        return arr;
    }

    public static Integer[] heapSort(Integer[] arr) {
        // 1.构建大顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            //从第一个非叶子结点从下至上，从右至左调整结构
            heapSort(arr, i, arr.length);
        }

        // 2.调整堆结构+交换堆顶元素与末尾元素
        for (int j = arr.length - 1; j > 0; j--) {
            //将堆顶元素与末尾元素进行交换
            swap(arr, 0, j);
            //重新对堆进行调整, 每次堆顶都是最大值;
            heapSort(arr, 0, j);
        }
        return arr;
    }

    // 每一轮挑出最大值
    private static void heapSort(Integer[] arr, int i, int length) {
        int temp = arr[i];
        for (int k = getLeftChild(i); k < length; k = getLeftChild(k)) {
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                k++;
            }
            if (arr[k] > temp) {
                swap(arr, i, k);
            } else {
                break;
            }
        }
    }

    private static Integer getLeftChild(Integer i) {
        return 2 * i + 1;
    }

}
