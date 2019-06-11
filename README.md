# hello-world-java
java practice

## environment setting
1. JAVA_HOME
C:\Program Files (x86)\Java\jdk1.8.0_91 // 要根据自己的实际路径配置
2. CLASSPATH
.;%JAVA_HOME%\lib\dt.jar;%JAVA_HOME%\lib\tools.jar; //记得前面有个"."
3. Path
%JAVA_HOME%\bin;%JAVA_HOME%\jre\bin;

## 路线
1. java本身 
    * Collection  Thread  IO  网络
2. java的框架
    * SpringBoot 
3. java使用的 **技术** **组件**
    * nginx MQ redis
    
## .gitignore
.idea 目录或者文件已经被 git 跟踪，此时再加入 .gitignore 就无法屏蔽了。
git 设置本地忽略必须保证 git 的远程仓库分支上没有这个要忽略的文件；
如果远程分支上存在这个文件，本地再设置 ignore，将不起作用。
1. 删除 .idea 目录（本地删除然后同步到远程）
2. .gitignore 文件中添加 .idea