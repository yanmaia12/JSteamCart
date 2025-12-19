#  Steam Budget Tracker

Uma aplicação de linha de comando (CLI) em Java que ajuda gamers a gerenciar o orçamento de compras, consultando preços reais através das APIs da **CheapShark** e da **Steam Store**.

##  Funcionalidades

- **Busca em Tempo Real:** Localiza jogos e seus IDs utilizando a API da CheapShark.
- **Consulta de Preços:** Obtém o valor atualizado (com descontos) diretamente da loja da Steam.
- **Carrinho Inteligente:** Permite adicionar múltiplos jogos e calcula o subtotal automaticamente.
- **Gerenciamento de Orçamento:**
    - Verifica se o seu saldo é suficiente.
    - Caso falte dinheiro, sugere a remoção de itens.
    - Permite remover jogos específicos pelo nome para ajustar a conta.

##  Tecnologias Utilizadas

- **Linguagem:** Java (JDK 17+)
- **HTTP Client:** `java.net.http` (Nativo do Java)
- **Processamento JSON:** Biblioteca Jackson (Core, Databind, Annotations)
- **Conceitos Aplicados:**
    - Consumo de APIs REST
    - Java Records
    - Collections & Streams API
    - Manipulação de Arquivos e Erros
    - Programação Orientada a Objetos

## Como Rodar o Projeto

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/yanmaia12/JSteamCart.git