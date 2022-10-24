# ORDERS

Aplicação responsável pelos orders

## Sumário

- [PAYMENTS](#payments)
    - [Sumário](#sumário)
    - [Pré requisitos](#pré-requisitos)
        - [Instalando o JDK com SDKMAN](#instalando-o-jdk-com-sdkman)
        - [Criando ambiente de desenvolvimento](#criando-ambiente-de-desenvolvimento)
        - [Instalando o Docker](#instalando-o-docker)

## Pré requisitos

Recomendamos que você possua os seguintes requisitos, antes de executar o projeto:

- JDK 17.
- [Docker](https://docs.docker.com/engine/install/) (Para desenvolvimento)

### Instalando o JDK com SDKMAN

O SDKMAN é um gerenciador de versões para Java, Gradle, Groovy e etc. Para instala-lo e em seguida o JDK siga os passos:

```bash
curl -s "https://get.sdkman.io" | bash
```

Para listar as opções de JDK:

```bash
sdk list java
```

Após instalação instale o JDK de sua preferência:

```bash
sdk install java 17.0.1-zulu
```

Referência: [https://sdkman.io/](https://sdkman.io/).

### Criando ambiente de desenvolvimento

### Instalando o Docker

Para instalar o Docker acesse:

Referências:
[Install Docker Engine on Debian](https://docs.docker.com/engine/install/debian/)
[Install Docker Engine on Ubuntu](https://docs.docker.com/engine/install/ubuntu/)

## Git Flow

Optamos por seguir o modelo de fluxo de trabalho para desenvolvimento de software **Git Flow**.

No Git do projeto existem os branchs:

```text
master: todas as features que irão para produção
develop: todas as features, em desenvolvimento, que irão para homologação
```

Para realizar o build: 'docker build -t orders-api .'
Após realizar o build da imagem:  'docker run orders-api'
