# Trino 479 Connection Control Plane (Gradle Skeleton)

这是一个用于 **Trino 连接配置管控** 的可扩展系统骨架，包含：

- 基于 Gradle 的 Spring Boot 后端
- 可扩展的连接策略校验插件接口（`ConnectionPolicyValidator`）
- REST API（创建/列表/删除/预检）
- 基础 UI 页面（`/index.html`）用于配置连接
- Kubernetes 部署样例清单

## 项目结构

```text
.
├── control-plane                 # Spring Boot 应用
│   ├── src/main/java/...         # API + Service + 插件式校验
│   └── src/main/resources/static # 简单 UI
└── deploy/k8s                    # K8s 部署模板
```

## 本地运行

```bash
./gradlew :control-plane:bootRun
```

访问：

- UI: `http://localhost:8080/`
- OpenAPI: `http://localhost:8080/swagger-ui.html`

## 扩展建议

1. 将 `InMemoryTrinoConnectionRepository` 替换为 PostgreSQL + Flyway。
2. 引入 RBAC 与 SSO（OIDC）以支持多租户团队隔离。
3. 通过策略模块对接审批流（如保存连接前必须审批）。
4. 在 dry-run 中加入真实 Trino connectivity check（可选短超时）。
5. 将 `attributes` 结构化为 typed config 并按 authMode 动态渲染 UI 表单。

## 与 Trino 479 对齐

- 认证模式示例包含：`NONE` / `PASSWORD` / `JWT` / `KERBEROS`
- 可继续扩展 validator 以对接组织内安全基线（TLS/allowlist/catalog policy）
