import java.io.*;
import java.util.HashMap;

public class Compressor {

    // Stores the frequency of each byte in the input file.
    private HashMap<Byte, Integer> freq;

    // Builds and returns the frequency table for the input file.
    private HashMap<Byte, Integer> frequency(String filename) {

        freq = new HashMap<>();

        try(FileInputStream fr = new FileInputStream(filename))
        {
            int ch;

            // Count the occurrence of every byte in the file.
            while((ch = fr.read()) != -1)
            {
                byte b = (byte) ch;
                freq.put(b, freq.getOrDefault(b, 0) + 1);
            }
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return freq;
    }

    // Compresses the input file using Huffman coding.
    public void compress(String input_filename, String output_filename) {

        // Build the frequency table.
        HashMap<Byte, Integer> freq = frequency(input_filename);

        // Handle the special case of an empty input file.
        if(freq.isEmpty())
        {
            try (FileOutputStream fos = new FileOutputStream(output_filename))
            {
                return;
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        // Build the Huffman tree and generate binary codes.
        HuffmanTree tree = new HuffmanTree();
        HuffmanNode root = tree.buildTree(freq);
        HashMap<Byte, String> code = tree.buildCode(root);

        try(FileInputStream fr = new FileInputStream(input_filename);
            DataOutputStream dos = new DataOutputStream(new FileOutputStream(output_filename)))
        {
            // Write the number of entries in the frequency table.
            dos.writeInt(freq.size());

            int originalByteSize = 0;

            // Write the frequency table to the compressed file.
            for (HashMap.Entry<Byte, Integer> entry : freq.entrySet()) {
                dos.writeByte(entry.getKey());
                dos.writeInt(entry.getValue());
                originalByteSize += entry.getValue();
            }

            // Write the original number of bytes for decompression.
            dos.writeInt(originalByteSize);

            int ch;
            int currentByte = 0;
            int bitsInCurrentByte = 0;

            // Read the input file again and encode each byte.
            while ((ch = fr.read()) != -1)
            {
                byte b = (byte) ch;
                String encoding = code.get(b);

                // Pack the Huffman bits into bytes.
                for(int i = 0; i < encoding.length(); i++)
                {
                    char bit = encoding.charAt(i);

                    currentByte <<= 1;

                    if(bit == '1') {
                        currentByte |= 1;
                    }

                    bitsInCurrentByte++;

                    // Write a full byte once 8 bits have been collected.
                    if(bitsInCurrentByte == 8) {
                        dos.write(currentByte);

                        currentByte = 0;
                        bitsInCurrentByte = 0;
                    }
                }
            }

            // Pad the final byte with zeros if necessary.
            if (bitsInCurrentByte > 0) {

                int remaining = 8 - bitsInCurrentByte;

                for(int i = 0; i < remaining; i++)
                {
                    currentByte <<= 1;
                }

                dos.write(currentByte);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}