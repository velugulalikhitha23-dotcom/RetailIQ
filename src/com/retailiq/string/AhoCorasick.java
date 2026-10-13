
package com.retailiq.string;

import java.util.*;

public class AhoCorasick {

    static class Node {
        Map<Character, Node> children = new HashMap<>();
        Node failure;
        List<String> output = new ArrayList<>();
    }

    private final Node root = new Node();

    public AhoCorasick(String[] patterns) {

        // Build Trie
        for (String pattern : patterns) {
            Node current = root;

            for (char c : pattern.toLowerCase().toCharArray()) {
                current = current.children.computeIfAbsent(
                    c, key -> new Node()
                );
            }

            current.output.add(pattern);
        }

        // Build failure links using BFS
        Queue<Node> queue = new LinkedList<>();
        root.failure = root;

        for (Node child : root.children.values()) {
            child.failure = root;
            queue.add(child);
        }

        while (!queue.isEmpty()) {
            Node current = queue.remove();

            for (Map.Entry<Character, Node> entry
                    : current.children.entrySet()) {

                char c = entry.getKey();
                Node child = entry.getValue();
                Node fallback = current.failure;

                while (fallback != root
                        && !fallback.children.containsKey(c)) {
                    fallback = fallback.failure;
                }

                if (fallback.children.containsKey(c)) {
                    child.failure = fallback.children.get(c);
                } else {
                    child.failure = root;
                }

                child.output.addAll(child.failure.output);
                queue.add(child);
            }
        }
    }

    public void search(String text) {
        Node current = root;
        text = text.toLowerCase();
        boolean found = false;

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            while (current != root
                    && !current.children.containsKey(c)) {
                current = current.failure;
            }

            if (current.children.containsKey(c)) {
                current = current.children.get(c);
            }

            for (String pattern : current.output) {
                System.out.println(
                    "Found keyword: " + pattern
                    + " at index " + (i - pattern.length() + 1)
                );
                found = true;
            }
        }

        if (!found) {
            System.out.println("No keywords found.");
        }
    }

    public static void main(String[] args) {

        String[] keywords = {"phone", "laptop", "mouse"};

        String review =
            "This phone is good and this laptop is fast";

        AhoCorasick ac = new AhoCorasick(keywords);

        System.out.println("Review: " + review);
        ac.search(review);
    }
}
