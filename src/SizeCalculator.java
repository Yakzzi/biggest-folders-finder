public class SizeCalculator
{
    public static String getHumanReadableSize(long size)
    {
        int count = 0;
        double doubleSize = (double) size;
        while (doubleSize >= 1024) {
            doubleSize /= 1024.;
            count++;
        }

        String readableSize = String.valueOf(Math.round(doubleSize * 100)/100.);

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
