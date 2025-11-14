
using System;

public class Solution
{
    private static readonly int NOT_POSSIBLE_MAKE_ALL_ARRAY_ELEMENTS_EQUAL_TO_ONE = -1;
    private int minSizeSubarrayWithGreatestCommonDivisorOfOne = int.MaxValue;

    public int MinOperations(int[] input)
    {
        int countFrequencyOfOnes = 0;
        for (int i = 0; i < input.Length; ++i)
        {
            if (input[i] == 1)
            {
                ++countFrequencyOfOnes;
            }
        }
        if (countFrequencyOfOnes > 0)
        {
            return input.Length - countFrequencyOfOnes;
        }

        for (int back = 1; back < input.Length; ++back)
        {
            int[] cloneInput = new int[input.Length];
            Array.Copy(input, cloneInput, input.Length);
            SearchForMinSizeSubarrayWithGreatestCommonDivisorOfOne(cloneInput, back);
        }

        if (minSizeSubarrayWithGreatestCommonDivisorOfOne == int.MaxValue)
        {
            return NOT_POSSIBLE_MAKE_ALL_ARRAY_ELEMENTS_EQUAL_TO_ONE;
        }

        return input.Length + minSizeSubarrayWithGreatestCommonDivisorOfOne - 1;
    }

    private int FindGreatestCommonDivisor(int smaller, int larger)
    {
        if (smaller == 0)
        {
            return larger;
        }
        return FindGreatestCommonDivisor(larger % smaller, smaller);
    }

    private void SearchForMinSizeSubarrayWithGreatestCommonDivisorOfOne(int[] cloneInput, int back)
    {
        for (int front = back; front < cloneInput.Length; ++front)
        {
            int smaller = Math.Min(cloneInput[front - 1], cloneInput[front]);
            int larger = Math.Max(cloneInput[front - 1], cloneInput[front]);
            int gcd = FindGreatestCommonDivisor(smaller, larger);

            if (gcd == 1)
            {
                minSizeSubarrayWithGreatestCommonDivisorOfOne
                        = Math.Min(minSizeSubarrayWithGreatestCommonDivisorOfOne, front - back + 1);
                break;
            }
            cloneInput[front] = gcd;
        }
    }
}
