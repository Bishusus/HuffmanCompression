//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Compressor compressor = new Compressor();
        compressor.compress("TextFiles/sample2.txt", "TextFiles/compressed_text.txt");
        Decompressor decompressor = new Decompressor();
        decompressor.decompress("TextFiles/compressed_text.txt", "TextFiles/decompressed_text.txt");
        compressor.compress("Images/bishesh.jpg", "Images/compressed_image.txt");
        decompressor.decompress("Images/compressed_image.txt", "Images/decompressed_image.jpg");
        compressor.compress("TextFiles/sample3.txt", "TextFiles/compressed_sample3.txt");
        decompressor.decompress("TextFiles/compressed_sample3.txt", "TextFiles/decompressed_sample3.txt");
    }
}