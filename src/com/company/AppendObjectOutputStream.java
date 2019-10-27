package com.company;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.IOException;

public class AppendObjectOutputStream extends ObjectOutputStream {

    public AppendObjectOutputStream(OutputStream out) throws IOException {
        super(out);
    }

    @Override
    protected void writeStreamHeader() throws IOException {
        reset();
    }

}