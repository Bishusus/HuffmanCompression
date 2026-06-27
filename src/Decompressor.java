import java.io.*;
import java.util.HashMap;

public class Decompressor {

    // Stores the frequency table read from the compressed file.
    private HashMap<Byte, Integer> freq;

    // Decompresses a Huffman-compressed file.
    public void decompress(String inputFile, String outputFile)
    {
        File file = new File(inputFile);

        // Handle the special case of an empty compressed file.
        if (file.length() == 0)
        {
            try (FileOutputStream fos = new FileOutputStream(outputFile))
            {
                return;
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try (DataInputStream dis = new DataInputStream(new FileInputStream(inputFile));
             FileOutputStream fw = new FileOutputStream(outputFile))
        {
            freq = new HashMap<>();

            // Read the number of entries in the frequency table.
            int n = dis.readInt();

            // Reconstruct the frequency table.
            for (int i = 0; i < n; i++)
            {
                byte b = dis.readByte();
                int f = dis.readInt();
                freq.put(b, f);
            }

            // Read the number of original bytes before compression.
            int originalByteCount = dis.readInt();

            // Rebuild the Huffman tree from the frequency table.
            HuffmanTree tree = new HuffmanTree();
            HuffmanNode root = tree.buildTree(freq);
            HuffmanNode currentNode = root;

            // Tracks how many bytes have been decoded.
            int decodedChar = 0;

            // Special case: the file contains only one unique byte.
            if(currentNode.isLeaf())
            {
                while(decodedChar < originalByteCount)
                {
                    int value = dis.read();

                    // Stop if the compressed data ends unexpectedly.
                    if (value == -1)
                    {
                        break;
                    }

                    // Every bit represents the same byte.
                    for(int i = 7; i >= 0 && decodedChar < originalByteCount; i--) {
                        fw.write(currentNode.b);
                        decodedChar++;
                    }
                }
                return;
            }

            // Read compressed bytes until all original bytes are decoded.
            while(decodedChar < originalByteCount)
            {
                int value = dis.read();

                // Stop if the compressed data ends unexpectedly.
                if (value == -1)
                {
                    break;
                }

                // Process each bit from most significant to least significant.
                for(int i = 7; i >= 0 && decodedChar < originalByteCount; i--) {

                    int bit = (value >> i) & 1;

                    // Traverse the Huffman tree according to the current bit.
                    if (bit == 0) {
                        currentNode = currentNode.left;
                    } else {
                        currentNode = currentNode.right;
                    }

                    // A leaf node means a byte has been decoded.
                    if (currentNode.left == null && currentNode.right == null) {
                        fw.write(currentNode.b);
                        decodedChar++;

                        // Restart traversal from the root for the next symbol.
                        currentNode = root;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}