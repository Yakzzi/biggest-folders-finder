import java.io.File;
import java.util.concurrent.ForkJoinPool;

public class Main
{
    public static void main(String[] args)
    {
        String folderPath = "E:/Games";
        File file = new File(folderPath);

        long start = System.currentTimeMillis();

        FolderSizeCalculator calculator = new FolderSizeCalculator(file);
        ForkJoinPool pool = new ForkJoinPool();
        long size = pool.invoke(calculator);
        System.out.println(getHumanReadableSize(size));
        System.out.println(getSizeFromHumanReadable(getHumanReadableSize(size)));

        long duration = System.currentTimeMillis() - start;
        System.out.println(duration/1000 + "seconds");

    }

//    public static long getFolderSize(File folder)
//    {
//        if (folder.isFile()) {
//            return folder.length();
//        }
//
//        long sum = 0;
//        File[] files = folder.listFiles();
//        for (File file : files)
//        {
//            sum += getFolderSize(file);
//        }
//        return sum;
//    }

    public static String getHumanReadableSize(long size)
    {
        int count = 0;
        while (size >= 1024) {
            size /= 1024;
            count++;
        }

        String readableSize = String.valueOf(size);

        return switch (count) {
            case 1 -> readableSize + "K";
            case 2 -> readableSize + "M";
            case 3 -> readableSize + "G";
            case 4 -> readableSize + "T";
            default -> readableSize + "b";
        };
    }

    public static long getSizeFromHumanReadable(String size)
    {
        long sizeFromHumanReadable = Long.parseLong(size.substring(0, size.length() - 1));
        char c = size.charAt(size.length() - 1);
        return switch (c) {
            case 'K' -> sizeFromHumanReadable * 1024;
            case 'M' -> sizeFromHumanReadable * 1024 * 1024;
            case 'G' -> sizeFromHumanReadable * 1024 * 1024 * 1024;
            case 'T' -> sizeFromHumanReadable * 1024 * 1024 * 1024 * 1024;
            default -> sizeFromHumanReadable;
        };
    }
}
