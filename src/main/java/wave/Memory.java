package wave;

import java.util.ArrayList;
import java.util.List;

public class Memory {
    private final int size;
    private byte[] objects;

    public Memory(int size) {
        this.size = size;
        objects = new byte[size*1024*1024];
    }

    public int getSize() {
        return size;
    }

}
