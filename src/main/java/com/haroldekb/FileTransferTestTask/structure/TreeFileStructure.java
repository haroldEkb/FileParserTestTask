package com.haroldekb.FileTransferTestTask.structure;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.LinkedList;
import java.util.List;

/**
 * @author haroldekb@mail.ru
 **/

public class TreeFileStructure {
    private static class Node{
        private Integer number;
        private Integer depth;
        @JsonIgnore
        private Node parent;
        private List<Node> children;

        public Node(Integer number, Integer depth, Node parent){
            this.number = number;
            this.depth = depth;
            this.parent = parent;
            children = new LinkedList<>();
            if (parent != null) parent.children.add(this);
        }

        public Integer getNumber() {
            return number;
        }

        public void setNumber(Integer number) {
            this.number = number;
        }

        public Integer getDepth() {
            return depth;
        }

        public void setDepth(Integer depth) {
            this.depth = depth;
        }

        public List<Node> getChildren() {
            return children;
        }

        public void setChildren(List<Node> children) {
            this.children = children;
        }
    }

    private Node root = new Node(-1, 0, null);

    public TreeFileStructure(List<String> list){
        if (list != null){
            String currentString;
            Node currentNode = root;
            int currentDepth;
            for (int i = 0; i < list.size(); i++) {
                currentString = list.get(i);
                if (currentString.startsWith("#")) {
                    currentDepth = getDepth(currentString);
                    currentNode = put(i, currentDepth, currentNode);
                }
            }
        }
    }

    private int getDepth(String currentString) {
        int i = 0;
        char ch = currentString.charAt(i);
        while (ch == '#') {
            i++;
            ch = currentString.charAt(i);
        }
        return i;
    }

    private Node put(Integer i, Integer depth, Node parent){
        if (depth.equals(parent.depth)) {
            return new Node(i, depth, parent.parent);
        }
        if (depth.compareTo(parent.depth) > 0){
            return new Node(i, depth, parent);
        }
        return put(i, depth, parent.parent);
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }
}
