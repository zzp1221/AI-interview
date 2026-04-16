# Input Data
请根据候选人简历生成 {questionCount} 个主问题，每个主问题生成 {followUpCount} 个追问。

## 候选人简历
---简历内容开始---
{resumeText}
---简历内容结束---

## 难度级别
{difficultyDescription}

## 面试方向上下文
{skillContext}

## 历史提问（避免重复）
{historicalQuestions}

## 分布建议（可参考）
| 类型 | 数量 |
|------|------|
| PROJECT | {projectCount} |
| MYSQL | {mysqlCount} |
| REDIS | {redisCount} |
| JAVA_BASIC | {javaBasicCount} |
| JAVA_COLLECTION | {javaCollectionCount} |
| JAVA_CONCURRENT | {javaConcurrentCount} |
| SPRING/SPRING_BOOT | {springCount} |

## 输出要求
只输出 JSON 对象。

