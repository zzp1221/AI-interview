# AI系统调用流程思维导图

## 一、核心机制与调用流程

- 核心机制
    - 关键点
        - 格式指令由业务代码通过 `systemPrompt + "\n\n" + outputConverter.getFormat()` 拼接到系统提示词后传入
        - AI 响应由 `ChatClient.call().entity(outputConverter)` 经 `BeanOutputConverter` 解析为指定的 Java Record 类型
    - 调用流程
        - 请求构建流程（自左至右）
            - 加载提示词
            - 填充变量
            - 调用 ChatClient
        - 响应处理流程（自右至左）
            - 获取 AI 响应
            - 解析响应
            - 转换业务对象

## 二、流程说明
### 核心机制

- **格式指令拼接方式**  
  系统提示词（`systemPrompt`）通过字符串拼接操作，附加由 `outputConverter.getFormat()` 提供的格式定义，中间以双换行符 `\n\n` 分隔。该设计确保AI模型在响应时遵循预设结构，便于后续解析。

- **响应解析路径**  
  AI返回的原始响应经由 `ChatClient.call().entity(outputConverter)` 调用链处理，最终由 `BeanOutputConverter` 将JSON-like结构转换为Java Record类型实例，实现类型安全的数据映射。

### 调用流程双向逻辑

| 流程方向 | 阶段 | 动作描述 |
| --- | --- | --- |
| 自左至右 | 请求构建 | 构造并发送AI请求前的准备步骤 |
| 自右至左 | 响应处理 | 接收并转化AI响应后的后置处理步骤 |

#### 请求构建流程（上行）

- **加载提示词**：初始化阶段读取基础提示模板
- **填充变量**：注入上下文动态参数，完成个性化定制
- **调用 ChatClient**：触发远程服务请求，进入AI处理环节

#### 响应处理流程（下行）

- **获取 AI 响应**：接收来自AI服务的原始输出数据
- **解析响应**：执行结构化解析，提取有效字段内容
- **转换业务对象**：将中间数据模型映射为领域专用的Java Record，供业务层直接使用
