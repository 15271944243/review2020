package review.backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
     * 约束条件:
     * 如果存在多种有效的行程，请你按字符自然排序返回最小的行程组合.
     * 例如,行程 ["JFK", "LGA"] 与 ["JFK", "LGB"] 相比就更小,排序更靠前
     * 所有的机场都用三个大写字母表示（机场代码）
     * 假定所有机票至少存在一种合理的行程
     * 所有的机票必须都用一次且只能用一次
     *
     * 思路: 采用递归-回溯-剪枝的思想
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

    private List<List<String>> getTickets2() {
        // [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
        List<List<String>> tickets = new ArrayList<>();
        List<String> list1 = new ArrayList<>();
        list1.add("JFK");
        list1.add("SFO");
        List<String> list2 = new ArrayList<>();
        list2.add("JFK");
        list2.add("ATL");
        List<String> list3 = new ArrayList<>();
        list3.add("SFO");
        list3.add("ATL");
        List<String> list4 = new ArrayList<>();
        list4.add("ATL");
        list4.add("JFK");
        List<String> list5 = new ArrayList<>();
        list5.add("ATL");
        list5.add("SFO");
        tickets.add(list1);
        tickets.add(list2);
        tickets.add(list3);
        tickets.add(list4);
        tickets.add(list5);
        return tickets;
    }

    private List<List<String>> getTickets3() {
        // Input    [["JFK","KUL"],["JFK","NRT"],["NRT","JFK"]]
        // Expected ["JFK","NRT","JFK","KUL"]
        List<List<String>> tickets = new ArrayList<>();
        List<String> list1 = new ArrayList<>();
        list1.add("JFK");
        list1.add("KUL");
        List<String> list2 = new ArrayList<>();
        list2.add("JFK");
        list2.add("NRT");
        List<String> list3 = new ArrayList<>();
        list3.add("NRT");
        list3.add("JFK");
        tickets.add(list1);
        tickets.add(list2);
        tickets.add(list3);
        return tickets;
    }

    public List<String> findItinerary(List<List<String>> tickets) {
        List<String> result = new ArrayList<>(tickets.size());
        // key : 出发地  value: key: 目的地 value: 可使用次数
        Map<String, TreeMap<String, Integer>> ticketMap = ticketMap(tickets);
        // 因为第一个"JFK"是递归结束才加入,所以这里的siz = tickets.size(), 而不是 tickets.size() + 1
        backtracking(ticketMap, "JFK", tickets.size(), result);
        if (result.size() > 0) {
            result.add(0, "JFK");
        }
        return result;
    }

    /**
     *
     * @param tickets
     * @return
     */
    private Map<String, TreeMap<String, Integer>> ticketMap(List<List<String>> tickets) {
        // 使用TreeMap就是为了给目的地机场进行排序,按字典升序排序
        Map<String, TreeMap<String, Integer>> ticketMap = new HashMap<>();
        for (List<String> ticket : tickets) {
            String departure = ticket.get(0);
            String arrival = ticket.get(1);
            if (ticketMap.containsKey(departure)) {
                TreeMap<String, Integer> arrivalMap = ticketMap.get(departure);
                if (arrivalMap.containsKey(arrival)) {
                    arrivalMap.put(arrival, arrivalMap.get(arrival) + 1);
                } else {
                    arrivalMap.put(arrival, 1);
                }
            } else {
                TreeMap<String, Integer> arrivalMap = new TreeMap<>();
                arrivalMap.put(arrival, 1);
                ticketMap.put(departure, arrivalMap);
            }
        }
        return ticketMap;
    }
    /**
     * 1. 注意不能成为死循环
     * 2. 要在能使用全部机票的情况下,再按字符自然排序返回最小的行程组合
     * 例如 Input:    [["JFK","KUL"],["JFK","NRT"],["NRT","JFK"]]  Expected ["JFK","NRT","JFK","KUL"]
     * 这是就不能先判断 KUL 和 NRT 的自然排序,因为 KUL < NRT,如果选了 ["JFK","KUL"],就无法使用全部机票了
     * @param ticketMap
     * @param departure
     * @param result
     */
    private boolean backtracking(Map<String, TreeMap<String, Integer>> ticketMap, String departure, int size, List<String> result) {
        // 结果集的数量已经足够
        if (result.size() == size) {
            return true;
        }
        TreeMap<String, Integer> treeMap = ticketMap.get(departure);
        if(treeMap != null) {
            for (Map.Entry<String, Integer> entry : treeMap.entrySet()) {
                Integer value = entry.getValue();
                if (value  < 1) {
                    continue;
                }
                String arrival = entry.getKey();
                treeMap.put(arrival, treeMap.get(arrival) - 1);
                result.add(arrival);
                boolean r = backtracking(ticketMap, arrival, size, result);
                if (r) {
                    // 因为TreeMap有序,所以直接返回结果就行
                    return true;
                }
                treeMap.put(arrival, treeMap.get(arrival) + 1);
                result.remove(result.size() - 1);
            }
        }
        // 当前出发地找不到目的地
        return false;
    }
}
