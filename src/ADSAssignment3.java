import java.util.Scanner;

public class ADSAssignment3 {

    public static void performMergeSort(int[] array, int leftIndex, int rightIndex) {
        if (leftIndex < rightIndex) {
            int middleIndex = (leftIndex + rightIndex) / 2;

            performMergeSort(array, leftIndex, middleIndex);
            performMergeSort(array, middleIndex + 1, rightIndex);

            mergeParts(array, leftIndex, middleIndex, rightIndex);
        }
    }

    public static void mergeParts(int[] array, int leftIndex, int middleIndex, int rightIndex) {
        int leftSize = middleIndex - leftIndex + 1;
        int rightSize = rightIndex - middleIndex;

        int[] leftArray = new int[leftSize];
        int[] rightArray = new int[rightSize];

        for (int i = 0; i < leftSize; i++)
            leftArray[i] = array[leftIndex + i];

        for (int j = 0; j < rightSize; j++)
            rightArray[j] = array[middleIndex + 1 + j];

        int i = 0, j = 0, k = leftIndex;

        while (i < leftSize && j < rightSize) {
            if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }

        while (i < leftSize) {
            array[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < rightSize) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }

    // TASK 1
    public static void checkIfStringsAreAnagrams(Scanner scanner) {
        String firstText = scanner.next();
        String secondText = scanner.next();

        if (firstText.length() != secondText.length()) {
            System.out.println("NO");
            return;
        }

        int length = firstText.length();
        int[] firstArray = new int[length];
        int[] secondArray = new int[length];

        for (int i = 0; i < length; i++) {
            firstArray[i] = firstText.charAt(i);
            secondArray[i] = secondText.charAt(i);
        }

        performMergeSort(firstArray, 0, length - 1);
        performMergeSort(secondArray, 0, length - 1);

        for (int i = 0; i < length; i++) {
            if (firstArray[i] != secondArray[i]) {
                System.out.println("NO");
                return;
            }
        }

        System.out.println("YES");
    }

    //TASK 2
    public static void findKthSmallestElement(Scanner scanner) {
        int arraySize = scanner.nextInt();
        int[] numbers = new int[arraySize];

        for (int i = 0; i < arraySize; i++) {
            numbers[i] = scanner.nextInt();
        }

        int kPosition = scanner.nextInt();

        performMergeSort(numbers, 0, arraySize - 1);

        System.out.println(numbers[kPosition - 1]);
    }

    //TASK 3
    public static void findMedianValue(Scanner scanner) {
        int arraySize = scanner.nextInt();
        int[] numbers = new int[arraySize];

        for (int i = 0; i < arraySize; i++) {
            numbers[i] = scanner.nextInt();
        }

        performMergeSort(numbers, 0, arraySize - 1);

        int middleIndex = arraySize / 2;
        System.out.println(numbers[middleIndex]);
    }

    //TASK 4
    public static boolean checkIfCapacityIsEnough(int[] weights, int daysLimit, int capacity) {
        int currentDays = 1;
        int currentLoad = 0;

        for (int weight : weights) {
            if (currentLoad + weight > capacity) {
                currentDays++;
                currentLoad = 0;
            }
            currentLoad += weight;
        }

        return currentDays <= daysLimit;
    }

    public static void findMinimumShippingCapacity(Scanner scanner) {
        int arraySize = scanner.nextInt();
        int[] weights = new int[arraySize];
        int maxWeight = 0;
        int totalWeight = 0;

        for (int i = 0; i < arraySize; i++) {
            weights[i] = scanner.nextInt();
            if (weights[i] > maxWeight) {
                maxWeight = weights[i];
            }
            totalWeight += weights[i];
        }

        int daysLimit = scanner.nextInt();

        int leftCapacity = maxWeight;
        int rightCapacity = totalWeight;
        int resultCapacity = totalWeight;

        while (leftCapacity <= rightCapacity) {
            int middleCapacity = (leftCapacity + rightCapacity) / 2;

            if (checkIfCapacityIsEnough(weights, daysLimit, middleCapacity)) {
                resultCapacity = middleCapacity;
                rightCapacity = middleCapacity - 1;
            } else {
                leftCapacity = middleCapacity + 1;
            }
        }

        System.out.println(resultCapacity);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int taskChoice = scanner.nextInt();

        if (taskChoice == 1) {
            checkIfStringsAreAnagrams(scanner);
        } else if (taskChoice == 2) {
            findKthSmallestElement(scanner);
        } else if (taskChoice == 3) {
            findMedianValue(scanner);
        } else if (taskChoice == 4) {
            findMinimumShippingCapacity(scanner);
        }

        scanner.close();
    }
}