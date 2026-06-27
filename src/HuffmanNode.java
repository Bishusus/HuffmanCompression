public class HuffmanNode implements Comparable<HuffmanNode> {
    byte b;
    int c;
    int frequency;
    HuffmanNode left;
    HuffmanNode right;

    // Leaf node
    public HuffmanNode(byte b, int frequency) {
        this.b = b;
        this.frequency = frequency;
    }

    // Internal node
    public HuffmanNode(int c, int frequency, HuffmanNode left, HuffmanNode right) {
        this.c = c;
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }

    @Override
    public int compareTo(HuffmanNode o) {
        return Integer.compare(this.frequency, o.frequency);
    }

    @Override
    public String toString() {
        return "HuffmanNode{" + "Byte=" + b + ", frequency=" + frequency + '}';
    }

}
