<p align="center">
   <img src="https://github.com/luizlopes12/sofisticao-app/assets/104948713/bb13d35c-9d14-4539-8d21-bdf765d5d2c9" align="center" height="200">
</p>

<div align="center">
   
   ![GitHub](https://img.shields.io/github/license/luizlopes12/sofisticao-app)
   ![GitHub language count](https://img.shields.io/github/languages/count/luizlopes12/sofisticao-app)
   ![GitHub last commit](https://img.shields.io/github/last-commit/luizlopes12/sofisticao-app)

</div>

<p align="center">Sofisticão App</p>

___

# Tecnologias utilizadas
<img src="https://img.shields.io/badge/Kotlin-0095D5?&style=for-the-badge&logo=kotlin&logoColor=white">

___

# Guia de Execução - Aplicação Mobile e API

Este guia fornece instruções passo a passo sobre como configurar e executar a aplicação mobile e a API associada. Certifique-se de seguir todas as etapas para garantir uma execução suave do projeto.

## Requisitos Prévios

Antes de começar, certifique-se de ter as seguintes ferramentas instaladas em seu sistema:

- **IntelliJ IDEA**: A aplicação mobile é desenvolvida em Kotlin, e recomendamos o uso da IntelliJ IDEA como a IDE preferencial. Faça o download e instalação a partir do [site oficial](https://www.jetbrains.com/idea/).

___

## Configurando a Aplicação Mobile

1. Abra o IntelliJ IDEA.

2. Clone o repositório do projeto:

   ```bash
   git clone https://github.com/luizlopes12/sofisticao-app.git
   ```

3. Abra o projeto na IntelliJ IDEA.

4. Aguarde a IDE sincronizar as dependências do projeto.

5. Certifique-se de ter o emulador Android configurado ou um dispositivo físico conectado.

6. Configure o Retrofit BUilder e XML de rede, para utilizar o IP de sua máquina:

  - ### Retrofit Builder
    No arquivo `ProductsService.kt`, em `app/src/main/java/com/example/app/services/ProductsService.kt`, encontre a configuração do Retrofit Builder. Substitua o endereço base pela IP da sua máquina. Por exemplo:
    ```kotlin
     object RetrofitClient {
    private const val BASE_URL = "http://SEU_IP_AQUI"

    // instância do Retrofit
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    }
    ```

- ### Arquivo de Configuração de Rede XML do Kotlin
  No arquivo de configuração de rede `network_security_config.xml` do Kotlin, em `app/src/main/res/xml/network_security_config.xml`, ajuste as configurações de URL para usar o IP da sua máquina. Por exemplo:
  ```xml
  <network-security-config>
    <base-config cleartextTrafficPermitted="true">
        <domain includeSubdomains="true">SEU_IP_AQUI</domain>
        <!-- ... outras configurações ... -->
    </base-config>
  </network-security-config>
  ```
  Certifique-se de substituir "SEU_IP_AQUI" pelo IP real da sua máquina.

7. Execute a aplicação mobile.

___

## Configurando a API

1. Abra o IntelliJ IDEA.

2. Clone o repositório da API:

   ```bash
   git clone https://github.com/MateusOK/ApiSofisticao.git
   ```

3. Abra o projeto na IntelliJ IDEA.

4. Aguarde a IDE sincronizar as dependências do projeto.

5. Certifique-se de colocar as credenciais do MongoDB Sofisticao, em `src/main/resources/application.yml`.

6. Execute a API.

___

## Observações importantes

- Certifique-se de que as portas necessárias (8080 para a API) estejam disponíveis em sua máquina.
- Certifique-se de que as credenciais do mongoDB estão corretamente configuradas. Elas estão disponíveis no arquivo `custom.properties`.

Ao seguir essas etapas, você deve ser capaz de executar tanto a aplicação mobile quanto a API em seu ambiente de desenvolvimento. Se encontrar problemas durante a execução, consulte a documentação do projeto ou entre em contato com a equipe de desenvolvimento para obter assistência.

___

## Ilustrações do projeto

<div align="center">

   ### Home

   <img src="https://github.com/luizlopes12/sofisticao-app/assets/104948713/1d999b34-8f73-4eac-9611-e1dc34b5f4b0" height="800">


   ### Shop

   <img src="https://github.com/luizlopes12/sofisticao-app/assets/104948713/eb48492d-eda3-46fc-8d19-8ecf997c3021" height="800">

   
   ### Produto
   
   <img src="https://github.com/luizlopes12/sofisticao-app/assets/104948713/8e8eb347-9e7f-4fef-acd4-783710904678" height="800">
</div>

