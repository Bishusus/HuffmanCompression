public class HuffmanNode implements Comparable<HuffmanNode> {

    // The byte value stored in a leaf node.
    byte b;

    // Placeholder value for internal nodes (not used for leaves).
    int c;

    // Frequency of the byte or the combined frequency of its children.
    int frequency;

    // Left child of the Huffman tree.
    HuffmanNode left;

    // Right child of the Huffman tree.
    HuffmanNode right;

    // Creates a leaf node containing a byte and its frequency.
    public HuffmanNode(byte b, int frequency) {
        this.b = b;
        this.frequency = frequency;
    }

    // Creates an internal node with the given children and combined frequency.
    public HuffmanNode(int c, int frequency, HuffmanNode left, HuffmanNode right) {
        this.c = c;
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }

    // Returns true if this node is a leaf (has no children).
    public boolean isLeaf() {
        return left == null && right == null;
    }

    // Compares nodes by frequency for use in a priority queue.
    @Override
    public int compareTo(HuffmanNode o) {
        return Integer.compare(this.frequency, o.frequency);
    }

    // Returns a string representation of the node for debugging.
    @Override
    public String toString() {
        return "HuffmanNode{" + "Byte=" + b + ", frequency=" + frequency + '}';
    }
}