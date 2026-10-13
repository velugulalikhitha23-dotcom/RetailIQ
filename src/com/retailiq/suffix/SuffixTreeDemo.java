package com.retailiq.suffix;

import java.util.Map;
import java.util.TreeMap;

public class SuffixTreeDemo {

    private static class Node {
        int start;
        int end;
        int suffixIndex = -1;

        Map<Character, Node> children = new TreeMap<>();

        Node(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    private final String text;
    private final Node root;

    public SuffixTreeDemo(String input) {
        // Unique terminal symbol makes every suffix distinct.
        this.text = input + "$";
        this.root = new Node(-1, -1);

        for (int i = 0; i < text.length(); i++) {
            insertSuffix(i);
        }
    }

    private void insertSuffix(int suffixStart) {
        Node current = root;
        int pos = suffixStart;

        while (pos < text.length()) {
            char first = text.charAt(pos);
            Node child = current.children.get(first);

            // No matching edge: create a new leaf.
            if (child == null) {
                Node leaf = new Node(pos, text.length());
                leaf.suffixIndex = suffixStart;
                current.children.put(first, leaf);
                return;
            }

            int matched = 0;

            while (matched < child.end - child.start
                    && pos + matched < text.length()
                    && text.charAt(child.start + matched)
                       == text.charAt(pos + matched)) {
                matched++;
            }

            // Entire edge matches. Continue from its child.
            if (matched == child.end - child.start) {
                pos += matched;
                current = child;
                continue;
            }

            // Split the existing edge.
            Node split = new Node(
                    child.start,
                    child.start + matched
            );

            current.children.put(first, split);

            child.start += matched;
            split.children.put(text.charAt(child.start), child);

            pos += matched;

            // Add the new suffix as another branch.
            if (pos < text.length()) {
                Node leaf = new Node(pos, text.length());
                leaf.suffixIndex = suffixStart;
                split.children.put(text.charAt(pos), leaf);
            } else {
                split.suffixIndex = suffixStart;
            }

            return;
        }
    }

    public boolean contains(String pattern) {
        Node current = root;
        int p = 0;

        while (p < pattern.length()) {
            Node child = current.children.get(pattern.charAt(p));

            if (child == null) {
                return false;
            }

            int k = child.start;

            while (k < child.end && p < pattern.length()) {
                if (text.charAt(k) != pattern.charAt(p)) {
                    return false;
                }

                k++;
                p++;
            }

            if (p == pattern.length()) {
                return true;
            }

            current = child;
        }

        return true;
    }

    private void printTree(Node node, String indent) {
        for (Node child : node.children.values()) {
            String edge = text.substring(child.start, child.end);

            System.out.println(indent + "|-- " + edge
                    + (child.suffixIndex >= 0
                    ? " [suffix index " + child.suffixIndex + "]"
                    : ""));

            printTree(child, indent + "    ");
        }
    }

    public static void main(String[] args) {
        String input = "banana";

        SuffixTreeDemo tree = new SuffixTreeDemo(input);

        System.out.println("Input: " + input);
        System.out.println("Suffix Tree:");

        tree.printTree(tree.root, "");

        System.out.println("Contains 'ana': "
                + tree.contains("ana"));

        System.out.println("Contains 'xyz': "
                + tree.contains("xyz"));
    }
}