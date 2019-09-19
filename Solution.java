import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Solution {
	static boolean isMobile = true; // this keeps track if we have any mobile
	// elements
	static int leftLargest = 0; // the largest mobile element that can move left
	static int rightLargest = 0; // the largest mobile element that can move
	// right
	static int largestMobile = 0; // the largest mobile element in general

	// Prints out all the permutations
	public static void permute(int arr[], int dir[]) {
		// Print out the first permutation
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]);
		}
		System.out.println();

		// run the until we do not have any more mobile elements
		while (isMobile == true) {
			// find its largest mobile element (largestMobile)
			findLargestLeftMobElement(arr, dir);
			findLargestRightMobElement(arr, dir);
			findLargesMobElement();

			// swap largestMobile with the adjacent element largestMobile's
			// arrow points to
			if (largestMobile == leftLargest) {
				leftSwap(arr, dir, largestMobile);
			} else {
				rightSwap(arr, dir, largestMobile);
			}

			// reverse the direction of all the elements that are larger than
			// largestMobile
			for (int i = 0; i < arr.length; i++) {
				if (arr[i] > largestMobile && dir[i] == -1) {
					dir[i] = 1;
				} else if (arr[i] > largestMobile && dir[i] == 1) {
					dir[i] = -1;
				}
			}

			// print out the new permutation
			for (int i = 0; i < arr.length; i++) {
				System.out.print(arr[i]);
			}
			System.out.println();

			// reset all our fields for the next permutation
			isMobile = false;
			leftLargest = 0;
			rightLargest = 0;
			largestMobile = 0;

			// check mobility
			hasMobileElement(arr, dir);
		}

	}

	// Just checking to see if our array contains a mobile element
	static boolean hasMobileElement(int arr[], int dir[]) {
		for (int i = 1; i < arr.length - 1; i++) {
			if (arr[0] > arr[1] && dir[0] == 1) {
				isMobile = true;
				break;

			}
			if (arr[arr.length - 1] > arr[arr.length - 2] && dir[arr.length - 1] == -1) {
				isMobile = true;
				break;
			}

			if (arr[i] > arr[i + 1] && dir[i] == 1) {
				isMobile = true;
				break;
			}
			if (arr[i] > arr[i - 1] && dir[i] == -1) {
				isMobile = true;
				break;
			} else {
				isMobile = false;
			}
		}
		return isMobile;
	}

	// finds the left largest mobile element
	static void findLargestLeftMobElement(int arr[], int dir[]) {

		for (int i = 1; i < arr.length; i++) {

			if ((arr[i] > leftLargest) && (arr[i] > arr[i - 1]) && (dir[i] == -1)) {
				leftLargest = arr[i];
			}
		}

	}

	// finds the right largest mobile element
	static void findLargestRightMobElement(int arr[], int dir[]) {

		for (int i = 0; i < arr.length - 1; i++) {
			if ((arr[i] > rightLargest) && (arr[i] > arr[i + 1]) && (dir[i] == 1)) {
				rightLargest = arr[i];
			}
		}

	}

	// finds the largest mobile element
	static void findLargesMobElement() {
		if (rightLargest > leftLargest) {
			largestMobile = rightLargest;
		} else {
			largestMobile = leftLargest;
		}

	}

	// performs a leftward swap of elements
	static void leftSwap(int[] arr, int[] dir, int j) {
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] == leftLargest) {
				int temp = arr[i];
				int temp2 = dir[i];
				arr[i] = arr[i - 1];
				dir[i] = dir[i - 1];
				arr[i - 1] = temp;
				dir[i - 1] = temp2;
				break;

			}

		}
	}

	// performs a rightward swap of elements
	static void rightSwap(int[] arr, int[] dir, int j) {
		for (int i = 0; i < arr.length - 1; i++) {
			if (arr[i] == rightLargest) {
				int temp = arr[i];
				int temp2 = dir[i];
				arr[i] = arr[i + 1];
				dir[i] = dir[i + 1];
				arr[i + 1] = temp;
				dir[i + 1] = temp2;
				break;

			}

		}
	}

	// main method
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		scan.close();
		// creating two arrays, arr to hold the numbers, and dir to hold the
		// direction of the arrows for the numbers in arr.
		int[] arr = new int[n];
		int[] dir = new int[n];

		// add numbers 0 to n-1 to the array
		for (int i = 0; i < n; i++) {
			arr[i] = i;
			dir[i] = -1; // everything starts pointing left
			// System.out.println(arr[i]);

		}

		permute(arr, dir);

	}
}
