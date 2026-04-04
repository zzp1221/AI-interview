# RAG问答框架

## 1. 整体架构概述

本系统是一个基于 **Spring Boot + Spring AI** 技术栈构建的 **RAG（Retrieval-Augmented Generation，检索增强生成）知识库问答系统**，采用典型的四层分层架构设计，旨在实现高效、智能的知识管理和问答能力。系统通过将非结构化文档内容进行解析、向量化并存储于专用向量数据库中，在用户发起提问时执行语义检索，并结合大语言模型（LLM）生成高质量回答，从而提升问答的准确性与上下文相关性。

### 技术栈
| 组件类别 | 技术/框架 |
|--------|---------|
| 核心框架 | Spring Boot, Spring AI |
| 向量数据库 | PostgreSQL + pgvector |
| 文档解析 | Apache Tika |
| 对象存储 | HuskyFS（疑似OCR识别为RustFS） |
| 消息队列 | Redis Stream |
| LLM 接口封装 | Spring AI ChatClient |
| 向量抽象层 | VectorStore |
| 实体映射 | MapStruct |

### 设计思想
- **分层架构（Layered Architecture）**：严格划分表现层、业务层、领域层和基础设施层，确保关注点分离。
- **高内聚低耦合**：每个服务模块职责单一，便于独立开发、测试与部署。
- **可扩展性**：支持新增文档类型、接入不同LLM、扩展新的管理功能（如简历、面试管理）。
- **异步处理**：利用Redis Stream实现异步消息传递，提高文件上传与向量化处理的响应效率。
- **面向AI集成**：通过Spring AI统一调用LLM，屏蔽底层模型差异，提升AI能力的可移植性。

### 主要功能
| 功能模块 | 功能说明 |
|--------|--------|
| 知识库管理 | 支持文档上传、解析、向量化、查询与列表展示 |
| RAG 聊天 | 用户可通过自然语言提问，系统返回基于知识库的精准答案 |
| 会话管理 | 维护用户对话上下文，支持多轮对话 |
| 简历管理 | 支持简历文件上传与结构化解析 |
| 面试管理 | 提供面试流程的数据支撑 |
| 统计分析 | 提供知识库使用情况的统计指标 |

---

## 2. 详细的分层架构描述

### 2.1 表现层（Presentation Layer）

表现层负责接收外部HTTP请求，提供RESTful API接口，是系统的前端入口。该层由多个控制器组成，分别对应不同的业务场景。

| 编号 | 控制器名称 | 功能说明 |
|-----|-----------|--------|
| 1 | KnowledgeBaseController | 知识库管理 API，处理知识库文档的上传、更新、删除等操作 |
| 2 | RagChatController | RAG 聊天 API，接收用户问题，触发问答流程，返回生成的回答 |
| 3 | ResumeController | 简历管理 API，处理简历文件的上传与基本信息获取 |
| 4 | InterviewController | 面试管理 API，管理面试相关的配置与会话数据 |

> 注：所有控制器均运行在Spring MVC环境下，通过@RequestMapping注解暴露端点。

---

### 2.2 业务层（Service Layer）

业务层是系统的核心逻辑处理单元，封装了具体的业务规则和服务流程，协调领域模型与基础设施组件之间的交互。

| 编号 | 服务模块名称 | 功能说明 |
|-----|-------------|--------|
| 1 | KnowledgeBaseUploadService | 文件上传与解析服务，调用Apache Tika提取文本内容 |
| 2 | KnowledgeBaseVectorService | 向量化与检索服务，将文本转换为嵌入向量，并执行相似度匹配 |
| 3 | KnowledgeBaseQueryService | RAG 问答服务，整合检索结果与LLM生成逻辑，完成最终回答生成 |
| 4 | RagChatSessionService | 会话管理服务，维护用户的聊天会话状态与历史消息 |
| 5 | KnowledgeBaseListService | 列表查询服务，支持分页查询知识库中的文档条目 |
| 6 | KnowledgeBaseCountService | 统计分析服务，提供知识库文档数量、访问频次等统计信息 |
| 7 | ResumeUploadService | 简历上传服务，专门处理简历类文件的上传与初步解析 |

> 该层服务通过Spring的@Service注解声明，彼此之间可通过接口调用协作。

---

### 2.3 领域层（Domain Model）

领域层包含系统的核心业务实体（Entity）和数据访问接口（Repository），体现了领域驱动设计（DDD）的思想，定义了持久化的数据结构和基本的数据操作契约。

#### 实体类（Entities）

| 编号 | 实体名称 | 功能说明 |
|-----|--------|--------|
| 1 | KnowledgeBaseEntity | 知识库实体，表示上传文档的元数据与内容索引信息 |
| 2 | RagChatSessionEntity | 会话实体，记录一次RAG对话的会话ID、用户标识、创建时间等 |
| 3 | RagChatMessageEntity | 消息实体，存储每一条用户输入与系统回复的内容 |
| 4 | ResumeEntity | 简历实体，表示简历文件的结构化数据 |
| 5 | InterviewSessionEntity | 面试实体，用于保存面试过程的相关信息 |

