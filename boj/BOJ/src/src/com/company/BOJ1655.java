package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1655 {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder("");

        // 최대힙은 swap을 위해 PriorityQueue를 재정의 ( 큰 숫자가 우선순위가 높아지도록 )
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>((element1, element2) -> element2 - element1);
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
        int N = Integer.parseInt(br.readLine());
        int num = 0;

        while (N-- > 0) {
            num = Integer.parseInt(br.readLine());
            // 최소힙의 크기가 최대힙의 크기보다 크거나 같도록 유지한다.
            if (maxHeap.size() <= minHeap.size()) {
                maxHeap.offer(num);
            } else {
                minHeap.offer(num);
            }
            // 힙이 비어있지 않고, 최대힙의 최댓값이 최소힙의 최솟값보다 작으면 SWAP
            if (!minHeap.isEmpty() && maxHeap.peek() > minHeap.peek()) {
                maxHeap.offer(minHeap.poll());
                minHeap.offer(maxHeap.poll());
            }

            sb.append(maxHeap.peek()).append("\n");
        }
        System.out.print(sb);
    }
}
