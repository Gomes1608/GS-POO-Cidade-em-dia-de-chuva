# Cidade em Dia de Chuva — Edição Web (Global Solution 2026.2)

Projeto desenvolvido para a avaliação Global Solution da disciplina **Object-Oriented Programming and Java Web Development** (Prof. Dr. Antonio Marcos Selmini).

## 1. Integrantes da Equipe e RM da Equipe

| Nome Completo | RM |
| :--- | :--- |
| **Murillo Moya Martins** | 561817 |
| **Vinicius de Almeida Lopes** | 562305 |
| **Guilherme Gomes Carneiro** *(responsável pelo envio)* | 561910 |

- **Cálculo do RM da equipe (`rm`)**: `561817 + 562305 + 561910 = 1686032`
- **Nome do projeto**: `guilherme561910`

## 2. Parâmetros da Equipe (P1 a P5)

Com `rm = 1686032` (divisão inteira):

| Parâmetro | Cálculo | Valor Obtido | Aplicação no Projeto |
| :--- | :--- | :--- | :--- |
| **P1 — Prefixo das tabelas** | `"GS" + (1686032 % 1000)` | **`GS032`** | Tabelas `GS032_BAIRRO`, `GS032_TRECHO` e `GS032_LEITURA` |
| **P2 — Limiar de alerta `L`** | `10 + 2 * (1686032 % 4)` | **`L = 10 cm`** | `< 5 cm`: `NORMAL` \| `5 a 9 cm`: `ATENCAO` \| `>= 10 cm`: `ALERTA` |
| **P3 — Atributo extra de Bairro** | `(1686032 / 10) % 3 = 168603 % 3` | **`0` (`areaKm2`)** | Atributo `areaKm2` (`Double`, obrigatório, maior que `0`) em `Bairro` |
| **P4 — Ordenação da lista de trechos** | `(1686032 / 100) % 3 = 16860 % 3` | **`0` (logradouro A a Z)** | Método `findAllByOrderByLogradouroAsc()` em `TrechoRepository` |
| **P5 — Exclusão de trecho com leituras** | `(1686032 / 1000) % 2 = 1686 % 2` | **`0` (bloquear)** | Bloqueia a exclusão de trecho que possui leituras com mensagem de erro |

## 3. Versão Escolhida e Forma dos DTOs

- **Versão do projeto**: **Versão Web (HTML com Thymeleaf e Bootstrap)** (`@Controller`).
- **Forma dos DTOs**: **Um único DTO por entidade** (`BairroDTO`, `TrechoDTO` e `LeituraDTO`), além do `PainelItemDTO` utilizado na consulta do painel do bairro (`/bairros/{id}/painel`), implementados como classes Java com Bean Validation e getters/setters explícitos.

## 4. Configuração de Execução (Oracle FIAP)

No IntelliJ IDEA, configure as variáveis de ambiente em **Run > Edit Configurations > Environment variables**:

```text
DB_USER=rm561910;DB_PASSWORD=sua_senha_aqui
```
