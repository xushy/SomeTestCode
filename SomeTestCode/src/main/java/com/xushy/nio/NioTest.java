package com.xushy.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * @author Administrator
 * @date 2019年11月29日
 * @description ZeroCopy 零拷贝 <br>
 *              原始的 读取磁盘文件通过socket发送,需要4次拷贝,两次内核态用户态的状态切换<br>
 *              1.磁盘拷贝到 内核态的read buffer (不需要CPU参与,由DMA完成)<br>
 *              2.read buffer 拷贝到 用户态的Application buffer<br>
 *              3.Application buffer 拷贝到内核态的socket buffer<br>
 *              4.socket buffer拷贝到NIC buffer <br>
 * 
 *              (1)linux sendfile() <br>
 *              调用sendfile时,DMA将磁盘数据拷贝到内核态的readbuffer<br>
 *              cpu参与 readbuffer 将数据拷贝到socketbuffer<br>
 *              DMA将 socket buffer拷贝到NIC buffer<br>
 *              以上经过三次拷贝,一次CPU拷贝,两次DMA拷贝<br>
 * 
 *              (2)Sendfile With DMA Scatter/Gather Copy<br>
 *              IO请求批量化 DMA scatter/gather：需要DMA控制器支持的。 <br>
 *              DMA工作流程：cpu发送IO请求给DMA，<br>
 *              DMA然后读取数据。<br>
 *              IO请求：相当于可以看作包含一个物理地址。<br>
 *              从一系列物理地址(10)读数据:普通的DMA (10请求) <br>
 *              dma scatter/gather:一次给10个物理地址， 一个请求就可以（批量处理）。<br>
 *              Scatter/Gather会经历2次拷贝: 0次cpu copy，2次DMA copy<br>
 *              (3) splice函数<br>
 *              零拷贝的具体实现最终取决于操作系统
 */
public class NioTest {
    @SuppressWarnings("resource")
    public static void main(String[] args) throws IOException {
        FileChannel fromChannel =
            new FileInputStream("F:\\xushuai\\LeetCode\\SomeTestCode\\SomeTestCode\\1.txt").getChannel();
        FileChannel toChannel =
            new FileOutputStream("F:\\xushuai\\LeetCode\\SomeTestCode\\SomeTestCode\\2.txt").getChannel();

        fromChannel.transferTo(0, fromChannel.size(), toChannel);

        fromChannel.close();
        toChannel.close();
        // fileChannel.transferTo(position, count, target)
    }
}
