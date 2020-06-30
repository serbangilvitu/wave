package wave;

import java.util.ArrayList;
import java.util.List;

public class Memory {
    private final long size;
    private List<String> objects = new ArrayList<>();
    private static List<String> list1mb;

    public Memory(long size) {
        this.size = size;
        if (list1mb == null) {
            list1mb = new ArrayList<>();
            for (int i = 0; i < 1024*1024; i++) list1mb.add(("1"));
        }
        objects = new ArrayList<>();
        for (int i = 0; i < size; i++) objects.addAll(list1mb);
    }

    public long getsize() {
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
