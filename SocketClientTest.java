package com.company.Nio.Blocking;


import javax.net.ssl.StandardConstants;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import static java.lang.Thread.sleep;

/***
 * 一,使用NIO完成网络通信的三个核心
 *
 * 1.通道(Channel): 负责连接
 *    java.nio.channels.Channel接口
 *          |--SelectableChannel
 *              |--SocketChannel
 *              |--ServerSocketChannel
 *              |--DatagramChannel
 *
 *              |--Pipe.SinkChannel
 *              |--Pipe.SourceChannel
 *
 * 2.缓冲区(Buffer): 负责数据存取
 *
 * 3.选择器(Selector):是 SelectablerChannel 的多路复用器,用于监控 IO 的状况
 */
public class SocketClientTest {




    public static void main(String[] args) throws Exception {
        /**阻塞nio-网络*/
            SocketChannel schannel = null;
            FileChannel inchannel = null;
            try {
                //1.获取通道
                schannel= SocketChannel.open(new InetSocketAddress("127.0.0.1",9898));
                inchannel= FileChannel.open(Paths.get("src/Resource/Client/1.png"), StandardOpenOption.READ);
                //2.分配缓冲区
                ByteBuffer buff = ByteBuffer.allocate(1024);
                //3.读取本地文件并发送到服务端
                while (inchannel.read(buff)!=-1){
                    buff.flip();//切换到读取模式
                    schannel.write(buff);
                    buff.clear();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                //4.关闭通道
                try {
                    inchannel.close();
                    schannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("客户端启动!");
            }
        }


}
