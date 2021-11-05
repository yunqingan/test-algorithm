Qemu调试

1.环境搭建

> sudo apt-get install qemu

2.编译配置

x86_64内核编译配置

```bash
1.开启 debug 相关的选项：
Kernel hacking  --->
  Compile-time checks and compiler options  --->
    [*] Compile the kernel with debug info
    [*]   Provide GDB scripts for kernel debugging
2.关闭内核地址空间布局随机：  
Processor type and features  --->
  [ ]   Randomize the address of the kernel image (KASLR)

    
```

```bash
make ARCH=x86_64 x86_64_defconfig
$ make ARCH=x86_64 menuconfig # 打开 `Kernel hacking -> Compile-time checks and compiler options -> Compile the kernel with debug info -> Provide GDB scripts for kernel debugging`
make -j$(nproc)
```









3.启动qemu

qemu-system-x86_64   -kernel linux-5.14.15/arch/x86_64/boot/bzImage  -append "root=/dev/sda init=/init nokaslr" -initrd  linux-5.14.15/initrd.img-5.11.0-38-generic -s -S -m 2048



1.-m表示非配内存大小为128Mb, -S表示以调试模式启动，暂定在启动界面 

2.-S表示“冷冻”虚拟机，等待调试器发出继续运行命令； 
3.-kernel表示要调试的内核镜像 

## 4.**nokaslr**

5.-s 启动gdbserver tcp:1234 

-M vexpress-a9 	指定要仿真的开发板：vexpress-a9
-m 512M 	     指定DRAM内存大小为512MB
-cpu cortex-a9 	指定CPU架构
-smp n 	CPU的个数，不设置的话，默认是1
-kernel ./zImage 	要运行的镜像
-dtb ./vexpress-vap-ca9.dtb 	要加载的设备树文件
-append cmdline 	设置Linux内核命令行、启动参数
-initrd file 	使用file文件作为初始化ram disk
-nographic 	非图形化启动，使用串口作为控制台
-sd rootfs.ext3 	使用rootfs.ext3作为SD卡镜像文件
-net nic 	创建一个网卡
-net nic -net tap 	将开发板网卡和主机网卡建立桥接(Bridge)

-mtdblock file 	使用file作为片上Flash镜像文件
-cdrom file 	使用file作为CDROM镜像文件
-display vnc= display 	设置显示后端类型
-vnc display 	-display vnc=的简写形式
-display none 	默认：-vnc localhost:0,to=99,id=default
-boot a c d n 	a从floppy启动，c从光盘，d从硬盘，n从网络启动



启动gdb

另外开一个终端到linux-kernel主目录下，输入命令：`gdb vmlinux` 
 在gdb下输入：`target remote localhost:1234` 连接qemu中开启的gdbserver开始调试



eclipse 调试配置

debug -> Debug Configurations -> C/C++ Remote Application -> Select other ... -> Use configuration specific settings -> GDB(DSF)Manual Remote Debugging Launcher -> C/C++ application -> Debugger -> port...