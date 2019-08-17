/***
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 *
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 *
 * 你可以假设 nums1 和 nums2 不会同时为空。
 *
 * 示例 1:
 *
 * nums1 = [1, 3]
 * nums2 = [2]
 *
 * 则中位数是 2.0
 * 示例 2:
 *
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 *
 * 则中位数是 (2 + 3)/2 = 2.5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class P4FindMedianInSortedArray {
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int m = nums1.length;
        int n = nums2.length;
        if (m > n) {
            int tempArray[] = nums1;
            nums1 = nums2;
            nums2 = tempArray;
            int tempLength = m;
            m = n;
            n = tempLength;
        }

        int iMin = 0, iMax = m;

        double median = 0;

        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2; //二分选择i
            int j = (m + n + 1) / 2 - i;

            if (i > 0 && nums1[i - 1] > nums2[j]) //i 大了， 继续二分选i
            {
                iMax = i - 1;
            } else if (i < m && nums1[i] < nums2[j - 1]) //i 小了， 继续二分选i
            {
                iMin = i + 1;
            } else //到达临界 i = 0， i=m， 找到合适的i, j
            {
                /*double maxLeft = 0;
                //i too small
                if (0 == i && m != 0) {
                    if (nums1[i] < nums2[j - 1]) {
                        iMin = i + 1;
                        continue;
                    }
                    //i too big
                } else if (i == m && m != 0 *//* when m is 0 *//*) {
                    if (nums1[i - 1] > nums2[j]) {
                        iMax = i - 1;
                        continue;
                    }
                }*/

                double maxLeft = 0;

                if (0 == i)
                {
                    maxLeft = nums2[j - 1];
                }
                else if (j == 0) {
                    maxLeft = nums1[i - 1];
                } else {
                    maxLeft = Math.max(nums1[i - 1], nums2[j - 1]);
                }


                //the length is odd
                if ((m + n) % 2 == 1) {
                    median = maxLeft;
                } else {
                    //the length is even
                    double minRight = 0;
                    if (i == m) {
                        minRight = nums2[j];
                    } else if (j == n) {
                        minRight = nums1[i];
                    } else {
                        minRight = Math.min(nums1[i], nums2[j]);
                    }

                    median = (maxLeft + minRight) / 2;
                }

                break;
            }
        }

        return median;
    }


    public static double findMedianSortedArraysOfficial(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        if (m > n) { // to ensure m<=n
            int[] temp = A; A = B; B = temp;
            int tmp = m; m = n; n = tmp;
        }
        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = halfLen - i;
            if (i < iMax && B[j-1] > A[i]){
                iMin = i + 1; // i is too small
            }
            else if (i > iMin && A[i-1] > B[j]) {
                iMax = i - 1; // i is too big
            }
            else { // i is perfect
                int maxLeft = 0;
                if (i == 0) { maxLeft = B[j-1]; }
                else if (j == 0) { maxLeft = A[i-1]; }
                else { maxLeft = Math.max(A[i-1], B[j-1]); }
                if ( (m + n) % 2 == 1 ) { return maxLeft; }

                int minRight = 0;
                if (i == m) { minRight = B[j]; }
                else if (j == n) { minRight = A[i]; }
                else { minRight = Math.min(B[j], A[i]); }

                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }


    public static void main(String[] args) {
        int[] nums1 = {3};
        int[] nums2 = {1,2,4};

        System.out.printf("" + findMedianSortedArrays(nums1, nums2));
        System.out.printf("" + findMedianSortedArraysOfficial(nums1, nums2));
    }


}
