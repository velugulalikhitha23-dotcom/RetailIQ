package com.retailiq.suffix;

import java.util.*;

public class SuffixAutomatonDemo {

    static class State {
        int len;
        int link = -1;
        Map<Character, Integer> next = new HashMap<>();
    }

    static List<State> states;
    static int last;

    static void build(String text) {
        states = new ArrayList<>();

        State root = new State();
        states.add(root);
        last = 0;

        for (char c : text.toCharArray()) {
            State current = new State();
            current.len = states.get(last).len + 1;

            int cur = states.size();
            states.add(current);

            int p = last;

            while (p != -1 &&
                   !states.get(p).next.containsKey(c)) {
                states.get(p).next.put(c, cur);
                p = states.get(p).link;
            }

            if (p == -1) {
                current.link = 0;
            } else {
                int q = states.get(p).next.get(c);

                if (states.get(p).len + 1 ==
                    states.get(q).len) {
                    current.link = q;
                } else {
                    State clone = new State();
                    clone.len = states.get(p).len + 1;
                    clone.link = states.get(q).link;
                    clone.next.putAll(states.get(q).next);

                    int cloneIndex = states.size();
                    states.add(clone);

                    while (p != -1 &&
                           states.get(p).next.getOrDefault(c, -1) == q) {
                        states.get(p).next.put(c, cloneIndex);
                        p = states.get(p).link;
                    }

                    states.get(q).link = cloneIndex;
                    current.link = cloneIndex;
                }
            }

            last = cur;
        }
    }

    static boolean contains(String pattern) {
        int current = 0;

        for (char c : pattern.toCharArray()) {
            Integer nextState =
                states.get(current).next.get(c);

            if (nextState == null) {
                return false;
            }

            current = nextState;
        }

        return true;
    }

    static long countDistinctSubstrings() {
        long count = 0;

        for (int i = 1; i < states.size(); i++) {
            State s = states.get(i);
            count += s.len - states.get(s.link).len;
        }

        return count;
    }

    public static void main(String[] args) {
        String description = "banana";

        build(description);

        System.out.println("Product Description: " + description);
        System.out.println("Contains 'ana': " + contains("ana"));
        System.out.println("Contains 'xyz': " + contains("xyz"));
        System.out.println("Distinct non-empty substrings: "
                + countDistinctSubstrings());
        System.out.println("Number of states: " + states.size());
    }
}