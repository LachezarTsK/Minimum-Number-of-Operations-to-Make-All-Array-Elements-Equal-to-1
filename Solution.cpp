
#include <span>
#include <vector>
#include <limits>
#include <algorithm>
using namespace std;

class Solution {

    static const int NOT_POSSIBLE_MAKE_ALL_ARRAY_ELEMENTS_EQUAL_TO_ONE = -1;
    int minSizeSubarrayWithGreatestCommonDivisorOfOne = numeric_limits<int>::max();

public:
    int minOperations(const vector<int>& input) {
        int countFrequencyOfOnes = 0;
        for (int i = 0; i < input.size(); ++i) {
            if (input[i] == 1) {
                ++countFrequencyOfOnes;
            }
        }
        if (countFrequencyOfOnes > 0) {
            return input.size() - countFrequencyOfOnes;
        }

        for (int back = 1; back < input.size(); ++back) {
            vector<int> cloneInput{ input.begin(),input.end() };
            searchForMinSizeSubarrayWithGreatestCommonDivisorOfOne(cloneInput, back);
        }

        if (minSizeSubarrayWithGreatestCommonDivisorOfOne == numeric_limits<int>::max()) {
            return NOT_POSSIBLE_MAKE_ALL_ARRAY_ELEMENTS_EQUAL_TO_ONE;
        }

        return input.size() + minSizeSubarrayWithGreatestCommonDivisorOfOne - 1;
    }

private:
    int findGreatestCommonDivisor(int smaller, int larger) {
        if (smaller == 0) {
            return larger;
        }
        return findGreatestCommonDivisor(larger % smaller, smaller);
    }

    void searchForMinSizeSubarrayWithGreatestCommonDivisorOfOne(span<int> cloneInput, int back) {

        for (int front = back; front < cloneInput.size(); ++front) {
            int smaller = min(cloneInput[front - 1], cloneInput[front]);
            int larger = max(cloneInput[front - 1], cloneInput[front]);
            int gcd = findGreatestCommonDivisor(smaller, larger);

            if (gcd == 1) {
                minSizeSubarrayWithGreatestCommonDivisorOfOne = min(minSizeSubarrayWithGreatestCommonDivisorOfOne, front - back + 1);
                break;
            }
            cloneInput[front] = gcd;
        }
    }
};
