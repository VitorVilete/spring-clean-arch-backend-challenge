# Clean Architecture Backend Challenge

## Instruções
### Executando este projeto
IMPORTANTE: Para execução desse projeto, é necessário ter o Docker instalado! Este projeto sobe uma instância um container com Zipkin via Docker Compose.

O arquivo "main" dessa aplicação pode ser encontrado em "backendchallenge-presentation/src/main/java/br/com/vilevidya/BackendChallengeApplication.java".

Suba a aplicação BackendChallengeApplication na sua IDE de preferência ou pelo "mvn spring-boot:run"

Uma vez que a aplicação estiver online, você pode ouví-la na porta 8080. O endpoint separado para o teste é o caminho PUT /produtos.

O Zipkin pode ser acessado em http://localhost:9411/zipkin/
O H2 console pode ser acessado em http://localhost:8080/h2-console/

## Sobre a solução
### Clean Architecture Base
Este projeto foi desenvolvido usando "The Clean Architecture", originalmente escrita por Robert C. Martin (Uncle Bob).

Abaixo uma imagem comparando a proposta de Robert versus a minha proposta de arquitetura deste projeto.

![Screenshot of the base diagrams used for this project](./assets/images/clean_arch_base.png)

### Clean Architecture Detalhe
Abaixo está um diagrama com uma estimativa do detalhe de como cada layer da arquitetura ficará ao final do projeto.

Obs.: eu esbocei esse diagrama nas primeiras horas que eu recebi o teste e eu vou manter neste estado inicial pra ilustrar o entendimento inicial e a evolução do projeto durante a execução.

![Screenshot of the detailed diagrams](./assets/images/layers_detailed.png)

#### Presentation Layer
No nosso projeto, esta camada irá conter classes que nos ajudarão a lidar com HTTP(Request/Response) e código de framework(spring-starter-web, actuator, validator, aop, zipkin). Trata a requisição e delega para a camada de Application via UseCase a fim de receber uma response.

#### Infrastructure Layer
Esta camada terá a responsabilidade de manter os Interface Adapters(DBContext, Repository) longe das outras camadas. No nosso caso, essa camada expõe uma interface para a camada de Application, que recebe sua implementação por injeção de dependência.

#### Application Layer
Esta camada manterá os "Use Cases" separados da camada de Domain. Um Use Case é a representação de um fluxo específico do negócio. É onde as regras de negócio serão processadas. Traduzindo, é um caso de uso.

#### Domain Layer
Por último, esta camada irá conter as definições de negócio.

### Clean Architecture No Projeto
Este projeto está estruturado em um esquema maven multi-module, que consiste na existência de um pom.xml parent e um pom.xml para cada module(child).
O intuito é fazer com que as camadas tenham apenas o acoplamento necessário para funcionar da forma mais independente possível.

### Funcionalidades
#### Salva & Atualiza Produtos de Seguros
PUT /produtos é o endpoint exposto para inserção e atualização de um Produto de Seguro. A inserção ou atualização é feita conforme as regras do desafio e o retorno também.
O endpoint também retorna um UUID no campo "id", que também é salvo no banco de dados.

Não entendi muito bem o sentido do UUID, uma vez que ele é um parâmetro opcional na API.
Como ele não é obrigatório, fiz uma chave composta por "nome" e "categoria" para evitar duplicidade com o .

Se você tentar salvar um Produto com "nome" e "categoria" igual a um que já existe, a API vai atualizar o registro já existente e vai devolver o UUID que foi salvo na primeira inserção.

#### Busca Categoria de Produto
Este caso de uso não tem um endpoint exposto e serve para realizar uma busca no "catálogo" de categorias de produto e retornar os impostos relacionados à categoria.
Inicialmente eu tinha criado uma entity pra guardar isso no banco de dados mas ficou desnecessário.
Por fim, substituí o Repository original que consultava o BD por um Repository local que guarda uma lista estática das categorias e busca a categoria desejada dentro da lista.

#### Observabilidade (métricas, traces e logs)
Este projeto utiliza o Observation API do pacote spring-boot-starter-aop para observar alguns pontos interessantes do projeto e traçar métricas.

Neste projeto, eu estou usando o Zipkin para colher os dados desses métodos. 
Estou colhendo os dados de execução da Controller e todos os Use Cases que estão sendo chamados.

Os logs estão formatados e estão sendo logados no console da aplicação e em um arquivo de texto.

É possível realizar o tracing das chamadas da aplicação uma vez que os logs possuem um spanID e parentID.



