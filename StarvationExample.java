public class StarvationExample {
    public static void main(String[] args) {
        Resource resource = new Resource();

        for (int i = 0; i < 3; i++) {
            Thread highPriorityThread = new Thread(() -> {
                while (true) {
                    resource.accessResource("High Priority");
                }
            });
            highPriorityThread.setPriority(Thread.MAX_PRIORITY);
            highPriorityThread.start();
        }

        Thread lowPriorityThread = new Thread(() -> {
            while (true) {
                resource.accessResource("Low Priority");
            }
        });
        lowPriorityThread.setPriority(Thread.MIN_PRIORITY);
        lowPriorityThread.start();
    }
}

class Resource {
    public synchronized void accessResource(String threadType) {
        System.out.println(threadType + " thread is accessing the resource");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}