#### 仓库接口（Repositories）

| 编号 | 仓库名称 | 功能说明 |
|-----|--------|--------|
| 1 | KnowledgeBaseRepository | 知识库数据访问接口，用于CRUD操作知识库文档 |
| 2 | VectorRepository | 向量数据访问接口，支持向量的存储与相似度检索 |
| 3 | RagChatSessionRepository | 会话数据访问接口，管理会话生命周期 |

> 这些仓库通常由Spring Data JPA或自定义DAO实现，对接PostgreSQL和pgvector。

---

### 2.4 基础设施层（Infrastructure Layer）

基础设施层提供系统运行所依赖的底层技术组件和第三方服务，是整个架构的基石。

| 编号 | 组件名称 | 功能说明 |
|-----|--------|--------|
| 1 | PostgreSQL + pgvector | 向量数据库，用于存储文档文本及其对应的向量表示，支持高效的近似最近邻搜索（ANN） |
| 2 | Redis Stream | 异步消息队列，用于解耦文件上传与耗时的向量化处理过程，提升系统吞吐量 |
| 3 | HuskyFS | 对象存储服务，用于持久化存储原始文件（如PDF、Word等文档） |
| 4 | Spring AI ChatClient | LLM 调用框架，封装对大语言模型（如OpenAI、通义千问等）的标准调用接口 |
| 5 | Apache Tika | 文档解析工具，支持多种格式（PDF、DOCX、PPT等）的文本内容抽取 |
| 6 | VectorStore | 向量存储抽象层，提供统一的API访问不同类型的向量数据库，增强可替换性 |
| 7 | MapStruct | 实体映射工具，简化Java对象之间的转换（如DTO ↔ Entity） |

> 该层实现了与外部系统的解耦，提升了系统的可维护性和可测试性。

---

## 3. 各层之间的调用关系和协作流程

系统遵循典型的自上而下调用模式，即：**表现层 → 业务层 → 领域层 → 基础设施层**。以下是两个典型业务流程的调用链路分析：

### 流程一：知识库文档上传与向量化

1. 用户通过 `KnowledgeBaseController` 上传文档。
2. 控制器调用 `KnowledgeBaseUploadService` 处理上传逻辑。
3. `KnowledgeBaseUploadService` 使用 `Apache Tika` 解析文档内容，并将原始文件存入 `HuskyFS`。
4. 解析后的文本传递给 `KnowledgeBaseVectorService`。
5. `KnowledgeBaseVectorService` 调用 `Spring AI ChatClient` 获取文本嵌入向量。
6. 向量通过 `VectorStore` 写入 `PostgreSQL + pgvector`。
7. 元数据保存至 `KnowledgeBaseEntity` 并通过 `KnowledgeBaseRepository` 持久化。
8. 可选地，通过 `Redis Stream` 发送异步事件通知后续处理模块。

### 流程二：RAG 问答交互

1. 用户通过 `RagChatController` 提交问题。
2. `RagChatController` 调用 `KnowledgeBaseQueryService`。
3. `KnowledgeBaseQueryService` 委托 `KnowledgeBaseVectorService` 执行语义检索：
   - 将用户问题向量化；
   - 在 `VectorRepository` 中查找最相似的知识片段。
4. 检索结果与问题一起传入 `Spring AI ChatClient`，生成自然语言回答。
5. 回答内容与上下文由 `RagChatSessionService` 管理，并通过 `RagChatMessageEntity` 和 `RagChatSessionRepository` 持久化。
6. 最终回答返回给前端。

> 所有跨层调用均通过接口隔离，保证了松耦合和可测试性。

---

## 4. 架构设计的特点和优势分析

| 特性维度 | 优势说明 |
|--------|--------|
| **清晰的分层结构** | 四层架构职责分明，降低系统复杂度，有利于团队分工协作与代码维护 |
| **良好的可扩展性** | 新增功能（如面试管理）只需增加对应Controller与Service即可；可轻松接入新类型的LLM或向量数据库 |
| **高可用与高性能** | 采用Redis Stream实现异步处理，避免阻塞主线程，提升并发处理能力 |
| **AI原生集成** | 基于Spring AI构建，天然支持主流LLM厂商接口，具备模型无关性 |
| **文档兼容性强** | 使用Apache Tika支持多种文档格式解析，适用于企业级知识库建设 |
| **语义检索精准** | 利用pgvector在PostgreSQL中实现向量检索，避免引入额外中间件，简化运维 |
| **对象存储解耦** | 原始文件独立存储于HuskyFS，保障主数据库轻量化 |
| **会话上下文管理** | 支持多轮对话，提升用户体验 |
| **统一抽象层** | `VectorStore` 和 `MapStruct` 的使用增强了系统的可配置性和可替换性 |
