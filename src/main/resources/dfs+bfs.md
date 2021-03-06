# **深度优先搜索算法（dfs)+广度优先搜索算法(bfs)**

区别



一般来说用DFS解决的问题都可以用BFS来解决，都是对图进行搜索的算法
DFS多用于连通性问题因为其运行思想与人脑的思维很相似，故解决连通性问题更自然。
BFS多用于解决最短路问题，其运行过程中需要储存每一层的信息，所以其运行时需要储存的信息量较大。
多数情况下运行BFS所需的内存会大于DFS需要的内存(DFS一次访问一条路，BFS一次访问多条路)，DFS容易爆栈(栈不易"控制")，BFS通过控制队列可以很好解决"爆队列"风险。
从起点开始通过某种规则进行搜索，直到找到指定节点(即终点)。在此过程中每走到一个节点，就会判断一次它是否为终点。
广度优先搜索会根据离起点的距离，按照从近到远的顺序对各节点进行搜索。
深度优先搜索会沿着一条路径不断往下搜索直到不能再继续为止，然后再折返，开始搜索下一条路径。
在广度优先搜索中，有一个保存候补节点的**队列**，队列的性质就是**先进先出**，即先进入该队列的候补节点就先进行搜索。
在深度优先搜索中，保存候补节点是**栈**，栈的性质就是**先进后出**，即最先进入该栈的候补节点就最后进行搜索。

