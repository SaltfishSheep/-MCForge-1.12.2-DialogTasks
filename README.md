# DialogTasks
## ——对话与任务
### 前言
我之所以写这个模组，是因为我对NPCmod的对话框很不满，非常不满！延迟导致多次对话、灵活度低、字体渲染模糊、选项手感差••••••  
所以，我决定自己写一个，就以聊天栏作为对话的模式！  
开始的时候想用Gui来实现编辑对话、任务和绑定实体，但是发现这样非常麻烦！  
最后决定用文本编辑实现了，如果你用过MM（插件：神话怪物）的话，应该会对我的格式感到非常熟悉！  
### 一些额外特性
逻辑客户端有效：  
在对话期间，会禁止玩家除了转头以外的任何行动（某些强制判断按键的模组除外）  
在对话期间，如果有除了对话和任务以外的消息，会暂时屏蔽，这些消息会被收集起来，等到对话结束（准确的说是锁定解除后）一次性全部发送。**（可在配置文件关闭）**  
逻辑服务端有效：  
对话、任务、实体绑定读取文件夹默认为存档内，**可在配置文件修改**，修改后为 .minecraft/dialogtasks 下或 版本/dialogtasks 下
### 目录
1. **文件**  
2. **“函数”**  
  * 对话  
  * 任务  
  * 额外运行  
  * 需求  
  * CustomNPCs任务相关  
3. **变量**  
4. **指令**  

### 文件
文件一共有三类，分别是后缀为".dialog",".task",".entity"，分别对应对话，任务，挂载实体  
对话和任务的信息将通过“函数”编辑、添加，而挂载实体文件，文件名即为实体名，其内每一行都对应“挂载”的一个对话。  
对于挂载实体，通过与挂载实体右键交互会触发对话，只有当上一行对应的对话无法执行（即其require不满足时）才会考虑下一行对应的对话。  
文件只要求在 存档目录/dialogtasks下，不论在哪个子文件夹都会检测到。  

**请使用UTF-8编码**

