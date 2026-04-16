# Input Data
请生成共 {questionCount} 个主问题。
每个主问题必须生成 {followUpCount} 个追问。

## 方向上下文
{skillContext}

## 难度级别
{difficultyDescription}

## 问题分布要求
| 类型 | 数量 |
|------|------|
| PROJECT | {projectCount} |
| MYSQL | {mysqlCount} |
| REDIS | {redisCount} |
| JAVA_BASIC | {javaBasicCount} |
| JAVA_COLLECTION | {javaCollectionCount} |
| JAVA_CONCURRENT | {javaConcurrentCount} |
| SPRING/SPRING_BOOT | {springCount} |

## 简历内容（可用于贴合候选人经历）
---简历内容开始---
{resumeText}
---简历内容结束---

## 历史提问（尽量避免重复）
{historicalQuestions}

## 输出要求
只输出 JSON 对象。

