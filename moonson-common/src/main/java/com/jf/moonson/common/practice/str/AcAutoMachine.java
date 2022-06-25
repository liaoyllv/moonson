package com.jf.moonson.common.practice.str;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * AC自动机
 */
public class AcAutoMachine {

    private static class ACNode {

        public Character getData() {
            return data;
        }

        Character data;

        public ACNode getParent() {
            return parent;
        }

        ACNode parent;
        List<ACNode> sonNodes;
        boolean wordFlag;

        public ACNode getFailNode() {
            return failNode;
        }

        public void setFailNode(ACNode failNode) {
            this.failNode = failNode;
        }

        ACNode failNode;

        public ACNode(Character data, ACNode parent, List<ACNode> sonNodes, boolean wordFlag,
                      ACNode failNode) {
            this.data = data;
            this.parent = parent;
            this.sonNodes = sonNodes;
            this.wordFlag = wordFlag;
            this.failNode = failNode;
        }


        public List<ACNode> getSonNodes() {
            return sonNodes;
        }


    }

    private static ACNode findSonNode(ACNode node, char c) {
        for (int i = 0; i < node.getSonNodes().size(); i++) {
            ACNode acNode = node.getSonNodes().get(i);
            if (acNode.getData() == c) {
                return acNode;
            }
        }
        return null;
    }


    private static ACNode initTrieTree(String[] strings) {
        ACNode root = new ACNode(null, null, new LinkedList<>(), false, null);

        for (int i = 0; i < strings.length; i++) {
            String headStr = strings[i];
            Character headChar = headStr.charAt(0);

            ACNode headNode = findSonNode(root, headChar);
            if (headNode == null) {
                headNode = new ACNode(headChar, root, new LinkedList<>(), headStr.length() == 1,
                        root);
                root.getSonNodes().add(headNode);
            }

            // 处理后续
            ACNode parentNode = headNode;
            for (int j = 1; j < headStr.length(); j++) {
                char currentChar = headStr.charAt(j);
                ACNode currentNode = findSonNode(parentNode, currentChar);
                if (currentNode == null) {
                    // 不存在则新建
                    currentNode = new ACNode(currentChar, parentNode, new LinkedList<>(), headStr.length() == j + 1,
                            null);
                    parentNode.getSonNodes().add(currentNode);
                }
                // 父节点指向这个节点
                parentNode = currentNode;
            }
        }

        // 按照层级来处理 fail 节点
        Queue<ACNode> acNodeQueue = new LinkedBlockingQueue<ACNode>();

        acNodeQueue.add(root);
        while (acNodeQueue.size() != 0) {
            ACNode currentNode = acNodeQueue.poll();
            if (currentNode.getSonNodes().size() != 0) {
                acNodeQueue.addAll(currentNode.getSonNodes());
            }
            // 处理fail
            initFailNode(currentNode, root);
        }

        return root;
    }

    private static void initFailNode(ACNode node, ACNode root) {

        // 处理当前的节点
        if (node != root) {
            // 从父节点开始找
            if (node.getParent() == root) {
                // 如果父节点是根结点
                node.setFailNode(node.getParent());
            } else {
                // 从父节点的 fail 节点的子节点中查找相同字符
                ACNode parent = node.getParent();
                // 从父节点的 fail 节点开始往上找
                ACNode failNode = parent.getFailNode();
                for (ACNode sonNode : failNode.getSonNodes()) {
                    if (sonNode.getData().equals(node.getData())) {
                        // 匹配到则设置
                        node.setFailNode(sonNode);
                        break;
                    }
                }
                if (node.getFailNode() == null) {
                    // 没匹配到则从根结点的子节点开始找
                    for (ACNode sonNode : root.getSonNodes()) {
                        if (sonNode.getData().equals(node.getData())) {
                            // 匹配到则设置
                            node.setFailNode(sonNode);
                            break;
                        }
                    }
                }
                // 找不到则直接设置为根结点
                if (node.getFailNode() == null) {
                    node.setFailNode(root);
                }
            }
        } else {
            node.setFailNode(root);
        }

    }

    private static String getString(ACNode node) {
        List<Character> characterList = new LinkedList<>();
        while (node.getData() != null) {
            characterList.add(node.getData());
            node = node.getParent();
        }

        int size = characterList.size();
        char[] chars = new char[size];
        for (int i = 0; i < size; i++) {
            chars[size - i - 1] = characterList.get(i);
        }
        return new String(chars);
    }

    private static List<String> match(ACNode root, String s) {
        LinkedList<String> strList = new LinkedList<>();

        ACNode matchTree = root;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            boolean hasMatch = false;
            // 一直滑动到匹配到值或者fail 节点为根结点为止
            do {
                for (ACNode sonNode : matchTree.getSonNodes()) {
                    if (sonNode.getData() == c) {
                        // 匹配到
                        hasMatch = true;
                        matchTree = sonNode;
                        if (sonNode.wordFlag) {
                            // 如果到这里是词汇
                            strList.add(getString(sonNode));
                        }
                        break;
                    }
                }
                if (!hasMatch) {
                    // 没有匹配到指向 fail 节点
                    matchTree = matchTree.getFailNode();
                }
            } while (matchTree != root && !hasMatch);
        }

        return strList;
    }

    public static void main(String[] args) {
        String[] strings = {"she", "he", "say", "shr", "her", "re"};
        ACNode trieTree = initTrieTree(strings);

        match(trieTree, "yashe shre").forEach(System.out::println);
    }


}
