# Huffman Compression

An implementation of the **Huffman Compression Algorithm** to compress different file types without loss of data. This is useful for storing and transmitting data over the network efficiently.

## Overview

Huffman coding is a lossless data compression algorithm that uses variable-length codes for different characters based on their frequency of occurrence. Characters that appear more frequently receive shorter binary codes, while less frequent characters receive longer codes. This results in significant file size reduction.

## Features

- ✅ **Lossless Compression** - Compress files without any data loss
- ✅ **Multiple File Type Support** - Works with various file types
- ✅ **Efficient Storage** - Reduces file size using variable-length encoding
- ✅ **Network Friendly** - Optimized for data transmission
- ✅ **Decompression** - Fully reversible compression
- ✅ **Java Implementation** - Built with Java for reliability and performance

## Algorithm Explanation

### How Huffman Compression Works:

1. **Frequency Analysis** - Count the frequency of each character in the input
2. **Build Huffman Tree** - Create a binary tree by repeatedly combining the two nodes with lowest frequencies
3. **Generate Codes** - Traverse the tree to generate variable-length binary codes for each character
4. **Encode** - Replace each character with its binary code
5. **Compress** - Write the encoded data and the Huffman tree to an output file

### Decompression:

1. **Read Tree** - Retrieve the Huffman tree from the compressed file
2. **Decode** - Use the tree to convert binary codes back to original characters
3. **Reconstruct** - Rebuild the original file with all its data intact

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- A Java IDE or command-line compiler

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/Bishusus/HuffmanCompression.git
   cd HuffmanCompression
   ```

2. Compile the project:
   ```bash
   javac *.java
   ```

## Usage

### Compressing a File

```java
// Create an instance of the Huffman compressor
HuffmanCompressor compressor = new HuffmanCompressor();

// Compress a file
compressor.compress("input.txt", "output.huf");
```

### Decompressing a File

```java
// Create an instance of the Huffman decompressor
HuffmanDecompressor decompressor = new HuffmanDecompressor();

// Decompress a file
decompressor.decompress("output.huf", "recovered.txt");
```

## Project Structure

```
HuffmanCompression/
├── HuffmanCompressor.java     # Main compression logic
├── HuffmanDecompressor.java   # Decompression logic
├── HuffmanNode.java           # Node structure for Huffman tree
├── HuffmanTree.java           # Huffman tree implementation
└── README.md                  # This file
```

## Example

```java
public class Main {
    public static void main(String[] args) {
        // Compress a file
        HuffmanCompressor compressor = new HuffmanCompressor();
        compressor.compress("document.txt", "document.huf");
        
        System.out.println("File compressed successfully!");
        
        // Decompress the file
        HuffmanDecompressor decompressor = new HuffmanDecompressor();
        decompressor.decompress("document.huf", "document_recovered.txt");
        
        System.out.println("File decompressed successfully!");
    }
}
```

## Compression Ratio

Huffman compression achieves varying compression ratios depending on the file type and content:
- **Text Files** - 40-60% compression
- **Source Code** - 30-50% compression
- **Already Compressed Files** - Little to no compression (limited effectiveness)

## Time Complexity

- **Compression** - O(n log n) where n is the number of unique characters
- **Decompression** - O(m) where m is the total number of characters in the decompressed file

## Space Complexity

- O(n) for storing the Huffman tree and character frequencies

## Advantages

- ✅ Simple and efficient algorithm
- ✅ Works well for text and source code files
- ✅ Lossless compression ensures data integrity
- ✅ Fast decompression process
- ✅ Optimal prefix-free code generation

## Limitations

- ❌ Less effective on already compressed files
- ❌ Requires storing the Huffman tree with compressed data
- ❌ Not ideal for binary files with uniform distribution

## License

This project is open source and available for educational and personal use.

## Contributing

Feel free to fork this repository, make improvements, and submit pull requests. Contributions are welcome!

## Author

**Bishusus** - [GitHub Profile](https://github.com/Bishusus)

## References

- [Huffman Coding - Wikipedia](https://en.wikipedia.org/wiki/Huffman_coding)
- [Data Compression Techniques](https://www.geeksforgeeks.org/huffman-coding-greedy-algo-3/)

---

⭐ If you find this project useful, please consider giving it a star!
