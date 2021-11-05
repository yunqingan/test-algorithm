在linux开发程序，没有集成开发环境IDE(integrated development environment),没有VC++6.0，只有Makefile和冰冷黑漆漆的shell窗口，寒冷的夜，考验着每一个工程师疲惫的心。

Makefile语法复杂、难以维护。对于一个小项目还好，对于大型的项目和开源项目，现在流行使用一些工具自动生成Makefile，可以大大减轻软件开发人员的负担。

比较常见的工具有GNU Autotools、CMake、QMake、SCons、Ant等。今天讲讲GNU Autotools，这个工具在开源软件和大型项目中被广泛地使用。

Autotools主要有Autoconf、automake、libtool等软件包工具组成，我们可以称为Autotools三剑客。

使用这几个工具，虽然能帮助我们自动生成Makefile，但是命令过程复杂，中间会生成大量的各种配置文件和脚本。很多人往往会觉得很麻烦、搞不懂里面错综复杂的关系，今天以这些工具的发展过程，给大家理理里面的关系。

## 一、手工makefile时代

早期我们在Unix、linux环境下开发软件，makefile都是手写的。通过makefile，我们就可以使用make命令直接去编译我们的源代码。

![img](https://pic2.zhimg.com/80/v2-77f15062db8e790c14037fcc748004b1_720w.jpg)

后来，随着Unix版本越来越多，各分支差异越来越大，我们写的makefile，有时候在其它Unix平台上可能就编译失败，比如库的问题等。不同的操作系统版本，库和头文件的路径可能不一样。怎么办？



后来我们通过手写一个配置脚本configure来解决这个问题：针对你当前的Unix平台，通过脚本对makefile进行配置，就可以在当前平台上愉快地编译了。

![img](https://pic2.zhimg.com/80/v2-c788ca9fbf973b525c4ca56cc24452ed_720w.jpg)



## 二、Autoconf时代

后来linux操作系统问世，后续的版本也越来越多，各种发布版本错综复杂，比如Redhat系列、debian系列等。差异越来越大，甚至包括操作系统的接口都出现差异。这时候别说makefile能不能正确编译的问题了，就连我们编写的应用程序，即使编译正确，也有可能在其它的平台上运行不起来。这个问题，大家都知道，后来出现了POSIX  API标准，就是可移植的操作系统接口。就是无论你是发布什么版本的Unix、Linux，OS的接口要遵循这个标准，这样我们编写的应用程序才能在linux、Unix等系列版本的操作系统上畅通无阻地运行。

而对于makefile来说，为了适配操作系统的更多版本，只能不断地添加代码，这就导致configure脚本越来远大，导致后来开发人员再也受不了了，维护成本越来越高。

1991年，David  Mackenzie开发了Autoconf工具，用来自动生成configure脚本。因为对于大多数用户来说，对于不同版本的差异、库的版本、底层的细节，鬼才懒得管。他们关心的就是库文件、头文件的位置、软件最终的安装路径是在哪里。所以这个工具的出现，大大解放了开发人员的时间，给广大程序员带来了福音。

用户只需要定义几个宏，表示我们关心的配置选项，保存在configure.ac文件里，然后使用Autoconf工具就可以帮我们自动生成configure脚本了！

![img](https://pic1.zhimg.com/80/v2-8b42177b41384b6e03baf2b02e6c03a8_720w.jpg)

Autoconf工具真是比大姨妈还贴心，为了减轻程序员的负担，configure.ac文件也不用我们手写了。Autoconf工具包里有个autoscan工具，可以自动扫描我们的项目，生成一个configure.scan文件，里面自动添加了很多宏，省得我们再手工添加。

我们只需要将这个configure.scan文件重命名为configure.ac文件，再修修补补，就可以了。

![img](https://pic4.zhimg.com/80/v2-079dfb6e1a57257c19852ee483f1820b_720w.jpg)

Autoconf工具大大减轻了程序员的负担，妈妈，再也不用担心晚回家吃饭了

## 三、automake时代

然而，随着项目越来越大，makefile也越来越复杂，尤其是大型项目，手写越来越困难，怎么办？automake工具这个时候闪亮登场了！对于开发人员来说，我们关心的就是这个项目要生成什么可执行文件，需要编译哪些源文件，至于怎么编译的？底层的链接细节，鬼才懒得管。程序员的加班已经够多，内心已经够疲惫，我们拒绝makefile的肆虐和压迫！

使用automake工具，我们只需要手工编写一个[http://makefile.am](https://link.zhihu.com/?target=http%3A//makefile.am)文件，在里面定义我们想要生成的目标、需要编译的源文件，然后使用automake工具就可以帮我们自动生成makefile！但是此时的makefile，是通用的makefile，定义了程序编译、链接的通用规则，保存在[http://makefile.in](https://link.zhihu.com/?target=http%3A//makefile.in)里面。

如果想在特定平台上成功编译，还需要我们前面的脚本configure配置一下，最终才生成我们项目的Makefile。

![img](https://pic1.zhimg.com/80/v2-2e822423c8f726155f1b9d570f165e30_720w.jpg)



Autoconf工具通过定义一系列的宏，提供给我们使用，让我们去设置一些需要的配置选项、去配置我们的makefile。

后来Automake工具出现后，自定义了一些宏，对这些宏进行了扩展。比如，Autoconf以前都是单独使用的，现在要跟automake配合使用，要在configure.ac文件里添加一个AM_INIT_AUTOMAKE这个宏。

这个宏是在automake工具包里定义的，当我们运行autoconf命令的时候，就是出错，因为找不到这个宏的定义。怎么办，咋办？

后来在configure.ac同目录下，定义一个aclocal.m4格式的文件，里面存放用户定义的一些宏、或者automake的一些宏，这样，autoconf运行的时候，就可以在这个文件里找到宏定义了。

偷懒，是人类社会进步的最大动力。后来，为了进一步减少工作量，又出现一个aclocal工具，会自动将automake、autoconf以及用户定义的所有宏统统放在aclocal.m4文件里。

![img](https://pic2.zhimg.com/80/v2-78c29e0a64daff325bd9e0689be89501_720w.jpg)



为什么要保存在aclocal.m4这种格式的文件里？我也不知道....，m4，macro宏后面4个字母，缩写就是m4.

文本文件嘛，后缀名可以随便定义，比如我姓王，后缀名定义为.wang也是可以的。不要纠结于这些细节，我们的征途是构建makefile。

autoconf工具包里还有一个autoheader工具，用来将configure.ac里面的宏配置转换为我们C语言格式的#define形式，并保存在[http://config.h.in](https://link.zhihu.com/?target=http%3A//config.h.in)文件里，当我们运行./configure生成makefile的时候，顺便也会将[http://config.h.in](https://link.zhihu.com/?target=http%3A//config.h.in)转换为config.h文件，这样在我们的程序里，如果想使用这些宏，就可以直接#include “config.h”就可以了。

![img](https://pic4.zhimg.com/80/v2-64c0e50eb3368712419bc885f88454bb_720w.jpg)

比如头文件里面定义的软件版本号VERSION宏，就可以在程序里直接使用，打印在程序里打印我们的软件版本号。

## 四、libtool时代

随着Unix、Linux之间的差异越来越大，对动态共享库的管理差异也越来越大，比如有些共享库，使用.so格式，有的是.a，有的是.o的形式。运行时对动态库的管理方式也一样，有的操作系统支持动态加载，有的就不支持。这就对我们Makefile带来了挑战。怎么办？libtool的工具出现就是为了解决这个问题的，它通过对生成的动态库进行抽象，统一生成.la的形式，可以支持十几种各种不同的平台。

![img](https://pic4.zhimg.com/80/v2-64c0e50eb3368712419bc885f88454bb_720w.jpg)



