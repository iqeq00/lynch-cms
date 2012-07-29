package com.lynch.cms.common.util;

import java.util.*;

/**
 * 数组操作的工具类(以int型数组为例)
 * 
 * @author Lynch
 * @email : iqeq00@163.com
 * @version 1.0
 */
public class ArrayUtil {

	/**
	 * 排序算法的分类如下：
	 * 1.插入排序（直接插入排序、折半插入排序、希尔排序）；
	 * 2.交换排序（冒泡泡排序、快速排序）；
	 * 3.选择排序（直接选择排序、堆排序）； 
	 * 4.归并排序； 
	 * 5.基数排序。
	 *
	 * 关于排序方法的选择：
	 * (1)若n较小(如n≤50)，可采用直接插入或直接选择排序。
	 * (2)若文件初始状态基本有序(指正序)，则应选用直接插人、冒泡或随机的快速排序为宜；
	 * (3)若n较大，则应采用时间复杂度为O(nlgn)的排序方法：快速排序、堆排序或归并排序。
	 */
	
		
	/**
	 * 名称：冒泡排序法 方法
	 * 描述：相邻两元素进行比较,将最大的数放在最后面
	 * 性能：比较次数O(n^2),n^2/2；交换次数O(n^2),n^2/4
	 * 
	 * @param array 需要进行冒泡排序的数组
	 * @return array 交换后的数组
	 */
    public static int[] bubbleSort(int[] array) {
    	
        for (int i = 1; i < array.length; i++) {
            for (int j = 0; j < array.length - 1; j++) {
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                }
            }
        }
        return array;
    }
    
    /**
	 * 名称：直接选择排序法 方法
	 * 描述：每一趟从待排序的数据元素中选出最小(或最大)的一个元素， 顺序放在已排好序的数列的最后，直到全部待排序的数据元素排完。
	 * 性能：比较次数O(n^2),n^2/2 交换次数O(n),n
	 * 备注：交换次数比冒泡排序少多了，由于交换所需CPU时间比比较所需的CUP时间多，所以选择排序比冒泡排序快。
	 * 		 但是N比较大时，比较所需的CPU时间占主要地位，所以这时的性能和冒泡排序差不太多，但毫无疑问肯定要快些。
	 * 
	 * @param array 需要进行选择排序的数组
	 * @return array 交换后的数组
	 */
    public static int[] selectSort(int[] array) {

        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    swap(array, i, j);
                }
            }
        }
        return array;
    }

    /**
	 * 名称：插入排序法 方法
	 * 描述：将一个记录插入到已排好序的有序表（有可能是空表）中,从而得到一个新的记录数增1的有序表。
	 * 性能：比较次数O(n^2),n^2/4 ，复制次数O(n),n^2/4 
	 * 备注：比较次数是前两者的一般，而复制所需的CPU时间较交换少，所以性能上比冒泡排序提高一倍多，而比选择排序也要快。
	 * 
	 * @param array 需要进行插入排序的数组
	 * @return array 交换后的数组
	 */
    public static int[] insertSort(int[] array) {

        for (int i = 1; i < array.length; i++) {
            for (int j = i; (j > 0) && (array[j] < array[j - 1]); j--) {
                swap(array, j, j - 1);
            }
        }
        return array;
    }
    
    /**
	 * 名称：快速排序 
	 * 描述：快速排序使用分治法(Divide and conquer)策略来把一个序列(list)分为两个子序列(sub-lists)。 
	 * 步骤： 1.从数列中挑出一个元素，称为 "基准"(pivot)
	 *       2.重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面。
	 *         (相同的数可以到任一边)。在这个分割之后，该基准是它的最后位置。这个称为分割(partition)操作。
	 *       3.递归地(recursive)把小于基准值元素的子数列和大于基准值元素的子数列排序。递回的最底部情形，是数列的大小是零或一，
	 *         也就是永远都已经被排序好了。虽然一直递回下去，但是这个算法总会结束，因为在每次的迭代(iteration)中，它至少会把一个元素摆到它最后的位置去。
	 * 
	 * @param array 需要进行快速排序 的数组
	 * @return array 交换后的数组
	 */
    public static int[] quickSort(int[] array) {
    	
        return qsort(array, 0, array.length - 1);
    }

    /**
     * 快速排序的具体实现，排正序
     * 
     * @param array 需要进行快速排序操作的数组
     * @param start 开始低位
     * @param end 结束高位
     * @return array 排序后的数组
     */
    private static int[] qsort(int[] array, int start, int end) {
    	
        int i, j, x;
        if (start < end) {
            i = start;
            j = end;
            x = array[i];
            while (i < j) {
                while (i < j && array[j] > x) {
                    j--;
                }
                if (i < j) {
                	array[i] = array[j];
                    i++;
                }
                while (i < j && array[i] < x) {
                    i++;
                }
                if (i < j) {
                	array[j] = array[i];
                    j--;
                }
            }
            array[i] = x;
            qsort(array, start, i - 1);
            qsort(array, i + 1, end);
        }
        return array;
    }
    
   
    /**
     * 二分法查找 查找线性表必须是有序列表
     * 
     * @param array 需要进行查找操作的数组
     * @param key 需要查找的值
     * @return 需要查找的值在数组中的位置，若未查到则返回-1
     */
    public static int binarySearch(int[] array, int key) {
    	
        int start = 0;
        int end = array.length - 1;
        int mid;
        while (start <= end) {
            mid = (start + end) >>> 1;
            if (key == array[mid]) {
                return mid;
            } else if (key < array[mid]) {
            	end = mid - 1;
            } else {
            	start = mid + 1;
            }
        }
        return -1;
    }
    
    /**
	 * 交换数组中两元素
	 * 
	 * @param array 需要进行交换操作的数组
	 * @param x 数组中的位置1
	 * @param y 数组中的位置2
	 * @return array 交换后的数组
	 */
	public static int[] swap(int[] array, int x, int y) {
		
        int temp = array[x];
        array[x] = array[y];
        array[y] = temp;
        return array;
    }
	
	/**
	 * 反转数组
	 * 
	 * @param array 需要进行反转操作的数组
	 * @return array 反转后的数组
	 */
    public static int[] reverse(int[] array) {
    	
        int length = array.length;
        int temp = 0;
        for (int i = 0; i < length>>1; i++) {
            temp = array[i];
            array[i] = array[length - 1 - i];
            array[length - 1 - i] = temp;
        }
        return array;
    }

	/**
	 * 在当前位置插入一个元素,数组中原有元素向后移动;
	 * 如果插入位置超出原数组，则抛IllegalArgumentException异常
	 * @param array 要插入元素的数组
	 * @param index 插入元素位置（从1开始算）
	 * @param insertNumber 插入值
	 * @return reslutArray 插入元素后的数组
	 */
	public static int[] insert(int[] array, int index, int insertNumber) {
		
		if (array == null || array.length == 0) {
			throw new IllegalArgumentException();
		}
		if (index-1 > array.length || index <= 0) {
			throw new IllegalArgumentException();
		}
		int[] reslutArray = new int[array.length+1];
		System.arraycopy(array, 0, reslutArray, 0, index-1);
		reslutArray[index-1] = insertNumber;
		System.arraycopy(array, index-1, reslutArray, index, reslutArray.length-index);
		return reslutArray;
	}
	
	/**
	 * 整形数组中特定位置删除掉一个元素,数组中原有元素向前移动;
	 * 如果插入位置超出原数组，则抛IllegalArgumentException异常
	 * @param array 要删除元素的数组
	 * @param index 要删除的位置（从1开始算）
	 * @return reslutArray 删除元素后的数组
	 */
	public static int[] remove(int[] array, int index) {
		
		if (array == null || array.length == 0) {
			throw new IllegalArgumentException();
		}
		if (index > array.length || index <= 0) {
			throw new IllegalArgumentException();
		}
		int[] reslutArray = new int[array.length-1];
		System.arraycopy(array, 0, reslutArray, 0, index-1);
		System.arraycopy(array, index, reslutArray, index-1, array.length-index);
		return reslutArray;
	}
	
	/**
	 * 2个数组合并，形成一个新的数组
	 * @param array1 第一个数组
	 * @param array2 第二个数组
	 * @return array 合并完成后的数组
	 */
	public static int[] merge(int[] array1,int[] array2) {
		
		int[] array = new int[array1.length+array2.length];
		System.arraycopy(array1, 0, array, 0, array1.length);
		System.arraycopy(array2, 0, array, array1.length, array2.length);
		return array;
	}

	/**
	 * 数组中有n个数据，要将它们顺序循环向后移动k位，
	 * 即前面的元素向后移动k位，后面的元素则循环向前移k位，
	 * 例如，0、1、2、3、4循环移动3位后为2、3、4、0、1。
	 * @param array 需要移动的数组
	 * @param offset 需要移动几位
	 * @return array 顺序向后移动后的数组
	 */
	public static int[] offsetArray(int[] array,int offset){
		
		int length = array.length;  
		int moveLength = length - offset; 
		int[] temp = Arrays.copyOfRange(array, moveLength, length);
		System.arraycopy(array, 0, array, offset, moveLength);  
		System.arraycopy(temp, 0, array, 0, offset);
		return array;
	}
	
	/**
	 * 对两个有序数组进行合并,并将重复的数字将其去掉
	 * 
	 * @param array1：已排好序的数组array1
	 * @param array2：已排好序的数组array2
	 * @return reslutList 合并后的排序数组
	 */
	public static List<Integer> mergeByList(int[] array1, int[] array2) {
		
		// 用于返回的新数组，长度可能不为a,b数组之和，因为可能有重复的数字需要去掉
		List<Integer> reslutList = new ArrayList<Integer>();
		// a数组下标
		int aIndex = 0;
		// b数组下标
		int bIndex = 0;
		// 对a、b两数组的值进行比较，并将小的值加到c，并将该数组下标+1，
		// 如果相等，则将其任意一个加到c，两数组下标均+1
		// 如果下标超出该数组长度，则退出循环
		while (true) {
			if (aIndex > array1.length - 1 || bIndex > array2.length - 1) {
				break;
			}
			if (array1[aIndex] < array2[bIndex]) {
				reslutList.add(array1[aIndex]);
				aIndex++;
			} else if (array1[aIndex] > array2[bIndex]) {
				reslutList.add(array2[bIndex]);
				bIndex++;
			} else {
				reslutList.add(array1[aIndex]);
				aIndex++;
				bIndex++;
			}
		}
		// 将没有超出数组下标的数组其余全部加到数组c中
		// 如果a数组还有数字没有处理
		if (aIndex <= array1.length - 1) {
			for (int i = aIndex; i <= array1.length - 1; i++) {
				reslutList.add(array1[i]);
			}
			// 如果b数组中还有数字没有处理
		} else if (bIndex <= array2.length - 1) {
			for (int i = bIndex; i <= array2.length - 1; i++) {
				reslutList.add(array2[i]);
			}
		}
		return reslutList;
	}
	
	/**
	 * 对两个有序数组进行合并,并将重复的数字将其去掉
	 * 返回数组的长度=array1.length + array2.length,不足部分补0
	 * @param array1:已排好序的数组array1
	 * @param array2:已排好序的数组array2
	 * @return reslutArray 合并后的排序数组
	 */
	public static int[] mergeByArray(int[] array1, int[] array2){
		
		int[] reslutArray = new int[array1.length + array2.length];
		int i = 0, j = 0, k = 0;
		while (i < array1.length && j < array2.length) {
			if (array1[i] <= array2[j]) {
				if (array1[i] == array2[j]) {
					j++;
				} else {
					reslutArray[k] = array1[i];
					i++;
					k++;
				}
			} else {
				reslutArray[k] = array2[j];
				j++;
				k++;
			}
		}
		while (i < array1.length) {
			reslutArray[k] = array1[i];
			k++;
			i++;
		}
		while (j < array2.length) {
			reslutArray[k] = array2[j];
			j++;
			k++;
		}
		return reslutArray;
	}
	
	/**
	 * 对两个有序数组进行合并,并将重复的数字将其去掉
	 * @param array1：可以是没有排序的数组
	 * @param array2：可以是没有排序的数组
	 * @return map 合并后的排序数组
	 * 打印时可以这样：
	 * Map<Integer,Integer> map=sortByTreeMap(a,b);
		Iterator iterator =  map.entrySet().iterator();   
		while (iterator.hasNext()) {   
           Map.Entry mapentry = (Map.Entry)iterator.next();   
           System.out.print(mapentry.getValue()+" ");   
		}
	 */
	public static Map<Integer,Integer> mergeByTreeMap(int[] array1, int[] array2) {
		
		Map<Integer,Integer> map=new TreeMap<Integer,Integer>();
		for(int i=0;i<array1.length;i++){
			map.put(array1[i], array1[i]);
		}
		for(int i=0;i<array2.length;i++){
			map.put(array2[i], array2[i]);
		}
		return map;
	}
	
	/**
	 * 交换位置
	 * 
	 * @param array 需要进行交换操作的数组
	 * @param p1 数组中的位置1
	 * @param p2 数组中的位置2
	 */ 
	private void exchange(int[] array, int p1, int p2) {
		
		int temp = array[p1];
		array[p1] = array[p2];
		array[p2] = temp;
	}
	
	/**
	 * 随机打乱一个数组
	 * @param list 需要打乱顺序的list
	 * @return list 打乱顺序后的list
	 */
	public static List shuffle(List list){
		
		Collections.shuffle(list);
		return list;
	}

	/**
	 * 随机打乱一个数组(从0到index处之间随机取一个值，跟index处的元素交换)
	 * @param array 需要打乱顺序的数组
	 * @return array 打乱顺序后的数组
	 */
	public int[] shuffle(int[] array) {
		
		Random random = new Random();
		for (int index = array.length - 1; index >= 0; index--) {
			exchange(array, random.nextInt(index + 1), index);
		}
		return array;
	}
}