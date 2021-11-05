Java启动参数分类

## **一、JVM标准参数(-)**

获取方法: java -help

## **二、JVM非标准参数(-X)**

获取方法: java -X

## **三、JVM非Stable参数（-XX）**

获取方法:  java -XX:+PrintFlagsInitial   

JDK8的-XX参数整理

JDK8 获取所有-XX参数列表

1.8所有-XX参数列表

 

 

 

 


Java启动参数分类

类别1: 其一是标准参数（-），所有的JVM实现都必须实现这些参数的功能，而且向后兼容；

类别2: 其二是非标准参数（-X），默认jvm实现这些参数的功能，但是并不保证所有jvm实现都满足，且不保证向后兼容；

类别3: 其三是非Stable参数（-XX），此类参数各个jvm实现会有所不同，将来可能会随时取消，需要慎重使用；

一、JVM标准参数(-)
获取方法: java -help

JVM的标准参数都是以”-“开头，通过输入”java -help”或者”java -?”，可以查看JVM标准参数列表。如
这里写代码片

![img](https://imgconvert.csdnimg.cn/aHR0cDovL3N0YXRpYy50aWFuc2hvdXpoaS5jb20vdWVkaXRvci91cGxvYWQvaW1hZ2UvMjAxNjAyMjAvMTQ1NTk4MzcyOTMwMTAzMDYzNy5wbmc?x-oss-process=image/format,png)

以下是JVM标准参数的详细介绍(红色标记的参数请着重注意):
以下是JVM标准参数的详细介绍(红色标记的参数请着重注意):

-client

设置jvm使用client模式，特点是启动速度比较快，但运行时性能和内存管理效率不高，通常用于客户端应用程序或者PC应用开发和调试。

-server

设置jvm使server模式，特点是启动速度比较慢，但运行时性能和内存管理效率很高，适用于生产环境。在具有64位能力的jdk环境下将默认启用该模式，而忽略-client参数。

-agentlib:libname[=options]

用于装载本地lib包；

其中libname为本地代理库文件名，默认搜索路径为环境变量PATH中的路径，options为传给本地库启动时的参数，多个参数之间用逗号分隔。在Windows平台上jvm搜索本地库名为libname.dll的文件，在linux上jvm搜索本地库名为libname.so的文件，搜索路径环境变量在不同系统上有所不同，比如Solaries上就默认搜索LD_LIBRARY_PATH。

比如：-agentlib:hprof

用来获取jvm的运行情况，包括CPU、内存、线程等的运行数据，并可输出到指定文件中；windows中搜索路径为JRE_HOME/bin/hprof.dll。

-agentpath:pathname[=options]

按全路径装载本地库，不再搜索PATH中的路径；其他功能和agentlib相同；更多的信息待续，在后续的JVMTI部分会详述。

-classpath classpath

-cp classpath

告知jvm搜索目录名、jar文档名、zip文档名，之间用分号;分隔；使用-classpath后jvm将不再使用CLASSPATH中的类搜索路径，如果-classpath和CLASSPATH都没有设置，则jvm使用当前路径(.)作为类搜索路径。

jvm搜索类的方式和顺序为：Bootstrap，Extension，User。

Bootstrap中的路径是jvm自带的jar或zip文件，jvm首先搜索这些包文件，用System.getProperty(“sun.boot.class.path”)可得到搜索路径。

Extension是位于JRE_HOME/lib/ext目录下的jar文件，jvm在搜索完Bootstrap后就搜索该目录下的jar文件，用System.getProperty(“java.ext.dirs”)可得到搜索路径。

User搜索顺序为当前路径.、CLASSPATH、-classpath，jvm最后搜索这些目录，用System.getProperty(“java.class.path”)可得到搜索路径。

-Dproperty=value

设置系统属性名/值对，运行在此jvm之上的应用程序可用System.getProperty(“property”)得到value的值。

如果value中有空格，则需要用双引号将该值括起来，如-Dname=”space string”。

该参数通常用于设置系统级全局变量值，如配置文件路径，以便该属性在程序中任何地方都可访问。

-enableassertions[:”…” | : ]

-ea[:”…” | : ]

上述参数就用来设置jvm是否启动断言机制（从JDK 1.4开始支持），缺省时jvm关闭断言机制。

用-ea 可打开断言机制，不加和classname时运行所有包和类中的断言，如果希望只运行某些包或类中的断言，可将包名或类名加到-ea之后。例如要启动包com.wombat.fruitbat中的断言，可用命令java -ea:com.wombat.fruitbat…。

-disableassertions[:”…” | :

 

 

 

 


二、JVM非标准参数(-X)
获取方法: java -X

通过”java -X”可以输出非标准参数列表，如下所示：
这里写图片描述

![这里写图片描述](https://imgconvert.csdnimg.cn/aHR0cDovL3N0YXRpYy50aWFuc2hvdXpoaS5jb20vdWVkaXRvci91cGxvYWQvaW1hZ2UvMjAxNjAyMjEvMTQ1NTk4NDMwOTc4MzAyNjYyNC5wbmc?x-oss-process=image/format,png)

非标准参数又称为扩展参数，其列表如下：

-Xint

设置jvm以解释模式运行，所有的字节码将被直接执行，而不会编译成本地码。

-Xbatch

关闭后台代码编译，强制在前台编译，编译完成之后才能进行代码执行；

默认情况下，jvm在后台进行编译，若没有编译完成，则前台运行代码时以解释模式运行。

-Xbootclasspath:bootclasspath

让jvm从指定路径（可以是分号分隔的目录、jar、或者zip）中加载bootclass，用来替换jdk的rt.jar；若非必要，一般不会用到；

-Xbootclasspath/a:path

将指定路径的所有文件追加到默认bootstrap路径中；

-Xbootclasspath/p:path

让jvm优先于bootstrap默认路径加载指定路径的所有文件；

-Xcheck:jni

对JNI函数进行附加check；此时jvm将校验传递给JNI函数参数的合法性，在本地代码中遇到非法数据时，jmv将报一个致命错误而终止；使用该参数后将造成性能下降，请慎用。

-Xfuture

让jvm对类文件执行严格的格式检查（默认jvm不进行严格格式检查），以符合类文件格式规范，推荐开发人员使用该参数。

-Xnoclassgc

关闭针对class的gc功能；因为其阻止内存回收，所以可能会导致OutOfMemoryError错误，慎用；

-Xincgc

开启增量gc（默认为关闭）；这有助于减少长时间GC时应用程序出现的停顿；但由于可能和应用程序并发执行，所以会降低CPU对应用的处理能力。

-Xloggc:file

与-verbose:gc功能类似，只是将每次GC事件的相关情况记录到一个文件中，文件的位置最好在本地，以避免网络的潜在问题。

若与verbose命令同时出现在命令行中，则以-Xloggc为准。

-Xms

指定jvm堆的初始大小，默认为物理内存的1/64，最小为1M；可以指定单位，比如k、m，若不指定，则默认为字节。

-Xmx

指定jvm堆的最大值，默认为物理内存的1/4或者1G，最小为2M；单位与-Xms一致。

-Xss

设置单个线程栈的大小，一般默认为512k。

-Xprof

输出 cpu 配置文件数据

-Xrs

减少jvm对操作系统信号（signals）的使用，该参数从1.3.1开始有效；

从jdk1.3.0开始，jvm允许程序在关闭之前还可以执行一些代码（比如关闭数据库的连接池），即使jvm被突然终止；

jvm关闭工具通过监控控制台的相关事件而满足以上的功能；更确切的说，通知在关闭工具执行之前，先注册控制台的控制handler，然后对CTRL_C_EVENT, CTRL_CLOSE_EVENT, CTRL_LOGOFF_EVENT, and CTRL_SHUTDOWN_EVENT这几类事件直接返回true。

但如果jvm以服务的形式在后台运行（比如servlet引擎），他能接收CTRL_LOGOFF_EVENT事件，但此时并不需要初始化关闭程序；为了避免类似冲突的再次出现，从jdk1.3.1开始提供-Xrs参数；当此参数被设置之后，jvm将不接收控制台的控制handler，也就是说他不监控和处理CTRL_C_EVENT, CTRL_CLOSE_EVENT, CTRL_LOGOFF_EVENT, or CTRL_SHUTDOWN_EVENT事件。

上面这些参数中，比如-Xmsn、-Xmxn……都是我们性能优化中很重要的参数；

-Xprof、-Xloggc:file等都是在没有专业跟踪工具情况下排错的好手；

 

 

 

 


三、JVM非Stable参数（-XX）
获取方法:  java -XX:+PrintFlagsInitial   

 

Java 6（update 21oder 21之后）版本， HotSpot JVM 提供给了两个新的参数，在JVM启动后，在命令行中可以输出所有XX参数和值。

-XX:+PrintFlagsFinal and -XX:+PrintFlagsInitial

读者可以使用以下语句输出所有的参数和默认值

java -XX:+PrintFlagsInitial >>1.txt   或者  java -XX:+PrintFlagsInitial>>1.txt

由于非State参数非常的多，因此这里就不列出所有参数进行讲解。只介绍我们比较常用的。

Java HotSpot VM中-XX:的可配置参数列表进行描述；

这些参数可以被松散的聚合成三类：

行为参数（Behavioral Options）：用于改变jvm的一些基础行为；

性能调优（Performance Tuning）：用于jvm的性能调优；

调试参数（Debugging Options）：一般用于打开跟踪、打印、输出等jvm参数，用于显示jvm更加详细的信息；

    行为参数(功能开关)
     
    -XX:-DisableExplicitGC  禁止调用System.gc()；但jvm的gc仍然有效
     
    -XX:+MaxFDLimit 最大化文件描述符的数量限制
     
    -XX:+ScavengeBeforeFullGC   新生代GC优先于Full GC执行
     
    -XX:+UseGCOverheadLimit 在抛出OOM之前限制jvm耗费在GC上的时间比例
     
    -XX:-UseConcMarkSweepGC 对老生代采用并发标记交换算法进行GC
     
    -XX:-UseParallelGC  启用并行GC
     
    -XX:-UseParallelOldGC   对Full GC启用并行，当-XX:-UseParallelGC启用时该项自动启用
     
    -XX:-UseSerialGC    启用串行GC
     
    -XX:+UseThreadPriorities    启用本地线程优先级
     
    性能调优
     
    -XX:LargePageSizeInBytes=4m 设置用于Java堆的大页面尺寸
     
    -XX:MaxHeapFreeRatio=70 GC后java堆中空闲量占的最大比例
     
    -XX:MaxNewSize=size 新生成对象能占用内存的最大值
     
    -XX:MaxPermSize=64m 老生代对象能占用内存的最大值
     
    -XX:MinHeapFreeRatio=40 GC后java堆中空闲量占的最小比例
     
    -XX:NewRatio=2  新生代内存容量与老生代内存容量的比例
     
    -XX:NewSize=2.125m  新生代对象生成时占用内存的默认值
     
    -XX:ReservedCodeCacheSize=32m   保留代码占用的内存容量
     
    -XX:ThreadStackSize=512 设置线程栈大小，若为0则使用系统默认值
     
    -XX:+UseLargePages  使用大页面内存
     
    调试参数
     
    -XX:-CITime 打印消耗在JIT编译的时间
     
    -XX:ErrorFile=./hs_err_pid<pid>.log 保存错误日志或者数据到文件中
     
    -XX:-ExtendedDTraceProbes   开启solaris特有的dtrace探针
     
    -XX:HeapDumpPath=./java_pid<pid>.hprof  指定导出堆信息时的路径或文件名
     
    -XX:-HeapDumpOnOutOfMemoryError 当首次遭遇OOM时导出此时堆中相关信息
     
    -XX:OnError="<cmd args>;<cmd args>" 出现致命ERROR之后运行自定义命令
     
    -XX:OnOutOfMemoryError="<cmd args>;<cmd args>"  当首次遭遇OOM时执行自定义命令
     
    -XX:-PrintClassHistogram    遇到Ctrl-Break后打印类实例的柱状信息，与jmap -histo功能相同
     
    -XX:-PrintConcurrentLocks   遇到Ctrl-Break后打印并发锁的相关信息，与jstack -l功能相同
     
    -XX:-PrintCommandLineFlags  打印在命令行中出现过的标记
     
    -XX:-PrintCompilation   当一个方法被编译时打印相关信息
     
    -XX:-PrintGC    每次GC时打印相关信息
     
    -XX:-PrintGC Details    每次GC时打印详细信息
     
    -XX:-PrintGCTimeStamps  打印每次GC的时间戳
     
    -XX:-TraceClassLoading  跟踪类的加载信息
     
    -XX:-TraceClassLoadingPreorder  跟踪被引用到的所有类的加载信息
     
    -XX:-TraceClassResolution   跟踪常量池
     
    -XX:-TraceClassUnloading    跟踪类的卸载信息
     
    -XX:-TraceLoaderConstraints 跟踪类加载器约束的相关信息
JDK8的-XX参数整理


JDK8 获取所有-XX参数列表

java -XX:+PrintFlagsInitial >>1.txt



1.8所有-XX参数列表
数据类型	参数名	默认值	不清楚干嘛的	自己的总结
intx	ActiveProcessorCount	-1	{product}	 
uintx	AdaptiveSizeDecrementScaleFactor	4	{product}	 
uintx	AdaptiveSizeMajorGCDecayTimeScale	10	{product}	 
uintx	AdaptiveSizePausePolicy	0	{product}	 
uintx	AdaptiveSizePolicyCollectionCostMargin	50	{product}	 
uintx	AdaptiveSizePolicyInitializingSteps	20	{product}	 
uintx	AdaptiveSizePolicyOutputInterval	0	{product}	 
uintx	AdaptiveSizePolicyWeight	10	{product}	 
uintx	AdaptiveSizeThroughPutPolicy	0	{product}	 
uintx	AdaptiveTimeWeight	25	{product}	 
bool	AdjustConcurrency	false	{product}	 
bool	AggressiveHeap	false	{product}	 
bool	AggressiveOpts	false	{product}	 
intx	AliasLevel	3	{C2	product}
bool	AlignVector	true	{C2	product}
intx	AllocateInstancePrefetchLines	1	{product}	 
intx	AllocatePrefetchDistance	-1	{product}	 
intx	AllocatePrefetchInstr	0	{product}	 
intx	AllocatePrefetchLines	3	{product}	 
intx	AllocatePrefetchStepSize	16	{product}	 
intx	AllocatePrefetchStyle	1	{product}	 
bool	AllowJNIEnvProxy	false	{product}	 
bool	AllowNonVirtualCalls	false	{product}	 
bool	AllowParallelDefineClass	false	{product}	 
bool	AllowUserSignalHandlers	false	{product}	 
bool	AlwaysActAsServerClassMachine	false	{product}	 
bool	AlwaysCompileLoopMethods	false	{product}	 
bool	AlwaysLockClassLoader	false	{product}	 
bool	AlwaysPreTouch	false	{product}	 
bool	AlwaysRestoreFPU	false	{product}	 
bool	AlwaysTenure	false	{product}	 
bool	AssertOnSuspendWaitFailure	false	{product}	 
bool	AssumeMP	false	{product}	 
intx	AutoBoxCacheMax	128	{C2	product}
uintx	AutoGCSelectPauseMillis	5000	{product}	 
intx	BCEATraceLevel	0	{product}	 
intx	BackEdgeThreshold	100000	{pd	product}
bool	BackgroundCompilation	true	{pd	product}
uintx	BaseFootPrintEstimate	268435456	{product}	 
intx	BiasedLockingBulkRebiasThreshold	20	{product}	 
intx	BiasedLockingBulkRevokeThreshold	40	{product}	 
intx	BiasedLockingDecayTime	25000	{product}	 
intx	BiasedLockingStartupDelay	4000	{product}	 
bool	BindGCTaskThreadsToCPUs	false	{product}	 
bool	BlockLayoutByFrequency	true	{C2	product}
intx	BlockLayoutMinDiamondPercentage	20	{C2	product}
bool	BlockLayoutRotateLoops	true	{C2	product}
bool	BranchOnRegister	false	{C2	product}
bool	BytecodeVerificationLocal	false	{product}	 
bool	BytecodeVerificationRemote	true	{product}	 
bool	C1OptimizeVirtualCallProfiling	true	{C1	product}
bool	C1ProfileBranches	true	{C1	product}
bool	C1ProfileCalls	true	{C1	product}
bool	C1ProfileCheckcasts	true	{C1	product}
bool	C1ProfileInlinedCalls	true	{C1	product}
bool	C1ProfileVirtualCalls	true	{C1	product}
bool	C1UpdateMethodData	true	{C1	product}
intx	CICompilerCount	2	{product}	 
bool	CICompilerCountPerCPU	false	{product}	 
bool	CITime	false	{product}	 
bool	CMSAbortSemantics	false	{product}	 
uintx	CMSAbortablePrecleanMinWorkPerIteration	100	{product}	 
intx	CMSAbortablePrecleanWaitMillis	100	{manageable}	 
uintx	CMSBitMapYieldQuantum	10485760	{product}	 
uintx	CMSBootstrapOccupancy	50	{product}	 
bool	CMSClassUnloadingEnabled	true	{product}	 
uintx	CMSClassUnloadingMaxInterval	0	{product}	 
bool	CMSCleanOnEnter	true	{product}	 
bool	CMSCompactWhenClearAllSoftRefs	true	{product}	 
uintx	CMSConcMarkMultiple	32	{product}	 
bool	CMSConcurrentMTEnabled	true	{product}	 
uintx	CMSCoordinatorYieldSleepCount	10	{product}	 
bool	CMSDumpAtPromotionFailure	false	{product}	 
bool	CMSEdenChunksRecordAlways	true	{product}	 
uintx	CMSExpAvgFactor	50	{product}	 
bool	CMSExtrapolateSweep	false	{product}	 
uintx	CMSFullGCsBeforeCompaction	0	{product}	 
uintx	CMSIncrementalDutyCycle	10	{product}	 
uintx	CMSIncrementalDutyCycleMin	0	{product}	 
bool	CMSIncrementalMode	false	{product}	 
uintx	CMSIncrementalOffset	0	{product}	 
bool	CMSIncrementalPacing	true	{product}	 
uintx	CMSIncrementalSafetyFactor	10	{product}	 
uintx	CMSIndexedFreeListReplenish	4	{product}	 
intx	CMSInitiatingOccupancyFraction	-1	{product}	 
uintx	CMSIsTooFullPercentage	98	{product}	 
double	CMSLargeCoalSurplusPercent	0.950000	{product}	 
double	CMSLargeSplitSurplusPercent	1.000000	{product}	 
bool	CMSLoopWarn	false	{product}	 
uintx	CMSMaxAbortablePrecleanLoops	0	{product}	 
intx	CMSMaxAbortablePrecleanTime	5000	{product}	 
uintx	CMSOldPLABMax	1024	{product}	 
uintx	CMSOldPLABMin	16	{product}	 
uintx	CMSOldPLABNumRefills	4	{product}	 
uintx	CMSOldPLABReactivityFactor	2	{product}	 
bool	CMSOldPLABResizeQuicker	false	{product}	 
uintx	CMSOldPLABToleranceFactor	4	{product}	 
bool	CMSPLABRecordAlways	true	{product}	 
uintx	CMSParPromoteBlocksToClaim	16	{product}	 
bool	CMSParallelInitialMarkEnabled	true	{product}	 
bool	CMSParallelRemarkEnabled	true	{product}	 
bool	CMSParallelSurvivorRemarkEnabled	true	{product}	 
uintx	CMSPrecleanDenominator	3	{product}	 
uintx	CMSPrecleanIter	3	{product}	 
uintx	CMSPrecleanNumerator	2	{product}	 
bool	CMSPrecleanRefLists1	true	{product}	 
bool	CMSPrecleanRefLists2	false	{product}	 
bool	CMSPrecleanSurvivors1	false	{product}	 
bool	CMSPrecleanSurvivors2	true	{product}	 
uintx	CMSPrecleanThreshold	1000	{product}	 
bool	CMSPrecleaningEnabled	true	{product}	 
bool	CMSPrintChunksInDump	false	{product}	 
bool	CMSPrintEdenSurvivorChunks	false	{product}	 
bool	CMSPrintObjectsInDump	false	{product}	 
uintx	CMSRemarkVerifyVariant	1	{product}	 
bool	CMSReplenishIntermediate	true	{product}	 
uintx	CMSRescanMultiple	32	{product}	 
uintx	CMSSamplingGrain	16384	{product}	 
bool	CMSScavengeBeforeRemark	false	{product}	 
uintx	CMSScheduleRemarkEdenPenetration	50	{product}	 
uintx	CMSScheduleRemarkEdenSizeThreshold	2097152	{product}	 
uintx	CMSScheduleRemarkSamplingRatio	5	{product}	 
double	CMSSmallCoalSurplusPercent	1.050000	{product}	 
double	CMSSmallSplitSurplusPercent	1.100000	{product}	 
bool	CMSSplitIndexedFreeListBlocks	true	{product}	 
intx	CMSTriggerInterval	-1	{manageable}	 
uintx	CMSTriggerRatio	80	{product}	 
intx	CMSWaitDuration	2000	{manageable}	 
uintx	CMSWorkQueueDrainThreshold	10	{product}	 
bool	CMSYield	true	{product}	 
uintx	CMSYieldSleepCount	0	{product}	 
uintx	CMSYoungGenPerWorker	67108864	{pd	product}
uintx	CMS_FLSPadding	1	{product}	 
uintx	CMS_FLSWeight	75	{product}	 
uintx	CMS_SweepPadding	1	{product}	 
uintx	CMS_SweepTimerThresholdMillis	10	{product}	 
uintx	CMS_SweepWeight	75	{product}	 
bool	CheckEndorsedAndExtDirs	false	{product}	 
bool	CheckJNICalls	false	{product}	 
bool	ClassUnloading	true	{product}	 
bool	ClassUnloadingWithConcurrentMark	true	{product}	 
intx	ClearFPUAtPark	0	{product}	 
bool	ClipInlining	true	{product}	 
uintx	CodeCacheExpansionSize	65536	{pd	product}
uintx	CodeCacheMinimumFreeSpace	512000	{product}	 
bool	CollectGen0First	false	{product}	 
bool	CompactFields	true	{product}	 
intx	CompilationPolicyChoice	0	{product}	 
ccstrlist	CompileCommand	{product}	 	 
ccstr	CompileCommandFile	{product}	 	 
ccstrlist	CompileOnly	{product}	 	 
intx	CompileThreshold	10000	{pd	product}
bool	CompilerThreadHintNoPreempt	true	{product}	 
intx	CompilerThreadPriority	-1	{product}	 
intx	CompilerThreadStackSize	0	{pd	product}
uintx	CompressedClassSpaceSize	1073741824	{product}	 
uintx	ConcGCThreads	0	{product}	 
intx	ConditionalMoveLimit	3	{C2	pd
intx	ContendedPaddingWidth	128	{product}	 
bool	ConvertSleepToYield	true	{pd	product}
bool	ConvertYieldToSleep	false	{product}	 
bool	CrashOnOutOfMemoryError	false	{product}	 
bool	CreateMinidumpOnCrash	false	{product}	 
bool	CriticalJNINatives	true	{product}	 
bool	DTraceAllocProbes	false	{product}	 
bool	DTraceMethodProbes	false	{product}	 
bool	DTraceMonitorProbes	false	{product}	 
bool	Debugging	false	{product}	 
uintx	DefaultMaxRAMFraction	4	{product}	 
intx	DefaultThreadPriority	-1	{product}	 
intx	DeferPollingPageLoopCount	-1	{product}	 
intx	DeferThrSuspendLoopCount	4000	{product}	 
bool	DeoptimizeRandom	false	{product}	 
bool	DisableAttachMechanism	false	{product}	 
bool	DisableExplicitGC	false	{product}	 
bool	DisplayVMOutputToStderr	false	{product}	 
bool	DisplayVMOutputToStdout	false	{product}	 
bool	DoEscapeAnalysis	true	{C2	product}
bool	DontCompileHugeMethods	true	{product}	 
bool	DontYieldALot	false	{pd	product}
ccstr	DumpLoadedClassList	{product}	 	 
bool	DumpReplayDataOnError	true	{product}	 
bool	DumpSharedSpaces	false	{product}	 
bool	EagerXrunInit	false	{product}	 
intx	EliminateAllocationArraySizeLimit	64	{C2	product}
bool	EliminateAllocations	true	{C2	product}
bool	EliminateAutoBox	true	{C2	product}
bool	EliminateLocks	true	{C2	product}
bool	EliminateNestedLocks	true	{C2	product}
intx	EmitSync	0	{product}	 
bool	EnableContended	true	{product}	 
bool	EnableResourceManagementTLABCache	true	{product}	 
bool	EnableSharedLookupCache	true	{product}	 
bool	EnableTracing	false	{product}	 
uintx	ErgoHeapSizeLimit	0	{product}	 
ccstr	ErrorFile	{product}	 	 
ccstr	ErrorReportServer	{product}	 	 
double	EscapeAnalysisTimeout	20.000000	{C2	product}
bool	EstimateArgEscape	true	{product}	 
bool	ExitOnOutOfMemoryError	false	{product}	 
bool	ExplicitGCInvokesConcurrent	false	{product}	 
bool	ExplicitGCInvokesConcurrentAndUnloadsClasses	false	{product}	 
bool	ExtendedDTraceProbes	false	{product}	 
ccstr	ExtraSharedClassListFile	{product}	 	 
bool	FLSAlwaysCoalesceLarge	false	{product}	 
uintx	FLSCoalescePolicy	2	{product}	 
double	FLSLargestBlockCoalesceProximity	0.990000	{product}	 
bool	FailOverToOldVerifier	true	{product}	 
bool	FastTLABRefill	true	{product}	 
intx	FenceInstruction	0	{ARCH	product}
intx	FieldsAllocationStyle	1	{product}	 
bool	FilterSpuriousWakeups	true	{product}	 
ccstr	FlightRecorderOptions	{product}	 	 
bool	ForceNUMA	false	{product}	 
bool	ForceTimeHighResolution	false	{product}	 
intx	FreqInlineSize	325	{pd	product}
double	G1ConcMarkStepDurationMillis	10.000000	{product}	 
uintx	G1ConcRSHotCardLimit	4	{product}	 
uintx	G1ConcRSLogCacheSize	10	{product}	 
intx	G1ConcRefinementGreenZone	0	{product}	 
intx	G1ConcRefinementRedZone	0	{product}	 
intx	G1ConcRefinementServiceIntervalMillis	300	{product}	 
uintx	G1ConcRefinementThreads	0	{product}	 
intx	G1ConcRefinementThresholdStep	0	{product}	 
intx	G1ConcRefinementYellowZone	0	{product}	 
uintx	G1ConfidencePercent	50	{product}	 
uintx	G1HeapRegionSize	0	{product}	 
uintx	G1HeapWastePercent	5	{product}	 
uintx	G1MixedGCCountTarget	8	{product}	 
intx	G1RSetRegionEntries	0	{product}	 
uintx	G1RSetScanBlockSize	64	{product}	 
intx	G1RSetSparseRegionEntries	0	{product}	 
intx	G1RSetUpdatingPauseTimePercent	10	{product}	 
intx	G1RefProcDrainInterval	10	{product}	 
uintx	G1ReservePercent	10	{product}	 
uintx	G1SATBBufferEnqueueingThresholdPercent	60	{product}	 
intx	G1SATBBufferSize	1024	{product}	 
intx	G1UpdateBufferSize	256	{product}	 
bool	G1UseAdaptiveConcRefinement	true	{product}	 
uintx	GCDrainStackTargetSize	64	{product}	 
uintx	GCHeapFreeLimit	2	{product}	 
uintx	GCLockerEdenExpansionPercent	5	{product}	 
bool	GCLockerInvokesConcurrent	false	{product}	 
uintx	GCLogFileSize	8192	{product}	 
uintx	GCPauseIntervalMillis	0	{product}	 
uintx	GCTaskTimeStampEntries	200	{product}	 
uintx	GCTimeLimit	98	{product}	 
uintx	GCTimeRatio	99	{product}	 
uintx	HeapBaseMinAddress	2147483648	{pd	product}
bool	HeapDumpAfterFullGC	false	{manageable}	 
bool	HeapDumpBeforeFullGC	false	{manageable}	 
bool	HeapDumpOnOutOfMemoryError	false	{manageable}	 
ccstr	HeapDumpPath	{manageable}	 	 
uintx	HeapFirstMaximumCompactionCount	3	{product}	 
uintx	HeapMaximumCompactionInterval	20	{product}	 
uintx	HeapSizePerGCThread	87241520	{product}	 
bool	IgnoreEmptyClassPaths	false	{product}	 
bool	IgnoreUnrecognizedVMOptions	false	{product}	 
uintx	IncreaseFirstTierCompileThresholdAt	50	{product}	 
bool	IncrementalInline	true	{C2	product}
uintx	InitialBootClassLoaderMetaspaceSize	4194304	{product}	 
uintx	InitialCodeCacheSize	2555904	{pd	product}
uintx	InitialHeapSize	0	{product}	 
uintx	InitialRAMFraction	64	{product}	 
double	InitialRAMPercentage	1.562500	{product}	 
uintx	InitialSurvivorRatio	8	{product}	 
uintx	InitialTenuringThreshold	7	{product}	 
uintx	InitiatingHeapOccupancyPercent	45	{product}	 
bool	Inline	true	{product}	 
ccstr	InlineDataFile	{product}	 	 
intx	InlineSmallCode	1000	{pd	product}
bool	InlineSynchronizedMethods	true	{C1	product}
bool	InsertMemBarAfterArraycopy	true	{C2	product}
intx	InteriorEntryAlignment	16	{C2	pd
intx	InterpreterProfilePercentage	33	{product}	 
bool	JNIDetachReleasesMonitors	true	{product}	 
bool	JavaMonitorsInStackTrace	true	{product}	 
intx	JavaPriority10_To_OSPriority	-1	{product}	 
intx	JavaPriority1_To_OSPriority	-1	{product}	 
intx	JavaPriority2_To_OSPriority	-1	{product}	 
intx	JavaPriority3_To_OSPriority	-1	{product}	 
intx	JavaPriority4_To_OSPriority	-1	{product}	 
intx	JavaPriority5_To_OSPriority	-1	{product}	 
intx	JavaPriority6_To_OSPriority	-1	{product}	 
intx	JavaPriority7_To_OSPriority	-1	{product}	 
intx	JavaPriority8_To_OSPriority	-1	{product}	 
intx	JavaPriority9_To_OSPriority	-1	{product}	 
bool	LIRFillDelaySlots	false	{C1	pd
uintx	LargePageHeapSizeThreshold	134217728	{product}	 
uintx	LargePageSizeInBytes	0	{product}	 
bool	LazyBootClassLoader	true	{product}	 
intx	LiveNodeCountInliningCutoff	40000	{C2	product}
bool	LoadExecStackDllInVMThread	true	{product}	 
bool	LogCommercialFeatures	false	{product}	 
intx	LoopMaxUnroll	16	{C2	product}
intx	LoopOptsCount	43	{C2	product}
intx	LoopUnrollLimit	60	{C2	pd
intx	LoopUnrollMin	4	{C2	product}
bool	LoopUnswitching	true	{C2	product}
bool	ManagementServer	false	{product}	 
uintx	MarkStackSize	4194304	{product}	 
uintx	MarkStackSizeMax	536870912	{product}	 
uintx	MarkSweepAlwaysCompactCount	4	{product}	 
uintx	MarkSweepDeadRatio	5	{product}	 
intx	MaxBCEAEstimateLevel	5	{product}	 
intx	MaxBCEAEstimateSize	150	{product}	 
uintx	MaxDirectMemorySize	0	{product}	 
bool	MaxFDLimit	true	{product}	 
uintx	MaxGCMinorPauseMillis	18446744073709551615	{product}	 
uintx	MaxGCPauseMillis	18446744073709551615	{product}	 
uintx	MaxHeapFreeRatio	70	{manageable}	 
uintx	MaxHeapSize	130862280	{product}	 
intx	MaxInlineLevel	9	{product}	 
intx	MaxInlineSize	35	{product}	 
intx	MaxJNILocalCapacity	65536	{product}	 
intx	MaxJavaStackTraceDepth	1024	{product}	 
intx	MaxJumpTableSize	65000	{C2	product}
intx	MaxJumpTableSparseness	5	{C2	product}
intx	MaxLabelRootDepth	1100	{C2	product}
intx	MaxLoopPad	15	{C2	product}
uintx	MaxMetaspaceExpansion	5452592	{product}	 
uintx	MaxMetaspaceFreeRatio	70	{product}	 
uintx	MaxMetaspaceSize	18446744073709551615	{product}	 
uintx	MaxNewSize	18446744073709551615	{product}	 
intx	MaxNodeLimit	80000	{C2	product}
uint64_t	MaxRAM	137438953472	{pd	product}
uintx	MaxRAMFraction	4	{product}	 
double	MaxRAMPercentage	25.000000	{product}	 
intx	MaxRecursiveInlineLevel	1	{product}	 
uintx	MaxTenuringThreshold	15	{product}	 
intx	MaxTrivialSize	6	{product}	 
intx	MaxVectorSize	32	{C2	product}
uintx	MetaspaceSize	21810376	{pd	product}
bool	MethodFlushing	true	{product}	 
uintx	MinHeapDeltaBytes	170392	{product}	 
uintx	MinHeapFreeRatio	40	{manageable}	 
intx	MinInliningThreshold	250	{product}	 
intx	MinJumpTableSize	10	{C2	pd
uintx	MinMetaspaceExpansion	340784	{product}	 
uintx	MinMetaspaceFreeRatio	40	{product}	 
uintx	MinRAMFraction	2	{product}	 
double	MinRAMPercentage	50.000000	{product}	 
uintx	MinSurvivorRatio	3	{product}	 
uintx	MinTLABSize	2048	{product}	 
intx	MonitorBound	0	{product}	 
bool	MonitorInUseLists	false	{product}	 
intx	MultiArrayExpandLimit	6	{C2	product}
bool	MustCallLoadClassInternal	false	{product}	 
uintx	NUMAChunkResizeWeight	20	{product}	 
uintx	NUMAInterleaveGranularity	2097152	{product}	 
uintx	NUMAPageScanRate	256	{product}	 
uintx	NUMASpaceResizeRate	1073741824	{product}	 
bool	NUMAStats	false	{product}	 
ccstr	NativeMemoryTracking	off	{product}	 
bool	NeedsDeoptSuspend	false	{pd	product}
bool	NeverActAsServerClassMachine	false	{pd	product}
bool	NeverTenure	false	{product}	 
uintx	NewRatio	2	{product}	 
uintx	NewSize	1363144	{product}	 
uintx	NewSizeThreadIncrease	5320	{pd	product}
intx	NmethodSweepActivity	10	{product}	 
intx	NmethodSweepCheckInterval	5	{product}	 
intx	NmethodSweepFraction	16	{product}	 
intx	NodeLimitFudgeFactor	2000	{C2	product}
uintx	NumberOfGCLogFiles	0	{product}	 
intx	NumberOfLoopInstrToAlign	4	{C2	product}
intx	ObjectAlignmentInBytes	8	{lp64_product}	 
uintx	OldPLABSize	1024	{product}	 
uintx	OldPLABWeight	50	{product}	 
uintx	OldSize	5452592	{product}	 
bool	OmitStackTraceInFastThrow	true	{product}	 
ccstrlist	OnError	{product}	 	 
ccstrlist	OnOutOfMemoryError	{product}	 	 
intx	OnStackReplacePercentage	140	{pd	product}
bool	OptimizeFill	true	{C2	product}
bool	OptimizePtrCompare	true	{C2	product}
bool	OptimizeStringConcat	true	{C2	product}
bool	OptoBundling	false	{C2	pd
intx	OptoLoopAlignment	16	{pd	product}
bool	OptoScheduling	false	{C2	pd
uintx	PLABWeight	75	{product}	 
bool	PSChunkLargeArrays	true	{product}	 
intx	ParGCArrayScanChunk	50	{product}	 
uintx	ParGCDesiredObjsFromOverflowList	20	{product}	 
bool	ParGCTrimOverflow	true	{product}	 
bool	ParGCUseLocalOverflow	false	{product}	 
uintx	ParallelGCBufferWastePct	10	{product}	 
uintx	ParallelGCThreads	0	{product}	 
bool	ParallelGCVerbose	false	{product}	 
uintx	ParallelOldDeadWoodLimiterMean	50	{product}	 
uintx	ParallelOldDeadWoodLimiterStdDev	80	{product}	 
bool	ParallelRefProcBalancingEnabled	true	{product}	 
bool	ParallelRefProcEnabled	false	{product}	 
bool	PartialPeelAtUnsignedTests	true	{C2	product}
bool	PartialPeelLoop	true	{C2	product}
intx	PartialPeelNewPhiDelta	0	{C2	product}
uintx	PausePadding	1	{product}	 
intx	PerBytecodeRecompilationCutoff	200	{product}	 
intx	PerBytecodeTrapLimit	4	{product}	 
intx	PerMethodRecompilationCutoff	400	{product}	 
intx	PerMethodTrapLimit	100	{product}	 
bool	PerfAllowAtExitRegistration	false	{product}	 
bool	PerfBypassFileSystemCheck	false	{product}	 
intx	PerfDataMemorySize	32768	{product}	 
intx	PerfDataSamplingInterval	50	{product}	 
ccstr	PerfDataSaveFile	{product}	 	 
bool	PerfDataSaveToFile	false	{product}	 
bool	PerfDisableSharedMem	false	{product}	 
intx	PerfMaxStringConstLength	1024	{product}	 
intx	PreInflateSpin	10	{pd	product}
bool	PreferContainerQuotaForCPUCount	true	{product}	 
bool	PreferInterpreterNativeStubs	false	{pd	product}
intx	PrefetchCopyIntervalInBytes	-1	{product}	 
intx	PrefetchFieldsAhead	-1	{product}	 
intx	PrefetchScanIntervalInBytes	-1	{product}	 
bool	PreserveAllAnnotations	false	{product}	 
bool	PreserveFramePointer	false	{pd	product}
uintx	PretenureSizeThreshold	0	{product}	 
bool	PrintAdaptiveSizePolicy	false	{product}	 
bool	PrintCMSInitiationStatistics	false	{product}	 
intx	PrintCMSStatistics	0	{product}	 
bool	PrintClassHistogram	false	{manageable}	 
bool	PrintClassHistogramAfterFullGC	false	{manageable}	 
bool	PrintClassHistogramBeforeFullGC	false	{manageable}	 
bool	PrintCodeCache	false	{product}	 
bool	PrintCodeCacheOnCompilation	false	{product}	 
bool	PrintCommandLineFlags	false	{product}	 
bool	PrintCompilation	false	{product}	 
bool	PrintConcurrentLocks	false	{manageable}	 
intx	PrintFLSCensus	0	{product}	 
intx	PrintFLSStatistics	0	{product}	 
bool	PrintFlagsFinal	false	{product}	 
bool	PrintFlagsInitial	false	{product}	 
bool	PrintGC	false	{manageable}	 
bool	PrintGCApplicationConcurrentTime	false	{product}	 
bool	PrintGCApplicationStoppedTime	false	{product}	 
bool	PrintGCCause	true	{product}	 
bool	PrintGCDateStamps	false	{manageable}	 
bool	PrintGCDetails	false	{manageable}	 
bool	PrintGCID	false	{manageable}	 
bool	PrintGCTaskTimeStamps	false	{product}	 
bool	PrintGCTimeStamps	false	{manageable}	 
bool	PrintHeapAtGC	false	{product	rw}
bool	PrintHeapAtGCExtended	false	{product	rw}
bool	PrintHeapAtSIGBREAK	true	{product}	 
bool	PrintJNIGCStalls	false	{product}	 
bool	PrintJNIResolving	false	{product}	 
bool	PrintOldPLAB	false	{product}	 
bool	PrintOopAddress	false	{product}	 
bool	PrintPLAB	false	{product}	 
bool	PrintParallelOldGCPhaseTimes	false	{product}	 
bool	PrintPromotionFailure	false	{product}	 
bool	PrintReferenceGC	false	{product}	 
bool	PrintSafepointStatistics	false	{product}	 
intx	PrintSafepointStatisticsCount	300	{product}	 
intx	PrintSafepointStatisticsTimeout	-1	{product}	 
bool	PrintSharedArchiveAndExit	false	{product}	 
bool	PrintSharedDictionary	false	{product}	 
bool	PrintSharedSpaces	false	{product}	 
bool	PrintStringDeduplicationStatistics	false	{product}	 
bool	PrintStringTableStatistics	false	{product}	 
bool	PrintTLAB	false	{product}	 
bool	PrintTenuringDistribution	false	{product}	 
bool	PrintTieredEvents	false	{product}	 
bool	PrintVMOptions	false	{product}	 
bool	PrintVMQWaitTime	false	{product}	 
bool	PrintWarnings	true	{product}	 
uintx	ProcessDistributionStride	4	{product}	 
bool	ProfileInterpreter	true	{pd	product}
bool	ProfileIntervals	false	{product}	 
intx	ProfileIntervalsTicks	100	{product}	 
intx	ProfileMaturityPercentage	20	{product}	 
bool	ProfileVM	false	{product}	 
bool	ProfilerPrintByteCodeStatistics	false	{product}	 
bool	ProfilerRecordPC	false	{product}	 
uintx	PromotedPadding	3	{product}	 
uintx	QueuedAllocationWarningCount	0	{product}	 
uintx	RTMRetryCount	5	{ARCH	product}
bool	RangeCheckElimination	true	{product}	 
intx	ReadPrefetchInstr	0	{ARCH	product}
bool	ReassociateInvariants	true	{C2	product}
bool	ReduceBulkZeroing	true	{C2	product}
bool	ReduceFieldZeroing	true	{C2	product}
bool	ReduceInitialCardMarks	true	{C2	product}
bool	ReduceSignalUsage	false	{product}	 
intx	RefDiscoveryPolicy	0	{product}	 
bool	ReflectionWrapResolutionErrors	true	{product}	 
bool	RegisterFinalizersAtInit	true	{product}	 
bool	RelaxAccessControlCheck	false	{product}	 
ccstr	ReplayDataFile	{product}	 	 
bool	RequireSharedSpaces	false	{product}	 
uintx	ReservedCodeCacheSize	50331648	{pd	product}
bool	ResizeOldPLAB	true	{product}	 
bool	ResizePLAB	true	{product}	 
bool	ResizeTLAB	true	{pd	product}
bool	RestoreMXCSROnJNICalls	false	{product}	 
bool	RestrictContended	true	{product}	 
bool	RewriteBytecodes	true	{pd	product}
bool	RewriteFrequentPairs	true	{pd	product}
intx	SafepointPollOffset	256	{C1	pd
intx	SafepointSpinBeforeYield	2000	{product}	 
bool	SafepointTimeout	false	{product}	 
intx	SafepointTimeoutDelay	10000	{product}	 
bool	ScavengeBeforeFullGC	true	{product}	 
intx	SelfDestructTimer	0	{product}	 
uintx	SharedBaseAddress	34359738368	{product}	 
ccstr	SharedClassListFile	{product}	 	 
uintx	SharedMiscCodeSize	122880	{product}	 
uintx	SharedMiscDataSize	4194304	{product}	 
uintx	SharedReadOnlySize	16777216	{product}	 
uintx	SharedReadWriteSize	16777216	{product}	 
bool	ShowMessageBoxOnError	false	{product}	 
intx	SoftRefLRUPolicyMSPerMB	1000	{product}	 
bool	SpecialEncodeISOArray	true	{C2	product}
bool	SplitIfBlocks	true	{C2	product}
intx	StackRedPages	1	{pd	product}
intx	StackShadowPages	20	{pd	product}
bool	StackTraceInThrowable	true	{product}	 
intx	StackYellowPages	2	{pd	product}
bool	StartAttachListener	false	{product}	 
intx	StarvationMonitorInterval	200	{product}	 
bool	StressLdcRewrite	false	{product}	 
uintx	StringDeduplicationAgeThreshold	3	{product}	 
uintx	StringTableSize	60013	{product}	 
bool	SuppressFatalErrorMessage	false	{product}	 
uintx	SurvivorPadding	3	{product}	 
uintx	SurvivorRatio	8	{product}	 
intx	SuspendRetryCount	50	{product}	 
intx	SuspendRetryDelay	5	{product}	 
intx	SyncFlags	0	{product}	 
ccstr	SyncKnobs	{product}	 	 
intx	SyncVerbose	0	{product}	 
uintx	TLABAllocationWeight	35	{product}	 
uintx	TLABRefillWasteFraction	64	{product}	 
uintx	TLABSize	0	{product}	 
bool	TLABStats	true	{product}	 
uintx	TLABWasteIncrement	4	{product}	 
uintx	TLABWasteTargetPercent	1	{product}	 
uintx	TargetPLABWastePct	10	{product}	 
uintx	TargetSurvivorRatio	50	{product}	 
uintx	TenuredGenerationSizeIncrement	20	{product}	 
uintx	TenuredGenerationSizeSupplement	80	{product}	 
uintx	TenuredGenerationSizeSupplementDecay	2	{product}	 
intx	ThreadPriorityPolicy	0	{product}	 
bool	ThreadPriorityVerbose	false	{product}	 
uintx	ThreadSafetyMargin	52428800	{product}	 
intx	ThreadStackSize	1024	{pd	product}
uintx	ThresholdTolerance	10	{product}	 
intx	Tier0BackedgeNotifyFreqLog	10	{product}	 
intx	Tier0InvokeNotifyFreqLog	7	{product}	 
intx	Tier0ProfilingStartPercentage	200	{product}	 
intx	Tier23InlineeNotifyFreqLog	20	{product}	 
intx	Tier2BackEdgeThreshold	0	{product}	 
intx	Tier2BackedgeNotifyFreqLog	14	{product}	 
intx	Tier2CompileThreshold	0	{product}	 
intx	Tier2InvokeNotifyFreqLog	11	{product}	 
intx	Tier3BackEdgeThreshold	60000	{product}	 
intx	Tier3BackedgeNotifyFreqLog	13	{product}	 
intx	Tier3CompileThreshold	2000	{product}	 
intx	Tier3DelayOff	2	{product}	 
intx	Tier3DelayOn	5	{product}	 
intx	Tier3InvocationThreshold	200	{product}	 
intx	Tier3InvokeNotifyFreqLog	10	{product}	 
intx	Tier3LoadFeedback	5	{product}	 
intx	Tier3MinInvocationThreshold	100	{product}	 
intx	Tier4BackEdgeThreshold	40000	{product}	 
intx	Tier4CompileThreshold	15000	{product}	 
intx	Tier4InvocationThreshold	5000	{product}	 
intx	Tier4LoadFeedback	3	{product}	 
intx	Tier4MinInvocationThreshold	600	{product}	 
bool	TieredCompilation	true	{pd	product}
intx	TieredCompileTaskTimeout	50	{product}	 
intx	TieredRateUpdateMaxTime	25	{product}	 
intx	TieredRateUpdateMinTime	1	{product}	 
intx	TieredStopAtLevel	4	{product}	 
bool	TimeLinearScan	false	{C1	product}
bool	TraceBiasedLocking	false	{product}	 
bool	TraceClassLoading	false	{product	rw}
bool	TraceClassLoadingPreorder	false	{product}	 
bool	TraceClassPaths	false	{product}	 
bool	TraceClassResolution	false	{product}	 
bool	TraceClassUnloading	false	{product	rw}
bool	TraceDynamicGCThreads	false	{product}	 
bool	TraceGen0Time	false	{product}	 
bool	TraceGen1Time	false	{product}	 
ccstr	TraceJVMTI	{product}	 	 
bool	TraceLoaderConstraints	false	{product	rw}
bool	TraceMetadataHumongousAllocation	false	{product}	 
bool	TraceMonitorInflation	false	{product}	 
bool	TraceParallelOldGCTasks	false	{product}	 
intx	TraceRedefineClasses	0	{product}	 
bool	TraceSafepointCleanupTime	false	{product}	 
bool	TraceSharedLookupCache	false	{product}	 
bool	TraceSuspendWaitFailures	false	{product}	 
intx	TrackedInitializationLimit	50	{C2	product}
bool	TransmitErrorReport	false	{product}	 
bool	TrapBasedNullChecks	false	{pd	product}
bool	TrapBasedRangeChecks	false	{C2	pd
intx	TypeProfileArgsLimit	2	{product}	 
uintx	TypeProfileLevel	111	{pd	product}
intx	TypeProfileMajorReceiverPercent	90	{C2	product}
intx	TypeProfileParmsLimit	2	{product}	 
intx	TypeProfileWidth	2	{product}	 
intx	UnguardOnExecutionViolation	0	{product}	 
bool	UnlinkSymbolsALot	false	{product}	 
bool	Use486InstrsOnly	false	{ARCH	product}
bool	UseAES	false	{product}	 
bool	UseAESIntrinsics	false	{product}	 
intx	UseAVX	99	{ARCH	product}
bool	UseAdaptiveGCBoundary	false	{product}	 
bool	UseAdaptiveGenerationSizePolicyAtMajorCollection	true	{product}	 
bool	UseAdaptiveGenerationSizePolicyAtMinorCollection	true	{product}	 
bool	UseAdaptiveNUMAChunkSizing	true	{product}	 
bool	UseAdaptiveSizeDecayMajorGCCost	true	{product}	 
bool	UseAdaptiveSizePolicy	true	{product}	 
bool	UseAdaptiveSizePolicyFootprintGoal	true	{product}	 
bool	UseAdaptiveSizePolicyWithSystemGC	false	{product}	 
bool	UseAddressNop	false	{ARCH	product}
bool	UseAltSigs	false	{product}	 
bool	UseAutoGCSelectPolicy	false	{product}	 
bool	UseBMI1Instructions	false	{ARCH	product}
bool	UseBMI2Instructions	false	{ARCH	product}
bool	UseBiasedLocking	true	{product}	 
bool	UseBimorphicInlining	true	{C2	product}
bool	UseBoundThreads	true	{product}	 
bool	UseCLMUL	false	{ARCH	product}
bool	UseCMSBestFit	true	{product}	 
bool	UseCMSCollectionPassing	true	{product}	 
bool	UseCMSCompactAtFullCollection	true	{product}	 
bool	UseCMSInitiatingOccupancyOnly	false	{product}	 
bool	UseCRC32Intrinsics	false	{product}	 
bool	UseCodeCacheFlushing	true	{product}	 
bool	UseCompiler	true	{product}	 
bool	UseCompilerSafepoints	true	{product}	 
bool	UseCompressedClassPointers	false	{lp64_product}	 
bool	UseCompressedOops	false	{lp64_product}	 
bool	UseConcMarkSweepGC	false	{product}	 
bool	UseCondCardMark	false	{C2	product}
bool	UseContainerSupport	true	{product}	 
bool	UseCountLeadingZerosInstruction	false	{ARCH	product}
bool	UseCountTrailingZerosInstruction	false	{ARCH	product}
bool	UseCountedLoopSafepoints	false	{C2	product}
bool	UseCounterDecay	true	{product}	 
bool	UseDivMod	true	{C2	product}
bool	UseDynamicNumberOfGCThreads	false	{product}	 
bool	UseFPUForSpilling	false	{C2	product}
bool	UseFastAccessorMethods	true	{product}	 
bool	UseFastEmptyMethods	true	{product}	 
bool	UseFastJNIAccessors	true	{product}	 
bool	UseFastStosb	false	{ARCH	product}
bool	UseG1GC	false	{product}	 
bool	UseGCLogFileRotation	false	{product}	 
bool	UseGCOverheadLimit	true	{product}	 
bool	UseGCTaskAffinity	false	{product}	 
bool	UseGHASHIntrinsics	false	{product}	 
bool	UseHeavyMonitors	false	{product}	 
bool	UseHugeTLBFS	false	{product}	 
bool	UseInlineCaches	true	{product}	 
bool	UseInterpreter	true	{product}	 
bool	UseJumpTables	true	{C2	product}
bool	UseLWPSynchronization	true	{product}	 
bool	UseLargePages	false	{pd	product}
bool	UseLargePagesInMetaspace	false	{product}	 
bool	UseLargePagesIndividualAllocation	false	{pd	product}
bool	UseLinuxPosixThreadCPUClocks	true	{product}	 
bool	UseLockedTracing	false	{product}	 
bool	UseLoopCounter	true	{product}	 
bool	UseLoopInvariantCodeMotion	true	{C1	product}
bool	UseLoopPredicate	true	{C2	product}
bool	UseMathExactIntrinsics	true	{C2	product}
bool	UseMaximumCompactionOnSystemGC	true	{product}	 
bool	UseMembar	false	{pd	product}
bool	UseMontgomeryMultiplyIntrinsic	false	{C2	product}
bool	UseMontgomerySquareIntrinsic	false	{C2	product}
bool	UseMulAddIntrinsic	false	{C2	product}
bool	UseMultiplyToLenIntrinsic	false	{C2	product}
bool	UseNUMA	false	{product}	 
bool	UseNUMAInterleaving	false	{product}	 
bool	UseNewLongLShift	false	{ARCH	product}
bool	UseOSErrorReporting	false	{pd	product}
bool	UseOldInlining	true	{C2	product}
bool	UseOnStackReplacement	true	{pd	product}
bool	UseOnlyInlinedBimorphic	true	{C2	product}
bool	UseOprofile	false	{product}	 
bool	UseOptoBiasInlining	true	{C2	product}
bool	UsePSAdaptiveSurvivorSizePolicy	true	{product}	 
bool	UseParNewGC	false	{product}	 
bool	UseParallelGC	false	{product}	 
bool	UseParallelOldGC	false	{product}	 
bool	UsePerfData	true	{product}	 
bool	UsePopCountInstruction	false	{product}	 
bool	UseRDPCForConstantTableBase	false	{C2	product}
bool	UseRTMDeopt	false	{ARCH	product}
bool	UseRTMLocking	false	{ARCH	product}
bool	UseSHA	false	{product}	 
bool	UseSHA1Intrinsics	false	{product}	 
bool	UseSHA256Intrinsics	false	{product}	 
bool	UseSHA512Intrinsics	false	{product}	 
bool	UseSHM	false	{product}	 
intx	UseSSE	99	{product}	 
bool	UseSSE42Intrinsics	false	{product}	 
bool	UseSerialGC	false	{product}	 
bool	UseSharedSpaces	true	{product}	 
bool	UseSignalChaining	true	{product}	 
bool	UseSquareToLenIntrinsic	false	{C2	product}
bool	UseStoreImmI16	true	{ARCH	product}
bool	UseStringDeduplication	false	{product}	 
bool	UseSuperWord	true	{C2	product}
bool	UseTLAB	true	{pd	product}
bool	UseThreadPriorities	true	{pd	product}
bool	UseTransparentHugePages	false	{product}	 
bool	UseTypeProfile	true	{product}	 
bool	UseTypeSpeculation	true	{C2	product}
bool	UseUnalignedLoadStores	false	{ARCH	product}
bool	UseVMInterruptibleIO	false	{product}	 
bool	UseXMMForArrayCopy	false	{product}	 
bool	UseXmmI2D	false	{ARCH	product}
bool	UseXmmI2F	false	{ARCH	product}
bool	UseXmmLoadAndClearUpper	true	{ARCH	product}
bool	UseXmmRegToRegMoveAll	false	{ARCH	product}
bool	VMThreadHintNoPreempt	false	{product}	 
intx	VMThreadPriority	-1	{product}	 
intx	VMThreadStackSize	1024	{pd	product}
intx	ValueMapInitialSize	11	{C1	product}
intx	ValueMapMaxLoopSize	8	{C1	product}
intx	ValueSearchLimit	1000	{C2	product}
bool	VerifyMergedCPBytecodes	true	{product}	 
bool	VerifySharedSpaces	false	{product}	 
intx	WorkAroundNPTLTimedWaitHang	1	{product}	 
uintx	YoungGenerationSizeIncrement	20	{product}	 
uintx	YoungGenerationSizeSupplement	80	{product}	 
uintx	YoungGenerationSizeSupplementDecay	8	{product}	 
uintx	YoungPLABSize	4096	{product}	 
bool	ZeroTLAB	false	{product}	 
intx	hashCode	5	{product}	 
————————————————
版权声明：本文为CSDN博主「guyue35」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/guyue35/article/details/107957859