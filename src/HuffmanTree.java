import java.util.HashMap;
import java.util.PriorityQueue;

public class HuffmanTree {
    private HuffmanNode root;

    public HuffmanNode buildTree(HashMap<Byte, Integer> map) {
        PriorityQueue<HuffmanNode> queue = new PriorityQueue<>();
        for (HashMap.Entry<Byte, Integer> entry : map.entrySet()) {
            queue.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }
        while (queue.size() > 1) {
            HuffmanNode left = queue.poll();
            HuffmanNode right = queue.poll();
            int total_frequency = left.frequency + right.frequency;
            HuffmanNode internal_node = new HuffmanNode(-1, total_frequency, left, right);
            queue.add(internal_node);
        }
        root = queue.poll();
        return root;
    }

    public HashMap<Byte, String> buildCode(HuffmanNode root) {
        HashMap<Byte, String> map = new HashMap<>();
        generateCodes(root, new StringBuilder(), map);
        return map;
    }

    private void generateCodes(HuffmanNode node, StringBuilder code, HashMap<Byte, String> map) {
        if (node == null) {
            return;
        }

        if (root.isLeaf()) {
            code.append('0');
            map.put(node.b, code.toString());
            return;
        }

        if (node.isLeaf()) {
            map.put(node.b, code.toString());
            return;
        }

        code.append('0');
        generateCodes(node.left, code, map);
        code.deleteCharAt(code.length() - 1);

        code.append('1');
        generateCodes(node.right, code, map);
        code.deleteCharAt(code.length() - 1);
    }
}
