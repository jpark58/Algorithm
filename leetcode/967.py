'''
class Solution {
    public int[] numsSameConsecDiff(int N, int K) {
        if (N == 1)
            return new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        List<Integer> result = new ArrayList<Integer>();
        for (int num = 1; num < 10; ++num)
            dfs(N - 1, num, K, result);
        
        int[] answer = new int[result.size()];
        int i = 0;
        for(int e: result){
            answer[i++] = e;
        }

        return answer;
    }

    private void dfs(int N, int num, int K, List<Integer> result) {
        if (N == 0) {
            result.add(num);
            return;
        }

        if (num % 10 + K < 10)
            dfs(N - 1, num * 10 + num % 10 + K, K, result);

        if (K > 0 && num % 10 - K >= 0)
            dfs(N - 1, num * 10 + num % 10 - K, K, result);
    }
}
'''


class Solution(object):
    def numsSameConsecDiff(self, n, k):
        """
        :type n: int
        :type k: int
        :rtype: List[int]
        """
        result = []

        if n == 1:
            result = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
            return result

        def dfs(N, num, k, result):
            if N == 0:
                result.append(num)
                return

            if (num % 10 + k < 10):
                dfs(N-1, num*10 + num % 10+k, k, result)
            if k > 0 and (num % 10 - k >= 0):
                dfs(N-1, num*10 + num % 10-k, k, result)

        for i in range(1, 10):
            dfs(n-1, i, k, result)

        return result
