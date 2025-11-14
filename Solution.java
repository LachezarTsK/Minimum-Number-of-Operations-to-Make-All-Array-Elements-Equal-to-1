
public class Solution {

    private static final int NOT_POSSIBLE_MAKE_ALL_ARRAY_ELEMENTS_EQUAL_TO_ONE = -1;
    private int minSizeSubarrayWithGreatestCommonDivisorOfOne = Integer.MAX_VALUE;

    public int minOperations(int[] input) {
        int countFrequencyOfOnes = 0;
        for (int i = 0; i < input.length; ++i) {
            if (input[i] == 1) {
                ++countFrequencyOfOnes;
            }
        }
        if (countFrequencyOfOnes > 0) {
            return input.length - countFrequencyOfOnes;
        }

        for (int back = 1; back < input.length; ++back) {
            int[] cloneInput = new int[input.length];
            System.arraycopy(input, 0, cloneInput, 0, input.length);
            searchForMinSizeSubarrayWithGreatestCommonDivisorOfOne(cloneInput, back);
        }

        if (minSizeSubarrayWithGreatestCommonDivisorOfOne == Integer.MAX_VALUE) {
            return NOT_POSSIBLE_MAKE_ALL_ARRAY_ELEMENTS_EQUAL_TO_ONE;
        }

        return input.length + minSizeSubarrayWithGreatestCommonDivisorOfOne - 1;
    }

    private int findGreatestCommonDivisor(int smaller, int larger) {
        if (smaller == 0) {
            return larger;
        }
        return findGreatestCommonDivisor(larger % smaller, smaller);
    }

    private void searchForMinSizeSubarrayWithGreatestCommonDivisorOfOne(int[] cloneInput, int back) {

        for (int front = back; front < cloneInput.length; ++front) {
            int smaller = Math.min(cloneInput[front - 1], cloneInput[front]);
            int larger = Math.max(cloneInput[front - 1], cloneInput[front]);
            int gcd = findGreatestCommonDivisor(smaller, larger);

            if (gcd == 1) {
                minSizeSubarrayWithGreatestCommonDivisorOfOne
                        = Math.min(minSizeSubarrayWithGreatestCommonDivisorOfOne, front - back + 1);
                break;
            }
            cloneInput[front] = gcd;
        }
    }
}
