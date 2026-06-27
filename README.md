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

## References

- [Huffman Coding - Wikipedia](https://en.wikipedia.org/wiki/Huffman_coding)
- [Data Compression Techniques](https://www.geeksforgeeks.org/huffman-coding-greedy-algo-3/)

---
