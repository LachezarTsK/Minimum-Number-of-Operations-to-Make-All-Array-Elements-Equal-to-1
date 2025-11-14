
function minOperations(input: number[]): number {
    Util.minSizeSubarrayWithGreatestCommonDivisorOfOne = Number.MAX_SAFE_INTEGER;
    let countFrequencyOfOnes = 0;
    for (let i = 0; i < input.length; ++i) {
        if (input[i] === 1) {
            ++countFrequencyOfOnes;
        }
    }
    if (countFrequencyOfOnes > 0) {
        return input.length - countFrequencyOfOnes;
    }

    for (let back = 1; back < input.length; ++back) {
        const cloneInput = [...input];
        searchForMinSizeSubarrayWithGreatestCommonDivisorOfOne(cloneInput, back);
    }

    if (Util.minSizeSubarrayWithGreatestCommonDivisorOfOne === Number.MAX_SAFE_INTEGER) {
        return Util.NOT_POSSIBLE_MAKE_ALL_ARRAY_ELEMENTS_EQUAL_TO_ONE;
    }

    return input.length + Util.minSizeSubarrayWithGreatestCommonDivisorOfOne - 1;
};

function findGreatestCommonDivisor(smaller: number, larger: number): number {
    if (smaller === 0) {
        return larger;
    }
    return findGreatestCommonDivisor(larger % smaller, smaller);
}

function searchForMinSizeSubarrayWithGreatestCommonDivisorOfOne(cloneInput: number[], back: number): void {

    for (let front = back; front < cloneInput.length; ++front) {
        const smaller = Math.min(cloneInput[front - 1], cloneInput[front]);
        const larger = Math.max(cloneInput[front - 1], cloneInput[front]);
        const gcd = findGreatestCommonDivisor(smaller, larger);

        if (gcd === 1) {
            Util.minSizeSubarrayWithGreatestCommonDivisorOfOne
                = Math.min(Util.minSizeSubarrayWithGreatestCommonDivisorOfOne, front - back + 1);
            break;
        }
        cloneInput[front] = gcd;
    }
}

const Util = {
    NOT_POSSIBLE_MAKE_ALL_ARRAY_ELEMENTS_EQUAL_TO_ONE: -1,
    minSizeSubarrayWithGreatestCommonDivisorOfOne: Number.MAX_SAFE_INTEGER
};
