# IO Streams
Just personal 101-cookbook about IO streams.

## Byte streams

### [InputStream](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/io/InputStream.html)
- Stream of **!! BYTES !!**, not characters
- It's an abstract class -- common predecessor for all input streams implementations
- Most important method is [read()](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/io/InputStream.html#read()), which reads the byte from its source:
    - 0-255: Valid byte read
    - -1: Denotes end of input
- Concrete implementations of `InputStream`, which are kinda common and are often used:
    - [FileInputStream](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/io/FilterInputStream.html)
        - The source of this stream is `File`
        - We are reading raw bytes from the file, not characters -- for that we use `FileReader`
    - [BufferedInputStream](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/io/BufferedInputStream.html)
        - Wrapper around `InputStream`, which adds buffering -- hence, we should use this for more performant readings

### [OutputStream](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/io/OutputStream.html)
- Again, stream of **!! BYTES !!**, not characters
- It's an abstract class -- common predecessor for all output streams implementations
- Most important method is [write()](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/io/OutputStream.html#write(int)), which gets the int and writes the least significant byte into its destination
- Concrete implementations are analogical to inputs streams:
    - [FileOutputStream](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/io/FileOutputStream.html)
        - For writing of the raw bytes into the destination file, for character files, we can use `FileWriter`
    - [BufferedOutputStream](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/io/BufferedOutputStream.html)
    - [PrintStream](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/io/PrintStream.html) is a wrapper around `OutputStream` to provide ability to print representations of various data to a character-output stream
      - In case when writing characters is enough, `PrintWriter` should be used

## Character streams
- As byte streams work over (raw) bytes, character streams work over characters (taking charsets into account)

### [Reader](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/io/Reader.html)
- Stream of characters, not raw bytes
- Analogical to what is `InputStream` to byte streams: common (abstract) parent class for all **character streams** readers
- Handy subclasses:
    - [FileReader](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/io/FileReader.html)
        - In case the source file is not character file, we should use `FileInputStream`
    - [BufferedReader](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/io/BufferedReader.html)
        - The only high-level change is that it does not wrap `InputStream`, but `Reader`

### [Writer](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/io/Writer.html)
- Again the same old song: common (abstract) parent class for all **character streams** writers
- Handy subclasses:
    - [FileWriter](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/io/FileWriter.html)
        - In case the destination file is not character file, we should use `FileOutputStream`
    - [BufferedWriter](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/io/BufferedWriter.html)
        - Wraps `Writer`
    - [PrintWriter](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/io/PrintWriter.html) is a wrapper around `Writer` to provide ability to print representations of various data to a text-output stream
        - In case when writing characters is not enough, `PrintStream` should be used

## Byte streams - Character streams bridge
- Byte streams and Character streams worlds are connected thanks to [InputStreamReader](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/io/InputStreamReader.html)
- This connection is **uni-directional**: from byte streams to character streams, i.e., when I've got byte stream which I wanna transfer to character stream, I can use `InputStreamReader` for this translation
