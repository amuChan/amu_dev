import java.util.*;
import java.io.*;
import java.net.*;

public class AmuTest {
    public static void main(String[] args) {
        try {
            AmuTest.Socket_thread_test();
        } catch (Exception e) {

        }
    }

    public static void test1() {
        Scanner scc = new Scanner(System.in);
        System.out.print("请输入文字：");
        String input = scc.nextLine();
        if (input.equals("amu")) {
            System.out.println("input: " + input);
        } else {
            System.out.println("not input");
        }
    }

    public static void test2() {
        int[] arr = {11, 22, 34, 45, 53};

        for (int i : arr) {
            System.out.println(i);
        }
    }

    public static void test3() {
        HashSet<String> quchong = new HashSet();
        String str = "a,b,c,d,d,d,s,a,s,s,x,x,x,y";
        String[] strArr = str.split(",");

//        ArrayList<String> strList = //str.split(",");

        HashSet<String> strSet = new HashSet();
        for (String tmp : strArr) {
            strSet.add(tmp);
        }

        for (String tmp : strSet) {
            System.out.println(tmp);
        }


    }

    public static void test_thread() {
        ThreadTest t1 = new ThreadTest("线程一");
        t1.start();

        ThreadTest t2 = new ThreadTest("线程二");
        t2.start();

        ThreadTest t3 = new ThreadTest("线程三");
        t3.start();

        ThreadTest t4 = new ThreadTest("线程四");
        t4.start();
    }

    public static void file_test() {
        File myfile = new File("/Users/khangechan/amu_git/amu_dev/mkdir_test");
//        myfile.mkdirs();
        boolean isExsit = myfile.isDirectory();
        System.out.println(isExsit);
    }

    public static void socket_listen_test() throws Exception {

        ServerSocket ss = new ServerSocket(10000);
        Socket sc = ss.accept();
        InputStream in = sc.getInputStream();

        byte[] read = new byte[120];
        int count = in.read(read);
//            char restr = (char) read;
        System.out.println(new String(read, 0, count));
        in.close();
        sc.close();
    }

    public static void socket_send_test() throws Exception {
        Socket sc = new Socket("127.0.0.1", 10000);
        OutputStream out = sc.getOutputStream();

        out.write("abcd".getBytes());

        sc.close();
        out.close();
    }

    public static void Socket_thread_test() throws Exception {
        SocketServerThreadTest s = new SocketServerThreadTest("s");
        s.start();
        SocketClientThreadTest c = new SocketClientThreadTest("c");
        c.start();
    }


}

class SocketServerThreadTest extends Thread {

    private String threadName;

    public SocketServerThreadTest(String name) {
        this.threadName = name;
        System.out.println("创建服务端进程：" + name);
    }

    @Override
    public void run() {
        try {
            ServerSocket ss = new ServerSocket(10000);
            String msg = "";
            Socket sc = ss.accept();

            while (true) {
                InputStream in = sc.getInputStream();
                byte[] read = new byte[120];
                int count = in.read(read);
                msg = new String(read, 0, count);
                System.out.println("【服务端】接收到消息：" + msg);
                in.close();

                OutputStream out = sc.getOutputStream();
                out.write("收到了".getBytes());
                out.close();

                if (msg.equals("拜拜")) {
                    break;
                }
            }
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("【服务端】拜拜，服务端结束监听");

    }

}

class SocketClientThreadTest extends Thread {

    private String threadName;

    public SocketClientThreadTest(String name) {
        this.threadName = name;
        System.out.println("创建客户端进程：" + name);
    }

    @Override
    public void run() {
        try {

            Scanner scnn = new Scanner(System.in);
            Socket sc = new Socket("127.0.0.1", 10000);
            String msg = "";

            while (true) {
                System.out.print("【客户端】输入发送消息：");
                msg = scnn.nextLine();

                OutputStream out = sc.getOutputStream();
                out.write(msg.getBytes());
                out.close();


                InputStream in = sc.getInputStream();
                byte[] read = new byte[120];
                int count = in.read(read);
                String receive = new String(read, 0, count);
                System.out.println("【客户端】收到：" + receive);
                in.close();

                if (msg.equals("拜拜")) {
                   break;
                }
            }
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("【客户端】拜拜，服务端结束监听");

    }

}

class ThreadTest extends Thread {

    private String threadName;

    public ThreadTest(String name) {
        this.threadName = name;
        System.out.println("创建进程：" + name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            System.out.println(threadName + "：睡眠 " + i);
            try {
                this.sleep(500);//进程睡眠1秒
            } catch (InterruptedException e) {
                System.out.println("异常");
            }
        }

    }

}




