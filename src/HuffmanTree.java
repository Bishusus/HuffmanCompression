import java.util.HashMap;
import java.util.PriorityQueue;

public class HuffmanTree {

    // Root node of the Huffman tree.
    private HuffmanNode root;

    // Builds the Huffman tree from a frequency map and returns the root.
    public HuffmanNode buildTree(HashMap<Byte, Integer> map) {

        // Priority queue automatically orders nodes by frequency.
        PriorityQueue<HuffmanNode> queue = new PriorityQueue<>();

        // Create a leaf node for each byte and add it to the queue.
        for (HashMap.Entry<Byte, Integer> entry : map.entrySet()) {
            queue.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        // Repeatedly combine the two least frequent nodes until one remains.
        while (queue.size() > 1) {
            HuffmanNode left = queue.poll();
            HuffmanNode right = queue.poll();

            int total_frequency = left.frequency + right.frequency;

            // Create a new internal node with the combined frequency.
            HuffmanNode internal_node = new HuffmanNode(-1, total_frequency, left, right);

            queue.add(internal_node);
        }

        // The remaining node is the root of the Huffman tree.
        root = queue.poll();
        return root;
    }

    // Generates the Huffman codes for every byte in the tree.
    public HashMap<Byte, String> buildCode(HuffmanNode root) {
        HashMap<Byte, String> map = new HashMap<>();
        generateCodes(root, new StringBuilder(), map);
        return map;
    }

    // Recursively traverses the tree to generate binary codes.
    private void generateCodes(HuffmanNode node, StringBuilder code, HashMap<Byte, String> map) {

        // Base case: reached a null node.
        if (node == null) {
            return;
        }

        // Special case: tree contains only one unique byte.
        if (root.isLeaf()) {
            code.append('0');
            map.put(node.b, code.toString());
            return;
        }

        // If a leaf node is reached, store its generated code.
        if (node.isLeaf()) {
            map.put(node.b, code.toString());
            return;
        }

        // Traverse left by appending '0'.
        code.append('0');
        generateCodes(node.left, code, map);
        code.deleteCharAt(code.length() - 1);

        // Traverse right by appending '1'.
        code.append('1');
        generateCodes(node.right, code, map);
        code.deleteCharAt(code.length() - 1);
    }
}