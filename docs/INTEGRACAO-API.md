# Integração charte-api1.0 → ChArte (backend unificado)

A API (repositório github.com/aifhag/CHARTE-API, árvore `src/` da raiz) foi incorporada ao `backend/` do ChArte como o módulo **catálogo**
(`br.com.charte.backend.catalogo`). Um único Spring Boot (porta 8080) atende tudo; o front
continua usando o proxy `/api` (proxy.conf.json) e o `authInterceptor` já envia o JWT do Supabase.

## Rotas do catálogo (todas sob /api, JWT exigido; GET de artes/categorias/artistas é público)
- /api/artes (CRUD, PATCH /{id}/disponibilidade) + busca: /disponiveis, /artista/{id}, /categoria/{id}, /buscar?titulo=, /preco?minimo=&maximo=, /menor-preco
- /api/artistas, /api/categorias (CRUD), /api/usuarios (POST, GET /{id})
- /api/pedidos (POST, GET /{id}, PATCH /{id}/status, PATCH /{id}/cancelar)
- Swagger: /swagger-ui.html

## O que mudou
- Pacotes `br.com.charte10.charte_api10.*` → `br.com.charte.backend.catalogo.*`; rotas ganharam prefixo `/api`.
- pom.xml: + spring-boot-starter-data-jpa, lombok, springdoc (Spring Boot 3.5.16 / Java 21 do ChArte; a API era 4.0.7).
- `CatalogoExceptionHandler` restrito ao pacote do catálogo (o original capturava qualquer RuntimeException do app inteiro).
- `SupabaseDatabaseConfig`: usa JpaTransactionManager (atende JPA e JdbcTemplate).
- Entidades: `usuario.id_usuario` e `categoria.nome_categoria` alinhados ao schema do ChArte.
- `database/migrations/V5__integracao_api_catalogo.sql`: cria arte/pedido/item_pedido e colunas faltantes.
- Segredos da API NÃO foram copiados: use variáveis de ambiente (backend/.env.example).

## Pendente / atenção
- Não foi possível compilar aqui (sem acesso ao Maven Central): rode `cd backend && mvn clean verify`.
- Rode a V5 no Supabase antes de subir o backend.
- `usuario`: o catálogo cria usuários com senha_hash; o login real continua pelo Supabase Auth.

## Correções feitas sobre o código do repositório
- `ArteBuscaService` estava na pasta `repository/` com pacote `service` → movido para `service/`.
- `ItemPedidoRequestDTO` estava em `dto/request/` com pacote `dto` → pacote corrigido.
- `ArteController` e `ArteBuscaController` tinham as mesmas 3 rotas GET (conflito ao subir) → mantidas as do `ArteBuscaController`.
- `PedidoRepository.findByClienteId` → `findByUsuarioId`; `PortifolioRepository.findByCategoriaId/findByArtistaId` → `findByCategoriaIdCategoria/findByArtistaIdArtista` (propriedades inexistentes quebrariam o start).
- Avisos do Lombok no VS Code ("Can't initialize javac processor") são do servidor Java da IDE e não afetam o `mvn`; se persistirem: Ctrl+Shift+P → "Java: Clean Java Language Server Workspace".
