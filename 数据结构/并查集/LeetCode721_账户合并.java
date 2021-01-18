import java.util.*;
import java.io.*;

class Solution {
    
    int[] parent;

    public void union(int a, int b) {
        parent[find(a)] = find(b);
    }

    public int find(int a) {
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    // 比较繁琐，想清楚了写就很简单，没啥意思
    public List<List<String>> accountsMerge(List<List<String>> acc) {
        //email : idx
        HashMap<String, Integer> e2i = new HashMap<>();
        //idx : name
        HashMap<Integer, String> i2n = new HashMap<>();
        int cnt = 0;
        for (int i = 0; i < acc.size(); i++) {
            String name = acc.get(i).get(0);
            for (int j = 1; j < acc.get(i).size(); j++) {
                String email = acc.get(i).get(j);
                if (!e2i.containsKey(email)) {
                    e2i.put(email, cnt);
                    i2n.put(cnt++, name);
                }
            }
        }
        parent = new int[cnt];
        for (int i = 0; i < cnt; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < acc.size(); i++) {
            int first = e2i.get(acc.get(i).get(1));
            for (int j = 2; j < acc.get(i).size(); j++) {
                union(first, e2i.get(acc.get(i).get(j)));
            }
        }
        // idx : emails
        HashMap<Integer, List<String>> map = new HashMap<>();
        for (String email : e2i.keySet()) {
            map.computeIfAbsent(find(e2i.get(email)), k->new LinkedList<>()).add(email);
        }
        List<List<String>> res = new ArrayList<>();
        for (Integer idx : map.keySet()) {
            String name = i2n.get(idx);
            List<String> emails = map.get(idx);
            Collections.sort(emails);
            emails.add(0, name);
            res.add(emails);
        }
        return res;
    }
}