import java.io.*;
import java.util.HashMap;

public class Decompressor {

    private HashMap<Byte, Integer> freq;

    public void decompress(String inputFile, String outputFile)
    {
        File file = new File(inputFile);
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
            int n = dis.readInt();
            for (int i = 0; i < n; i++)
            {
                byte b = dis.readByte();
                int f = dis.readInt();
                freq.put(b, f);
            }
            int originalByteCount = dis.readInt();
            HuffmanTree tree = new HuffmanTree();
            HuffmanNode root = tree.buildTree(freq);
            HuffmanNode currentNode = root;
            int decodedChar = 0;
            if(currentNode.isLeaf())
            {
                while(decodedChar < originalByteCount)
                {
                    int value = dis.read();
                    if (value == -1)
                    {
                        break;
                    }
                    for(int i = 7; i >= 0 && decodedChar < originalByteCount; i--) {
                        fw.write(currentNode.b);
                        decodedChar++;
                    }
                }
                return;
            }
            while(decodedChar < originalByteCount)
            {
                int value = dis.read();
                if (value == -1)
                {
                    break;
                }
                for(int i = 7; i >= 0 && decodedChar < originalByteCount; i--) {
                    int bit = (value >> i) & 1;

                    if (bit == 0) {
                        currentNode = currentNode.left;
                    } else {
                        currentNode = currentNode.right;
                    }

                    if (currentNode.left == null && currentNode.right == null) {
                        fw.write(currentNode.b);
                        decodedChar++;
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
