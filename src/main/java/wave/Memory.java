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

    public String getHeapSize() {
        // Get current size of heap in bytes
        Long heapSize = Runtime.getRuntime().totalMemory();
        Long heapSizeMiB = heapSize/1024/1024;
        return String.format("heapsize[MiB]=%s heapsize[B]=%s",heapSizeMiB.toString(), heapSize.toString());
    }

    public String getHeapMaxSize() {
        // Get maximum size of heap in bytes. The heap cannot grow beyond this size.
        Long heapMaxSize = Runtime.getRuntime().maxMemory();
        Long heapMaxSizeMiB = heapMaxSize/1024/1024;
        return String.format("heapMaxSize[MiB]=%s heapMaxSize[B]=%s",heapMaxSizeMiB.toString(), heapMaxSize.toString());
    }

    public String getHeapFreeSize() {
        // Get amount of free memory within the heap in bytes. This size will increase
        // after garbage collection and decrease as new objects are created.
        Long heapFreeSize = Runtime.getRuntime().freeMemory();
        Long heapFreeSizeMiB = heapFreeSize/1024/1024;
        return String.format("heapFreeSize[MiB]=%s heapFreeSize[B]=%s",heapFreeSizeMiB.toString(), heapFreeSize.toString());
    }


}
