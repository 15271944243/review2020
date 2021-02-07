package review.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/reconstruct-itinerary/ No.332
 * 重新安排行程
 * @author: xiaoxiaoxiang
 * @date: 2021/2/7 11:23
 */
public class ReconstructItinerary {

    /**
     * Given a list of airline tickets represented by pairs of departure and arrival airports [from, to],
     * reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK.
     * Thus, the itinerary must begin with JFK.
     *
     * Note:
     *
     * If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order
     * when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
     * All airports are represented by three capital letters (IATA code).
     * You may assume all tickets form at least one valid itinerary.
     * One must use all the tickets once and only once.
     *
     * Example 1:
     *
     * Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
     * Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]
     * Example 2:
     *
     * Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
     * Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
     * Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
     *              But it is larger in lexical order.
     */

    /**
     * 题目意思: 给定一个机票的字符串二维数组 [from, to],子数组中的两个成员分别表示飞机出发和降落的机场地点,
     * 对该行程进行重新规划排序.所有这些机票都属于一个从 JFK（肯尼迪国际机场）出发的先生,所以该行程必须从 JFK 开始
     *
     * 提示:
     * 如果存在多种有效的行程，请你按字符自然排序返回最小的行程组合.
     * 例如,行程 ["JFK", "LGA"] 与 ["JFK", "LGB"] 相比就更小,排序更靠前
     * 所有的机场都用三个大写字母表示（机场代码）
     * 假定所有机票至少存在一种合理的行程
     * 所有的机票必须都用一次且只能用一次
     */

    public static void main(String[] args) {
        ReconstructItinerary demo = new ReconstructItinerary();
        List<List<String>> tickets = demo.getTickets();
        List<String> result = demo.findItinerary(tickets);
    }

    private List<List<String>> getTickets() {
        // [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
        List<List<String>> tickets = new ArrayList<>();
        List<String> list1 = new ArrayList<>();
        list1.add("MUC");
        list1.add("LHR");
        List<String> list2 = new ArrayList<>();
        list2.add("JFK");
        list2.add("MUC");
        List<String> list3 = new ArrayList<>();
        list3.add("SFO");
        list3.add("SJC");
        List<String> list4 = new ArrayList<>();
        list4.add("LHR");
        list4.add("SFO");
        tickets.add(list1);
        tickets.add(list2);
        tickets.add(list3);
        tickets.add(list4);
        return tickets;
    }

    public List<String> findItinerary(List<List<String>> tickets) {


        return null;
    }
}
