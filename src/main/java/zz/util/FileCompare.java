package zz.util;

import java.io.*;

public class FileCompare {
    public static void main(String[] args) {

//        compare("D:\\onlineData\\test\\a.txt", "D:\\onlineData\\test\\b.txt", "D:\\onlineData\\test\\cba.txt");
        //temp 保存相同的instanceId
//        compare("D:\\onlineData\\test\\UCO.txt", "D:\\onlineData\\test\\UCA.txt", "D:\\onlineData\\test\\same.txt");
        compare("D:\\onlineData\\TJ\\UCA.txt", "D:\\onlineData\\TJ\\UCO.txt", "D:\\onlineData\\TJ\\same.txt");

    }

    public static void compare(String UCOPath, String UCAPath, String outputPath) {
        File writeName = new File(outputPath);
        FileWriter writer = null;
        try {
            writeName.createNewFile();
            writer = new FileWriter(writeName);

        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter out = new BufferedWriter(writer);
        if (getLineNumber(UCOPath) >= getLineNumber(UCAPath)) {
            try {
                String lineUCO;
                String lineUCA;
                FileReader UCOReader = new FileReader(UCOPath);
                BufferedReader UCOBr = new BufferedReader(UCOReader);
                for (int i = 0; i < getLineNumber(UCOPath); i++) {
                    lineUCO = UCOBr.readLine();
                    if (lineUCO != null) {
                        System.out.println("比对中：" + i);
                        //一条 UCO 的数据循环比对所有 UCA 的数据 因此需要重新 new File 对象
                        FileReader UCAReader = new FileReader(UCAPath);
                        BufferedReader UCABr = new BufferedReader(UCAReader);
                        for (int j = 0; j < getLineNumber(UCAPath); j++) {
                            lineUCA = UCABr.readLine();
                            if (lineUCA != null) {
                                if (lineUCO.equals(lineUCA)) {
                                    if (j == getLineNumber(UCAPath) - 1) {
                                        out.write(lineUCA);
                                        out.flush();
                                    } else {
                                        out.write(lineUCA + "\r\n");
                                        out.flush();
                                    }
                                }
                            } else {
                                System.out.println("有空数据问题！");
                            }
                        }
                    } else {
                        System.out.println("有空数据问题！");
                    }
                }
                System.out.println("相同的instanceId：" + getLineNumber(outputPath));
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("请切换前两个参数位置");
        }
    }


    public static long getLineNumber(String pathName) {
        File file = new File(pathName);
        if (file.exists()) {
            try {
                FileReader fileReader = new FileReader(file);
                LineNumberReader lineNumberReader = new LineNumberReader(fileReader);
                lineNumberReader.skip(Long.MAX_VALUE);
                long lines = lineNumberReader.getLineNumber() + 1;
                fileReader.close();
                lineNumberReader.close();
                return lines;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    /**
     * 读入TXT文件
     */
    public static void readFile() {
        String pathname = "D:\\onlineData\\UCA.txt"; // 绝对路径或相对路径都可以，写入文件时演示相对路径,读取以上路径的input.txt文件
        //防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw;
        //不关闭文件会导致资源的泄露，读写文件都同理
        //Java7的try-with-resources可以优雅关闭文件，异常时自动关闭文件；详细解读https://stackoverflow.com/a/12665271
        try (
                FileReader reader = new FileReader(pathname);
                BufferedReader br = new BufferedReader(reader) // 建立一个对象，它把文件内容转成计算机能读懂的语言
        ) {
            String line;
            int i = 0;
            //网友推荐更加简洁的写法
            while ((line = br.readLine()) != null) {
                // 一次读入一行数据
                i += 1;
                System.out.println(line + "---总第" + i + "行");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 写入TXT文件
     */
    public static void writeFile() {
        try {
            File writeName = new File("output.txt"); // 相对路径，如果没有则要建立一个新的output.txt文件
            writeName.createNewFile(); // 创建新文件,有同名的文件的话直接覆盖
            try (FileWriter writer = new FileWriter(writeName);
                 BufferedWriter out = new BufferedWriter(writer)
            ) {
                out.write("我会写入文件啦1\r\n"); // \r\n即为换行
                out.write("我会写入文件啦2\r\n"); // \r\n即为换行
                out.flush(); // 把缓存区内容压入文件
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
