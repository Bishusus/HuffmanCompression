import java.io.*;
import java.util.HashMap;

public class Compressor {

    private HashMap<Byte, Integer> freq;

    private HashMap<Byte, Integer> frequency(String filename){
        freq = new HashMap<>();
        try(FileInputStream fr = new FileInputStream(filename))
        {
           int ch;
           while((ch = fr.read()) != -1)
           {
               byte b = (byte)ch;
               freq.put(b, freq.getOrDefault(b,0)+1);
           }
        }
        catch (FileNotFoundException e) {
           throw new RuntimeException(e);
       } catch (IOException e) {
           throw new RuntimeException(e);
       }
       return freq;
   }

   public void compress(String input_filename, String output_filename) {
        HashMap<Byte, Integer> freq = frequency(input_filename);
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
        HuffmanTree tree = new HuffmanTree();
        HuffmanNode root = tree.buildTree(freq);
        HashMap<Byte, String> code = tree.buildCode(root);
        try(FileInputStream fr = new FileInputStream(input_filename);
            DataOutputStream dos = new DataOutputStream(new FileOutputStream(output_filename))
        )
        {
            dos.writeInt(freq.size());
            int originalByteSize= 0;
            for (HashMap.Entry<Byte, Integer> entry : freq.entrySet()) {
                dos.writeByte(entry.getKey());
                dos.writeInt(entry.getValue());
                originalByteSize += entry.getValue();
            }
            dos.writeInt(originalByteSize);
            int ch;
            int currentByte = 0;
            int bitsInCurrentByte = 0;
            while ((ch = fr.read()) !=-1)
            {
                byte b = (byte)ch;
                String encoding = code.get(b);
                for(int i =0; i < encoding.length(); i++)
                {
                    char bit = encoding.charAt(i);

                    currentByte <<= 1;

                    if(bit == '1') {
                        currentByte |= 1;
                    }

                    bitsInCurrentByte += 1;
                    if(bitsInCurrentByte == 8) {
                        dos.write(currentByte);

                        currentByte = 0;
                        bitsInCurrentByte = 0;
                    }
                }
            }
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