
import kotlin.math.min
import kotlin.math.max

class Solution {

    private companion object {
        const val NOT_POSSIBLE_MAKE_ALL_ARRAY_ELEMENTS_EQUAL_TO_ONE = -1
    }

    private var minSizeSubarrayWithGreatestCommonDivisorOfOne = Int.MAX_VALUE

    fun minOperations(input: IntArray): Int {
        var countFrequencyOfOnes = 0
        for (i in input.indices) {
            if (input[i] == 1) {
                ++countFrequencyOfOnes
            }
        }
        if (countFrequencyOfOnes > 0) {
            return input.size - countFrequencyOfOnes
        }

        for (back in 1..<input.size) {
            val cloneInput = input.clone()
            searchForMinSizeSubarrayWithGreatestCommonDivisorOfOne(cloneInput, back)
        }

        if (minSizeSubarrayWithGreatestCommonDivisorOfOne == Int.MAX_VALUE) {
            return NOT_POSSIBLE_MAKE_ALL_ARRAY_ELEMENTS_EQUAL_TO_ONE
        }

        return input.size + minSizeSubarrayWithGreatestCommonDivisorOfOne - 1
    }

    private fun findGreatestCommonDivisor(smaller: Int, larger: Int): Int {
        if (smaller == 0) {
            return larger
        }
        return findGreatestCommonDivisor(larger % smaller, smaller)
    }

    private fun searchForMinSizeSubarrayWithGreatestCommonDivisorOfOne(cloneInput: IntArray, back: Int) {

        for (front in back..<cloneInput.size) {
            val smaller = min(cloneInput[front - 1], cloneInput[front])
            val larger = max(cloneInput[front - 1], cloneInput[front])
            val gcd = findGreatestCommonDivisor(smaller, larger)

            if (gcd == 1) {
                minSizeSubarrayWithGreatestCommonDivisorOfOne =
                    min(minSizeSubarrayWithGreatestCommonDivisorOfOne, front - back + 1)
                break
            }
            cloneInput[front] = gcd
        }
    }
}
