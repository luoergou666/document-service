Word文档转PDF

OpenOffice安装流程

事前准备

    OpenOffice可以在多种平台上运行，同时需要JRE运行环境。本文在CentOS上安装了JDK1.8以及Xwindow
    
    [root@liumiaocn ~]# yum groupinstall “X Window System”
    OS	CentOS7
    JDK	1.8:https://github.com/liumiaocn/easypack/blob/master/maven/easypack_jdk
    X Window	yum groupinstall “X Window System”
    [root@liumiaocn ~]# uname -a
    Linux liumiaocn 3.10.0-514.el7.x86_64 #1 SMP Tue Nov 22 16:42:41 UTC 2016 x86_64 x86_64 x86_64 GNU/Linux
    [root@liumiaocn ~]# java -version
    java version "1.8.0_131"
    Java(TM) SE Runtime Environment (build 1.8.0_131-b11)
    Java HotSpot(TM) 64-Bit Server VM (build 25.131-b11, mixed mode)
    [root@liumiaocn ~]#
    

下载

    下载中文版的64位linux上运行安装的Apache OpenOffice的RPM安装包，也可以根据需要自行选择。
    
    项目	详细
    下载地址	http://www.openoffice.org/zh-cn/download/
    当前最新版本	4.1.3
    下载文件名称	Apache_OpenOffice_4.1.3_Linux_x86-64_install-rpm_zh-CN.tar.gz
    安装
    下载&解压
    [root@liumiaocn ~]# cd /tmp/openoffice/
    [root@liumiaocn openoffice]# ls
    Apache_OpenOffice_4.1.3_Linux_x86-64_install-rpm_zh-CN.tar.gz
    [root@liumiaocn openoffice]# tar xvpf Apache_OpenOffice_4.1.3_Linux_x86-64_install-rpm_zh-CN.tar.gz 
    zh-CN/
    ...
    zh-CN/RPMS/openoffice-graphicfilter-4.1.3-9783.x86_64.rpm
    ...
    zh-CN/readmes/README_zh-CN.html
    [root@liumiaocn openoffice]# ls
    Apache_OpenOffice_4.1.3_Linux_x86-64_install-rpm_zh-CN.tar.gz  zh-CN
    [root@liumiaocn openoffice]#

安装

    [root@liumiaocn openoffice]# cd zh-CN/RPMS/
    [root@liumiaocn RPMS]# rpm -ivh *.rpm
    Preparing...                          ################################# [100%]
    Updating / installing...
       1:openoffice-ure-4.1.3-9783        ################################# [  2%]
       2:openoffice-core01-4.1.3-9783     ################################# [  5%]
       3:openoffice-zh-CN-4.1.3-9783      ################################# [  7%]
       4:openoffice-impress-4.1.3-9783    ################################# [ 10%]
       5:openoffice-zh-CN-base-4.1.3-9783 ################################# [ 12%]
       6:openoffice-zh-CN-calc-4.1.3-9783 ################################# [ 15%]
       7:openoffice-zh-CN-draw-4.1.3-9783 ################################# [ 17%]
       8:openoffice-zh-CN-help-4.1.3-9783 ################################# [ 20%]
       9:openoffice-zh-CN-impress-4.1.3-97################################# [ 22%]
      10:openoffice-zh-CN-math-4.1.3-9783 ################################# [ 24%]
      11:openoffice-zh-CN-res-4.1.3-9783  ################################# [ 27%]
      12:openoffice-zh-CN-writer-4.1.3-978################################# [ 29%]
      13:openoffice-base-4.1.3-9783       ################################# [ 32%]
      14:openoffice-calc-4.1.3-9783       ################################# [ 34%]
      15:openoffice-core02-4.1.3-9783     ################################# [ 37%]
      16:openoffice-core03-4.1.3-9783     ################################# [ 39%]
      17:openoffice-core04-4.1.3-9783     ################################# [ 41%]
      18:openoffice-core05-4.1.3-9783     ################################# [ 44%]
      19:openoffice-core06-4.1.3-9783     ################################# [ 46%]
      20:openoffice-core07-4.1.3-9783     ################################# [ 49%]
      21:openoffice-draw-4.1.3-9783       ################################# [ 51%]
      22:openoffice-images-4.1.3-9783     ################################# [ 54%]
      23:openoffice-4.1.3-9783            ################################# [ 56%]
      24:openoffice-math-4.1.3-9783       ################################# [ 59%]
      25:openoffice-writer-4.1.3-9783     ################################# [ 61%]
      26:openoffice-brand-writer-4.1.3-978################################# [ 63%]
      27:openoffice-brand-math-4.1.3-9783 ################################# [ 66%]
      28:openoffice-brand-base-4.1.3-9783 ################################# [ 68%]
      29:openoffice-brand-calc-4.1.3-9783 ################################# [ 71%]
      30:openoffice-brand-draw-4.1.3-9783 ################################# [ 73%]
      31:openoffice-brand-impress-4.1.3-97################################# [ 76%]
      32:openoffice-brand-zh-CN-4.1.3-9783################################# [ 78%]
      33:openoffice-ogltrans-4.1.3-9783   ################################# [ 80%]
      34:openoffice-gnome-integration-4.1.################################# [ 83%]
      35:openoffice-graphicfilter-4.1.3-97################################# [ 85%]
      36:openoffice-javafilter-4.1.3-9783 ################################# [ 88%]
      37:openoffice-onlineupdate-4.1.3-978################################# [ 90%]
      38:openoffice-ooofonts-4.1.3-9783   ################################# [ 93%]
      39:openoffice-ooolinguistic-4.1.3-97################################# [ 95%]
      40:openoffice-pyuno-4.1.3-9783      ################################# [ 98%]
      41:openoffice-xsltfilter-4.1.3-9783 ################################# [100%]
    [root@liumiaocn RPMS]#
