# VibeCheck (MoodSync) 🚀

O VibeCheck é um aplicativo Android moderno desenvolvido com **Jetpack Compose** e **Material Design 3** que recomenda atividades, filmes ou músicas com base no seu humor atual. O aplicativo apresenta uma interface dinâmica que altera seu tema e gradientes de fundo para combinar com a "vibe" da sua seleção.

## ✨ Funcionalidades

- **Grade de Seleção de Humor**: Escolha entre diferentes humores como Energético, Relaxado (Chill), Criativo ou Melancólico.
- **Tematização Dinâmica**: Todo o esquema de cores e gradientes de fundo do app são atualizados instantaneamente com base no humor selecionado.
- **Recomendações Inteligentes**: Receba sugestões personalizadas de atividades, músicas ou mídia.
- **Shuffle Vibe (Embaralhar)**: Não gostou da sugestão atual? Toque no botão de embaralhar para obter uma nova instantaneamente.
- **Experiência Edge-to-Edge**: Interface totalmente imersiva que respeita as barras do sistema e oferece um visual moderno.
- **Ícone Adaptativo**: Ícone do app projetado sob medida que se integra perfeitamente ao sistema de ícones adaptativos do Android.

## 🛠️ Tecnologias Utilizadas

- **Linguagem**: [Kotlin](https://kotlinlang.org/)
- **Framework de UI**: [Jetpack Compose](https://developer.android.com/jetpack/compose)
- **Sistema de Design**: [Material Design 3](https://m3.material.io/)
- **Carregamento de Imagens**: [Coil](https://coil-kt.github.io/coil/)
- **Arquitetura**: Gerenciamento de estado reativo usando `mutableStateOf` e `remember`.

## 📸 Capturas de Tela

| Humor: Energético | Humor: Relaxado |
|:---:|:---:|
| ![Energético](https://github.com/user-attachments/assets/screenshot_energetic.png) | ![Relaxado](https://github.com/user-attachments/assets/screenshot_chill.png) |

*(Nota: Substitua os links de espaço reservado pelas capturas de tela reais se hospedadas no GitHub)*

## 🚀 Como Começar

1. Clone o repositório:
   ```bash
   git clone https://github.com/seuusuario/MoodSync.git
   ```
2. Abra o projeto no **Android Studio (Ladybug ou mais recente)**.
3. Sincronize o Gradle e execute o app em um emulador ou dispositivo físico.

## 📂 Estrutura do Projeto

- `br.ordnavile.moodsync.data`: Contém os modelos de dados (`Mood`, `Recommendation`) e o `DataSource`.
- `br.ordnavile.moodsync.ui.theme`: Implementação do tema Material 3 personalizado com suporte a cores dinâmicas.
- `MainActivity.kt`: O ponto de entrada principal e toda a implementação da UI usando funções Composable.

## 📄 Licença

Este projeto está licenciado sob a Licença MIT - consulte o arquivo [LICENSE](LICENSE) para mais detalhes.


