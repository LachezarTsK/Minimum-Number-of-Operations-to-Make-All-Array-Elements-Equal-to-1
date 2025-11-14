
package main

import (
    "math"
    "slices"
)

const NOT_POSSIBLE_MAKE_ALL_ARRAY_ELEMENTS_EQUAL_TO_ONE = -1
var minSizeSubarrayWithGreatestCommonDivisorOfOne int

func minOperations(input []int) int {
    minSizeSubarrayWithGreatestCommonDivisorOfOne = math.MaxInt

    var countFrequencyOfOnes = 0
    for i := range input {
        if input[i] == 1 {
            countFrequencyOfOnes++
        }
    }
    if countFrequencyOfOnes > 0 {
        return len(input) - countFrequencyOfOnes
    }

    for back := 1; back < len(input); back++ {
        cloneInput := slices.Clone(input)
        searchForMinSizeSubarrayWithGreatestCommonDivisorOfOne(cloneInput, back)
    }

    if minSizeSubarrayWithGreatestCommonDivisorOfOne == math.MaxInt {
        return NOT_POSSIBLE_MAKE_ALL_ARRAY_ELEMENTS_EQUAL_TO_ONE
    }

    return len(input) + minSizeSubarrayWithGreatestCommonDivisorOfOne - 1
}

func findGreatestCommonDivisor(smaller int, larger int) int {
    if smaller == 0 {
        return larger
    }
    return findGreatestCommonDivisor(larger % smaller, smaller)
}

func searchForMinSizeSubarrayWithGreatestCommonDivisorOfOne(cloneInput []int, back int) {

    for front := back; front < len(cloneInput); front++ {
        smaller := min(cloneInput[front - 1], cloneInput[front])
        larger := max(cloneInput[front - 1], cloneInput[front])
        gcd := findGreatestCommonDivisor(smaller, larger)

        if gcd == 1 {
            minSizeSubarrayWithGreatestCommonDivisorOfOne =
                min(minSizeSubarrayWithGreatestCommonDivisorOfOne, front - back+1)
            break
        }
        cloneInput[front] = gcd
    }
}
