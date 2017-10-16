package ru.spbau.alyokhina.trie;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/** Interface for read/write byte */
public interface IOByte {
    void serialize(OutputStream out) throws IOException;
    void deserialize(InputStream in) throws IOException, ClassNotFoundException;
}
