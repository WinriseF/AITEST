## 鸿蒙开发后端文档
前端代码参考 [https://github.com/WinriseF/AITESTH.git](https://github.com/WinriseF/AITESTH.git)
### Config
ChatConfig 是一个配置类，用于存储和管理聊天相关的配置项

TaskPoolConfig 自定义线程池配置类，用于管理任务执行的线程池,将耗时任务从主请求线程中剥离，交给一个独立的后台线程池去执行

SecurityConfig 使用JWT来实现基于token的认证和授权

JwtAuthenticationFilter 继承自OncePerRequestFilter，用于在每次请求时验证JWT的有效性
### Controller
ChatController 作为应用程序的web入口，处理请求并提供与大模型的交互，提供两种请求方式：普通和流式

ExceptionController 处理全局异常，提供统一的错误响应

HelloController 作为一个简单的测试接口，返回一个欢迎消息

QuestionController 协调 AI、业务逻辑和数据库，以实现题目的生成与查询，包括异步生成题目和查询题目列表

QuizBankController 处理题库相关的请求，创建题库和查询题库

### Dao
Dao中使用了spring Data JPA 提供的接口，简化了数据库操作

QuestionDao 提供对题目数据的访问和操作

QuizBankDao 提供对题库数据的访问和操作

### Dto
GenerateQuestionDto 用于生成题目的请求数据传输对象，包含题库ID和题目数量等

### Entity
QuestionEntity 代表题目实体，包含题目的内容、答案、解析等信息

QuizSet 代表题库实体，包含题库的ID等