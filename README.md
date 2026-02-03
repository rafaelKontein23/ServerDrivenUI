# Server-Driven UI (SDUI) Engine 🚀

Uma engine robusta e flexível para renderização de interfaces Android nativas a partir de configurações dinâmicas do **Firebase Remote Config**.

## 📌 Sobre o Projeto

Esta biblioteca permite que a interface do aplicativo seja alterada em tempo real, sem a necessidade de uma nova publicação na Google Play Store. A engine interpreta payloads JSON e os converte em componentes nativos de alta performance.

### 🏗️ Arquitetura e Tecnologias
O projeto segue os princípios de código limpo e escalável:
* **MVVM (Model-View-ViewModel):** Separação clara entre lógica de UI e dados.
* **Clean Architecture:** Camadas bem definidas para facilitar a manutenção e testes.
* **Kotlin & Coroutines:** Para processamento assíncrono e código conciso.
* **Firebase Remote Config:** Fonte de dados para os layouts dinâmicos.

---

## 🛠️ O que contém no projeto?

### Componentes Suportados
* **Text:** Suporte a fontes customizadas (**Poppins**) e estilos dinâmicos (Bold, Italic).
* **Button:** Ações configuráveis e suporte a acessibilidade.
* **Input:** Campos de entrada com validações.
* **Compound Text:** Combinações de textos com diferentes estilos em uma única linha.

### Tipografia Inteligente
A engine utiliza o `SduiFontCache` para garantir que a fonte **Poppins** seja carregada uma única vez na memória, otimizando a performance de renderização.

---

## 🚦 Telas Disponíveis (`SduiScreen`)

Atualmente, a engine está mapeada para gerenciar as seguintes telas via Remote Config:

| Tela | Chave do Remote Config | Status |
| :--- | :--- | :--- |
| **LOGIN** | `ds_login_layout` | ✅ Implementado |
| **FORGOT_PASSWORD** | `ds_forgot_password_layout` | 🚧 Em desenvolvimento |
| **REGISTER** | `ds_register_layout` | ⏳ Planejado |
| **HOME** | `ds_home_layout` | ⏳ Planejado |
| **MESSAGE** | `ds_message_layout` | ⏳ Planejado |

---

## 🚀 Como usar

### 1. Adicionar o Repositório
No seu arquivo `settings.gradle.kts`, adicione o JitPack:

```kotlin
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("[https://jitpack.io](https://jitpack.io)") }
    }
}
```
### 2. Adicionar a Dependência
No `build.gradle.kts` (ou `build.gradle`) do seu módulo **app**, adicione a referência à biblioteca:

```kotlin
dependencies {
    implementation("com.github.rafaelKontein23:sdui-engine:1.0.0")
}
```

## ⚡ Ações e Interatividade
A engine permite mapear cliques e interações através de um sistema de actionId. Você deve implementar a lógica de resposta na sua View:

```kotlin
private fun handleSduiActions(actionId: String) {
    when (actionId) {
        "btn_login" -> { /* Lógica de Autenticação */ }
        "tv_register" -> {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
        "tv_forgot_password" -> { /* Navegação para Recuperação */ }
    }
}
```

## 🎨 Design System & Tipografia
O projeto utiliza a fonte **Poppins** como padrão global para garantir consistência visual e modernidade na interface.

* **Configuração Automática:** A engine utiliza um arquivo de família (`poppins_family.xml`) que gerencia os diferentes pesos da fonte.
* **Negrito Dinâmico:** Para aplicar negrito via JSON, basta enviar `"textStyle": "bold"`. A engine resolve o peso corretamente utilizando o arquivo de fonte correspondente de forma nativa.
* **Performance (Anti-Jitter):** O uso do `SduiFontCache` evita atrasos na renderização e "pulos" de texto ao carregar fontes customizadas, mantendo a fluidez da interface.

---

## 📄 Licença
Este projeto é de uso interno e pedagógico, focado no treinamento de arquiteturas avançadas de Android e sistemas de **Server-Driven UI**.