[实例下载](https://pan.quark.cn/s/3d9ca27cd444)

### 函数
类似于MM，类似于代码的函数调用，其使用形式是这样的  
`函数名(参数名=输入值;参数二=输入值)`  
用';'分隔不同参数输入  
为了便于参考，下文提供函数、参数名、输入值会采用这样的形式。  

 函数名 | 参数 | 说明  
 ---- | ----- | ------  
 function | var(D)[0.0] | 临时参数  

写出来的效果是`function(var=1.0)`，其中`(D)`表示该参数应输入小数，`[0.0]`表示该参数选填，默认值为0.0  
参数类型：  
D:小数，I:整数,S:文本（可能有格式要求）  
ps:除了有格式要求的文本以外，请尽量不要写空格、缩进，这可能会造成Bug  

#### 对话
例子：  
`setID(...)`  
`setType(...)`  
`setMain(...)`  
`setTextLine(...)`  
`setText(...)`  
`addRequire(...)`  
`addRun(...)`  
`addNext(...)`  
`具体请见前文提供的实例`  

 函数名 | 参数 | 说明
 ---- | ----- | ------  
 setID | ID(S) | 设置对话的ID
 setType | type(S) | 只能够是NO,NONL,JS,MO,YON中的一种,JS按任意键继续,MO按1-9选项继续,YON按Y或N选项继续，NO和NONL没有下一个对话,NONL相对NO而言会提前解除锁定（如果放在开头就是不会锁定玩家），且一个对话链必须以NO、NONL或没有任何可用下一对话的JS、MO结尾
 setMain | \ | 标记该对话为主对话，只有标记为主对话的对话可以用指令/runChain调用
 setTextLine | line(I) | 设置对话文本的行数
 setText | line(I),text(S),delay(I)[0] | 设置文本对应行数的内容，以及到下一行的延迟，delay的单位是毫秒(ms)，支持占位符%PLAYER%表示进行对话的玩家名称
 addRequire | require(S) | 添加**对话触发**的需求，添加require（需求）也是使用函数，但是必须是require（需求）类的函数，写法如`addRequire(require=function(...))`，详细见下文需求
 addRun | run(S),line(I) | 在**对话某一行之后**添加**额外运行**的内容，添加run（额外运行）也是使用函数，但是必须是run（额外运行）类的函数，写法如`addRun(line=1;run=function(...))`，详细见下文额外运行
 addNext | text(S),next(S) | 添加该对话的下一对话，并且提示一段文本，模组内会按照添加顺序自动处理提示文本的按键与顺序，比如JS类对话会显示`ANY text`,YON会显示`Y text`,`N text`,MO会显示`数字 text`,且最多显示和可选9个选项
 
#### 任务
例子：

`setID(...)`  
`addRequire(...)`  
`addRun(...)`  
`具体请见前文提供的实例`  

 函数名 | 参数 | 说明
 ---- | ----- | ------  
 setID | ID(S) | 设置任务的ID
 addRequire | require(S) | 添加**任务完成**的需求，其他同对话一样
 addRun | run(S),runTime(S)[finish] | 在任务开始(runTime=start)或任务结束(runTime=finish)时添加**额外运行**的内容，写法如`addRun(runTime=start;run=function(...))`，其他同对话一样
 
#### 额外运行

 函数名 | 参数 | 说明
 ---- | ----- | ------  
 sendMessage | text(S) | 给进行对话的玩家发送一条消息，支持占位符%PLAYER%表示进行对话的玩家名称，支持变量占位符
 setDialogRunTimes | dialogID(S),value(I) | 设置玩家某一对话的运行次数，*值得一提的是，对话次数在当前对话全部文本播放完后，下一对话之前增加*
 addDialogRunTimes | dialogID(S),value(I) | 增加玩家某一对话的运行次数，不过也可以写负数
 giveItem | item(S),count(I),damage(I),tagnbt(S)[{}] | item为(minecraft:apple)之类的注册名,用**类似于give指令**的方式给予物品
 giveItemByNBT | nbt(S) | 将读取**物品的整个NBT信息**以给予物品，获取物品NBT信息可以见指令`copyItemNBT`，推荐使用这个，因为有些模组和插件会使用`capability`储存信息，用giveItem无法读取`capability`，但是**物品的整个NBT信息**包含`capability`
 removeItem | itemName(S),count(I)[1] | 从玩家背包中扣除count数量的名为itemName的物品，无论物品是否足够都会扣除
 executeCommand | command(S) | 作为后台执行指令，支持占位符%PLAYER%表示进行对话的玩家名称，支持变量占位符
 startTask | taskID(S) | 开始ID为taskID的任务
 addVar | var(S),value(D) | 给变量var增加value
 setVar | var(S),value(D) | 给变量var设置为value
 assignVar | var(S),value(S),max(D)[2147483647],min(D)[-2147483648] | 计算后给变量赋值，支持四则运算与小括号，变量占位符，详见下文占位符
 等你建议··· | ··· | ···

#### 需求

 函数名 | 参数 | 说明
 ---- | ----- | ------  
 dialogRunTimesInRange | dialogID(S),max(I)[2147483647],min(I)[-2147483648] | 只有指定对话的运行次数在min和max之间时才满足需求
 notInDialog | \ | 只有在没有进行对话时才能满足需求，**不要在给非对话链首对话的对话添加该需求**
 taskStartTimesInRange | taskID(S),max(I)[2147483647],min(I)[-2147483648] | 只有当指定任务开始次数在min和max之间时才满足需求
 taskFinishTimesInRange | taskID(S),max(I)[2147483647],min(I)[-2147483648] | 只有当指定任务完成次数在min和max之间时才满足需求
 taskStartint | taskID(S) | 只有当指定任务正在进行时时才满足需求
 hasItem | itemName(S),count(I)[1] | 背包内拥有至少count个名为itemName的物品
 heldItem | itemName(S),count(I)[1] | 主手持有至少count个名为itemName的物品
 varInRange | var(S),max(D)[2147483647],min(D)[-2147483648] | 变量var在min到max之间
 等你建议··· | ··· | ···

#### CustomNPCs任务相关

##### 版权说明

CustomNPCs 采用了 CC BY-NC 3.0 协议，这意味着非商业化地使用其代码是允许的。
对此，非常感激。

##### 正文

 类型 | 函数名 | 参数 | 说明
 ---- | ----- | ----- | ----
 需求 | startingCNPCQuest | questID(I) | 只有当正在进行某个CNPC任务时才满足需求
 需求 | finishedCNPCQuest | questID(I) | 只有曾经完成过某个CNPC任务时才满足需求
 额外运行 | startCNPCQuest | questID(I) | 如果没有的话强行开始某个CNPC任务（无视CNPC中设置的冷却时间、可用次数）
 额外运行 | stopCNPCQuest | questID(I) | 如果有的话强行停止某个CNPC任务（不会给予CNPC中设置的任务奖励）
 
### 变量
`addVar`以及`setVar`比较简单，暂且不讲，`assignVar`才是变量的主要功能，assignVar支持四则运算，使用小括号提高计算优先级，以及使用变量占位符  
例子：  
`assignVar(var=变量;value=(%PLAYER_HEALTH%/%PLAYER_MAXHEALTH%)*100)`  
其返回值便是玩家血量的百分比数值  

 变量占位符 | 说明
 ---- | ----
 %DIALOG_RUN_对话ID% | 指定对话的执行次数
 %TAST_START_任务ID% | 指定任务的开始次数
 %TAST_FINISH_任务ID% | 指定任务的结束次数
 %PLAYER_HEALTH% | 进行对话/任务玩家的当前生命值
 %PLAYER_MAXHEALTH% | 进行对话/任务玩家的最大生命值
 %VAR_变量ID% | 指定变量的值
 等你建议··· | ···
 
### 指令
模组指令一共有六个：  
`runChain (对话ID)`当满足对话要求时开始指定对话（限定为标记了setMain的对话），可以被命令方块执行，可以用**原版execute**给其他玩家执行  
`startTask (任务ID)`当没有进行该任务时开始任务，可以被命令方块执行，可以用**原版execute**给其他玩家执行  
`resetLock`没有权限要求，任何玩家都可以执行，用于防止服务器延迟过高，导致对话结束时解锁失败，可以被命令方块执行，可以用**原版execute**给其他玩家执行  
`resetChainRun (对话ID)`清空对话的运行次数，可以被命令方块执行，可以用**原版execute**给其他玩家执行  
`reloadDialog`重载对话、任务、实体文件，可以由命令方块执行  
`copyItemNBT`能且仅能由**玩家管理员游戏内执行**，会将物品的NBT表达复制到使用指令的玩家的剪切板上（可能会由于网络延迟导致失败）  
