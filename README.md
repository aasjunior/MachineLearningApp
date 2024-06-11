# Machine Learning App

<p align="justify">
O Machine Learning App é um aplicativo desenvolvido com Kotlin, Android Studio e Jetpack Compose, para aplicação de algoritmos de aprendizado de máquina e exibição dos resultados.
</p>

<p align="center">
  <p align="center">
    <img src="img.png" width="250"/>
  </p>
</p>
<br>

## Pré-Requisitos

* JDK (Java Development Kit)
* Git
* Android Studio
* VSCode
<br>

## Dependências

Este projeto depende da seguinte APIs:

* [mlapp-api](https://github.com/aasjunior/mlapp-api.git)

## Configuração do Projeto

1. Clone o repositório para sua máquina local usando o seguinte comando
```
git clone https://github.com/aasjunior/MachineLearningApp.git
```

2. Abra o projeto pelo Android Studio

3. Certifique-se de que o JDK está instalado e configurado corretamente

4. Certifique-se de que o Mongodb está instalado e funcionando corretamente

5. Configure a API mlapp-api pelo VS Code ou IDE de sua preferência. A API estará rodando em `http://127.0.0.1:8000`

6. Execute o Emulador do Android Studio
<br>

## Problemas Conhecidos

### Erro de Caracteres Não-ASCII 

Se você encontrar um erro relacionado a caracteres não-ASCII durante a execução ou compilação do projeto, existem duas soluções possíveis:

1. **Mover o projeto para um diretório diferente**: Certifique-se de que o novo diretório não contém caracteres não-ASCII no caminho.

2. **Adicionar uma linha ao arquivo gradle.properties**: Você pode adicionar a linha 'android.overridePathCheck=true' ao arquivo gradle.properties no diretório do projeto. Isso desativará a verificação do caminho do projeto.
<br>

## UI
A interface do usuário deste aplicativo foi construída usando **Jetpack Compose** e **Material 3**.

O Jetpack Compose é uma moderna toolkit de UI para Android que simplifica e acelera o desenvolvimento da interface do usuário. Ele permite a criação de interfaces de usuário concisas e idiomáticas com menos código e ferramentas poderosas para visualização de layout.

<div>O Material 3 é a mais recente versão do Material Design, que introduz novos componentes, estilos e recursos para ajudar a criar experiências de usuário mais expressivas e dinâmicas.
<br> </div><br>

#### Tecnologias

<br>

<div align="center">
   <img src="https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white" alt="Android"/>
   <img src="https://img.shields.io/badge/kotlin-%237F52FF.svg?style=for-the-badge&logo=kotlin&logoColor=white"/>
   <img src="https://img.shields.io/badge/Jetpack%20Compose-FF4081?style=for-the-badge&logo=jetpack&logoColor=white" alt="Jetpack Compose"/>
   <img src="https://img.shields.io/badge/Python-3776AB?style=for-the-badge&logo=python&logoColor=white" alt="Python"/>
   <img src="https://img.shields.io/badge/FastAPI-005571?style=for-the-badge&logo=fastapi" alt="FastAPI"/>
   <img src="https://img.shields.io/badge/Scikit_Learn-F7931E?style=for-the-badge&logo=scikit-learn&logoColor=white" alt="Scikit-Learn"/>
</div>


##
###### Aviso
Este é um trabalho acadêmico realizado como tarefa da disciplina de Laboratório Mobile/Computação Natural no 5º Semestre de Desenvolvimento de Software Multiplataforma
